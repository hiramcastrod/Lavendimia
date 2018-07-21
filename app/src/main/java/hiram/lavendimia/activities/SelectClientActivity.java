package hiram.lavendimia.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Spinner;

import hiram.lavendimia.R;

public class SelectClientActivity extends AppCompatActivity {
    private Spinner spinSelectClient;
    Button btAcceptSelectClient, btCancelSelectClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_client);
        spinSelectClient = findViewById(R.id.spinner_clients);
        btAcceptSelectClient = findViewById(R.id.button_accept_selectclient);
        btCancelSelectClient = findViewById(R.id.button_cance_selectclient);

        DisplayMetrics displayMetrics =  new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;

        getWindow().setLayout((int) (width*.6), (int) (height*.6));

        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;

        getWindow().setAttributes(params);
    }
}
