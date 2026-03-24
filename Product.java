public class Product {
    private String title = "";
    private float price = 0.0f;

    public Product(){}
    public Product(String t, float p){
        title = t;
        price = p;
    }

    public String getTitle() {
        return title;
    }

    public float getPrice() {
        return price;
    }
}
