import java.util.*;
public class Main {
    public static List<User> users = new ArrayList<>();
    public static Inventory inventory = new Inventory();
    public static List<Order> orders = new ArrayList<>();

    public static void saveState() {
        inventory.saveData();
    }

    public static void run() {
        System.out.println("Application started...");
    }

    public static void main(String[] args) {
        inventory.loadData();
        inventory.displayProductList();

        inventory.displayLowStockProducts();
        Client nono = new Client("nonodubendo", "mdp");
        Order order = new Order();

        nono.placeOrder(order);
        inventory.displayLowStockProducts();




        run();
        saveState();
    }
}