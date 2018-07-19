package hiram.lavendimia.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hiram.lavendimia.R;
import hiram.lavendimia.models.Client;

public class NewClientActivity extends AppCompatActivity {
    EditText etName, etLastname, etRFC;
    Button btAccept, btCancel;
    private DatabaseReference Clientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        Clientes = FirebaseDatabase.getInstance().getReference("Ventas");
        etName = findViewById(R.id.edittext_name);
        etLastname = findViewById(R.id.edittext_lastname);
        etRFC = findViewById(R.id.edittext_rfc);
        btAccept = findViewById(R.id.button_accept);
        btCancel = findViewById(R.id.button_cancel);

        btAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerClient();
            }
        });

        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registerClient(){
        String name = etName.getText().toString();
        String lastname = etLastname.getText().toString();
        String rfc = etRFC.getText().toString();

        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(lastname) && !TextUtils.isEmpty(rfc)){
            String id = Clientes.push().getKey();
            Client clientes = new Client(id, name, lastname, rfc);
            Clientes.child("Clients").child(id).setValue(clientes);
            Toast.makeText(this, "Cliente registrado", Toast.LENGTH_SHORT).show();
            etName.setText("");
            etLastname.setText("");
            etRFC.setText("");
        } else {
            Toast.makeText(this, "Faltan campos", Toast.LENGTH_SHORT).show();
        }

    }
}
