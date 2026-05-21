import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//
// https://accounts.google.com/v3/signin/accountchooser?continue=https%3A%2F%2Fdocs.google.com%2Fforms%2Fd%2Fe%2F1FAIpQLSfWbKuSDmAUAggSY8YjMW_9j5VXKznsmnsRKSyIqw_2AE11bA%2Fviewform%3Fusp%3Dheader&dsh=S303256082%3A1774346379493958&followup=https%3A%2F%2Fdocs.google.com%2Fforms%2Fd%2Fe%2F1FAIpQLSfWbKuSDmAUAggSY8YjMW_9j5VXKznsmnsRKSyIqw_2AE11bA%2Fviewform%3Fusp%3Dheader&ltmpl=forms&osid=1&passive=1209600&service=wise&flowName=GlifWebSignIn&flowEntry=ServiceLogin&ifkv=AT1y2_V_6A1z14cYUGDuHRBT7lnASXk4IHFjyURnKFQWqhglSfhcl1U7TkpYNZebJbVtj2Jb-ajGCA

/*************************************
 *  Latest Log
 *  Factory pattern in products create
 *  Better orders system (Order class)
 *  Added immutable classes variables
 *  Added sorting for categories
 *
 *  Pre-Latest Log
 *  Added singleton pattern for Catalog
 *************************************/

public class Main {
    private static Catalog catalog = Catalog.getInstance();
    public static utils IDUtility = new utils();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initProducts();
        initMoves();
        userMenuLoop();
    }



    enum FinanceStatus {
        pass;
    }

    enum SortType {
        Id,
        Title,
        Price
    }

    private static Map<Integer, String> menuMoves = new HashMap<>(Map.of(
            0, "Exit",
            1, "Show Catalog",
            2, "Add product to cart",
            3, "Remove product from cart",
            4, "Show cart",
            5, "Pay",
            6, "Deposit",
            7, "Check balance",
            8, "Set sort type",
            9, "Show products by name"
    ));

    public static void showUserMenu() {
        menuMoves.keySet().forEach(key->System.out.printf("%d: %s\n", key, menuMoves.get(key)));
    }

    private static void userMenuLoop() {
        Customer customer = new Customer("Aaa", "Bbbb", 42);

        while (true) {
            System.out.println();
            showUserMenu();
            System.out.println();

            int move = Integer.parseInt(scanner.nextLine());

            switch (move) {
                case 0: return;
                case 1: {
                    catalog.showCatalog();
                    scanner.nextLine();
                    break;
                }
                case 2: {
                    if (customer.paid()) {
                        System.out.println("You can't add products after paid");
                        scanner.nextLine();

                        break;
                    }

                    String m = scanner.nextLine();
                    if (m.isEmpty()) continue;
                    int move2 = Integer.parseInt(m);
                    customer.addProduct(move2);
                    System.out.printf("Added - %s\n",customer.getProductByID(move2).getTitle());
                    scanner.nextLine();

                    break;
                }
                case 3: {
                    if (customer.paid()) {
                        System.out.println("You can't remove products after paid");
                        scanner.nextLine();

                        break;
                    }

                    String m = scanner.nextLine();
                    if (m.isEmpty()) continue;
                    int move2 = Integer.parseInt(m);
                    customer.removeProduct(move2);
                    System.out.printf("Removed - %s\n",customer.getProductByID(move2).getTitle());
                    scanner.nextLine();

                    break;
                }
                case 4: {
                    for (Product p : customer.getCart().getProducts()) {
                        System.out.printf("%s | %f\n", p.getTitle(), p.getPrice());
                    }
                    scanner.nextLine();

                    break;
                }
                case 5: {
                    customer.getCart().pay();
                    scanner.nextLine();

                    break;
                }
                case 6: {
                    String m = scanner.nextLine();
                    if (m.isEmpty()) continue;
                    double move2 = Double.parseDouble(m);
                    customer.deposit(move2);

                    break;
                }
                case 7: {
                    customer.printBalance();
                    scanner.nextLine();

                    break;
                }
                case 8: {
                    System.out.print("Select sort type:\n 1. ID\n2. Title\n3. Price\n");
                    int move2 = Integer.parseInt(scanner.nextLine());

                    if (move2 > 3 || move2 < 1) break;

                    for (Category c : catalog.getCategories()) {
                        c.SetSortType(((move2 == 1) ? SortType.Id : ((move2 == 2) ? SortType.Price : SortType.Title)));
                    }
                    scanner.nextLine();

                    break;
                }
                case 9: {
                    String move2 = scanner.nextLine();

                    AtomicInteger i = new AtomicInteger(0);

                    for (Category c : catalog.getCategories()) {
                        c.getProducts(P -> P.getTitle().contains(move2)).stream().forEach(p -> {System.out.printf("[%d] %s (%f)\n", p.getId(), p.getTitle(), p.getPrice());
                            i.getAndIncrement();
                        });
                    }

                    if (i.get() == 0) {
                        System.out.printf("No products with name \"%s\"\n", move2);
                    }
                    scanner.nextLine();

                    break;
                }
                case 10: {
                    System.out.println("Type filter price:");
                    float move2 = Float.parseFloat(scanner.nextLine());
                    System.out.println("> or <:");
                    boolean move3 = scanner.nextLine().equals("<");

                    AtomicInteger i = new AtomicInteger(0);

                    for (Category c : catalog.getCategories()) {
                        c.getProducts(P -> move3 ? P.getPrice() > move2 : P.getPrice() < move2).stream().forEach(p -> {System.out.printf("[%d] %s (%f)\n", p.getId(), p.getTitle(), p.getPrice());
                            i.getAndIncrement();
                        });
                    }

                    if (i.get() == 0) {
                        System.out.printf("No products with price %s than %f\n", move3 ? "less" : "more", move2);
                    }
                    scanner.nextLine();

                    break;
                }
                case 11: {
                    ArrayList<Order> Orders = customer.getOrders();
                    for (Order order : Orders) {
                        order.print();
                    }
                    scanner.nextLine();

                    break;
                }
            }
        }
    }

    private static void createGarden(String s, double pr) {
        new Garden(s, pr);
    }

    private static void createElectronics(String s, double pr) {
        new Electronics(s, pr);
    }

    private static void createClothes(String s, double pr) {
        new Clothes(s, pr);
    }

    private static void initProducts() {
        createGarden("Flower", 3.15);
        createGarden("Tree", 15.99);
        createGarden("Shovel", 7.99);
        createElectronics("Computer", 40.75);
        createElectronics("iPhone", 25.99);
        createElectronics("TV", 79.99);
        createClothes("Jeans", 7.49);
        createClothes("Shirt", 5.79);
        createClothes("Gloves", 4.99);
        createClothes("Jacket", 10.25);
    }

    private static void initMoves() {
        menuMoves.put(10, "Show products filtered by price");
        menuMoves.put(11, "Show orders history");
    }


}