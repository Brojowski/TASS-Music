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
import com.tass.services.TassService;

/**
 * Created by Tyler on 5/8/2016.
 */
public class FragConfigGroup extends Fragment implements TassService.GroupCallback {

    EditText txtGroupName = null;
    private TassService.GroupCallback callBack = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.page_config_group, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        txtGroupName = (EditText) view.findViewById(R.id.create_group_name);
        callBack = this;

        Button btnCreate = (Button) view.findViewById(R.id.create_button);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String groupName = txtGroupName.getText().toString();
            TassService.Instance(getContext()).create(groupName, callBack, true);
            }
        });

        Button btnJoin = (Button) view.findViewById(R.id.join_button);
        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            String groupName = txtGroupName.getText().toString();
            TassService.Instance(getContext()).join(groupName, callBack, false);
            }
        });
    }

    @Override
    public void sessionCallback(boolean success, boolean isCreator) {
        if (success) {
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragViewGroup fvg = new FragViewGroup();
            fvg.IsCreator = isCreator;
            fragmentTransaction.replace(R.id.app_content, new FragViewGroup());
            fragmentTransaction.addToBackStack(null); // this may not be needed depending on how we want state preserved
            fragmentTransaction.commit();
        } else {
             // tuyrn off sinenir
        }
    }
}

