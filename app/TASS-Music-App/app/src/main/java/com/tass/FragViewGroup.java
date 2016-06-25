package com.tass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.tass_music_app.R;
import com.tass.controls.ViewGroupCustomAdapter;
import com.tass.services.Group;
import com.tass.services.QueueItem;
import com.tass.services.TassService;

import java.util.ArrayList;

/**
 * Created by Sean on 6/25/2016.
 */
public class FragViewGroup extends Fragment implements TassService.SongListCallback {

    private ViewGroupCustomAdapter _adapter;
    public static boolean IsCreator = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_view_group, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // This event is triggered soon after onCreateView().
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        String[] songs = new String[]{"Hello", "Take Me out", "take a walk", "Take Me out", "take a walk"};


        //QueueItem[] items =
//
        ListView lView = (ListView) view.findViewById(R.id.viewGroupList);
        //s_adapter = new ViewGroupCustomAdapter(view.getContext(), items);


//        _adapter = new ViewGroupCustomAdapter(view.getContext(), items);
        lView.setAdapter(_adapter);

        FloatingActionButton btnQuit = (FloatingActionButton) view.findViewById(R.id.btn_quit);
        btnQuit.setOnClickListener(new View.OnClickListener() {
            // ask the user for th spotify uid
            @Override
            public void onClick(View view) {

                ContextThemeWrapper c = new ContextThemeWrapper(view.getContext(), R.style.darkDialog);

                AlertDialog.Builder builder = new AlertDialog.Builder(c);

                LayoutInflater linf = LayoutInflater.from(view.getContext());
                final View inflator = linf.inflate(R.layout.dialog_add_song, null);
                String Message = "Are you sure you want to quit?";
                String Title = "Leave Party";
                if(IsCreator)
                {
                    Title = "Destroy Group";
                    Message = "Leaving the group will disband the party and kick everyone out. \r\n Are you sure you want to quit?";

                }

                builder.setMessage(Message).setTitle(Title);
                builder.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;

                        if (IsCreator) {
                            // If the group creator is leaving the fragment then we want the group to close
                            TassService.Instance(getContext()).closeGroup();
                        }
                        dialog.cancel();
                        FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        FragConfigGroup fcg = new FragConfigGroup();

                        fragmentTransaction.replace(R.id.app_content, new FragConfigGroup());
                        fragmentTransaction.addToBackStack(null); // this may not be needed depending on how we want state preserved
                        fragmentTransaction.commit();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        FloatingActionButton btnAddSong = (FloatingActionButton) view.findViewById(R.id.btn_add_song);
        btnAddSong.setOnClickListener(new View.OnClickListener() {
            // ask the user for th spotify uid
            @Override
            public void onClick(View view) {

                ContextThemeWrapper c = new ContextThemeWrapper(view.getContext(), R.style.darkDialog);

                AlertDialog.Builder builder = new AlertDialog.Builder(c);

                LayoutInflater linf = LayoutInflater.from(view.getContext());
                final View inflator = linf.inflate(R.layout.dialog_add_song, null);

                builder.setView(linf.inflate(R.layout.dialog_add_song, null));
                builder.setMessage("Enter your Spotify song uri:").setTitle("Add a song");
                builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Dialog f = (Dialog) dialog;
                        //This is the input I can't get text from
                        EditText editText = (EditText) f.findViewById(R.id.spotify_uri);
                        String spotifyUri = editText.getText().toString();
                        //TassService.Instance(getContext()).addOrVoteSong(spotifyUri);
                        TassService.Instance(getContext()).getList(FragViewGroup.this);
                        dialog.cancel();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public void onSuccess(Group group)
    {
    }

    @Override
    public void onError()
    {
        //TODO: Need to do something better.
        Toast.makeText(getContext(),"Could not load the songs from the group.", Toast.LENGTH_SHORT);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (IsCreator) {
            // If the group creator is leaving the fragment then we want the group to close
            TassService.Instance(getContext()).closeGroup();
        }
    }
}
