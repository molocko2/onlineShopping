public class Main {
    public static void main(String[] args) {
        Product g1 = new Garden("Z", 41);
        Product g2 = new Garden("A", 42);
        Product g3 = new Garden("S", 43);

        Product e1 = new Electronics("ZAS",41);
        Product e2 = new Electronics("SAZ",42);
        Product e3 = new Electronics("ASZ",43);
        System.out.printf("%d\n%d\n%d\n",g1.getId(),g2.getId(),e3.getId());

    }
}