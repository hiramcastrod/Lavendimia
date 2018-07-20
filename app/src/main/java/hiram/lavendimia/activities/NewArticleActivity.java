package hiram.lavendimia.activities;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.util.NumberUtils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import hiram.lavendimia.R;
import hiram.lavendimia.models.Article;

public class NewArticleActivity extends AppCompatActivity {
    TextInputEditText inputModel, inputDescription, inputPrice, inputStock;
    Button btnAccept, btnCancel;
    private DatabaseReference Muebles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_article);
        Muebles = FirebaseDatabase.getInstance().getReference("Ventas");
        inputModel = findViewById(R.id.input_modelo);
        inputDescription = findViewById(R.id.input_description);
        inputPrice = findViewById(R.id.input_price);
        inputStock = findViewById(R.id.input_stock);
        btnAccept = findViewById(R.id.button_accept_article);
        btnCancel = findViewById(R.id.button_cancel_article);

        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerArticle();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registerArticle(){
        String model = inputModel.getText().toString();
        String description = inputDescription.getText().toString();
        float price = Float.valueOf(inputPrice.getText().toString());
        int stock = Integer.parseInt(inputStock.getText().toString());

        if(!TextUtils.isEmpty(model) ||
                !TextUtils.isEmpty(description) ||
                price != 0){
            String id = Muebles.push().getKey();
            Article articles = new Article(id, stock, description, model, price);
            Muebles.child("Muebles").child(id).setValue(articles);
            Toast.makeText(this, "Articulo registrado", Toast.LENGTH_SHORT).show();
            //inputStock.setText("");
            //inputModel.setText("");
            //inputDescription.setText("");
            //inputPrice.setText("");
            finish();
        } else {
            Toast.makeText(this, "Faltan campos", Toast.LENGTH_SHORT).show();
        }
    }
}
