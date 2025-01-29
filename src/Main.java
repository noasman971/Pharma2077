import java.time.Period;
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

    public static Inventory getInventory() {
        return inventory;
    }

    public static void main(String[] args) {
        loadFromJSON("stocks_pharma.json");
        inventory.displayProductList();

        inventory.displayLowStockProducts();
        Client nono = new Client("nonodubendo", "mdp");
        Order order = new Order();

        nono.placeOrder(order);
        inventory.displayLowStockProducts();





        run();
    }
}