public class Employee extends User implements Serializable {
    public Employee(String username, String password) {
        super(username, password);
    }

    public void viewProductList() {}
    public void addProduct(Product product) {}
    public void removeProduct(String productName) {}
    public void recordOrder(Order order) {}
    public void viewLowStockProducts() {}
    public void viewOrderHistory() {}

    public void saveData() {}
    public void loadData() {}
}