package hiram.lavendimia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.activities.NewClientActivity;
import hiram.lavendimia.adapters.ClientAdapter;
import hiram.lavendimia.models.Client;

public class ClientsFragment extends Fragment {
    FloatingActionButton fabNewClient;
    DatabaseReference Clients;
    RecyclerView rvClients;

    public ClientsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_clients, container, false);
        fabNewClient = view.findViewById(R.id.fab_add_client);
        Clients = FirebaseDatabase.getInstance().getReference("Ventas");
        rvClients = view.findViewById(R.id.recyclerview_client);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvClients.setLayoutManager(linearLayoutManager);

        Clients.child("Clients").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                dataSnapshot.getValue();
                System.out.println(dataSnapshot.getClass());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        Clients.child("Clients").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Client> clients = new ArrayList<>();
                for(DataSnapshot post : dataSnapshot.getChildren()){
                    Client client = post.getValue(Client.class);
                    clients.add(new Client(client.getClient_id(), client.getName(), client.getLastname(), client.getRfc()));
                    System.out.println(clients);
                }
                ClientAdapter clientAdapter = new ClientAdapter(clients, R.layout.clients_view_holder, getActivity());
                rvClients.setAdapter(clientAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
