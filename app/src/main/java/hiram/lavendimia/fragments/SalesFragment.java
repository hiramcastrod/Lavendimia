package hiram.lavendimia.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.activities.SelectClientActivity;
import hiram.lavendimia.adapters.SalesAdapter;
import hiram.lavendimia.models.Sale;

public class SalesFragment extends Fragment {
    FloatingActionButton fabNewSale;
    RecyclerView rvSales;
    DatabaseReference Sales;

    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sales, container, false);
        fabNewSale = view.findViewById(R.id.fab_add_sale);
        rvSales = view.findViewById(R.id.recyclerview_sale);
        Sales = FirebaseDatabase.getInstance().getReference("Ventas");
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Data-Vault", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSales.setLayoutManager(linearLayoutManager);

        Sales.child("Sales").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                dataSnapshot.getValue();
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
        Sales.child("Sales").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Sale> sales = new ArrayList<>();
                for(DataSnapshot post: dataSnapshot.getChildren()){
                    Sale sale = post.getValue(Sale.class);
                    sales.add(new Sale(sale.getId_sale(), sale.getId_client(), sale.getStatus(), sale.getTotal(), sale.getDate(), sale.getClient_name()));
                }
                SalesAdapter salesAdapter = new SalesAdapter(sales, R.layout.sales_view_holder, getActivity());
                rvSales.setAdapter(salesAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        fabNewSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SelectClientActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
