import java.util.ArrayList;
import java.util.List;

public abstract class Category {
    private String name;
    private String description;
    private List<Product> productList;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.productList = new ArrayList<Product>();
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public void searchProduct(String name) {
        for (Product product : productList) {
            System.out.println(product);
        }
    }
}

