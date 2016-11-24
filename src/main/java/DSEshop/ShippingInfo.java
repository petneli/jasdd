package DSEshop;

import java.util.Date;

/**
 * ShippingInfo class not used at the moment. Will be used for manipulating data regarding to the shipment of goods.
 */
public class ShippingInfo {
    private int shippingID;
    private String shippingType;
    private double shippingCost;
    private String shippingAddress;
    private Date dateShipped;

    public ShippingInfo(int shippingID, String shippingType, double shippingCost, String shippingAddress, Date dateShipped) {
        this.shippingID = shippingID;
        this.shippingType = shippingType;
        this.shippingCost = shippingCost;
        this.shippingAddress = shippingAddress;
        this.dateShipped = dateShipped;
    }

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public String getShippingType() {
        return shippingType;
    }

    public void setShippingType(String shippingType) {
        this.shippingType = shippingType;
    }

    public double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Date getDateShipped() {
        return dateShipped;
    }

    public void setDateShipped(Date dateShipped) {
        this.dateShipped = dateShipped;
    }
}
