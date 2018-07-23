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
import hiram.lavendimia.models.Cart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder>{
    private ArrayList<Cart> carts;
    private int resource;
    private Activity activity;

    public CartAdapter(ArrayList<Cart> carts, int resource, Activity activity) {
        this.carts = carts;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Cart cart = carts.get(position);
        //GET DATA
        holder.modelCard.setText(cart.getModel());
        holder.quantityCard.setText(cart.getQuantity());
        holder.priceCard.setText(String.valueOf(cart.getPrice()));
        holder.amountCard.setText(String.valueOf(cart.getAmount()));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder{
        private TextView modelCard, quantityCard, priceCard, amountCard;
        public CartViewHolder(View itemView) {
            super(itemView);
            modelCard = itemView.findViewById(R.id.tv_model_card);
            quantityCard = itemView.findViewById(R.id.tv_quantity_card);
            priceCard = itemView.findViewById(R.id.tv_price_card);
            amountCard = itemView.findViewById(R.id.tv_amount_card);
        }
    }
}
