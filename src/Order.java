import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private LocalDateTime orderDate;
    private boolean isPriority = false;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    public Order() {
        this.products = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    public void diplayProducts() {
        for(Product p : products){
            System.out.println(Colors.LIGHT_CYAN + p.getName()+ ": " + p.getQuantity() + Colors.RESET);
        }
    }


    public void addProduct(Product product, int quantity, Product productorder) {
        products.add(productorder);
        product.setQuantity(product.getQuantity() - quantity);

    }


    public void removeProduct(Product product) {
        products.remove(product);
    }

    public LocalDateTime getOrderDate() { return orderDate; }
    public String getFormattedOrderDate() {
        return orderDate.format(formatter);
    }
    public List<Product> getProducts() {
        return products;
    }

    public String getProductsString(List<Product> listproduct) {
        String productString = "";
        int quantity = 0;
        for(Product product : listproduct){
            quantity = product.getQuantity();
            productString = productString+product.getName() + " Quantity: " + quantity + " ";
            productString += " | ";

        }
        return productString;
    }
    public boolean isPriority() { return isPriority; }
    public void setPriority(boolean priority){
        this.isPriority = priority;
    }
}