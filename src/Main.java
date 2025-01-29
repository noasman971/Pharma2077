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
        loadFromJSON("stocks_pharma.json");
        inventory.displayProductList();
        run();
    }
}