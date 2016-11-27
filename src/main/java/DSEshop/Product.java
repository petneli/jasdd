package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Daria on 26.10.16.
 */
@XmlRootElement
public class Product implements Serializable{


    private static final long serialVersionUID = 5618388080350418557L;
    private String productName;
    private int productID;
    private double productPrice;

    public Product(){}

    public Product(String productName, double productPrice) {
        this.productName = productName;
        this.productID = Admin.getInstance().getProductCatalogue().getProductList().size() +1;
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }
    @XmlElement
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductID() {
        return productID;
    }
    @XmlElement
    public void setProductID(int productID) {
        this.productID = productID;
    }

    public double getProductPrice() {
        return productPrice;
    }
    @XmlElement
    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String toString(){
        String productDetails = "[ " + productID+ ".  Product Name: " + productName + ", Price: " + productPrice + " ]\n ";
        return productDetails;
    }
}
