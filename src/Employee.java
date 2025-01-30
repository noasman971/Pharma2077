import java.util.Scanner;

public class Employee extends User implements Serializable {
    public Employee(String username, String password) {
        super(username, password);
    }

    public void viewProductList() {}
    public void addProduct(Inventory inventory) {
        Scanner scanner = new Scanner(System.in);
        String productName;
        int quantity;

        while (true) {
            try {
                System.out.print("Enter product name: ");
                productName = scanner.nextLine().trim();
                if (productName.isEmpty()) {
                    System.out.println("Product name cannot be empty. Try again.");
                    continue;
                }

                Product product = inventory.searchProduct(productName);
                if (product == null) {
                    System.out.println("Product not found in inventory. Try again.");
                    continue;
                }

                System.out.print("Enter quantity to add: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than zero. Try again.");
                    continue;
                }

                product.setQuantity(product.getQuantity() + quantity);
                System.out.println("Product quantity updated successfully.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
            }
        }
    }
    public void removeProduct(String productName) {}
    public void recordOrder(Order order) {}
    public void viewLowStockProducts() {}
    public void viewOrderHistory() {}

    public void saveData() {}
    public void loadData() {}
}