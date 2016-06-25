package com.tass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.alex.tass_music_app.R;
import com.tass.controls.ViewGroupCustomAdapter;
import com.tass.services.TassService;

/**
 * Created by Sean on 6/25/2016.
 */
public class FragViewGroup extends Fragment {

    public boolean IsCreator = false;

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
        String[] songs= new String[]{"Hello","Take Me out","take a walk"};
        ListView lView= (ListView)view.findViewById(R.id.viewGroupList);
        lView.setAdapter( new ViewGroupCustomAdapter(view.getContext(), songs));

        if (IsCreator) {
            // enable the close button
            // hook up the close button event
        }

        FloatingActionButton btnAddSong = (FloatingActionButton) view.findViewById(R.id.btn_add_song);
        btnAddSong.setOnClickListener(new View.OnClickListener() {
            // ask the user for th spotify uid
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());

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
                        TassService.Instance(getContext()).addOrVoteSong(spotifyUri);
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
}
