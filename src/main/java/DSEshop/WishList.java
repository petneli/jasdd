package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Wishlist class. Implements Serializable interface. Used for storing items on the users wishlist.
 */
@XmlRootElement
public class WishList implements Serializable {

    private static final long serialVersionUID = -5838413452365634032L;

    private Date creationDate;
    private Customer customer;
    private List<Product> wishList;

    /**
     * Wishlist constructor. Sets the creation date and customer according to the passed parameters.
     * @param creationDate
     * @param customer
     */
    public WishList(Date creationDate, Customer customer) {
        this.creationDate = creationDate;
        this.customer = customer;
        this.wishList = new ArrayList<Product>();
    }

    public Date getCreationDate() {
        return creationDate;
    }
    @XmlElement
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Customer getCustomer() {
        return customer;
    }
    @XmlElement
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void addToWishlist(Product p) {
        this.wishList.add(p);
    }

    public List<Product> getWishList(){
        return wishList;
    }

}
