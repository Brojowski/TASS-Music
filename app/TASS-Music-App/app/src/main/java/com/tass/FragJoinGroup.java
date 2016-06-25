package com.tass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.alex.tass_music_app.R;

/**
 * Created by Tyler on 5/8/2016.
 */
public class FragJoinGroup extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.page_join_group, container, false);
    }

    public boolean sendRequest(String playListName, String password){
        return false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button join_button = (Button) view.findViewById(R.id.join_button);
        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText playlistTextField= (EditText) view.findViewById(R.id.playlistNameTextField);
                EditText pinTextField = (EditText) view.findViewById(R.id.passwordTextField);

                String playListName= playlistTextField.getText().toString();
                String password= pinTextField.getText().toString();
                Log.v("Does this shit work", playListName+" "+password);
                if(sendRequest(playListName,password)){
                    Log.v("Does this shit work", "i love ice cream");
                }else{
                    playlistTextField.setText("");
                    pinTextField.setText("");
                }
            }
        });
    }
}

