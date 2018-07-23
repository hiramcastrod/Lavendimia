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
import hiram.lavendimia.models.Sale;

public class SalesAdapter extends  RecyclerView.Adapter<SalesAdapter.SaleViewHolder> {
    private ArrayList<Sale> sales;
    private int resource;
    Activity activity;

    public SalesAdapter(ArrayList<Sale> sales, int resource, Activity activity) {
        this.sales = sales;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new SaleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHolder holder, int position) {
        Sale sale = sales.get(position);
        holder.clientCard.setText(sale.getId_client() + " - " + sale.getClient_name());
        holder.idCard.setText(sale.getId_sale());
        holder.dateCard.setText(sale.getDate());
        holder.totalCard.setText(String.valueOf(sale.getTotal()));
    }

    @Override
    public int getItemCount() {
        return sales.size();
    }

    public class SaleViewHolder extends RecyclerView.ViewHolder{
        private TextView clientCard, totalCard, dateCard, idCard;
        public SaleViewHolder(View itemView) {
            super(itemView);
            clientCard = itemView.findViewById(R.id.textview_client_data);
            totalCard = itemView.findViewById(R.id.textview_total_sale);
            dateCard = itemView.findViewById(R.id.textview_date);
            idCard = itemView.findViewById(R.id.textview_id_sale);
        }
    }
}
