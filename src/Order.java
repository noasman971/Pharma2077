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

    public void diplayProducts() {
        for(Product p : products){
            System.out.println(p.getName()+ ": " + p.getQuantity());
        }
    }


    public void addProduct(Product product, int quantity, Product productorder) {
        products.add(productorder);
        product.setQuantity(product.getQuantity() - quantity);
        if (product.getQuantity() < 5) {
            System.out.println("This product : " + product.getName() + " has a low quantiy ("+product.getQuantity()+" remaining)");
        }
    }


    public void removeProduct(Product product) {
        products.remove(product);
    }

    public LocalDateTime getOrderDate() { return orderDate; }
    public List<Product> getProducts() { return products; }
}