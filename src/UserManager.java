import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The {@code UserManager} class manages a list of users, provides functionality to add,
 * remove, and load users from a file, and supports saving user data.
 * This class implements {@code Serializable} to allow saving and loading of user data.
 */
class UserManager implements Serializable {
    private static final String USER_FILE = "user.ser";
    private List<User> users = new ArrayList<>();
    private User currentUser = null;

    /**
     * Adds a new user to the list of users.
     *
     * @param user The {@code User} object to be added.
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Removes a user from the list based on their username.
     *
     * @param username The username of the user to be removed.
     */
    public void removeUser(String username) {
        users.removeIf(user -> user.getUsername().equalsIgnoreCase(username));
    }

    /**
     * Returns the list of all users.
     *
     * @return A {@code List<User>} containing all users.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Saves the current list of users to a file using object serialization.
     * If the save is successful, a message will be printed in {@link Colors#NEON_BLUE}.
     * If there is an error during saving, an error message will be printed in {@link Colors#NEON_PINK}.
     */
    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
            System.out.println(Colors.NEON_BLUE + "Users successfully saved." + Colors.RESET);
        } catch (IOException e) {
            System.err.println(Colors.NEON_PINK + "Error saving users: " + e.getMessage() + Colors.RESET);
        }
    }

    /**
     * Loads the list of users from a file. If no saved users are found, default users will be created
     * and saved. Upon successful loading, a message will be printed in {@link Colors#NEON_BLUE}.
     * If there is an error during loading, an error message will be printed in {@link Colors#NEON_PINK}.
     */
    @Override
    public void loadData() {
        File userFile = new File(USER_FILE);
        if (!userFile.exists()) {
            System.out.println(Colors.NEON_PINK + "No saved users found, creating default users." + Colors.RESET);
            users.add(new Admin("admin1", "ad"));
            users.add(new Employee("employee1", "em"));
            users.add(new Client("client1", "cl"));
            saveData();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            users = (List<User>) ois.readObject();
            System.out.println(Colors.NEON_BLUE + "Users successfully loaded from saved file." + Colors.RESET);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(Colors.NEON_PINK + "Error loading users: " + e.getMessage() + Colors.RESET);
        }
    }

    // Uncomment this method if needed for future user authentication handling.
    /*
    public void authenticateUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome! Choose an option:");
        System.out.println("1. Login");
        System.out.println("2. Sign up");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 2) {
            System.out.print("Enter new username: ");
            String username = scanner.nextLine();
            System.out.print("Enter new password: ");
            String password = scanner.nextLine();
            Client newUser = new Client(username, password);
            addUser(newUser);
            saveData();
            currentUser = newUser;
            System.out.println("Sign-up successful! Logged in as " + username);
        } else {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            for (User user : users) {
                if (user.login(username, password)) {
                    currentUser = user;
                    System.out.println("Login successful! Welcome " + username);
                    return;
                }
            }
            System.out.println("Invalid credentials");

            // Reset and show logo
            PharmacyMenu.displayLogo();
            authenticateUser();
        }
    }
    */
}
