import java.util.*;

public class Clothes extends Product {
    @Override
    float calcPrice(int quantity, float price) {
        return quantity * price;
    }

    Clothes() {
        super();
    }

    public Clothes(String t, double p) {
        super(t, p);
        Catalog.getInstance().add("Clothes", this);
    }
}