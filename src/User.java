/**
 * The {@code User} class represents an abstract user with authentication capabilities.
 * It provides functionalities for login, logout, and placing orders.
 * This class implements {@code Serializable} to allow object serialization.
 *
 * @author [Your Name]
 * @version 1.0
 */
import java.awt.*;
import java.util.Scanner;

public abstract class User implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;

    /**
     * Constructs a new {@code User} with the specified username and password.
     *
     * @param username the username of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the username of this user.
     *
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Authenticates the user by checking the provided credentials.
     *
     * @param username the input username
     * @param password the input password
     * @return {@code true} if the credentials match, {@code false} otherwise
     */
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * Logs out the user and prints a logout message.
     */
    public void logout() {
        System.out.println(Colors.CYBER_YELLOW + username + " has logged out." + Colors.RESET);
    }

    /**
     * Allows the user to place an order by selecting products from the inventory.
     * The user can add multiple products with specified quantities and mark the order as a priority.
     *
     * The process involves:
     * <ul>
     *     <li>Selecting a product from inventory</li>
     *     <li>Entering the quantity</li>
     *     <li>Confirming the order priority</li>
     *     <li>Adding the order to the order manager</li>
     * </ul>
     */
    public void placeOrder() {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();
        boolean isOrderComplete = false;

        System.out.println(Colors.CYBER_YELLOW + "Please enter your order:" + Colors.RESET);

        while (!isOrderComplete) {
            try {
                System.out.println(Colors.BRIGHT_PURPLE + "\nChoose a product (or type 'done' to finish):" + Colors.RESET);
                String inputNameProduct = sc.nextLine().trim();

                if (inputNameProduct.equalsIgnoreCase("done")) {
                    if (order.getProducts().isEmpty()) {
                        System.out.println(Colors.NEON_PINK + "Cannot place an empty order. Please add at least one product." + Colors.RESET);
                        continue;
                    }
                    break;
                }

                // Search for the product in inventory
                Product stockProduct = Main.inventory.searchProduct(inputNameProduct);
                if (stockProduct == null) {
                    System.out.println(Colors.NEON_PINK + "Product not found in inventory." + Colors.RESET);
                    continue;
                }

                System.out.println(Colors.LIGHT_CYAN + "Current stock for " + stockProduct.getName() + ": " + stockProduct.getQuantity() + Colors.RESET);
                System.out.println(Colors.BRIGHT_PURPLE + "Enter quantity:" + Colors.RESET);

                int inputQuantity;
                try {
                    inputQuantity = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println(Colors.NEON_PINK + "Please enter a valid number." + Colors.RESET);
                    continue;
                }

                if (inputQuantity <= 0) {
                    System.out.println(Colors.NEON_PINK + "Please enter a positive quantity." + Colors.RESET);
                    continue;
                }

                if (stockProduct.getQuantity() < inputQuantity) {
                    System.out.println(Colors.NEON_PINK + "Insufficient stock. Available: " + stockProduct.getQuantity() + Colors.RESET);
                    continue;
                }

                Product orderProduct = new Product(stockProduct.getName(), stockProduct.getPrice(), inputQuantity, stockProduct.getCategory());
                order.addProduct(stockProduct, inputQuantity, orderProduct);

                Sale sale = new Sale(orderProduct.getName(), orderProduct.getQuantity(), orderProduct.getPrice());
                SalesStatistics.addSale(sale);

                System.out.println(Colors.MEDICAL_CYAN + inputQuantity + " " + orderProduct.getName() + " added to the order." + Colors.RESET);

                System.out.println(Colors.CYBER_YELLOW + "\nCurrent order contents:" + Colors.RESET);
                order.diplayProducts();
            } catch (Exception e) {
                System.out.println(Colors.NEON_PINK + "An error occurred. Please try again." + Colors.RESET);
                continue;
            }
        }

        while (true) {
            System.out.println(Colors.BRIGHT_PURPLE + "Is this order priority? (yes/no)" + Colors.RESET);
            String priority = sc.nextLine().trim().toLowerCase();

            if (priority.equals("yes")) {
                order.setPriority(true);
                break;
            } else if (priority.equals("no")) {
                order.setPriority(false);
                break;
            } else {
                System.out.println(Colors.NEON_PINK + "Please enter 'yes' or 'no'." + Colors.RESET);
            }
        }

        Main.orderManager.addOrder(order);
        System.out.println(Colors.CYBER_YELLOW + "Order placed successfully!" + Colors.RESET);
        System.out.println(Colors.NEON_BLUE + "---Order summary---" + Colors.RESET);
        order.diplayProducts();
    }
}
