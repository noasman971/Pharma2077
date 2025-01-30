import java.util.Scanner;

public class PharmacyMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static UserManager userManager = Main.userManager;

    public static void displayLogo() {
        System.out.println(Colors.BRIGHT_PURPLE +
                           "                             " + Colors.MEDICAL_CYAN + "$$$$$$$" + Colors.BRIGHT_PURPLE + "\\  " + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\                                                    " + Colors.MEDICAL_CYAN + "$$$$$$" + Colors.BRIGHT_PURPLE + "\\   " + Colors.MEDICAL_CYAN + "$$$$$$" + Colors.BRIGHT_PURPLE + "\\  " + Colors.MEDICAL_CYAN + "$$$$$$$$" + Colors.BRIGHT_PURPLE + "\\ " + Colors.MEDICAL_CYAN + "$$$$$$$$" + Colors.BRIGHT_PURPLE + "\\       ");
        System.out.println("                             " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\ " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|                                                  " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\ " + Colors.MEDICAL_CYAN + "$$$ " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\ \\____" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "|\\____" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "|      ");
        System.out.println("                             " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|  " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$$$$$$" + Colors.BRIGHT_PURPLE + "\\   " + Colors.MEDICAL_CYAN + "$$$$$$" + Colors.BRIGHT_PURPLE + "\\   " + Colors.MEDICAL_CYAN + "$$$$$$" + Colors.BRIGHT_PURPLE + "\\  " + Colors.MEDICAL_CYAN + "$$$$$$" + Colors.BRIGHT_PURPLE + "\\" + Colors.MEDICAL_CYAN + "$$$$\\   " + Colors.MEDICAL_CYAN + "$$$$$$" + Colors.BRIGHT_PURPLE + "\\  \\__/  " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$$$" + Colors.BRIGHT_PURPLE + "\\ " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|    " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/     " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/       ");
        System.out.println("                             " + Colors.MEDICAL_CYAN + "$$$$$$$  " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\  \\____" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\ " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\ " + Colors.MEDICAL_CYAN + "$$  _" + Colors.MEDICAL_CYAN + "$$  _" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\  \\____" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\  " + Colors.MEDICAL_CYAN + "$$$$$$  " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\" + Colors.MEDICAL_CYAN + "$$" + Colors.BRIGHT_PURPLE + "\\" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|   " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/     " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/        ");
        System.out.println("                             " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "____/ " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|  " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$$$$$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|  " + Colors.BRIGHT_PURPLE + "\\__|" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "/ " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "/ " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$$$$$$ |" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "____/ " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "\\" + Colors.MEDICAL_CYAN + "$$$$ |  " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/     " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/         ");
        System.out.println("                             " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|      " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|  " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|      " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "__" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|      " + Colors.MEDICAL_CYAN + "$$ |\\" + Colors.MEDICAL_CYAN + "$$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/     " + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/          ");
        System.out.println("                             " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|      " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|  " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|\\" + Colors.MEDICAL_CYAN + "$$$$$$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|      " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "| " + Colors.MEDICAL_CYAN + "$$ " + Colors.BRIGHT_PURPLE + "|\\" + Colors.MEDICAL_CYAN + "$$$$$$$ " + Colors.BRIGHT_PURPLE + "|" + Colors.MEDICAL_CYAN + "$$$$$$$$" + Colors.BRIGHT_PURPLE + "\\ \\" + Colors.MEDICAL_CYAN + "$$$$$$  /" + Colors.MEDICAL_CYAN + "$$  " + Colors.BRIGHT_PURPLE + "/    " + Colors.MEDICAL_CYAN + " $$  " + Colors.BRIGHT_PURPLE + "/           ");
        System.out.println("                             \\__|      \\__|  \\__| \\_______|\\__|      \\__| \\__| \\__| \\_______|\\________| \\______/ \\__/      \\__/            ");
        System.out.println(Colors.RESET);
    }

    public static void start() {
        userManager.loadData();
        while (currentUser == null) {
            displayLogo();
            System.out.println("\nWelcome! Please choose an option:");
            System.out.println("1 - Login");
            System.out.println("2 - Signup");
            System.out.print("Enter your choice: ");
            int choice = getValidInteger();

            switch (choice) {
                case 1 -> login();
                case 2 -> signup();
                default -> System.out.println("Invalid option, please try again.");
            }
        }
        displayMenu();
    }

    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        for (User user : userManager.getUsers()) {
            if (user.login(username, password)) {
                currentUser = user;
                System.out.println("Login successful! Welcome, " + username + ".");
                return;
            }
        }
        System.out.println("Invalid username or password. Try again.");
    }

    private static void signup() {
        System.out.print("Choose a username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Choose a password: ");
        String password = scanner.nextLine().trim();
        System.out.println("Select role: 1 - Admin, 2 - Employee, 3 - Client");
        int role = getValidInteger();

        User newUser = switch (role) {
            case 1 -> new Admin(username, password);
            case 2 -> new Employee(username, password);
            case 3 -> new Client(username, password);
            default -> {
                System.out.println("Invalid role. Signup failed.");
                yield null;
            }
        };

        if (newUser != null) {
            userManager.addUser(newUser);
            userManager.saveData();
            currentUser = newUser;
            System.out.println("Signup successful! You are now logged in.");
        }
    }

    public static void displayMenu() {
        while (true) {
            displayLogo();
            System.out.println("\n===== Main Menu =====");
            if (currentUser instanceof Admin) {
                System.out.println("1 - Manage Users");
                System.out.println("2 - Manage Products");
                System.out.println("3 - Manage Orders");
            } else if (currentUser instanceof Employee) {
                System.out.println("1 - Manage Products");
                System.out.println("2 - Manage Orders");
            } else if (currentUser instanceof Client) {
                System.out.println("1 - Place Order");
                System.out.println("2 - View Orders");
            }
            System.out.println("9 - Logout");
            System.out.print("Enter your choice: ");
            int choice = getValidInteger();

            if (choice == 9) {
                System.out.println("Logging out...");
                currentUser = null;
                start();
                return;
            }
            executeChoice(choice);
        }
    }

    private static void executeChoice(int choice) {
        if (currentUser instanceof Admin) {
            switch (choice) {
                case 1 -> manageUsers();
                case 2 -> manageProducts();
                case 3 -> manageOrders();
                default -> System.out.println("Invalid option, please try again.");
            }
        } else if (currentUser instanceof Employee) {
            switch (choice) {
                case 1 -> manageProducts();
                case 2 -> manageOrders();
                default -> System.out.println("Invalid option, please try again.");
            }
        } else if (currentUser instanceof Client) {
            switch (choice) {
                case 1 -> System.out.println("order");//placeOrder();
                case 2 -> System.out.println("vieworder");//viewOrders();
                default -> System.out.println("Invalid option, please try again.");
            }
        }
    }

    private static void manageUsers() {
        displayLogo();
        System.out.println("1 - Add User");
        System.out.println("2 - Remove User");
        int choice = getValidInteger();
        switch (choice) {
            case 1 -> System.out.println("Adding user...");
            case 2 -> System.out.println("Removing user...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void manageProducts() {
        displayLogo();
        System.out.println("1 - Update Stock");
        System.out.println("2 - Add Item");
        System.out.println("3 - Remove Item");
        int choice = getValidInteger();
        switch (choice) {
            case 1 -> System.out.println("Updating stock...");
            case 2 -> System.out.println("Adding item...");
            case 3 -> System.out.println("Removing item...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void manageOrders() {
        displayLogo();
        System.out.println("1 - Display Orders");
        System.out.println("2 - Display Order History");
        int choice = getValidInteger();
        switch (choice) {
            case 1 -> System.out.println("Displaying orders...");
            case 2 -> System.out.println("Displaying order history...");
            default -> System.out.println("Invalid choice.");
        }
    }

    private static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
