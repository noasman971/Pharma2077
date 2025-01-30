import java.util.Scanner;

public class PharmacyMenu {
    private static Scanner scanner = new Scanner(System.in);


    public static void displayMenu() {
        while (true) {
            System.out.println("");
            System.out.println("\u001B[33m                             $$$$$$$\\  $$\\                                                    $$$$$$\\   $$$$$$\\  $$$$$$$$\\ $$$$$$$$\\       \n" +
                    "                             $$  __$$\\ $$ |                                                  $$  __$$\\ $$$ __$$\\ \\____$$  |\\____$$  |      \n" +
                    "                             $$ |  $$ |$$$$$$$\\   $$$$$$\\   $$$$$$\\  $$$$$$\\$$$$\\   $$$$$$\\  \\__/  $$ |$$$$\\ $$ |    $$  /     $$  /       \n" +
                    "                             $$$$$$$  |$$  __$$\\  \\____$$\\ $$  __$$\\ $$  _$$  _$$\\  \\____$$\\  $$$$$$  |$$\\$$\\$$ |   $$  /     $$  /        \n" +
                    "                             $$  ____/ $$ |  $$ | $$$$$$$ |$$ |  \\__|$$ / $$ / $$ | $$$$$$$ |$$  ____/ $$ \\$$$$ |  $$  /     $$  /         \n" +
                    "                             $$ |      $$ |  $$ |$$  __$$ |$$ |      $$ | $$ | $$ |$$  __$$ |$$ |      $$ |\\$$$ | $$  /     $$  /          \n" +
                    "                             $$ |      $$ |  $$ |\\$$$$$$$ |$$ |      $$ | $$ | $$ |\\$$$$$$$ |$$$$$$$$\\ \\$$$$$$  /$$  /     $$  /           \n" +
                    "                             \\__|      \\__|  \\__| \\_______|\\__|      \\__| \\__| \\__| \\_______|\\________| \\______/ \\__/      \\__/            \n" +
                    "                                                                                                              \u001B[0m" );
            System.out.println("\u001B[33m                                                                 =========== Main Menu =========== \u001B[0m");
            System.out.println("                                                                     \u001B[36m[1]\u001B[0m Product Management ");
            System.out.println("                                                                     \u001B[36m[2]\u001B[0m Order Management");
            System.out.println("                                                                     \u001B[36m[3]\u001B[0m User Management");
            System.out.println("                                                                     \u001B[36m[4]\u001B[0m Advanced Management");
            System.out.println("                                                                     \u001B[31m[5]\u001B[0m Exit\u001B[0m");
            System.out.println("                                                                 \u001B[33m================================= \u001B[0m");
            System.out.println();
            System.out.print("                                                                         Choose an option: ");
            int choice = getValidInteger();




            switch (choice) {
                case 1:
                    manageProducts();
                    break;
                case 2:
                    manageOrders();
                    break;
                case 3:
                    manageUsers();
                    break;
                case 4:
                    advancedManagement();
                    break;
                case 5:
                    System.out.println("\u001B[31m /$$$$$$$$ /$$   /$$ /$$$$$$ /$$$$$$$$         /$$   \n" +
                            "| $$_____/| $$  / $$|_  $$_/|__  $$__/        |  $$  \n" +
                            "| $$      |  $$/ $$/  | $$     | $$            \\  $$ \n" +
                            "| $$$$$    \\  $$$$/   | $$     | $$    /$$$$$$  \\  $$\n" +
                            "| $$__/     >$$  $$   | $$     | $$   |______/   /$$/\n" +
                            "| $$       /$$/\\  $$  | $$     | $$             /$$/ \n" +
                            "| $$$$$$$$| $$  \\ $$ /$$$$$$   | $$            /$$/  \n" +
                            "|________/|__/  |__/|______/   |__/           |__/ \u001B[0m");

                    System.out.println();
                    System.out.println("Closing the application...");
                    return;
                default:
                    System.out.println("Invalid option, please try again.");

            }
        }
    }
    private static int getValidInteger() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim()); // Lire et convertir en int
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.print("                                                                 \u001B[31mInvalid input. Please enter a number: \u001B[0m");

            }
        }
    }
    private static void manageProducts() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[33m                                                                   ------ Product Management ------\u001B[0m");
            System.out.println("                                                                     \u001B[36m[1]\u001B[0m View product list");
            System.out.println("                                                                     \u001B[36m[2]\u001B[0m Add a product");
            System.out.println("                                                                     \u001B[36m[3]\u001B[0m Remove a product");
            System.out.println("                                                                     \u001B[36m[4]\u001B[0m Search for a product");
            System.out.println("                                                                     \u001B[38;5;214m[5]\u001B[0m Back to Main Menu\u001B[0m");
            System.out.println("\u001B[33m                                                                   ---------------------------------\u001B[0m");
            System.out.print("                                                                         Choose an option: ");
            int choice = getValidInteger();
            if (choice == 5) return;
        }


        // Add associated functionalities
    }

    private static void manageOrders() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[33m                                                                   ------ Order Management -------\u001B[0m");
            System.out.println("                                                                     \u001B[36m[1]\u001B[0m Record an order");
            System.out.println("                                                                     \u001B[36m[2]\u001B[0m Display order history");
            System.out.println("                                                                     \u001B[38;5;214m[3]\u001B[0m Back to Main Menu\u001B[0m");
            System.out.println("\u001B[33m                                                                   -------------------------------\u001B[0m");
            System.out.print("                                                                         Choose an option: ");
            int choice = getValidInteger();
            if (choice == 3) return;
        }


        // Add associated functionalities
    }

    private static void manageUsers() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[33m                                                                   ------ User Management --------\u001B[0m");
            System.out.println("                                                                     \u001B[36m[1]\u001B[0m Add a user");
            System.out.println("                                                                     \u001B[36m[2]\u001B[0m Remove a user");
            System.out.println("                                                                     \u001B[36m[3]\u001B[0m Log in");
            System.out.println("                                                                     \u001B[38;5;214m[4]\u001B[0m Back to Main Menu\u001B[0m");
            System.out.println("\u001B[33m                                                                   -------------------------------\u001B[0m");
            System.out.print("                                                                         Choose an option: ");
            int choice = getValidInteger();
            if (choice == 4) return;
        }


        // Add associated functionalities
    }

    private static void advancedManagement() {
        while (true) {
            System.out.println();
            System.out.println("\u001B[33m                                                                   ------ Advanced Management ------\u001B[0m");
            System.out.println("                                                                     \u001B[36m[1]\u001B[0m Display low stock products");
            System.out.println("                                                                     \u001B[36m[2]\u001B[0m Save/Load data");
            System.out.println("                                                                     \u001B[36m[3]\u001B[0m Export statistics");
            System.out.println("                                                                     \u001B[38;5;214m[4]\u001B[0m Back to Main Menu\u001B[0m");
            System.out.println("\u001B[33m                                                                   ---------------------------------\u001B[0m");
            System.out.print("                                                                         Choose an option: ");
            int choice = getValidInteger();
            if (choice == 4) return;
        }


        // Add associated functionalities
    }


}
