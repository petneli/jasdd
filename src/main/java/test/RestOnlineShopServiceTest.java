package test;

import DSEshop.Admin;
import junit.framework.TestCase;
import main.java.RestOnlineShopService;
import org.junit.Test;

/**
 * Created by Danutz on 26/11/16.
 */
public class RestOnlineShopServiceTest extends TestCase {

    RestOnlineShopService tester = new RestOnlineShopService();

    @Test
    public void testAddProduct() throws Exception {
        tester.addProduct("testName","40");
        assertEquals("The list size must be 1, after adding a product", 1, tester.getCatalogue().getProductList().size());
    }

    @Test
    public void testGetCatalogue() throws Exception {
        assertEquals("The list size must be 1, if there is 1 product.", 1, tester.getCatalogue().getProductList().size());
    }

    @Test
    public void testRegister() throws Exception {
        tester.register("testName", "testPass");
        assertEquals("The list size must be 1, after registering a customer", 1, Admin.getInstance().getCustomerList().size());
    }

    @Test
    public void testLogin() throws Exception {
        if(Admin.getInstance().login("testName", "testPass"))
            System.out.println("Is a customer");
        else
            System.out.println("Is not a customer");
    }
}