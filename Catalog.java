import java.util.*;

public class Catalog implements Printable {
    private static HashMap<String, Category> map = new HashMap<>();
    private static Catalog cat;

    public int getQuantity() {
        return map.size();
    }

    public void add(String category, Product obj) {
        if (map.get(category) == null) {
            map.put(category, new Category(category));
        }
        map.get(category).addProduct(obj);
    }

    @Override
    public void print() {
        showCatalog();
    }

    public Collection<Category> getCategories() {
        return map.values();
    }

    public void showCatalog() {
        map.values().stream().sorted().forEach(c -> {
            System.out.printf("%s [%d]:\n", c.getTitle(), c.getID());
            c.print();
        });
    }

    private Catalog() {}

    public static Catalog getInstance() {
        if (cat == null) {
            cat = new Catalog();
        }
        return cat;
    }
}