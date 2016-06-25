package com.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.alex.tass_music_app.R;

/**
 * Created by Tyler on 5/8/2016.
 */
public class FragJoinGroup extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_join_group, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // This event is triggered soon after onCreateView().
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}

