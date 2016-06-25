package com.tass.controls;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alex.tass_music_app.R;
import com.tass.services.TassService;

/**
 * Created by Sean on 6/25/2016.
 */
public class ViewGroupCustomAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] values;

    public ViewGroupCustomAdapter(Context context, String [] values){
        super(context,-1,values);
        this.context =context;
        this.values =values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView= inflater.inflate(R.layout.view_adapter_layout,parent,false);
        TextView songNameTextView = (TextView) rowView.findViewById(R.id.songName);
        TextView artistNameTextView = (TextView) rowView.findViewById(R.id.artistName);
        ImageView albumCover = (ImageView) rowView.findViewById(R.id.albumCover);
        Button upVoteButton = (Button) rowView.findViewById(R.id.upVoteButton);

        songNameTextView.setText(values[position]);
        artistNameTextView.setText(values[position]);
        upVoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo make call to server to upvote song.
                //TassService.Instance(context).addOrVoteSong();
            }
        });
        return rowView;
    }

}
