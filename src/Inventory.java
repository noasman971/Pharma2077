import java.util.*;
public class Inventory implements Stockable, Serializable {
    private List<Product> products = new ArrayList<>();

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

    public void updateStock(Product product, int quantity) {
        product.setQuantity(quantity);
    }

    public void saveData() {}
    public void loadData() {}
}