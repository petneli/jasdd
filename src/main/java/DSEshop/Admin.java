package DSEshop;

import misc.Settings;

import java.util.ArrayList;
import java.util.List;

/**
 * Admin class. Built on the singleton pattern, contains a list of customers, as well as the product catalogue.
 */
public class Admin extends User {

    private List <Customer> customerList;
    private ProductCatalogue productCatalogue;

    private static Admin instance = null;

    /**
     * Admin constructor.
     * @param userName username of the admin
     * @param password admin password
     */
    public Admin(String userName, String password) {
        super(0,userName, password);
        productCatalogue = ProductCatalogue.getInstance();
        customerList = new ArrayList<>();
    }

    /**
     * Returns the admin instance. In case an admin does not exist, it will be created. Else, the existing instance will be returned (singleton)
     * @return Admin instance
     */
    public static Admin getInstance() {
        if(instance == null) {
            instance = new Admin(Settings.ADMINUSERNAME, Settings.ADMINPASSWORD);
        }
        return instance;
    }

    /**
     * Logs the customer in. Checks if his username and password match the ones that are already saved in the list.
     * @param customerName
     * @param customerPass
     * @return Returns true if the customer was successfully logged in, false if not.
     */
    public boolean login(String customerName, String customerPass) {

        for (int i=0; i<customerList.size(); i++) {

            if (customerList.get(i).getUserName().equals(customerName) && customerList.get(i).getPassword().equals(customerPass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registers a new customer by adding his credentials to the list.
     * @param customerName
     * @param customerPass
     * @return
     */
    public boolean register (String customerName, String customerPass) {
        Customer c = new Customer(customerName, customerPass);
        customerList.add(c);
        return true;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public ProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }


    /**
     * Creates a new product by adding it to the catalogue and prints the added local in the console.
     * @param productName
     * @param price
     */
    public void createProduct (String productName, double price){
        Product p = new Product(productName, price);
        productCatalogue.addProduct(p);
        System.out.println(getProductCatalogue());
    }
            // boolean?

    /**
     * Returns the customer object whose name matches the one passed as a parameter.
     * @param username
     * @return
     */
    public Customer getCustomerByUsername(String username){
        for(Customer c:customerList){
            if(c.getUserName().equals(username))
                return c;
        }
        return null;
    }
}
