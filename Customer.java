import java.sql.Array;
import java.util.ArrayList;

public class Customer extends Person implements Financeable {
    private Wallet wallet = new Wallet(0, 0);
    private Main.FinanceStatus FinanceStatus;
    private ProductCart Cart = new ProductCart(this);
    private ArrayList<Order> Orders = new ArrayList<>();

    public boolean paid() {
        return Cart.isPaid();
    }

    public boolean deposit(double q) {
        if (q <= 0) return false;
        return wallet.deposit(q);
    }

    public ArrayList<Order> getOrders() {
        return Orders;
    }

    public void addOrder(ArrayList<Product> a) {
        Orders.add(new Order(a));
    }

    public boolean withdraw(double q) {
        if (q <= 0) return false;
        return wallet.withdraw(q);
    }

    public void printBalance() {
        System.out.printf("Credit balance: %f\nDebit balance: %f\n", wallet.credit(), wallet.balance());
    }

    public double balance() {
        return wallet.balance();
    }

    public boolean hasEnoughMoney() {
        return wallet.fullBalance() >= Cart.amount();
    }

    public Main.FinanceStatus getFinanceStatus() {
        return FinanceStatus;
    }

    public void addProduct(Product p) {
        Cart.add(p);
    }

//    private Product getProductByID(int id) {
    public Product getProductByID(int id) {
        Product p = null;
        boolean t = false;
        for (Category c : Catalog.getInstance().getCategories()) {
            if (t) break;
            for (Product pr : c.getProducts(v -> true)) {
                if (pr.getId() == id) {
                    p = pr;
                    t = true;
                    break;
                }
            }
        }

        return p;
    }

    public void addProduct(int id) {
        Product p = getProductByID(id);
        if (p != null) {
            addProduct(p);
        }
    }

    public void removeProduct(Product p) {
        Cart.remove(p);
    }

    public void removeProduct(int id) {
        Product p = getProductByID(id);
//        System.out.println(p.getTitle());
        if (p != null) {
            removeProduct(p);
        }
    }

    public ProductCart getCart() {
        return Cart;
    }

    Customer(String n, String s, int b) {
        super(n, s, b);
    }
}
