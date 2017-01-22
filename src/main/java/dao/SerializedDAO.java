package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import DSEshop.*;
import misc.Settings;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by TestUser on 28.10.2016.
 */
public class SerializedDAO {

    private String pathCatalogue;
    private String pathCustomers;
    private String pathWishlists;
    private String pathShoppingCarts;
    private File fileCatalogue;
    private File fileCustomers;
    private File fileWishlists;
    private File fileShoppingCarts;

    public SerializedDAO() {
        this.pathCatalogue=Settings.PATH_CATALOGUE;
        this.pathCustomers=Settings.PATH_CUSTOMERS;
        this.pathWishlists=Settings.PATH_WISHLISTS;
        this.pathShoppingCarts = Settings.PATH_SHOPPING_CARTS;
        this.fileCatalogue=new File(pathCatalogue);
        this.fileCustomers=new File(pathCustomers);
        this.fileWishlists=new File(pathWishlists);
        this.fileShoppingCarts = new File(pathShoppingCarts);
    }

    /**
     * Function that retrieves the data from the specified file.
     */
    public void getData(){

        if(fileCatalogue.exists()){
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathCatalogue));
                List<Product> products = (List<Product>) ois.readObject();
                Admin.getInstance().getProductCatalogue().setProductList(products);
                ois.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

        if(fileCustomers.exists()) {
            try {
                ObjectInputStream ois2 = new ObjectInputStream(new FileInputStream(pathCustomers));
                List<Customer> customers = (List<Customer>) ois2.readObject();
                Admin.getInstance().setCustomerList(customers);
                ois2.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(fileWishlists.exists()) {
            try {
                ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream(pathWishlists));
                List<WishList> wishLists = (List<WishList>) ois3.readObject();
                Admin.getInstance().setWishLists(wishLists);
                ois3.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(fileShoppingCarts.exists()) {
            try {
                ObjectInputStream ois3 = new ObjectInputStream(new FileInputStream(pathShoppingCarts));
                List<ShoppingCart> shoppingCarts = (List<ShoppingCart>) ois3.readObject();
                Admin.getInstance().setShoppingCarts(shoppingCarts);
                ois3.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * Function that saves the data into the specified path of a file.
     */
    public void saveData(){
        try{

            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(pathCatalogue));
            oos.writeObject(Admin.getInstance().getProductCatalogue().getProductList());
            oos.close();


            ObjectOutputStream oos2=new ObjectOutputStream(new FileOutputStream(pathCustomers));
            oos2.writeObject(Admin.getInstance().getCustomerList());
            oos2.close();

            ObjectOutputStream oos3=new ObjectOutputStream(new FileOutputStream(pathWishlists));
            List<WishList> wishLists = Admin.getInstance().getWishLists();
            oos3.writeObject(wishLists);
            oos3.close();

            ObjectOutputStream oos4 = new ObjectOutputStream(new FileOutputStream(pathShoppingCarts));
            List<ShoppingCart> shoppingCarts = Admin.getInstance().getShoppingCarts();
            oos4.writeObject(shoppingCarts);
            oos4.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
