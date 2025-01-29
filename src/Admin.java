public class Admin extends Users{

    public Admin(String name, String password, String role) {
        super(name, password, role);
    }

    public void manageRole(String nom, String role) {
        //
    }

    public void newCategory(String nom, String category) {
        //
    }

    @Override
    public String addUser(String name, String password, String role) {
        return "";
    }

    @Override
    public String removeUser(String name) {
        return "";
    }
}
