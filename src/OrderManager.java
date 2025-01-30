import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class OrderManager implements Serializable, java.io.Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ORDER_FILE = "orders.ser";
    public List<Order> orders = new ArrayList<>();

    public void addOrder(Order orderAdded){
        orders.add(orderAdded);
        Main.inventory.saveData();
        Main.orderManager.saveData();
        Main.userManager.saveData();
    }

    public void sortOrders() {
        orders.sort(Comparator.comparing(Order::isPriority, Comparator.reverseOrder())
                .thenComparing(Order::getOrderDate));
    }



    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDER_FILE))) {
            oos.writeObject(orders);
            System.out.println("Orders successfully saved.");
        } catch (IOException e) {
            System.err.println("Error saving orders: " + e.getMessage());
        }
    }

    @Override
    public void loadData() {
        File orderFile = new File(ORDER_FILE);
        if (!orderFile.exists()) {
            System.out.println("No saved orders found.");
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDER_FILE))) {
            orders = (List<Order>) ois.readObject();
            System.out.println("Orders successfully loaded from saved file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading orders: " + e.getMessage());
        }
    }
}

