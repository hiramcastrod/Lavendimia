package hiram.lavendimia.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import hiram.lavendimia.R;
import hiram.lavendimia.activities.NewArticleActivity;
import hiram.lavendimia.activities.NewClientActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticlesFragment extends Fragment {
    FloatingActionButton fabNewArticle;


    public ArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        fabNewArticle = view.findViewById(R.id.fab_add_article);
        fabNewArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NewArticleActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

}
