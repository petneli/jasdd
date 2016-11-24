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
    private File fileCatalogue;
    private File fileCustomers;

    public SerializedDAO() {
        this.pathCatalogue=Settings.PATH_CATALOGUE;
        this.pathCustomers=Settings.PATH_CUSTOMERS;
        this.fileCatalogue=new File(pathCatalogue);
        this.fileCustomers=new File(pathCustomers);
    }


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

    }

    public void saveData(){
        try{

            ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(pathCatalogue));
            oos.writeObject(Admin.getInstance().getProductCatalogue().getProductList());
            oos.close();


            ObjectOutputStream oos2=new ObjectOutputStream(new FileOutputStream(pathCustomers));
            oos2.writeObject(Admin.getInstance().getCustomerList());
            oos2.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
