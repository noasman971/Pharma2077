import java.util.Stack;

public class Employee extends Users implements Storeable{

    public Employee(String name, String password, String role) {
        super(name, password, role);
    }



    @Override
    public String addUser(String username, String password, String role) {
        return "";
    }

    @Override
    public String removeUser(String username) {
        return "";
    }

    @Override
    public void addItem(String name, Category category, float price, int quantity) {

    }

    @Override
    public void removeItem(int id, String name, int quantity) {

    }


    @Override
    public void displayStock() {

    }

    @Override
    public void search(Product product) {

    }

    @Override
    public void displayCriticalStock() {

    }
}
