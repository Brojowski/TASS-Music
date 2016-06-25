package com.tass.services;

import android.content.Context;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;
import com.tass.services.MyAudioController;
import com.tass.services.SpotifyService;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by Hyland on 6/25/2016.
 */
public class MyObserver implements Player.InitializationObserver, PlayerNotificationCallback, TassService.SongListCallback {

    private Context _c;
    public static Player SpotifyPlayer;
    public MyObserver(Context c) {
        _c = c;
        Config spotifyConfig = new Config(c, SpotifyService.Token, SpotifyService.ClientID);
        MyAudioController myAudioController = new MyAudioController();
        Player.Builder pbuilder = new Player.Builder(spotifyConfig);
        pbuilder.setAudioController(myAudioController);
        SpotifyPlayer = Spotify.getPlayer(spotifyConfig, pbuilder, this);
        SpotifyPlayer.addPlayerNotificationCallback(this);
        TassService.Instance(_c).getList(this);
    }


    @Override
    public void onInitialized(Player player) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onPlaybackEvent(EventType eventType, PlayerState playerState) {
        if (eventType == EventType.TRACK_CHANGED) {
            PlayNextSong();
        }
    }

    private void PlayNextSong() {
        String uri = songsToPlay.remove();
        SpotifyPlayer.play(uri);
        TassService.Instance(_c).removeTrack(uri);
    }

    @Override
    public void onPlaybackError(ErrorType errorType, String s) {

    }

    @Override
    public void onSuccess(Group group) {

        String toRemove = group.getSongQueue().get(0)._uri;
        TassService.Instance(_c).removeTrack(toRemove);
    }

    @Override
    public void onError() {

    }

    Queue<String> songsToPlay = new PriorityQueue<>();
    public void PlayFirstSong() {
        songsToPlay.add("spotify:track:2nIkdJaURvnUzzRZ5mzqAB");
        songsToPlay.add("spotify:track:2AkcjsKlRbIBYGAgpQVFii");
        songsToPlay.add("spotify:track:6TaqooOXAEcijL6G1AWS2K");
        songsToPlay.add("spotify:track:2hsza71y54ABzmabyNDtM4");
        songsToPlay.add("spotify:track:1brwdYwjltrJo7WHpIvbYt");
        songsToPlay.add("spotify:track:0nWWxoOmEPUtAHRiFOSAMc");
        songsToPlay.add("spotify:track:6nRwc5GgNvBMkKaynhQzrm");
        songsToPlay.add("spotify:track:3528IXKpbb7OMjdjWYlbfD");
        SpotifyPlayer.play(songsToPlay.remove());
    }
}
