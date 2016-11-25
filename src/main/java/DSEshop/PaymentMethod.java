package DSEshop;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Payment Method class. Implements Serializable interface. Not used at the moment. Will be used to store customers'
 * desired payment methods.
 */
@XmlRootElement
public class PaymentMethod implements Serializable{

    private static final long serialVersionUID = 2294701042624960016L;
    private String billingAddress;
    private String paymentMethod;

    public PaymentMethod(String billingAddress, String paymentMethod) {
        this.billingAddress = billingAddress;
        this.paymentMethod = paymentMethod;
    }

    public String getBillingAddress() {
        return billingAddress;
    }
    @XmlElement
    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    @XmlElement
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
