package hiram.lavendimia.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.adapters.ArticleAdapter;
import hiram.lavendimia.models.Article;

public class SelectProductsActivity extends AppCompatActivity {
    FloatingActionButton fabSelectProducts;
    DatabaseReference Muebles;
    RecyclerView rvSelectArticles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_products);
        Muebles = FirebaseDatabase.getInstance().getReference("Ventas");
        fabSelectProducts = findViewById(R.id.fab_select_product);
        rvSelectArticles = findViewById(R.id.recyclerview_select_products);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSelectArticles.setLayoutManager(linearLayoutManager);

        fabSelectProducts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FinalizeSaleActivity.class);
                startActivity(intent);
            }
        });

        Muebles.child("Muebles").addChildEventListener(new ChildEventListener() {
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

        Muebles.child("Muebles").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ArrayList<Article> articles = new ArrayList<>();
                for (DataSnapshot post : dataSnapshot.getChildren()){
                    Article article = post.getValue(Article.class);
                    articles.add(new Article(article.getProduct_id(), article.getStock(),
                            article.getDescription(), article.getModel(), article.getPrice()));
                }
                ArticleAdapter articleAdapter = new ArticleAdapter(articles, R.layout.articles_view_holder, SelectProductsActivity.this);
                rvSelectArticles.setAdapter(articleAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
