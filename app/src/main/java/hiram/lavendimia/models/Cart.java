package hiram.lavendimia.models;

public class Cart {
    String model;
    int quantity;
    double price, amount;

    public Cart(String model, int quantity, double price, double amount) {
        this.model = model;
        this.quantity = quantity;
        this.price = price;
        this.amount = amount;
    }

    public String getModel() {
        return model;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getAmount() {
        return amount;
    }
}
