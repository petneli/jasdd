package DSEshop;

import misc.Settings;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Product catalogue class. Contains a list of products which are listed into the online shop. Built on the singleton pattern.
 */
@XmlRootElement
public class ProductCatalogue {
    private List <Product> productList;

    private static ProductCatalogue instance = null;

    /**
     * Constructor which creates an empty ArrayList of Products.
     */
    private ProductCatalogue() {
        this.productList =new ArrayList<Product>();
    }

    /**
     * Returns the instance of Product Catalogue. If none exists, a new one will be created.
     * @return
     */
    public static ProductCatalogue getInstance() {
        if(instance == null) {
            instance = new ProductCatalogue();
        }
        return instance;
    }
    @XmlElement
    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    /**
     * Adds desired item to the list.
     * @param p
     */
    public void addProduct(Product p){ productList.add(p);}          // boolean?

    public void removeProduct(int productId){
        for(int i = 0; i<productList.size(); i++){
            if(productList.get(i).getProductID() == productId)
                productList.remove(i);
        }
    }

    public String toString(){
        String s = "";
        for(Product p: productList){
            s = s + p.toString();
        }
        return s;
    }

}
