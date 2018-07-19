package hiram.lavendimia.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import hiram.lavendimia.R;

public class NewClientActivity extends AppCompatActivity {
    EditText etName, etLastname, etRFC;
    Button btAccept, btCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_client);
        etName = findViewById(R.id.edittext_name);
        etLastname = findViewById(R.id.edittext_lastname);
        etRFC = findViewById(R.id.edittext_rfc);
        btAccept = findViewById(R.id.button_accept);
        btCancel = findViewById(R.id.button_cancel);
    }

    public void registerClient(){

    }
}
