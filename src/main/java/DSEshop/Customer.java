package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Customer class which implements the Serializable interface.
 */
@XmlRootElement
public class Customer extends User implements Serializable {

    private static final long serialVersionUID = -2336083485963790178L;
    private WishList wishList;
    private ShoppingCart shoppingCart;
    private PaymentMethod paymentMethod;
    private Checkout checkout;

    /**
     * Constructor of the class customer. Sets the passed parameters as the users name and password.
     * Creates an empty wishlist, shopping cart and a payment method filled with placeholders at the time being.
     * @param userName
     * @param password
     */
    public Customer(String userName, String password) {
        super(Admin.getInstance().getCustomerList().size()+1,userName, password);
        this.wishList = new WishList(new Date(), this);
        this.shoppingCart = new ShoppingCart(this);
        this.paymentMethod = new PaymentMethod("billing Adress", "Payment Adress");
    }

    public WishList getWishList() {
        return this.wishList;
    }
    @XmlElement
    public void setWishList(WishList wishList) {
        this.wishList = wishList;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
    @XmlElement
    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
    @XmlElement
    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Checkout getCheckout() {return checkout; }
    @XmlElement
    public void setCheckout(Checkout checkout) {this.checkout = checkout;}

}


