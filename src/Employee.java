import java.util.Scanner;

public class Employee extends User implements Serializable, java.io.Serializable{
    private static final long serialVersionUID = 1L;

    public Employee(String username, String password) {
        super(username, password);
    }

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
     * Display products with low stock (less than 5) sort by quantity
     */
    public void displayLowStockProducts() {
        System.out.println(Colors.NEON_BLUE + "Low stock products:" + Colors.RESET);
        for (byte i=0; i< Main.inventory.sortLowProducts().size();i++) {
            Product product = Main.inventory.sortLowProducts().get(i);
            System.out.println(product.getName() + ": " + product.getQuantity());
        }
    }
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



    public void saveData() {}
    public void loadData() {}
}