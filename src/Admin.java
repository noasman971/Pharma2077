import java.util.Scanner;

public class Admin extends User implements java.io.Serializable{
    private static final long serialVersionUID = 1L;

    public Admin(String username, String password) {
        super(username, password);
    }

    public void addUser(UserManager userManager) {
        Scanner scanner = new Scanner(System.in);
        String username;
        String password;

        while (true) {
            try {
                System.out.print("Enter new employee username: ");
                username = scanner.nextLine().trim();
                if (username.isEmpty()) {
                    System.out.println("Username cannot be empty. Try again.");
                    continue;
                }

                System.out.print("Enter new employee password: ");
                password = scanner.nextLine().trim();
                if (password.isEmpty()) {
                    System.out.println("Password cannot be empty. Try again.");
                    continue;
                }

                Employee newEmployee = new Employee(username, password);
                userManager.addUser(newEmployee);
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                System.out.println("Employee " + username + " added successfully.");
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
    }
    public void removeUser(UserManager userManager) {
        Scanner scanner = new Scanner(System.in);
        String username;

        while (true) {
            try {
                System.out.print("Enter username of employee to remove: ");
                username = scanner.nextLine().trim();
                if (username.isEmpty()) {
                    System.out.println("Username cannot be empty. Try again.");
                    continue;
                }

                String finalUsername = username;
                if (userManager.getUsers().stream().noneMatch(user -> user.getUsername().equalsIgnoreCase(finalUsername))) {
                    System.out.println("User " + username + " not found. Try again.");
                    continue;
                }

                userManager.removeUser(username);
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
            }
        }
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
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
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
                    System.out.println("Product name cannot be empty. Try again.");
                    continue;
                }

                Product product = inventory.searchProduct(productName);
                if (product == null) {
                    System.out.println("Product not found in inventory. Try again.");
                    continue;
                }

                System.out.print("Enter quantity to remove: ");
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity <= 0) {
                    System.out.println("Quantity must be greater than zero. Try again.");
                    continue;
                }
                if (quantity > product.getQuantity()) {
                    System.out.println("Not enough stock to remove that amount. Try again.");
                    continue;
                }

                product.setQuantity(product.getQuantity() - quantity);
                System.out.println("Product quantity updated successfully.");
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number for quantity.");
            }
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
}