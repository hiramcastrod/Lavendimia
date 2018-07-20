package hiram.lavendimia.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.models.Article;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>  {
    private ArrayList<Article> articles;
    private int resource;
    private Activity activity;

    public ArticleAdapter(ArrayList<Article> articles, int resource, Activity activity) {
        this.articles = articles;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article article = articles.get(position);

        //GETS
        holder.modelCard.setText(article.getModel());
        holder.stockCard.setText(Integer.toString(article.getStock()));
        holder.priceCard.setText(String.valueOf(article.getPrice()));
        holder.descriptionCard.setText(article.getDescription());
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        private TextView modelCard, descriptionCard, priceCard, stockCard;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            modelCard = itemView.findViewById(R.id.texview_model_article);
            descriptionCard = itemView.findViewById(R.id.textview_description_article);
            priceCard = itemView.findViewById(R.id.textview_price);
            stockCard = itemView.findViewById(R.id.textview_stock);
        }
    }
}
