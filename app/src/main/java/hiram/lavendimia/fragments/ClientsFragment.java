package hiram.lavendimia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hiram.lavendimia.R;
import hiram.lavendimia.activities.NewClientActivity;

public class ClientsFragment extends Fragment {
    FloatingActionButton fabNewClient;

    public ClientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_clients, container, false);
        fabNewClient = view.findViewById(R.id.fab_add_client);
        fabNewClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NewClientActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
