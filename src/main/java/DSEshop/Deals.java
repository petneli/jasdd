package DSEshop;

import java.util.List;

/**
 * Deals class. Not used at the time being. Will be used to discount products.
 */
public class Deals {
    private String dealType;
    private List <Product> productList;

    public Deals(String dealType, List<Product> productList) {
        this.dealType = dealType;
        this.productList = productList;
    }

    public String getDealType() { return dealType; }

    public void setDealType(String dealType) {
        this.dealType = dealType;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
