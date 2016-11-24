package DSEshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Daria on 26.10.16.
 */
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = -532984408656747948L;

    private Customer customer;
    private List<Product> shoppingCart;

    public ShoppingCart(Customer customer) {
        this.customer = customer;
        this.shoppingCart = new ArrayList<Product>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addToShoppingCart(Product p) {
        this.shoppingCart.add(p);
    }

}
