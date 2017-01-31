package misc;

/**
 * Created by nelipetkova on 28.10.16.
 */
public class Settings {

    public final static String ADMINUSERNAME = "admin";
    public final static String ADMINPASSWORD = "password";

    //client Events:
    public static final int REGISTER_EVENT= 0, LOGIN_EVENT= 1, ADDTOWISH_EVENT= 2, ADDTOSHOPPINGCART_EVENT = 3,
            REMOVEFROMSHOPPINGCART_EVENT = 4, REMOVEFROMWISHLIST_EVENT = 5, NEW_PRODUCT = 6, EDITED_PRODUCT=7, DELETED_PRODUCT=8,
            PRODUCT_ONDISCOUNT = 9, PRODUCT_DISCOUNT_REVERTED = 10;

    //Server Events:
    public static final int SUCCESS= 0, FAILURE= 1, ERROR= 2, NEWSLETTER=3;



    public final static String PATH_CATALOGUE = "/Users/nelipetkova/Desktop/GitLab/g2016w_dse_0206/untitled/src/catalogue.txt";
    public final static String PATH_CUSTOMERS = "/Users/nelipetkova/Desktop/GitLab/g2016w_dse_0206/untitled/src/customers.txt";
    public final static String PATH_WISHLISTS = "/Users/nelipetkova/Desktop/GitLab/g2016w_dse_0206/untitled/src/wishlists.txt";
    public final static String PATH_SHOPPING_CARTS = "/Users/nelipetkova/Desktop/GitLab/g2016w_dse_0206/untitled/src/shoppingCarts.txt";

    //public final static String PATH_CATALOGUE = "/Users/Danutz/Documents/~UNI/WS 2016/DSE/Test/g2016w_dse_0206/untitled/src/catalogue.txt";
    //public final static String PATH_CUSTOMERS = "/Users/Danutz/Documents/~UNI/WS 2016/DSE/Test/g2016w_dse_0206/untitled/src/customers.txt";

    //public final static String PATH_CATALOGUE = "/Users/Daria/IdeaProjects/g2016w_dse_0206/untitled/src/catalogue.txt";
    //public final static String PATH_CUSTOMERS = "/Users/Daria/IdeaProjects/g2016w_dse_0206/untitled/src/customers.txt";

/*
    public final static String PATH_CATALOGUE = "/Users/sebastian/Desktop/dseFinal/jasdd/src/catalogue.txt";
    public final static String PATH_CUSTOMERS = "/Users/sebastian/Desktop/dseFinal/jasdd/src/customers.txt";
    public final static String PATH_WISHLISTS = "/Users/sebastian/Desktop/dseFinal/jasdd/src/wishlists.txt";
    public final static String PATH_SHOPPING_CARTS = "/Users/sebastian/Desktop/dseFinal/jasdd/src/shoppingCarts.txt";
*/
}
