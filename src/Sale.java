/**
 * Class representing a sale with a product, its quantity, and unit price.
 */
class Sale {
    String product;
    int quantity;
    double unitPrice;

    /**
     * Constructor for the Sale class.
     *
     * @param product The name of the product.
     * @param quantity The quantity sold.
     * @param unitPrice The unit price of the product.
     */
    public Sale(String product, int quantity, double unitPrice) {
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    /**
     * Calculates the total price of the sale (quantity * unit price).
     *
     * @return The total price of the sale.
     */
    double getTotalPrice() {
        return quantity * unitPrice;
    }
}

