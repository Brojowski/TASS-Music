package com.android;

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

    private View inflatedView=null;
    private Button join_button=null;
    private EditText playlistTextField=null;
    private EditText pinTextField=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.page_join_group, container, false);
        inflatedView = inflater.inflate(R.layout.page_join_group, container, false);
        join_button = (Button) inflatedView.findViewById(R.id.join_button);
        playlistTextField= (EditText) inflatedView.findViewById(R.id.playlistNameTextField);
        pinTextField = (EditText) inflatedView.findViewById(R.id.passwordTextField);

        join_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        return inflatedView;
    }

    public boolean sendRequest(String playListName, String password){
        return false;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // This event is triggered soon after onCreateView().
        // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
    }
}

