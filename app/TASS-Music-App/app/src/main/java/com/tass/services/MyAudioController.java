package com.tass.services;

import com.spotify.sdk.android.player.AudioController;
import com.spotify.sdk.android.player.Player;

/**
 * Created by Hyland on 6/25/2016.
 */
public class MyAudioController implements AudioController {
    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public int onAudioDataDelivered(short[] shorts, int i, int i1, int i2) {
        return 0;
    }

    @Override
    public void onAudioFlush() {

    }

    @Override
    public void onAudioPaused() {

    }

    @Override
    public void onAudioResumed() {

    }
}
