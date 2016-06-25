package com.tass.controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.alex.tass_music_app.R;

import java.util.List;

/**
 * Created by Hyland on 6/25/2016.
 */
public class ListAdapter extends ArrayAdapter<String> {

    public ListAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public ListAdapter(Context context, int resource, List<String> items) {
        super(context, resource, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;


        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.view_adapter_layout, null);
        }

        String p = getItem(position);
        String[] split = p.split(";");

        if (p != null) {
            TextView txtTitle = (TextView) v.findViewById(R.id.songTitle);
            TextView txtArtist = (TextView) v.findViewById(R.id.songArtist);

            txtTitle.setText(split[0].substring(16));
            txtArtist.setText(split[1]);
        }

        return v;
    }
}
