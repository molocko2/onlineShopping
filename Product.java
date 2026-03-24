import java.util.ArrayList;
import java.util.HashMap;

public abstract class Product {
    private String title = "";
    private float price = 0.0f;
    protected int id = 0;
    private HashMap<Product, ArrayList<Product>> category;
    private void incId(){id++;}
    public abstract int calcPrice();

    protected static int temp = 0;


    public Product(){}
    public Product(String t, float p){
        title = t;
        price = p;
//        incId();
        id = temp++;

    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }
    public void print(){
        System.out.printf("[%s] %f",this.getTitle(), this.getPrice());
    }
    public int getId() {
        return id;
    }
}

