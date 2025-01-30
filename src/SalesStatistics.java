import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Main class to generate sales statistics and a CSV file.
 */
public class SalesStatistics {

    private static List<Sale> sales = new ArrayList<>();  // Keeping track of all sales

    public void setupCSV() {

        if (sales.isEmpty()) {
            System.out.println(Colors.CYBER_YELLOW + "Nothing here. End of execution." + Colors.RESET);
            return;  // Annuler l'exécution si la liste des ventes est vide
        }
        // Storing statistics in a HashMap
        Map<String, double[]> stats = new HashMap<>();

        // Example sales data
        for (Sale sale : sales) {
            if (!stats.containsKey(sale.product)) {
                stats.put(sale.product, new double[]{0, 0});
            }
            double[] values = stats.get(sale.product);
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

    public static void addSale(Sale sale) {
        // Adding sale to the static list of sales
        sales.add(sale);
    }

    /**
     * Sorts products by quantity sold using the Insertion Sort algorithm.
     *
     * @param products List of products.
     * @param quantities Corresponding quantities of the products.
     * @param revenues Corresponding revenues of the products.
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
     * Generates a CSV file with the sales statistics.
     *
     * @param products List of products.
     * @param quantities Corresponding quantities of the products.
     * @param revenues Corresponding revenues of the products.
     * @param file The name of the CSV file to generate.
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
}
