package hiram.lavendimia.models;

public class Article {
    private int product_id, stock;
    private String description, model;
    private float price;

    public Article(int product_id, int stock, String description, String model, float price) {
        this.product_id = product_id;
        this.stock = stock;
        this.description = description;
        this.model = model;
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
