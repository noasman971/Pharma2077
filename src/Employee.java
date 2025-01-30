import java.util.Scanner;

/**
 * The {@code Employee} class extends the {@link User} class and represents an employee with
 * specific functionality for managing inventory and displaying orders.
 * The class implements {@link java.io.Serializable} to allow the object to be serialized.
 *
 * <p>
 * Employees have the ability to add or remove products from the inventory,
 * display products with low stock, and view orders (including priority orders).
 * </p>
 *
 */
public class Employee extends User implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * Constructs an {@code Employee} object with the specified username and password.
     *
     * @param username the username of the employee
     * @param password the password of the employee
     */
    public Employee(String username, String password) {
        super(username, password);
    }

    /**
     * Adds a product to the inventory after verifying the product's existence and the
     * requested quantity to be added. The updated inventory is saved.
     *
     * @param inventory the inventory to add the product to
     */
    public void addProduct(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        String productName;
        int quantity;

        while (true) {
            try {
                System.out.print("Enter product name: ");
                productName = scanner.nextLine().trim();
                if (productName.isEmpty()) {
                    System.out.println(Colors.NEON_PINK + "Product name cannot be empty. Try again." + Colors.RESET);
                    continue;
                }

                Product product = inventory.searchProduct(productName);
                if (product == null) {
                    System.out.println(Colors.NEON_PINK + "Product not found in inventory. Try again." + Colors.RESET);
                    continue;
                }

                System.out.print("Enter quantity to add: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println(Colors.NEON_PINK + "Quantity must be greater than zero. Try again." + Colors.RESET);
                    continue;
                }

                product.setQuantity(product.getQuantity() + quantity);
                System.out.println(Colors.CYBER_YELLOW + "Product quantity updated successfully." + Colors.RESET);
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.NEON_PINK + "Invalid input. Please enter a valid number for quantity." + Colors.RESET);
            }
        }
    }

    /**
     * Removes a specified quantity of a product from the inventory after verifying the product's existence
     * and ensuring that the quantity to be removed is available. The updated inventory is saved.
     *
     * @param inventory the inventory to remove the product from
     */
    public void removeProduct(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        String productName;
        int quantity;

        while (true) {
            try {
                System.out.print("Enter product name: ");
                productName = scanner.nextLine().trim();
                if (productName.isEmpty()) {
                    System.out.println(Colors.NEON_PINK + "Product name cannot be empty. Try again." + Colors.RESET);
                    continue;
                }

                Product product = inventory.searchProduct(productName);
                if (product == null) {
                    System.out.println(Colors.NEON_PINK + "Product not found in inventory. Try again." + Colors.RESET);
                    continue;
                }

                System.out.print("Enter quantity to remove: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println(Colors.NEON_PINK + "Quantity must be greater than zero. Try again." + Colors.RESET);
                    continue;
                }
                if (quantity > product.getQuantity()) {
                    System.out.println(Colors.NEON_PINK + "Not enough stock to remove that amount. Try again." + Colors.RESET);
                    continue;
                }

                product.setQuantity(product.getQuantity() - quantity);
                System.out.println(Colors.CYBER_YELLOW + "Product quantity updated successfully." + Colors.RESET);
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            } catch (NumberFormatException e) {
                System.out.println(Colors.NEON_PINK + "Invalid input. Please enter a valid number for quantity." + Colors.RESET);
            }
        }
    }

    /**
     * Displays the products with low stock (less than 5), sorted by quantity.
     * The products with low stock are printed with their names and quantities.
     */
    public void displayLowStockProducts() {
        System.out.println(Colors.NEON_BLUE + "Low stock products:" + Colors.RESET);
        for (byte i = 0; i < Main.inventory.sortLowProducts().size(); i++) {
            Product product = Main.inventory.sortLowProducts().get(i);
            System.out.println(product.getName() + ": " + product.getQuantity());
        }
    }

    /**
     * Displays all the orders, sorted by priority and order date. If an order is marked as priority,
     * it is displayed with the "Priority" label.
     */
    public void displayOrders() {
        Main.orderManager.sortOrders();
        for (Order order : Main.orderManager.orders) {
            if (order.isPriority()) {
                System.out.println("Date: " + order.getFormattedOrderDate() + " | Priority | Contents: " + order.getProductsString(order.getProducts()));
            } else {
                System.out.println("Date: " + order.getFormattedOrderDate() + " | Contents: " + order.getProductsString(order.getProducts()));
            }
        }
    }

    /**
     * This method is overridden but does nothing as the Employee class doesn't implement specific save logic.
     */
    public void saveData() {}

    /**
     * This method is overridden but does nothing as the Employee class doesn't implement specific load logic.
     */
    public void loadData() {}
}
