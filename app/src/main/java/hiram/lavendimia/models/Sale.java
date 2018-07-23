package hiram.lavendimia.models;

public class Sale {
    private int status;
    private float total;
    private String id_sale, id_client, date, client_name;

    public Sale(String id_sale, String id_client, int status, float total, String date, String client_name) {
        this.id_sale = id_sale;
        this.id_client = id_client;
        this.status = status;
        this.total = total;
        this.date = date;
        this.client_name = client_name;
    }

    public Sale() {
    }

    public String getId_sale() {
        return id_sale;
    }

    public String getId_client() {
        return id_client;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }
}
