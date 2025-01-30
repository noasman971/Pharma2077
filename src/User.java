import java.util.Scanner;

public abstract class User implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    /**
     * This method allows the user to place an order by selecting products from the inventory.
     * The user can add multiple products with specified quantities and mark the order as a priority.
     */
    public void makeOrder() {
        Scanner sc = new Scanner(System.in);
        Order order = new Order();
        boolean isOrderComplete = false;

        System.out.println("Please enter your order:");

        while (!isOrderComplete) {
            try {
                System.out.println("\nChoose a product (or type 'done' to finish):");
                String inputNameProduct = sc.nextLine().trim();

                if (inputNameProduct.equalsIgnoreCase("done")) {
                    if (order.getProducts().isEmpty()) {
                        System.out.println("Cannot place an empty order. Please add at least one product.");
                        continue;
                    }
                    break;
                }

                // Recherche du produit dans l'inventaire
                Product stockProduct = Main.inventory.searchProduct(inputNameProduct);
                if (stockProduct == null) {
                    System.out.println("Product not found in inventory.");
                    continue;
                }

                System.out.println("Current stock for " + stockProduct.getName() + ": " + stockProduct.getQuantity());
                System.out.println("Enter quantity:");

                // Validation de la quantité
                int inputQuantity;
                try {
                    inputQuantity = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    continue;
                }

                if (inputQuantity <= 0) {
                    System.out.println("Please enter a positive quantity.");
                    continue;
                }

                if (stockProduct.getQuantity() < inputQuantity) {
                    System.out.println("Insufficient stock. Available: " + stockProduct.getQuantity());
                    continue;
                }

                // Création du produit pour la commande
                Product orderProduct = new Product(
                        stockProduct.getName(),
                        stockProduct.getPrice(),
                        inputQuantity,
                        stockProduct.getCategory()
                );

                // Ajout du produit à la commande
                order.addProduct(stockProduct, inputQuantity, orderProduct);

                // Enregistrement de la vente
                Sale sale = new Sale(orderProduct.getName(), orderProduct.getQuantity(), orderProduct.getPrice());
                SalesStatistics.addSale(sale);

                System.out.println(inputQuantity + " " + orderProduct.getName() + " added to the order.");

                // Affichage de la commande en cours
                System.out.println("\nCurrent order contents:");
                order.diplayProducts();
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                continue;
            }
        }

        // Gestion de la priorité
        while (true) {
            System.out.println("Is this order priority? (yes/no)");
            String priority = sc.nextLine().trim().toLowerCase();

            if (priority.equals("yes")) {
                order.setPriority(true);
                break;
            } else if (priority.equals("no")) {
                order.setPriority(false);
                break;
            } else {
                System.out.println("Please enter 'yes' or 'no'.");
            }
        }

        // Finalisation de la commande
        Main.orderManager.addOrder(order);
        System.out.println("Order placed successfully!");
        System.out.println("---Order summary---");
        order.diplayProducts();
    }
}