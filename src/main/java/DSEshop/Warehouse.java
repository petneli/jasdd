package DSEshop;

import java.util.List;

/**
 * Warehouse class not used at the moment. Will be used for manipulating data regarding the warehouse where the products are stored.
 */
public class Warehouse {
    private String warehouseName;
    private String location;
    private List <Product> productList;

    public Warehouse(String warehouseName, String location, List<Product> productList) {
        this.warehouseName = warehouseName;
        this.location = location;
        this.productList = productList;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
