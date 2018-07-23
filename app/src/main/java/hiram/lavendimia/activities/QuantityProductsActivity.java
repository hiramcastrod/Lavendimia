package hiram.lavendimia.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

import hiram.lavendimia.R;

public class QuantityProductsActivity extends AppCompatActivity {
    TextView tvPrice, tvAmount, tvModel;
    Spinner spinQty;
    Button btAccept, btCancel;
    double price;
    double priceUnit;
    ArrayList<>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantity_products);
        tvModel = findViewById(R.id.textview_model_quantity);
        tvAmount = findViewById(R.id.textview_amount_quantity);
        tvPrice = findViewById(R.id.textview_price_quantity);
        spinQty = findViewById(R.id.spinner_quantity);
        btAccept = findViewById(R.id.button_accept_quantity);
        btCancel = findViewById(R.id.button_cancel_quantity);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int)(width*.7), (int)(height*.7));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);

        Bundle extras = getIntent().getExtras();
        tvModel.setText(extras.getString("MODEL"));
        int quantity = extras.getInt("STOCK");
        ArrayList<Integer> stock = new ArrayList<>();
        for(int i = 0; i<quantity; i++)
            stock.add(i);
        final ArrayAdapter<Integer> adapter= new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stock);
        spinQty.setAdapter(adapter);
        spinQty.setSelection(1);
        priceUnit = extras.getFloat("PRICE");
        priceUnit = priceUnit * (1 + (2.8 * 12)/100);
        tvPrice.setText(" "+ (float)priceUnit);
        price = priceUnit * (int)spinQty.getSelectedItem();
        tvAmount.setText(""+(float) price);
        spinQty.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                price = priceUnit * (int)adapterView.getItemIdAtPosition(i);
                tvAmount.setText(" "+ (float)price);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(getApplicationContext(), ShoppingCartActivity.class);

            }
        });
    }

    public void addToCart(){

    }
}
