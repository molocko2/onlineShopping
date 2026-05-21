import java.util.*;

public class Electronics extends Product {
    @Override
    float calcPrice(int quantity, float price) {
        return quantity * price;
    }


    Electronics() {
        super();
    }

    public Electronics(String t, double p) {
        super(t, p);
        Catalog.getInstance().add("Electronics", this);
    }
}
