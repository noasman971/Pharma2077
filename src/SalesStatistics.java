import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code SalesStatistics} class is responsible for managing sales data, generating statistics,
 * and creating a CSV file with the details of the sales, including the total quantity sold and revenue.
 * It keeps track of all sales and provides methods to add sales, calculate statistics, and generate a report.
 * <p>
 * The main features of this class include:
 * <ul>
 *     <li>Adding new sales</li>
 *     <li>Calculating statistics like total quantity and revenue per product</li>
 *     <li>Sorting products by quantity sold</li>
 *     <li>Generating a CSV file with the sales report</li>
 * </ul>
 * </p>
 *
 */
public class SalesStatistics implements Serializable, java.io.Serializable{
    private static final long serialVersionUID = 1L;
    private static final String SALES_FILE = "sales_statistics.ser";
    private static List<Sale> sales = new ArrayList<>();  // Keeping track of all sales

    /**
     * Sets up the CSV file by gathering sales statistics.
     * This method calculates the total quantity and revenue per product, sorts the products by quantity sold,
     * and generates the CSV file with the sales data.
     * <p>
     * If no sales have been made, the method prints a message and terminates the execution.
     * </p>
     */
    public void setupCSV() {

        if (sales.isEmpty()) {
            System.out.println(Colors.CYBER_YELLOW + "Nothing here. End of execution." + Colors.RESET);
            return;  // Cancels execution if no sales have been recorded
        }

        // Storing statistics in a HashMap
        Map<String, double[]> stats = new HashMap<>();

        // Example sales data
        for (Sale sale : sales) {
            String productKey = sale.product;
            if (!stats.containsKey(productKey)) {
                stats.put(productKey, new double[]{0, 0});
            }
            double[] values = stats.get(productKey);
            values[0] += sale.quantity;  // Total quantity
            values[1] += sale.getTotalPrice();  // Revenue
        }

        // Convert HashMap to list for sorting
        List<String> products = new ArrayList<>(stats.keySet());
        int[] quantities = new int[products.size()];
        double[] revenues = new double[products.size()];

        for (int i = 0; i < products.size(); i++) {
            double[] values = stats.get(products.get(i));
            quantities[i] = (int) values[0];
            revenues[i] = values[1];
        }

        // Sort products by quantity sold (Insertion Sort)
        insertionSort(products, quantities, revenues);

        // Generate the CSV file
        generateCSV(products, quantities, revenues, "sales.csv");
    }

    /**
     * Adds a sale to the list of recorded sales.
     *
     * @param sale the sale to be added
     */
    public static void addSale(Sale sale) {
        // Adding sale to the static list of sales
        sales.add(sale);
    }

    /**
     * Sorts products by quantity sold using the Insertion Sort algorithm.
     * This method sorts the products based on the quantities sold in descending order.
     *
     * @param products List of products to sort
     * @param quantities Corresponding quantities of the products
     * @param revenues Corresponding revenues of the products
     */
    public static void insertionSort(List<String> products, int[] quantities, double[] revenues) {
        int n = quantities.length;
        for (int i = 1; i < n; i++) {
            int keyQuantity = quantities[i];
            double keyRevenue = revenues[i];
            String keyProduct = products.get(i);

            int j = i - 1;
            while (j >= 0 && quantities[j] < keyQuantity) {
                quantities[j + 1] = quantities[j];
                revenues[j + 1] = revenues[j];
                products.set(j + 1, products.get(j));
                j--;
            }

            quantities[j + 1] = keyQuantity;
            revenues[j + 1] = keyRevenue;
            products.set(j + 1, keyProduct);
        }
    }

    /**
     * Generates a CSV file with the sales statistics, including the product names, quantities sold,
     * revenues, and additional summary information such as total sales and best-selling product.
     *
     * @param products List of product names
     * @param quantities Corresponding quantities sold
     * @param revenues Corresponding revenues from the sales
     * @param file The name of the CSV file to generate
     */
    public static void generateCSV(List<String> products, int[] quantities, double[] revenues, String file) {
        try (FileWriter writer = new FileWriter(file)) {
            writer.write("----------------------------------------------------\n");
            writer.write("                      INVOICE\n");
            writer.write("----------------------------------------------------\n");
            writer.write(String.format("%-15s %-15s %-15s\n", "Product", "Quantity", "Revenue"));
            writer.write("----------------------------------------------------\n");

            double totalRevenue = 0;

            for (int i = 0; i < products.size(); i++) {
                totalRevenue += revenues[i];
                writer.write(String.format("%-20s %-15d %-15s\n", products.get(i), quantities[i], String.format("%.2f €", revenues[i])));
            }

            writer.write("----------------------------------------------------\n");
            writer.write(String.format("%-35s %-5s %-15s\n", "Total sales (Revenue):", String.format("%.2f", totalRevenue), "€"));
            writer.write("----------------------------------------------------\n");
            writer.write(String.format("%-35s %-25s \n", "Best-selling product:", products.get(0)));
            writer.write("----------------------------------------------------\n");

            System.out.println(Colors.CYBER_YELLOW + "📁 CSV file generated: " + file + Colors.RESET);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    @Override
    public void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SALES_FILE))) {
            oos.writeObject(sales);
            System.out.println(Colors.CYBER_YELLOW + "📁 Sales data successfully saved." + Colors.RESET);
        } catch (IOException e) {
            System.err.println(Colors.NEON_PINK + "Error saving sales data: " + e.getMessage() + Colors.RESET);
        }
    }

    @Override
    public void loadData() {
        File salesFile = new File(SALES_FILE);
        if (!salesFile.exists()) {
            System.out.println(Colors.CYBER_YELLOW + "No saved sales data found." + Colors.RESET);
            return;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SALES_FILE))) {
            sales = (List<Sale>) ois.readObject();
            System.out.println(Colors.CYBER_YELLOW + "📁 Sales data successfully loaded." + Colors.RESET);
        } catch (IOException | ClassNotFoundException e) {
            System.err.println(Colors.NEON_PINK + "Error loading sales data: " + e.getMessage() + Colors.RESET);
        }
    }
}
