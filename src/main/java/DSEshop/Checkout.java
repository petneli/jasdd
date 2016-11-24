package DSEshop;

import java.util.List;

/**
 * Checkout class. No use at the moment. Future functionality will be added.
 */
public class Checkout {
    private Customer customer;
    private int orderID;
    private int shippingID;
    private double subtotal;
    private List <Product> productList;

    public Checkout(Customer customer, int orderID, int shippingID, double subtotal, List<Product> productList) {
        this.customer = customer;
        this.orderID = orderID;
        this.shippingID = shippingID;
        this.subtotal = subtotal;
        this.productList = productList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
