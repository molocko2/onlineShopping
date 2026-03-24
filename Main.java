public class Main {
    public static void main(String[] args) {
        Product p = new Product("Z", 42);
        System.out.printf("[%s] %f",p.getTitle(), p.getPrice());
    }
}