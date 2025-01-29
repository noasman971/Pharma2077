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
        inventory.saveData();
        userManager.saveData();
    }
}