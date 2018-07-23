package hiram.lavendimia.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.adapters.CartAdapter;
import hiram.lavendimia.models.Cart;

public class FinalizeSaleActivity extends AppCompatActivity {
    TextView tvEnganche, tvBonificacion, tvTotal;
    RecyclerView rvFinalize;
    ArrayList<Cart> cartList;
    Button btProceed;
    SharedPreferences sharedPreferences;
    float enganche, bonificacion, total, importes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalize_sale);
        tvEnganche = findViewById(R.id.tv_enganche);
        tvBonificacion = findViewById(R.id.tv_bonificacion);
        tvTotal = findViewById(R.id.tv_total_cart);
        rvFinalize = findViewById(R.id.recyclerview_cart);
        btProceed = findViewById(R.id.button_finalize);
        sharedPreferences = getSharedPreferences("Data-Vault", MODE_PRIVATE);
        getCartList();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFinalize.setLayoutManager(linearLayoutManager);
        CartAdapter cartAdapter = new CartAdapter(cartList, R.layout.cart_view_holder, this);
        rvFinalize.setAdapter(cartAdapter);
        getEstimate();
        btProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SelectMonthActivity.class);
                Bundle bundle = new Bundle();
                bundle.putFloat("TOTAL", total);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }

    public ArrayList<Cart> getCartList() {
        Gson gson = new Gson();
        String json = sharedPreferences.getString("cart list", null);
        Type type = new TypeToken<ArrayList<Cart>>() {}.getType();
        cartList = gson.fromJson(json, type);
        for (int i = 0; i<cartList.size(); i++)
            System.out.println(cartList.get(i).getModel());

        if (cartList == null){
            cartList = new ArrayList<>();
        }
        return cartList;
    }

    public void getEstimate(){
        for (int i = 0; i<cartList.size(); i++){
            importes = (float)cartList.get(i).getAmount() + importes;
        }
        System.out.println(importes);
        enganche = (float).2 * importes;
        bonificacion =(float)(enganche * ((2.8 * 12)/100));
        total = importes - enganche -bonificacion;
        tvEnganche.setText(String.valueOf(enganche));
        tvBonificacion.setText(String.valueOf(bonificacion));
        tvTotal.setText(String.valueOf(total));

    }

}
