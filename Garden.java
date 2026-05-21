import java.util.*;

public class Garden extends Product {
    @Override
    float calcPrice(int quantity, float price) {
        return quantity * price;
    }

    Garden() {
        super();
    }

    public Garden(String t, double p) {
        super(t, p);
        Catalog.getInstance().add("Garden", this);
    }
}