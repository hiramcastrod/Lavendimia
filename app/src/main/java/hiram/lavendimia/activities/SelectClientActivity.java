package hiram.lavendimia.activities;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.models.Article;
import hiram.lavendimia.models.Client;

public class SelectClientActivity extends AppCompatActivity {
    private Spinner spinSelectClient;
    Button btAcceptSelectClient, btCancelSelectClient;
    DatabaseReference Clients;
    SharedPreferences sharedPreferences;
    String selectedClient;
    int positionClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_client);
        spinSelectClient = findViewById(R.id.spinner_clients);
        btAcceptSelectClient = findViewById(R.id.button_accept_selectclient);
        btCancelSelectClient = findViewById(R.id.button_cance_selectclient);
        Clients = FirebaseDatabase.getInstance().getReference("Ventas");
        sharedPreferences = getSharedPreferences("Data-Vault", MODE_PRIVATE);
        selectedClient = (String)spinSelectClient.getSelectedItem();
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
                for(DataSnapshot post: dataSnapshot.getChildren()){
                    Client client = post.getValue(Client.class);
                    clients.add(new Client(client.getClient_id(), client.getName(), client.getLastname(), client.getRfc()));
                }
                ArrayList<String> clientname = new ArrayList<>();
                for(int i = 0; i<clients.size(); i++){
                    clientname.add(clients.get(i).getName() + " " + clients.get(i).getLastname());
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, clientname);
                adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
                spinSelectClient.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btAcceptSelectClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("Client Position", Integer.toString(positionClient));
                editor.putString("Client Name", selectedClient);
                //Toast.makeText(SelectClientActivity.this, positionClient + selectedClient, Toast.LENGTH_SHORT).show();
            }
        });
        btCancelSelectClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        spinSelectClient.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedClient =(String)adapterView.getItemAtPosition(i);
                positionClient = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
}
