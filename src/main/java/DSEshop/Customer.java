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


    private static final long serialVersionUID = -620613886440708173L;
    private String billingAddress = ".";
    private String paymentMethod = ".";
    @XmlElement
    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
    @XmlElement
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    /**

     * Constructor of the class customer. Sets the passed parameters as the users name and password.
     * Creates an empty wishlist, shopping cart and a payment method filled with placeholders at the time being.
     * @param userName
     * @param password
     */
    public Customer(String userName, String password) {
        super(Admin.getInstance().getCustomerList().size()+1,userName, password);
    }

    public Customer(int customerID, String userName, String password, String _bill, String _pay) {
        super(customerID, userName, password);
        this.billingAddress = _bill;
        this.paymentMethod = _pay;
    }

    public Customer() {
        super(Admin.getInstance().getCustomerList().size()+1,"unknown", "unknown");
    }

    public String toString(){
        return " [Username: " + getUserName() + ", Billing Address: ] " + getBillingAddress() + ", Payment Mehtod: " + getPaymentMethod();
    }

}


