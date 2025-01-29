import java.util.*;
public class Main {
    private static List<User> users = new ArrayList<>();
    private static Inventory inventory = new Inventory();
    private static List<Order> orders = new ArrayList<>();



    public static void loadFromJSON(String filePath) {
        inventory.loadFromJSON(filePath);
    }

    public static void run() {
        System.out.println("Application started...");
    }

    public static void main(String[] args) {
        System.out.println(Colors.NEON_PINK + "This is neon pink text!" + Colors.RESET);
        System.out.println(Colors.NEON_GREEN + "This is neon green text!" + Colors.RESET);
        System.out.println(Colors.NEON_BLUE + "This is neon blue text!" + Colors.RESET);
        System.out.println(Colors.CYBER_YELLOW + "This is cyber yellow text!" + Colors.RESET);
        System.out.println(Colors.MEDICAL_CYAN + "This is medical cyan text!" + Colors.RESET);
        System.out.println(Colors.MEDICAL_WHITE + "This is medical white text!" + Colors.RESET);
        System.out.println(Colors.BRIGHT_PURPLE + "This is bright purple text!" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "This is light cyan text!" + Colors.RESET);



        loadFromJSON("stocks_pharma.json");
        inventory.displayProductList();
        run();
        Category test = new Category("TEST");
        Product produit1 = new Product("Test", 15.0, 10, test);

        inventory.addProduct(produit1);

        inventory.searchProduct("Ibuprofène");

        inventory.removeProduct("Tramadol");
        inventory.removeProduct("tramadol");

        inventory.displayProductList();
    }
}