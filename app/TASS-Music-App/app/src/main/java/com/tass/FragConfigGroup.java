package com.tass;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.alex.tass_music_app.R;

/**
 * Created by Tyler on 5/8/2016.
 */
public class FragConfigGroup extends Fragment {

    EditText txtGroupName = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_config_group, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        Button btnGroupConnect = (Button) view.findViewById(R.id.create_button);
        txtGroupName = (EditText) view.findViewById(R.id.create_group_name);
        btnGroupConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String groupName = txtGroupName.getText().toString();

                try {
                    // TODO: Pass group name to services

                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.app_content, new FragViewGroup());
                    fragmentTransaction.addToBackStack(null); // this may not be needed depending on how we want state preserved
                    fragmentTransaction.commit();
                } catch (Exception ex) {
                    // TODO: HANDLE ME (unique group name?)
                }
            }
        });


    }
}

