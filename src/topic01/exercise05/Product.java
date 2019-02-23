package topic01.exercise05;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private double price;
    private int quantity;
    public Product() {
        name = "";
        price = 0;
        quantity = 0;
    }
    public Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }
    public void setName(String newName) { name = newName; }
    public void setPrice(double newPrice) { price = newPrice; }
    public void setQuantity(int newQuantity) { quantity = newQuantity; }
    public String toString() {
        return super.toString() + " : " + this.name + " (" + this.price + " , " +
                this.quantity + ")";
    }
}
