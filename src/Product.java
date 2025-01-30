import java.io.Serializable;

/**
 * The {@code Product} class represents a product with a name, price, quantity, and category.
 * It implements {@link java.io.Serializable} to allow the object to be serialized for storage or transmission.
 * <p>
 * This class provides methods to get and set the product's attributes, such as its name, price, quantity, and category.
 * </p>
 *
 */
public class Product implements Serializable {
    private String name;
    private double price;
    private int quantity;
    private Category category;

    /**
     * Constructs a {@code Product} with the specified name, price, quantity, and category.
     *
     * @param name the name of the product
     * @param price the price of the product
     * @param quantity the quantity of the product
     * @param category the category of the product
     */
    public Product(String name, double price, int quantity, Category category) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    /**
     * Returns the name of the product.
     *
     * @return the name of the product
     */
    public String getName() { return name; }

    /**
     * Returns the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice() { return price; }

    /**
     * Returns the quantity of the product.
     *
     * @return the quantity of the product
     */
    public int getQuantity() { return quantity; }

    /**
     * Returns the category of the product.
     *
     * @return the category of the product
     */
    public Category getCategory() { return category; }

    /**
     * Sets the price of the product.
     *
     * @param price the new price of the product
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Sets the quantity of the product.
     *
     * @param quantity the new quantity of the product
     */
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
