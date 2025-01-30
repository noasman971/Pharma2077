import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The {@code OrderManager} class manages the orders in the system.
 * It provides functionality to add orders, sort orders, and save/load the order list to/from a file.
 * The class implements {@link java.io.Serializable} to allow the object to be serialized and deserialized.
 *
 * <p>
 * The order list is stored in a file named {@code orders.ser}, and methods are provided for managing
 * the orders, including adding, sorting, saving, and loading orders.
 * </p>
 *
 */
class OrderManager implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String ORDER_FILE = "orders.ser";
    public List<Order> orders = new ArrayList<>();

    /**
     * Adds an order to the list of orders and saves the data.
     *
     * @param orderAdded the order to be added
     */
    public void addOrder(Order orderAdded){
        orders.add(orderAdded);
        Main.inventory.saveData();
        Main.orderManager.saveData();
        Main.userManager.saveData();
    }

    /**
     * Sorts the orders by priority in reverse order and by order date.
     * The priority is sorted first in descending order, followed by sorting by order date.
     */
    public void sortOrders() {
        orders.sort(Comparator.comparing(Order::isPriority, Comparator.reverseOrder())
                .thenComparing(Order::getOrderDate));
    }

    /**
     * Saves the current list of orders to the {@code orders.ser} file.
     * The orders are serialized into the file for persistent storage.
     */
    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDER_FILE))) {
            oos.writeObject(orders);
            System.out.println(Colors.NEON_GREEN + "Orders successfully saved." + Colors.RESET);
        } catch (IOException e) {
            System.err.println("Error saving orders: " + e.getMessage());
        }
    }

    /**
     * Loads the list of orders from the {@code orders.ser} file.
     * If the file does not exist, a message is printed indicating that no saved orders were found.
     */
    @Override
    public void loadData() {
        File orderFile = new File(ORDER_FILE);
        if (!orderFile.exists()) {
            System.out.println(Colors.NEON_PINK + "No saved orders found." + Colors.RESET);
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ORDER_FILE))) {
            orders = (List<Order>) ois.readObject();
            System.out.println(Colors.NEON_BLUE + "Orders successfully loaded from saved file." + Colors.RESET);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading orders: " + e.getMessage());
        }
    }
}
