import java.awt.*;
import java.util.Scanner;

/**
 * The PharmacyMenu class handles the interactive menu for a pharmacy system,
 * allowing users to log in, sign up, and access the system's main features based on their role.
 * It displays various menus and provides access to functionalities for Admin, Employee, and Client users.
 */
public class PharmacyMenu {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser = null;
    private static UserManager userManager = Main.userManager;

    /**
     * Displays the logo in the terminal with colors for a cyberpunk pharmacy theme.
     * The logo is composed of text art and is printed using predefined colors from the Colors class.
     */
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

    /**
     * Starts the pharmacy system by loading user data and presenting the login/signup options.
     * Continuously prompts the user to log in or sign up until a user is authenticated.
     */
    public static void start() {
        userManager.loadData();
        while (currentUser == null) {
            displayLogo();
            System.out.println(Colors.NEON_GREEN + "\nWelcome! Please choose an option:" + Colors.RESET);
            System.out.println(Colors.LIGHT_CYAN + "1 - Login" + Colors.RESET);
            System.out.println(Colors.LIGHT_CYAN + "2 - Signup" + Colors.RESET);
            System.out.print(Colors.BRIGHT_PURPLE + "Enter your choice: " + Colors.RESET);
            int choice = getValidInteger();

            switch (choice) {
                case 1 -> login();
                case 2 -> signup();
                default -> System.out.println(Colors.NEON_PINK + "Invalid option, please try again." + Colors.RESET);
            }
        }
        displayMenu();
    }

    /**
     * Prompts the user for their username and password to log in.
     * If the credentials match an existing user, the user is logged in and welcomed.
     * If the login fails, an error message is shown.
     */
    private static void login() {
        System.out.print(Colors.BRIGHT_PURPLE + "Enter username: " + Colors.RESET);
        String username = scanner.nextLine().trim();
        System.out.print(Colors.BRIGHT_PURPLE + "Enter password: " + Colors.RESET);
        String password = scanner.nextLine().trim();

        for (User user : userManager.getUsers()) {
            if (user.login(username, password)) {
                currentUser = user;
                System.out.println(Colors.NEON_GREEN + "Login successful! Welcome, " + username + "." + Colors.RESET);
                return;
            }
        }
        System.out.println(Colors.NEON_PINK + "Invalid username or password. Try again." + Colors.RESET);
    }

    /**
     * Prompts the user to choose a username, password, and role (Admin, Employee, or Client)
     * to sign up for a new account. The new user is then added to the user manager.
     * If the role is invalid, the signup fails.
     */
    private static void signup() {
        System.out.print(Colors.BRIGHT_PURPLE + "Choose a username: " + Colors.RESET);
        String username = scanner.nextLine().trim();
        System.out.print(Colors.BRIGHT_PURPLE + "Choose a password: " + Colors.RESET);
        String password = scanner.nextLine().trim();
        System.out.println(Colors.NEON_BLUE + "Select role: 1 - Admin, 2 - Employee, 3 - Client" + Colors.RESET);
        int role = getValidInteger();

        User newUser = switch (role) {
            case 1 -> new Admin(username, password);
            case 2 -> new Employee(username, password);
            case 3 -> new Client(username, password);
            default -> {
                System.out.println(Colors.NEON_PINK + "Invalid role. Signup failed." + Colors.RESET);
                yield null;
            }
        };

        if (newUser != null) {
            userManager.addUser(newUser);
            userManager.saveData();
            currentUser = newUser;
            System.out.println(Colors.NEON_GREEN + "Signup successful! You are now logged in." + Colors.RESET);
        }
    }

    /**
     * Displays the main menu based on the current user's role (Admin, Employee, or Client).
     * Provides access to different functionalities such as managing users, products, or placing orders.
     * The user can also log out from this menu.
     */
    public static void displayMenu() {
        Main.inventory.saveData();
        userManager.saveData();

        while (true) {
            //displayLogo();
            System.out.println(Colors.NEON_BLUE + "\n===== Main Menu =====" + Colors.RESET);
            if (currentUser instanceof Admin) {
                System.out.println(Colors.LIGHT_CYAN + "1 - Manage Users" + Colors.RESET);
                System.out.println(Colors.LIGHT_CYAN +"2 - Manage Products" + Colors.RESET);
                System.out.println(Colors.LIGHT_CYAN + "3 - Manage Orders" + Colors.RESET);
            } else if (currentUser instanceof Employee) {
                System.out.println(Colors.LIGHT_CYAN + "1 - Manage Products" + Colors.RESET);
                System.out.println(Colors.LIGHT_CYAN + "2 - Manage Orders" + Colors.RESET);
            } else if (currentUser instanceof Client) {
                System.out.println(Colors.LIGHT_CYAN + "1 - Make an order" + Colors.RESET);
                System.out.println(Colors.LIGHT_CYAN + "2 - Watch product list" + Colors.RESET);
                System.out.println(Colors.LIGHT_CYAN + "3 - Search product" + Colors.RESET);
            }
            System.out.println("9 - Logout" + Colors.RESET);
            System.out.print(Colors.BRIGHT_PURPLE + "Enter your choice: " + Colors.RESET);
            int choice = getValidInteger();

            if (choice == 9) {
                System.out.println(Colors.NEON_PINK + "Logging out..."+ Colors.RESET);
                currentUser = null;
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                start();
                return;
            }
            executeChoice(choice);
        }
    }

    /**
     * Executes the chosen action based on the user's role.
     * The options vary depending on whether the user is an Admin, Employee, or Client.
     * After performing the action, the menu is displayed again.
     *
     * @param choice the user's choice from the menu
     */
    private static void executeChoice(int choice) {
        if (currentUser instanceof Admin) {
            switch (choice) {
                case 1 -> manageUsers();
                case 2 -> manageProducts();
                case 3 -> manageOrders();
                default -> System.out.println(Colors.NEON_PINK + "Invalid option, please try again." + Colors.RESET);
            }
        } else if (currentUser instanceof Employee) {
            switch (choice) {
                case 1 -> manageProducts();
                case 2 -> manageOrders();
                default -> System.out.println(Colors.NEON_PINK  + "Invalid option, please try again." + Colors.RESET);
            }
        } else if (currentUser instanceof Client) {
            switch (choice) {
                case 1 -> currentUser.makeOrder();
                case 2 -> Main.inventory.displayProductList();
                case 3 -> Main.inventory.searchProductScanner();
                default -> System.out.println(Colors.NEON_PINK  + "Invalid option, please try again." + Colors.RESET);
            }
        }
    }

    /**
     * Allows the Admin user to manage other users (add or remove users).
     * Displays the user management options and prompts the Admin to choose an action.
     */
    private static void manageUsers() {
        displayLogo();
        System.out.println(Colors.LIGHT_CYAN + "1 - Add User" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "2 - Remove User" + Colors.RESET);
        int choice = getValidInteger();
        switch (choice) {
            case 1 -> ((Admin)currentUser). addUser(Main.userManager);
            case 2 -> ((Admin)currentUser).removeUser(Main.userManager);
            default -> System.out.println(Colors.NEON_PINK + "Invalid choice." + Colors.RESET);
        }
    }

    /**
     * Allows the Admin or Employee user to manage products (view stock, add, remove, or search products).
     * Displays the product management options and performs the selected action.
     */
    private static void manageProducts() {
        displayLogo();
        System.out.println(Colors.LIGHT_CYAN + "1 - Show Stock" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "2 - Add Item" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "3 - Remove Item" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "4 - Search" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "5 - Load delivery !" + Colors.RESET);
        int choice = getValidInteger();
        switch (choice) {
            case 1:
                Main.inventory.displayProductListInfo();
                break;
            case 2 :
                if (currentUser instanceof Employee){
                    ((Employee)currentUser).addProduct(Main.inventory);
                }else{
                    ((Admin)currentUser).addProduct(Main.inventory);
                }
                break;
            case 3 :
                if (currentUser instanceof Employee) {
                    ((Employee)currentUser).removeProduct(Main.inventory);
                }else{
                    ((Admin)currentUser).removeProduct(Main.inventory);
                }
                break;
            case 4:
                Main.inventory.searchProductScanner();
                break;
            case 5:
                Main.inventory.loadFromJSON("stocks_pharma.json");
                Main.inventory.saveData();
                Main.orderManager.saveData();
                Main.userManager.saveData();
                break;
            default:
                System.out.println(Colors.NEON_PINK + "Invalid choice." + Colors.RESET);
                break;
        }
    }


    private static void manageOrders() {
        displayLogo();
        System.out.println(Colors.LIGHT_CYAN + "1 - Display orders" + Colors.RESET);
        System.out.println(Colors.LIGHT_CYAN + "2 - Export sales history"+ Colors.RESET);
        int choice = getValidInteger();
        switch (choice) {
            case 1 :
                if (currentUser instanceof Employee){
                    ((Employee)currentUser).displayOrders();
                }else{
                    ((Admin)currentUser).displayOrders();
                }
                break;
            case 2 :
                Main.salesStatistics.setupCSV();
                break;
            default :
                System.out.println(Colors.NEON_PINK + "Invalid choice." + Colors.RESET);
                break;
        }
    }

    /**
     * Prompts the user to enter a valid integer input from the console.
     * Ensures that the input is a valid integer and returns it.
     *
     * @return a valid integer entered by the user
     */
    private static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print(Colors.NEON_PINK +"Invalid input. Please enter a number: " + Colors.RESET);
            }
        }
    }
}
