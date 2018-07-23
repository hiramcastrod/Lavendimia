package hiram.lavendimia.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import hiram.lavendimia.MainActivity;
import hiram.lavendimia.R;
import hiram.lavendimia.models.Sale;

public class SelectMonthActivity extends AppCompatActivity {
    TextView tvAbonotres, tvAbonoseis, tvAbononueve, tvAbonodoce;
    TextView tvTotal3, tvTotal6, tvTotal9, tvTotal12;
    TextView tvAhorro3, tvAhorro6, tvAhorro9, tvAhorro12;
    float total, contado;
    RadioGroup radioGroup;
    Button finishSale;
    private DatabaseReference Sales;
    SharedPreferences sharedPreferences;
    float finalTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_month);
        Sales = FirebaseDatabase.getInstance().getReference("Ventas");
        sharedPreferences = getSharedPreferences("Data-Vault", MODE_PRIVATE);
        finishSale = findViewById(R.id.button_add_sale);
        radioGroup = findViewById(R.id.radiogroup_months);
        tvAbonotres = findViewById(R.id.tv_abono_tres);
        tvAbonoseis = findViewById(R.id.tv_abono_seis);
        tvAbononueve = findViewById(R.id.tv_abono_nueve);
        tvAbonodoce = findViewById(R.id.tv_abono_doce);
        tvTotal3 = findViewById(R.id.tv_total_tres);
        tvTotal6 = findViewById(R.id.tv_total_seis);
        tvTotal9 = findViewById(R.id.tv_total_nueve);
        tvTotal12 = findViewById(R.id.tv_total_doce);
        tvAhorro3 = findViewById(R.id.tv_ahorro_tres);
        tvAhorro6 = findViewById(R.id.tv_ahorro_seis);
        tvAhorro9 = findViewById(R.id.tv_ahorro_nueve);
        tvAhorro12 = findViewById(R.id.tv_ahorro_doce);
        Bundle extras = getIntent().getExtras();
        total = extras.getFloat("TOTAL");
        contado =(float) (total / (1 + ((2.8 * 12)/100)));
        radioGroup.check(R.id.radio_three);
        String client_name = sharedPreferences.getString("Client Name", null);
        Toast.makeText(this, client_name, Toast.LENGTH_SHORT).show();

        tvTotal3.setText(String.valueOf((int)(contado * (1 + (2.8 * 3)/100))));
        tvTotal6.setText(String.valueOf((int)(contado * (1 + (2.8 * 6)/100))));
        tvTotal9.setText(String.valueOf((int)(contado * (1 + (2.8 * 9)/100))));
        tvTotal12.setText(String.valueOf((int)(contado * (1 + (2.8 * 12)/100))));

        tvAbonotres.setText(String.valueOf(Float.parseFloat(tvTotal3.getText().toString())/3));
        tvAbonoseis.setText(String.valueOf(Float.parseFloat(tvTotal6.getText().toString())/6));
        tvAbononueve.setText(String.valueOf(Float.parseFloat(tvTotal9.getText().toString())/9));
        tvAbonodoce.setText(String.valueOf(Float.parseFloat(tvTotal12.getText().toString())/12));

        tvAhorro3.setText(String.valueOf(total - Float.parseFloat(tvTotal3.getText().toString())));
        tvAhorro6.setText(String.valueOf(total - Float.parseFloat(tvTotal6.getText().toString())));
        tvAhorro9.setText(String.valueOf(total - Float.parseFloat(tvTotal9.getText().toString())));
        tvAhorro12.setText(String.valueOf((int)(total - Float.parseFloat(tvTotal12.getText().toString()))));

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio_three:
                        finalTotal = Float.parseFloat(tvTotal3.getText().toString());
                        break;
                    case R.id.radio_six:
                        finalTotal = Float.parseFloat(tvTotal6.getText().toString());
                        break;
                    case R.id.radio_nine:
                        finalTotal = Float.parseFloat(tvTotal9.getText().toString());
                        break;
                    case R.id.radio_twelve:
                        finalTotal = Float.parseFloat(tvTotal12.getText().toString());
                        break;
                }
            }
        });

        finishSale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNewSale();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }

    public void addNewSale(){
        String id_client = sharedPreferences.getString("client position", null);
        String client_name = sharedPreferences.getString("client name", null);
        String id = Sales.push().getKey();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy / MM / dd");
        String date = simpleDateFormat.format(calendar.getTime());
        Sale sale = new Sale(id, id_client, 1, finalTotal, date, client_name);
        Sales.child("Sales").child(id).setValue(sale);
    }
}
