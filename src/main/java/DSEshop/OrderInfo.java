package DSEshop;

import java.util.Date;

/**
 * Created by Daria on 26.10.16.
 */
public class OrderInfo {
    private int orderID;
    private  int productID;
    private int quantity;
    private Date dateCreated;

    public OrderInfo(int orderID, int productID, int quantity, Date dateCreated) {
        this.orderID = orderID;
        this.productID = productID;
        this.quantity = quantity;
        this.dateCreated = dateCreated;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
