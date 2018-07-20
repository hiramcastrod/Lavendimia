package hiram.lavendimia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.activities.NewArticleActivity;
import hiram.lavendimia.activities.NewClientActivity;
import hiram.lavendimia.adapters.ArticleAdapter;
import hiram.lavendimia.models.Article;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {
    FloatingActionButton fabNewArticle;
    DatabaseReference Muebles;
    RecyclerView rvArticles;


    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        Muebles = FirebaseDatabase.getInstance().getReference("Ventas");
        fabNewArticle = view.findViewById(R.id.fab_add_article);
        rvArticles = view.findViewById(R.id.recyclerview_articles);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        Muebles.keepSynced(true);


      /*  Muebles.child("Muebles").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                ArrayList<Article> articlesList = new ArrayList<>();
                for(DataSnapshot post : dataSnapshot.getChildren()) {
                    System.out.println(post.child("product_id"));
                    //Article article = post.getValue(Article.class);
                    //articlesList.add(new Article(post.child("product_id").toString(), Integer.parseInt(post.child("stock").toString()), post.child("description").toString(),
                    //        post.child("model").toString(), Float.parseFloat(post.child("price").toString())));
                    System.out.print("ENTRO: " + articlesList);
                }

                ArticleAdapter articleAdapter = new ArticleAdapter(articlesList, R.layout.articles_view_holder, getActivity());
                rvArticles.setAdapter(articleAdapter);
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
        });*/

      Muebles.child("Muebles").addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              ArrayList<Article> articles = new ArrayList<>();
              GenericTypeIndicator<ArrayList<Article>> t = new GenericTypeIndicator<ArrayList<Article>>();
              articles = dataSnapshot.getValue(t);
              System.out.print(articles);
          }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {

          }
      });


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
