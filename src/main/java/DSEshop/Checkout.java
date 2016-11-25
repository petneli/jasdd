package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Checkout class. No use at the moment. Future functionality will be added.
 */
@XmlRootElement
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
    @XmlElement
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getOrderID() {
        return orderID;
    }
    @XmlElement
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getShippingID() {
        return shippingID;
    }
    @XmlElement
    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public double getSubtotal() {
        return subtotal;
    }
    @XmlElement
    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public List<Product> getProductList() {
        return productList;
    }
    @XmlElement
    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
