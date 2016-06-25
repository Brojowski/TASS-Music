package com.tass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.alex.tass_music_app.R;
import com.tass.controls.ViewGroupCustomAdapter;
import com.tass.services.Group;
import com.tass.services.TassService;

/**
 * Created by Sean on 6/25/2016.
 */
public class FragViewGroup extends Fragment implements TassService.SongListCallback {

    private ViewGroupCustomAdapter _adapter;


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
        String [] songs= new String[]{"Hello","Take Me out","take a walk"};
        ListView lView= (ListView)view.findViewById(R.id.viewGroupList);
        _adapter = new ViewGroupCustomAdapter(view.getContext(), songs);
        lView.setAdapter(_adapter);
    }

    @Override
    public void onSuccess(Group group) {
     //   _adapter.
    }

    @Override
    public void onError() {
        //TODO: Need to do something better.
        Toast.makeText(getContext(),"Could not load the songs from the group.", Toast.LENGTH_SHORT);
    }
}
