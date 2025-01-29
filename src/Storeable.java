import java.util.ArrayList;
import java.util.List;

public interface Storeable {
    List<Category> products = new ArrayList<>();

    public void addItem(String name, Category category, float price, int quantity);
    public void removeItem(int id, String name, int quantity);
    public void displayStock();
    public void search(Product product);
    public void displayCriticalStock();


}
