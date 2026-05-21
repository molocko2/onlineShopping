import java.util.*;

public abstract class Product implements Printable {
    protected final int id = Main.IDUtility.getID(this);
    private String title = "";
    private double price = 0.0f;

    abstract float calcPrice(int quantity, float price);
    public String getTitle() {
        return title;
    }
    public double getPrice() {
        return price;
    }
    public int getId() {
        return id;
    }
    public void setTitle(String s) {
        title = s;
    }
    public void setPrice(float p) {
        price = p;
    }

    public Product(String t, double p) {
        title = t;
        price = p;
    }

    @Override
    public void print() {
        System.out.printf("\"%s\": \n   ID: %d\n   Price: %f\n", title, id, price);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, price);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return false;
        if ((other == null) || !(other instanceof Product)) return false;
        Product pr = (Product)other;
        return pr.id == id;
    }

    public Product() {}
}