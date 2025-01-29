public abstract class User {
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

    public void placeOrder(Order order) {

        Inventory inventory = Main.getInventory();

        for (Product product : order.getProducts()) {
            Product stockProduct = inventory.searchProduct(product.getName());
            if (stockProduct.getQuantity() <= 0 ) {
                System.out.println("Insufficient stock for : " + product.getName() + ". You can't order");
                return;
            }


        System.out.println("Yes i can do this order");




        }
    }
}