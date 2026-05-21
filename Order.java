import java.sql.Array;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public final class Order implements Printable {
    private final int id = Main.IDUtility.getID(this);
    private ArrayList<Product> items = new ArrayList<>();

    public void print() {
        AtomicReference<Double> total = new AtomicReference<>((double) 0);
        items.stream().forEach(p-> total.updateAndGet(v -> new Double((double) (v + p.getPrice()))));
        System.out.printf("Order %d   |   Total: %f:\n", id, total.get());
        items.stream().forEach(p -> System.out.printf("  - [%d] %s | %f\n", p.getId(), p.getTitle(), p.getPrice()));
    }

    public Order(ArrayList<Product> p) {
        items = p;
    }
}
