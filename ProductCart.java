import java.util.ArrayList;

public class ProductCart implements Payable{
    private ArrayList<Product> Cart = new ArrayList<>();
    private final int id = Main.IDUtility.getID(this);
    private Customer customer;
    private boolean paid = false;

    private void lastPay() {
        customer.addOrder(Cart);
        paid = true;
        System.out.println("Purchased successfully");
    }

    public void pay() {
        if (paid) {
            System.out.println("Already paid");
            return;
        }
        if (Cart.isEmpty()) {
            System.out.println("Cart is empty");
            return;
        }
        if (customer.hasEnoughMoney()) {
            if (customer.balance() >= amount()) {
                if (customer.withdraw(amount())) {
                    lastPay();
                }
            } else {
                System.out.println("Not enough money on debit card | Do you want to pay with credit card? [Y/N]");
                String move = Main.scanner.nextLine();
                if (move.equals("Y")) {
                    if (customer.withdraw(amount())) {
                        lastPay();
                    }
                } else {
                    System.out.println("Not enough money");
                }
            }
        } else {
            System.out.println("Not enough money");
        }
    }

    public double amount() {
        double s = 0;
        for (Product p : Cart) { s += p.getPrice(); }
        return s;
    }

    public boolean isPaid() {
        return paid;
    }

    public void add(Product p) {
        Cart.add(p);
    }

    public void remove(Product p) {
        if(!Cart.remove(p)) System.out.println("====== NOT FOUND =========");
//        for(Product pr : getProducts()){
//            pr.print();
//            System.out.println(p.equals( pr));
//        }
        ArrayList<Product> list = getProducts();
        for(int i = 0; i < list.size();i++){
            list.get(i).print();
            System.out.println(p.equals( list.get(i)));
            System.out.println(p == list.get(i));
        }
//        Cart.remove(Cart.indexOf(   p));
    }

    public ArrayList<Product> getProducts() {
        return Cart;
    }

    public ProductCart(Customer i) {
        customer = i;
    }
}
