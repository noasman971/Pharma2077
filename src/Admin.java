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
                System.out.print(Colors.BRIGHT_PURPLE + "Enter new employee username: " + Colors.RESET);
                username = scanner.nextLine().trim();
                if (username.isEmpty()) {
                    System.out.println(Colors.NEON_PINK + "Username cannot be empty. Try again." + Colors.RESET);
                    continue;
                }

                System.out.print(Colors.BRIGHT_PURPLE + "Enter new employee password: " + Colors.RESET);
                password = scanner.nextLine().trim();
                if (password.isEmpty()) {
                    System.out.println(Colors.NEON_PINK + "Password cannot be empty. Try again." + Colors.RESET);
                    continue;
                }

                Employee newEmployee = new Employee(username, password);
                userManager.addUser(newEmployee);
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                System.out.println(Colors.CYBER_YELLOW + "Employee " + username + " added successfully." + Colors.RESET);
                break;
            } catch (Exception e) {
                System.out.println(Colors.NEON_PINK + "An error occurred. Please try again." + Colors.RESET);
            }
        }
    }
    public void removeUser(UserManager userManager) {
        Scanner scanner = new Scanner(System.in);
        String username;

        while (true) {
            try {
                System.out.print(Colors.BRIGHT_PURPLE + "Enter username of employee to remove: " + Colors.RESET);
                username = scanner.nextLine().trim();
                if (username.isEmpty()) {
                    System.out.println(Colors.NEON_PINK + "Username cannot be empty. Try again." + Colors.RESET);
                    continue;
                }

                String finalUsername = username;
                if (userManager.getUsers().stream().noneMatch(user -> user.getUsername().equalsIgnoreCase(finalUsername))) {
                    System.out.println(Colors.NEON_PINK + "User " + username + " not found. Try again." + Colors.RESET);
                    continue;
                }

                userManager.removeUser(username);
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            } catch (Exception e) {
                System.out.println(Colors.NEON_PINK + "An error occurred. Please try again." + Colors.RESET);
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