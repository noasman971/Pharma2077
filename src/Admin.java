public class Admin extends User implements Serializable {
    public Admin(String username, String password) {
        super(username, password);
    }

    public void addUser(Employee employee) {
        System.out.println("User " + employee.username + " added.");
    }

    public void removeUser(String username) {
        System.out.println("User " + username + " removed.");
    }

    public void saveData() {}
    public void loadData() {}
}