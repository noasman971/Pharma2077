import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Order} class represents an order in a system, containing a list of products,
 * the order date, and a priority flag.
 * It also provides methods to manipulate the order's products and retrieve order information.
 * This class implements {@code Serializable} to allow saving and loading of order data.
 */
public class Order implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private List<Product> products;
    private LocalDateTime orderDate;
    private boolean isPriority = false;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    /**
     * Constructs a new {@code Order} with an empty product list and sets the order date to the current date and time.
     */
    public Order() {
        this.products = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
    }

    /**
     * Displays the list of products in the order with their names and quantities.
     * The product information is printed in a color defined by {@link Colors#LIGHT_CYAN}.
     */
    public void diplayProducts() {
        for(Product p : products){
            System.out.println(Colors.LIGHT_CYAN + p.getName()+ ": " + p.getQuantity() + Colors.RESET);
        }
    }

    /**
     * Adds a product to the order and adjusts the quantity of the product.
     *
     * @param product The product to be added.
     * @param quantity The quantity to be deducted from the product.
     * @param productorder The product to be added to the order.
     */
    public void addProduct(Product product, int quantity, Product productorder) {
        products.add(productorder);
        product.setQuantity(product.getQuantity() - quantity);
    }

    /**
     * Removes a product from the order.
     *
     * @param product The product to be removed from the order.
     */
    public void removeProduct(Product product) {
        products.remove(product);
    }

    /**
     * Returns the date and time when the order was created.
     *
     * @return A {@code LocalDateTime} object representing the order's date and time.
     */
    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    /**
     * Returns the formatted order date as a string in the format "dd/MM/yyyy HH:mm".
     *
     * @return A string representing the formatted order date.
     */
    public String getFormattedOrderDate() {
        return orderDate.format(formatter);
    }

    /**
     * Returns the list of products in the order.
     *
     * @return A {@code List<Product>} representing the products in the order.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Returns a string representation of the products in the provided list,
     * including the product name and quantity.
     * Each product is separated by " | ".
     *
     * @param listproduct The list of products to be represented as a string.
     * @return A string representing the products in the list.
     */
    public String getProductsString(List<Product> listproduct) {
        String productString = "";
        int quantity = 0;
        for(Product product : listproduct){
            quantity = product.getQuantity();
            productString = productString + product.getName() + " Quantity: " + quantity + " ";
            productString += " | ";
        }
        return productString;
    }

    /**
     * Returns whether the order has priority.
     *
     * @return {@code true} if the order is marked as priority, {@code false} otherwise.
     */
    public boolean isPriority() {
        return isPriority;
    }

    /**
     * Sets the priority status of the order.
     *
     * @param priority A boolean indicating whether the order should be priority.
     */
    public void setPriority(boolean priority){
        this.isPriority = priority;
    }
}
