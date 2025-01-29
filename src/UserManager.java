import java.io.*;
import java.util.ArrayList;
import java.util.List;

class UserManager implements Serializable {
    private static final String USER_FILE = "user.ser";
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
            oos.writeObject(users);
            System.out.println("Users successfully saved.");
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    @Override
    public void loadData() {
        File userFile = new File(USER_FILE);
        if (!userFile.exists()) {
            System.out.println("No saved users found, creating default users.");
            users.add(new Admin("admin1", "ad"));
            users.add(new Employee("employee1", "em"));
            users.add(new Client("client1", "cl"));
            saveData();
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {
            users = (List<User>) ois.readObject();
            System.out.println("Users successfully loaded from saved file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
    }
}