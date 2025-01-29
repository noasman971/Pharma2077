import java.util.*;
public class Main {
    public static Inventory inventory = new Inventory();
    public static UserManager userManager = new UserManager();
    public static List<Order> orders = new ArrayList<>();

    public static void run() {
        System.out.println("Pharma2077 started...");
        inventory.displayProductList();
    }

    public static void main(String[] args) {
        inventory.loadData();
        userManager.loadData();
        run();
        inventory.displayProductList();

        inventory.displayLowStockProducts();
        Client nono = new Client("nonodubendo", "mdp");
        Order order = new Order();

        nono.placeOrder(order);
        inventory.displayLowStockProducts();
        inventory.saveData();
        userManager.saveData();
    }
}