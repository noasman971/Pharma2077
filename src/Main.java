import java.util.*;
public class Main {
    public static Inventory inventory = new Inventory();
    public static UserManager userManager = new UserManager();
    public static OrderManager orderManager = new OrderManager();

    public static void run() {
        System.out.println("Pharma2077 started...");
        //inventory.displayProductList();
    }

    public static void main(String[] args) {
        inventory.loadData();
        userManager.loadData();
        orderManager.loadData();
        run();
        inventory.displayProductListInfo();
        orderManager.displayOrders();
        Employee nono = new Employee("nonodubendo", "mdp");
        //nono.placeOrder();
        //nono.addProduct(inventory);
        orderManager.displayOrders();

        inventory.saveData();
        userManager.saveData();
        orderManager.saveData();
    }
}