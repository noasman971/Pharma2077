import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
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
        inventory.displayProductList();
//        Category category1 = new Category("TestSaveCat");
//        Product produit1 = new Product("TestSavePro", 2500, 5, category1);
//        inventory.addProduct(produit1);
//        inventory.displayProductList();
//        inventory.saveData();
    }

    public static void main(String[] args) {
        inventory.loadData();
        run();
        saveState();
    }
}