import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Inventory implements Stockable, Serializable {
    private List<Product> products = new ArrayList<>();
    private Map<String, Category> categories = new HashMap<>();

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(String productName) {
        products.removeIf(p -> p.getName().equalsIgnoreCase(productName));
    }

    public Product searchProduct(String productName) {
        return products.stream()
                .filter(p -> p.getName().equalsIgnoreCase(productName))
                .findFirst().orElse(null);
    }

    public List<Product> getLowStockProducts() {
        return products.stream().filter(p -> p.getQuantity() < 5).toList();
    }
    public void displayLowStockProducts() {
        System.out.println("Low stock products:");
        for (byte i=0; i< getLowStockProducts().size();i++) {
            Product product = getLowStockProducts().get(i);
            System.out.println(product.getName());
        }
    }

    public void updateStock(Product product, int quantity) {
        product.setQuantity(quantity);
    }

    public void displayProductList() {
        products.sort(Comparator.comparing(Product::getName));
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println("- " + product.getName() + " | Price: " + product.getPrice() + " | Quantity: " + product.getQuantity() + " | Category: " + product.getCategory().getName());
        }
    }

    public void loadFromJSON(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));
            JSONObject pharmacie = (JSONObject) jsonData.get("pharmacie");
            JSONArray produits = (JSONArray) pharmacie.get("produits");

            for (Object obj : produits) {
                JSONObject categoryData = (JSONObject) obj;
                String categoryName = (String) categoryData.get("categorie");

                if (!categories.containsKey(categoryName)) {
                    categories.put(categoryName, new Category(categoryName));
                }

                JSONArray productArray = (JSONArray) categoryData.get("produits");
                for (Object prodObj : productArray) {
                    JSONObject productData = (JSONObject) prodObj;
                    String name = (String) productData.get("nom");
                    double price = ((Number) productData.get("prix")).doubleValue();
                    int quantity = ((Number) productData.get("quantiteStock")).intValue();

                    Product product = new Product(name, price, quantity, categories.get(categoryName));
                    this.addProduct(product);
                }
            }

            System.out.println("Data successfully loaded from JSON.");
        } catch (Exception e) {
            System.err.println("Error loading JSON: " + e.getMessage());
        }
    }

    @Override
    public void saveData() {
    }

    @Override
    public void loadData() {

    }
}