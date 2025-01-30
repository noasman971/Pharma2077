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
        System.out.println(Colors.NEON_PINK + "This is neon pink text!" + Colors.RESET);
        System.out.println(Colors.NEON_GREEN + "This is neon green text!" + Colors.RESET);
        System.out.println(Colors.NEON_BLUE + "This is neon blue text!" + Colors.RESET);
        System.out.println(Colors.CYBER_YELLOW + "This is cyber yellow text!" + Colors.RESET);
        System.out.println(Colors.MEDICAL_CYAN + "This is medical cyan text!" + Colors.RESET);
        System.out.println(Colors.MEDICAL_WHITE + "This is medical white text!" + Colors.RESET);
        System.out.println(Colors.BRIGHT_PURPLE + "This is bright purple text!" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "This is light cyan text!" + Colors.RESET);
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