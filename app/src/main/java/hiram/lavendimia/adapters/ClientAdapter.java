package hiram.lavendimia.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import hiram.lavendimia.R;
import hiram.lavendimia.models.Client;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ClientViewHolder>{
    private ArrayList<Client> clients;
    private int resource;
    Activity activity;

    public ClientAdapter(ArrayList<Client> clients, int resource, Activity activity) {
        this.clients = clients;
        this.resource = resource;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);

        return new ClientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClientViewHolder holder, int position) {
        Client client = clients.get(position);
        holder.nameCard.setText(client.getName());
        holder.lastnameCard.setText(client.getLastname());
        holder.rfcCard.setText(client.getRfc());
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public class ClientViewHolder extends RecyclerView.ViewHolder{
        private TextView nameCard, lastnameCard, rfcCard;
        public ClientViewHolder(View itemView) {
            super(itemView);
            nameCard = itemView.findViewById(R.id.textView_name_client);
            lastnameCard = itemView.findViewById(R.id.textView_lastname_client);
            rfcCard = itemView.findViewById(R.id.textView_rfc_client);
        }
    }
}
