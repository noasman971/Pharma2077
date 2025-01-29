public interface Stockable {
    void addProduct(Product product);
    void removeProduct(String productName);
    void updateStock(Product product, int quantity);
}