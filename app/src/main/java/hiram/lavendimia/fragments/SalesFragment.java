package hiram.lavendimia.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hiram.lavendimia.R;
import hiram.lavendimia.activities.SelectClientActivity;

public class SalesFragment extends Fragment {
    FloatingActionButton fabNewSale;

    public SalesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_sales, container, false);
        fabNewSale = view.findViewById(R.id.fab_add_sale);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("Data-Vault", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.clear();
        editor.apply();

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
