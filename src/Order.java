import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> products;
    private LocalDateTime orderDate;

    public Order() {
        this.products = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    public void addProduct(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        products.add(product);
        if (product.getQuantity() < 5) {
            System.out.println("This product : " + product.getName() + " has a low quantiy");
        }
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public LocalDateTime getOrderDate() { return orderDate; }
    public List<Product> getProducts() { return products; }
}