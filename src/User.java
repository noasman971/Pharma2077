import java.util.Scanner;

public abstract class User implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println(username + " has logged out.");
    }

    /**
     * This method allows the user to place an order by selecting products from the inventory.
     * The user can add multiple products with specified quantities and mark the order as a priority.
     */
    public void placeOrder() {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();
        boolean isOrderComplete = false;

        System.out.println("Please enter your order:");

        while (!isOrderComplete) {
            try {
                System.out.println("\nChoose a product (or type 'done' to finish):");
                String inputNameProduct = sc.nextLine().trim();

                if (inputNameProduct.equalsIgnoreCase("done")) {
                    if (order.getProducts().isEmpty()) {
                        System.out.println("Cannot place an empty order. Please add at least one product.");
                        continue;
                    }
                    break;
                }

                Product stockProduct = Main.inventory.searchProduct(inputNameProduct);
                if (stockProduct == null) {
                    System.out.println("Product not found in inventory.");
                    continue;
                }

                System.out.println("Current stock for " + stockProduct.getName() + ": " + stockProduct.getQuantity());
                System.out.println("Enter quantity:");

                int inputQuantity;
                try {
                    inputQuantity = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                if (inputQuantity <= 0) {
                    System.out.println("Please enter a positive quantity.");
                    continue;
                }

                if (stockProduct.getQuantity() < inputQuantity) {
                    System.out.println("Insufficient stock. Available: " + stockProduct.getQuantity());
                    continue;
                }

                Product orderProduct = new Product(
                        stockProduct.getName(),
                        stockProduct.getPrice(),
                        inputQuantity,
                        stockProduct.getCategory()
                );

                order.addProduct(stockProduct, inputQuantity, orderProduct);

                Sale sale = new Sale(orderProduct.getName(), orderProduct.getQuantity(), orderProduct.getPrice());
                SalesStatistics.addSale(sale);

                System.out.println(inputQuantity + " " + orderProduct.getName() + " added to the order.");

                System.out.println("Current order contents:");
                order.diplayProducts();
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                continue;
            }
        }

        while (true) {
            System.out.println("Is this order priority? (yes/no)");
            String priority = sc.nextLine().trim().toLowerCase();

            if (priority.equals("yes")) {
                order.setPriority(true);
                break;
            } else if (priority.equals("no")) {
                order.setPriority(false);
                break;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }

        Main.orderManager.addOrder(order);
        System.out.println("Order placed successfully!");
        System.out.println("---Order summary---");
        order.diplayProducts();
    }
}