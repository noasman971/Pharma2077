public class Product {
    private int id;
    private String name;
    private float price;
    private int quantity;
    private String category; /// A CHANGER

    public Product(int id, String name, float price, int quantity, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }

    public void displayDetails() {
        System.out.println(this.id + ", "+ this.name + ", "+ this.price + ", "+ this.quantity + ", "+ this.category );
    }

    public void modifyQuantity(int quantity, boolean add) {
        if (add) {
            this.quantity += quantity;
        }else {
            this.quantity -= quantity;
        }
    }

}
