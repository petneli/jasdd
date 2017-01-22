package DSEshop;

import misc.Settings;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Admin class. Built on the singleton pattern, contains a list of customers, as well as the product catalogue.
 */
public class Admin extends User {

    private List <Customer> customerList;
    private ProductCatalogue productCatalogue;
    private List<WishList> wishLists;

    private List<Checkout> checkouts;
    private List<ShoppingCart> shoppingCarts;


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
        wishLists = new ArrayList<>();

        checkouts = new ArrayList<>();
        shoppingCarts = new ArrayList<>();
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
        WishList w = new WishList(new Date(), c.getUserID());
        ShoppingCart s = new ShoppingCart(c.getUserID());

        customerList.add(c);
        wishLists.add(w);
        shoppingCarts.add(s);
        return true;
    }

    public boolean registerComplete (int _customerId, String _customerName, String _customerPass, String _customerBillingAddress, String _customerPaymentMethod) {
        Customer c = new Customer(_customerId, _customerName, _customerPass, _customerBillingAddress, _customerPaymentMethod);

        return true;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.customerList = customerList;
    }

    public List<Customer> getCustomerList() {
        return customerList;
    }

    public void setWishLists(List<WishList> wishLists) {
        this.wishLists = wishLists;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }

    public List<WishList> getWishLists() {
        return wishLists;
    }

    public ProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }

    public List<Checkout> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<Checkout> checkouts) {
        this.checkouts = checkouts;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
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

    public void createProduct (String productName, double price, boolean discount, double oldPrice){
        Product p = new Product(productName, price, discount, oldPrice);
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

    public Customer getCustomerById(int _customerId) {
        for (Customer c:customerList) {
            if (c.getUserID() == _customerId) {
                return c;
            }
        }
        return null;
    }

    /**
     * Returns the wishlist object whose customer id matches the one passed as a parameter.
     * @param userID
     * @return
     */
    public WishList getWishListByUserID(int userID){
        for(WishList w:wishLists){
            if(w.getCustomerID() == userID)
                return w;
        }
        return null;
    }

    public void removeWishListByUserID(int userID){
        for(int i=0; i<wishLists.size(); i++){
            if(wishLists.get(i).getCustomerID() == userID)
                wishLists.remove(i);
        }
    }

    public ShoppingCart getShoppingCartByUserID(int userID) {
        for (ShoppingCart s:shoppingCarts) {
            if (s.getCustomerID() == userID) {
                return s;
            }
        }
        return null;
    }

    public void removeShoppingCartByUserID(int userID){
        for(int i=0; i<shoppingCarts.size(); i++){
            if(shoppingCarts.get(i).getCustomerID() == userID)
                shoppingCarts.remove(i);
        }
    }


    public void removeCustomerByUsername(String username){
        for(int i = 0; i<customerList.size(); i++){
            if(customerList.get(i).getUserName().equals(username)){
                customerList.remove(i);
                removeWishListByUserID(customerList.get(i).getUserID());
                removeShoppingCartByUserID(customerList.get(i).getUserID());
            }

        }

    }

    public void applyDiscountById(int productId, String productName, double price, double discount){
        List<WishList> wToBeEdited = new ArrayList<>();
        List<ShoppingCart> scToBeEdited = new ArrayList<>();

        for(int i=0; i<wishLists.size(); i++){
            for(int j=0; j<wishLists.get(i).getWishList().size(); j++){
                if(wishLists.get(i).getWishList().get(j).getProductID() == productId)
                    wToBeEdited.add(wishLists.get(i));
            }
        }

        for(int i=0; i<shoppingCarts.size(); i++){
            for(int j=0; j<shoppingCarts.get(i).getShoppingCart().size(); j++){
                if(shoppingCarts.get(i).getShoppingCart().get(j).getProductID() == productId)
                    scToBeEdited.add(shoppingCarts.get(i));
            }
        }

        removeProductById(productId);
        createProduct(productName,price*(100-discount)/100, true, price);


        int index=0;
        for(Product p: productCatalogue.getProductList())
            index = p.getProductID();


        for(WishList w: wToBeEdited)
            w.addToWishlist(getProductById(index));

        for(ShoppingCart sc: scToBeEdited)
            sc.addToShoppingCart(getProductById(index));

        for(WishList w1: wishLists){
            for(WishList w2: wToBeEdited){
                if(w1.getCustomerID() == w2.getCustomerID())
                    w1 = w2;
            }
        }

        for(ShoppingCart sc1: shoppingCarts){
            for(ShoppingCart sc2: scToBeEdited){
                if(sc1.getCustomerID() == sc2.getCustomerID())
                    sc1 = sc2;
            }
        }
    }

    public void revertDiscountById(int productId){
        Product p = getProductById(productId);

        if(p.isDiscount()){
            List<WishList> wToBeEdited = new ArrayList<>();
            List<ShoppingCart> scToBeEdited = new ArrayList<>();

            for(int i=0; i<wishLists.size(); i++){
                for(int j=0; j<wishLists.get(i).getWishList().size(); j++){
                    if(wishLists.get(i).getWishList().get(j).getProductID() == productId)
                        wToBeEdited.add(wishLists.get(i));
                }
            }

            for(int i=0; i<shoppingCarts.size(); i++){
                for(int j=0; j<shoppingCarts.get(i).getShoppingCart().size(); j++){
                    if(shoppingCarts.get(i).getShoppingCart().get(j).getProductID() == productId)
                        scToBeEdited.add(shoppingCarts.get(i));
                }
            }

            removeProductById(productId);
            createProduct(p.getProductName(),p.getOldPrice());


            int index=0;
            for(Product p1: productCatalogue.getProductList())
                index = p1.getProductID();


            for(WishList w: wToBeEdited)
                w.addToWishlist(getProductById(index));

            for(ShoppingCart sc: scToBeEdited)
                sc.addToShoppingCart(getProductById(index));

            for(WishList w1: wishLists){
                for(WishList w2: wToBeEdited){
                    if(w1.getCustomerID() == w2.getCustomerID())
                        w1 = w2;
                }
            }

            for(ShoppingCart sc1: shoppingCarts){
                for(ShoppingCart sc2: scToBeEdited){
                    if(sc1.getCustomerID() == sc2.getCustomerID())
                        sc1 = sc2;
                }
            }
        }

    }

    public void editUserById(int _customerID, String _userName, String _password, String _bill, String _pay) {
        removeCustomerById(_customerID);

        Customer add = new Customer(_customerID, _userName, _password, _bill, _pay);

        customerList.add(add);
    }

    public void editProductById(int productId, String productName, double price) {
        List<WishList> wToBeEdited = new ArrayList<>();
        List<ShoppingCart> scToBeEdited = new ArrayList<>();

        for(int i=0; i<wishLists.size(); i++){
            for(int j=0; j<wishLists.get(i).getWishList().size(); j++){
                if(wishLists.get(i).getWishList().get(j).getProductID() == productId)
                    wToBeEdited.add(wishLists.get(i));
            }
        }

        for(int i=0; i<shoppingCarts.size(); i++){
            for(int j=0; j<shoppingCarts.get(i).getShoppingCart().size(); j++){
                if(shoppingCarts.get(i).getShoppingCart().get(j).getProductID() == productId)
                    scToBeEdited.add(shoppingCarts.get(i));
            }
        }

        removeProductById(productId);
        createProduct(productName,price);


        int index=0;
        for(Product p: productCatalogue.getProductList())
            index = p.getProductID();


        for(WishList w: wToBeEdited)
            w.addToWishlist(getProductById(index));

        for(ShoppingCart sc: scToBeEdited)
            sc.addToShoppingCart(getProductById(index));

        for(WishList w1: wishLists){
            for(WishList w2: wToBeEdited){
                if(w1.getCustomerID() == w2.getCustomerID())
                    w1 = w2;
            }
        }

        for(ShoppingCart sc1: shoppingCarts){
                for(ShoppingCart sc2: scToBeEdited){
                    if(sc1.getCustomerID() == sc2.getCustomerID())
                        sc1 = sc2;
                }
        }

    }

    public void removeProductById(int productId) {
        productCatalogue.removeProduct(productId);
        removeProductFromAllShoppingCarts(productId);
        removeProductFromAllWishlists(productId);
    }

    public void removeCustomerById(int userId) {
        Customer delete = getCustomerById(userId);
        customerList.remove(delete);
    }

    public void removeProductFromAllWishlists(int productId) {
        for(int i=0; i<wishLists.size(); i++){
            for(int j=0; j<wishLists.get(i).getWishList().size(); j++){
                if(wishLists.get(i).getWishList().get(j).getProductID() == productId)
                    wishLists.get(i).getWishList().remove(j);
            }
        }
    }

    public void removeProductFromAllShoppingCarts(int productId) {
        for(int i=0; i<shoppingCarts.size(); i++){
            for(int j=0; j<shoppingCarts.get(i).getShoppingCart().size(); j++){
                if(shoppingCarts.get(i).getShoppingCart().get(j).getProductID() == productId)
                    shoppingCarts.get(i).getShoppingCart().remove(j);
            }
        }
    }

    public Product getProductById(int productId){
        for(Product p: productCatalogue.getProductList()){
            if(p.getProductID() == productId)
                return p;
        }
        return null;
    }

    public Product lastProduct(){
        int index=0;
        for(Product p: productCatalogue.getProductList())
            index = p.getProductID();

        return getProductById(index);
    }

}
