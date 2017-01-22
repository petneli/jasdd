package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Daria on 26.10.16.
 */
@XmlRootElement(name = "product")
public class Product implements Serializable{


    private static final long serialVersionUID = 6174051633288084112L;

    private String productName;
    private int productID;
    private double productPrice;
    private boolean discount;
    private double oldPrice;


    /**
     * Product default constructor
     */
    public Product() {
        this.productName = "";
        this.productID = Admin.getInstance().getProductCatalogue().getProductList().size() +1;
        this.productPrice = 0;
    }

    /**
     * Passing the name and price to the constructor
     * @param productName name of the product
     * @param productPrice price of the product
     */
    public Product(String productName, double productPrice) {
        this.productName = productName;

        int index=0;
        for(Product p: Admin.getInstance().getProductCatalogue().getProductList())
            index = p.getProductID();

        this.productID = index +1;
        this.productPrice = productPrice;
    }

    public Product(String productName, double productPrice, boolean discount, double oldPrice) {
        this.productName = productName;

        int index=0;
        for(Product p: Admin.getInstance().getProductCatalogue().getProductList())
            index = p.getProductID();

        this.productID = index +1;
        this.productPrice = productPrice;
        this.discount = discount;
        this.oldPrice = oldPrice;
    }
    @XmlElement
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    @XmlElement
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
    @XmlElement
    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    @XmlElement
    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }
    @XmlElement
    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    public String toString(){
        String productDetails = "[ Product Name: " + productName + ", Price: " + productPrice + " ]\n ";
        return productDetails;
    }
}
