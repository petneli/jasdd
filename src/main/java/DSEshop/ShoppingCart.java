package DSEshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 26.10.16.
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = -532984408656747948L;

    private int customerID;
    private List<Product> shoppingCart;

    /**
     * Creates a shopping cart for each customer that is passed in.
     * @param customerID id of the customer
     */
    public ShoppingCart(int customerID) {
        this.customerID = customerID;
        this.shoppingCart = new ArrayList<Product>();
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void addToShoppingCart(Product p) {
        this.shoppingCart.add(p);
    }

    public void removeFromShoppingCart(Product p) {
        this.shoppingCart.remove(p);
    }

    public List<Product> getShoppingCart() {
        return shoppingCart;
    }

}
