import java.util.List;

public abstract class Users {
    private String name;
    private String password;
    private String role;

    public Users(String name, String password, String role) {
        this.name = name;
        this.password = password;
        this.role = role;
    }

    public void createCommand(Product product){
        System.out.println("Creating command");
    }

    public abstract String addUser(String username, String password, String role);
    public abstract String removeUser(String username);


}
