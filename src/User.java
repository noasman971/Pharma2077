import java.util.Scanner;

public abstract class User implements java.io.Serializable{
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        System.out.println(username + " has logged out.");
    }

    /**
     * Verification if it's possible to order
     * Do an order
     */
    public void placeOrder() {
        boolean choice = false;
        Order order = new Order();
        Scanner sc = new Scanner(System.in);

        System.out.println("Please enter the following order:");

        while (true) {
            System.out.println("Choose a product (or type 'done' to finish):");
            String inputNameProduct = sc.nextLine();

            if (inputNameProduct.equals("done")) {
                choice = true;
                break;
            }
            System.out.println("Choose a quantity:");
            byte inputQuantity = sc.nextByte();
            sc.nextLine();


            Product produit1 = new Product(Main.inventory.searchProduct(inputNameProduct).getName(), Main.inventory.searchProduct(inputNameProduct).getPrice(), inputQuantity, Main.inventory.searchProduct(inputNameProduct).getCategory());
            Product stockProduct = Main.inventory.searchProduct(inputNameProduct);


            if (stockProduct == null) {
                System.out.println("Product not found in inventory.");
                continue;
            }


            if (inputQuantity <= 0) {
                System.out.println("Please enter a positive quantity.");
                continue;
            }

            if (stockProduct.getQuantity() < inputQuantity) {
                System.out.println("Insufficient stock for " + stockProduct.getName());
                continue;
            }
            order.addProduct(stockProduct, inputQuantity, produit1);
            System.out.println(inputQuantity + " quantity of " + produit1.getName() + " added to the order.");
        }
        while (choice){
            System.out.println("This command has priority ? (yes/no)");
            String priority = sc.nextLine();
            if (priority.toLowerCase().equals("yes")) {
                order.setPriority(true);
                choice = false;
            } else if (priority.toLowerCase().equals("no")) {
                //PARCEQUE VRAIMENT, on sais jamais
                order.setPriority(false);
                choice = false;

            }
        }
        order.diplayProducts();
        Main.orderManager.addOrder(order);
        System.out.println("Order placed successfully.");

    }
}