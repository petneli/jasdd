package DSEshop;

import java.util.List;

/**
 * Checkout class. No use at the moment. Future functionality will be added.
 */
public class Checkout {
    private int customerID;
    private int orderID;
    private int shippingID;
    private double subtotal;
    private List <Product> productList;

    /**
     * Constructor for the checkout cart.
     * @param customerID Id of the customer
     * @param orderID Id of the order that was placed
     * @param shippingID Id of the shipping
     * @param subtotal Subtotal amount
     * @param productList List of the products that were ordered
     */
    public Checkout(int customerID, int orderID, int shippingID, double subtotal, List<Product> productList) {
        this.customerID = customerID;
        this.orderID = orderID;
        this.shippingID = shippingID;
        this.subtotal = subtotal;
        this.productList = productList;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomer(Customer customer) {
        this.customerID = customerID;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
