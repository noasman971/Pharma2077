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
        Employee nono = new Employee("nonodubendo", "mdp");
        //nono.placeOrder();
        //nono.addProduct(inventory);
        inventory.loadFromJSON("stocks_pharma.json");
        inventory.displayProductListInfo();
        orderManager.displayOrders();

        inventory.saveData();
        userManager.saveData();
        orderManager.saveData();
    }
}