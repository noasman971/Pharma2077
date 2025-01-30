import java.util.*;
import java.io.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Represents an inventory that manages a list of products and categories.
 * Implements Stockable and Serializable for stock management and data saving/loading.
 */
public class Inventory implements Stockable, Serializable, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> products = new ArrayList<>();
    private Map<String, Category> categories = new HashMap<>();
    private static final String SAVE_FILE = "pharmacy_data.ser";

    /**
     * Adds a product to the inventory, sorts the list, and prints a confirmation.
     * @param product The product to be added.
     */
    public void addProduct(Product product) {
        products.add(product);
        insertionSort(products);
        System.out.println(Colors.NEON_GREEN + "Added product: " + product.getName() + Colors.RESET);
    }

    /**
     * Removes a product by its name if confirmed by the user.
     * @param productName The name of the product to remove.
     */
    public void removeProduct(String productName) {
        products.removeIf(product -> {
            if (product.getName().equalsIgnoreCase(productName)) {
                if (confirmDeletion(productName)) {
                    System.out.println(Colors.CYBER_YELLOW + "Removed product: " + product.getName() + Colors.RESET);
                    return true;
                }else{
                    System.out.println(Colors.NEON_PINK + "Product " + productName + " still exist" + Colors.RESET);
                    return false;
                }

            }
            return false;
        });
    }

    /**
     * Asks for user confirmation to delete a product.
     * @param productName The name of the product to delete.
     * @return true if the user confirms, false otherwise.
     */
    private boolean confirmDeletion(String productName) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(Colors.MEDICAL_CYAN + "Are you sure you want to delete the product " + productName + "?" + Colors.RESET);
        String answer = scanner.nextLine();
        switch (answer.toLowerCase()) {
            case "yes":
                return true;
            case "no":
                return false;
            default:
                System.out.println(Colors.NEON_PINK + "Invalid choice. Please type 'yes' or 'no'." + Colors.RESET);
                return confirmDeletion(productName);
        }
    }

    public Product searchProduct(String productName) {
        int start = 0;
        int end = products.size() - 1;

        // Ensure the list is sorted before searching
        insertionSort(products);

        while (start <= end) {
            int mid = (start + end) / 2;
            int compare = products.get(mid).getName().compareTo(productName);

            if (compare == 0) {
                return products.get(mid); // Return the product if found
            }

            if (compare < 0) {
                start = mid + 1; // Search in the right half
            } else {
                end = mid - 1; // Search in the left half
            }

        }
        return null;
    }
    /**
     * Searches for a product by its name using binary search.
     *
     */
    public void searchProductScanner() {
        Scanner sc = new Scanner(System.in);
        String productName;
        while (true){
            System.out.print("Entrer le nom d'un produit : ");
            try {
                productName = sc.nextLine();
                break;
            }catch (Exception e){
                continue;
            }
        }

        int start = 0;
        int end = products.size() - 1;

        // Ensure the list is sorted before searching
        insertionSort(products);

        while (start <= end) {
            int mid = (start + end) / 2;
            int compare = products.get(mid).getName().compareTo(productName);

            if (compare == 0) {
                System.out.println(Colors.NEON_BLUE + "Product found: " + products.get(mid).getName() + Colors.RESET);
                return; // Return the product if found
            }

            if (compare < 0) {
                start = mid + 1; // Search in the right half
            } else {
                end = mid - 1; // Search in the left half
            }
        }

        // If product is not found
        System.out.println(Colors.NEON_PINK + "Product " + productName + " does not exist" + Colors.RESET);
    }

    /**
     * Returns a list of products with low stock (less than 5).
     * @return A list of products with low stock.
     */
    public List<Product> getLowStockProducts() {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getQuantity() < 5) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }
    /**
     * Return a list of products with low stock (less than 5) sort by quanrtity
     * @return a list of products
     */
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


    /**
     * Updates the stock quantity of a product.
     * @param product The product to update.
     * @param quantity The new quantity of the product.
     */
    public void updateStock(Product product, int quantity) {
        product.setQuantity(quantity);
        System.out.println(Colors.CYBER_YELLOW + "Stock updated for " + product.getName() + " to " + quantity + " units." + Colors.RESET);
    }

    /**
     * Displays the list of products, sorting them first if necessary.
     */
    public void displayProductList() {
        // Check if the list is empty
        if (products.isEmpty()) {
            System.out.println(Colors.NEON_PINK + "No products found" + Colors.RESET);
        } else {
            // Sort the list before displaying
            insertionSort(products);

            // Print each product
            for (Product product : products) {
                System.out.println(Colors.LIGHT_CYAN + product.getName() + Colors.RESET);
            }
        }
    }

    /**
     * Displays the list of products, sorting them first if necessary.
     */
    public void displayProductListInfo() {
        // Check if the list is empty
        if (products.isEmpty()) {
            System.out.println(Colors.NEON_PINK + "No products found" + Colors.RESET);
        } else {
            // Sort the list before displaying
            insertionSort(products);

            // Print each product
            for (Product product : products) {
                System.out.println(Colors.LIGHT_CYAN + product.getName() + " // " + product.getPrice() + "$ // Quantity : " + product.getQuantity() + Colors.RESET );
            }
        }
    }

    /**
     * Sorts the product list alphabetically by product name using insertion sort.
     * @param products The list of products to sort.
     */
    private void insertionSort(List<Product> products) {
        int n = products.size();
        for (int i = 1; i < n; i++) {
            Product key = products.get(i);
            int j = i - 1;

            // Compare by product name (alphabetical order)
            while (j >= 0 && products.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                // Shift elements to the right
                products.set(j + 1, products.get(j));
                j = j - 1;
            }
            // Insert the key at the right position
            products.set(j + 1, key);
        }
    }

    /**
     * Loads product and category data from a JSON file and adds them to the inventory.
     * @param filePath The path to the JSON file.
     */
    public void loadFromJSON(String filePath) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonData = (JSONObject) parser.parse(new FileReader(filePath));
            JSONObject pharmacie = (JSONObject) jsonData.get("pharmacie");
            JSONArray produits = (JSONArray) pharmacie.get("produits");

            for (Object obj : produits) {
                JSONObject categoryData = (JSONObject) obj;
                String categoryName = (String) categoryData.get("categorie");

                categories.putIfAbsent(categoryName, new Category(categoryName));
                Category category = categories.get(categoryName);

                JSONArray productArray = (JSONArray) categoryData.get("produits");
                for (Object prodObj : productArray) {
                    JSONObject productData = (JSONObject) prodObj;
                    String name = (String) productData.get("nom");
                    double price = ((Number) productData.get("prix")).doubleValue();
                    int quantity = ((Number) productData.get("quantiteStock")).intValue();

                    Product existingProduct = searchProduct(name);
                    if (existingProduct != null) {
                        existingProduct.setQuantity(existingProduct.getQuantity() + quantity);
                    } else {
                        Product product = new Product(name, price, quantity, category);
                        this.addProduct(product);
                    }
                }
            }
            System.out.println("\u001B[34mData successfully loaded from JSON.\u001B[0m");
        } catch (Exception e) {
            System.err.println("\u001B[35mError loading JSON: " + e.getMessage() + "\u001B[0m");
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