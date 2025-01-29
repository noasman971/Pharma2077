import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Inventory implements Stockable, Serializable, java.io.Serializable {
    private List<Product> products = new ArrayList<>();
    private Map<String, Category> categories = new HashMap<>();
    private static final String SAVE_FILE = "pharmacy_data.ser";

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
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < 5) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    public List<Product> sortLowProducts() {
        List<Product> lowStockProducts = getLowStockProducts();

        for (int i = 1; i < lowStockProducts.size(); i++) {
            Product currentProduct = lowStockProducts.get(i);
            int currentQuantity = currentProduct.getQuantity();
            int j = i - 1;

            // Move elements that are bigger than the current product
            while (j >= 0 && lowStockProducts.get(j).getQuantity() > currentQuantity) {
                lowStockProducts.set(j + 1, lowStockProducts.get(j));
                j--;
            }

            // Insert the current element in its correct position
            lowStockProducts.set(j + 1, currentProduct);
        }
        return lowStockProducts;
    }

    public void displayLowStockProducts() {
        System.out.println("Low stock products:");
        for (byte i=0; i< sortLowProducts().size();i++) {
            Product product = sortLowProducts().get(i);
        System.out.println(product.getName() + ": " + product.getQuantity());
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
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(this.products);
            oos.writeObject(this.categories);
            System.out.println("Data successfully saved.");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    @Override
    public void loadData() {
        File saveFile = new File(SAVE_FILE);
        if (!saveFile.exists()) {
            System.out.println("No saved data found, loading from JSON.");
            loadFromJSON("stocks_pharma.json");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            this.products = (List<Product>) ois.readObject();
            this.categories = (Map<String, Category>) ois.readObject();
            System.out.println("Data successfully loaded from saved file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading saved data: " + e.getMessage());
        }
    }
}