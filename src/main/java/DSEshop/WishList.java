package DSEshop;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Wishlist class. Implements Serializable interface. Used for storing items on the users wishlist.
 */
public class WishList implements Serializable {


    private static final long serialVersionUID = -4211889636207880392L;
    private Date creationDate;
    private int customerID;


    private List<Product> wishList;

    public WishList() {
    }

    /**
     * Wishlist constructor. Sets the creation date and customer according to the passed parameters.
     * @param creationDate
     * @param customerID
     */
    public WishList(Date creationDate, int customerID) {
        this.creationDate = creationDate;
        this.customerID = customerID;
        this.wishList = new ArrayList<Product>();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public void addToWishlist(Product p) {
        this.wishList.add(p);
    }

    public List<Product> getWishList(){
        return wishList;
    }

    public void setWishList(List<Product> wishList) {
        this.wishList = wishList;
    }

    public void removeFromWishlist(Product p) {
        this.wishList.remove(p);
    }

    public void removeFromWishlistById(int productId){
        for(int i=0; i<wishList.size(); i++){
            if(wishList.get(i).getProductID() == productId)
                wishList.remove(i);
        }
    }

}
