package DSEshop;

import dao.SerializedDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by sebastian on 22/11/2016.
 */

@Path("/Products")
public class OnlineShopREST {

    Admin admin;
    SerializedDAO dao;

    public OnlineShopREST() {
        dao = new SerializedDAO();
        dao.getData();

        admin = Admin.getInstance();
    }

    @GET
    @Path("/get_products")
    @Produces(MediaType.APPLICATION_XML)
    public ProductCatalogue getProducts() {
        return admin.getProductCatalogue();
    }

    @GET
    @Path("/fuck")
    @Produces(MediaType.APPLICATION_XML)
    public String fuckOff() {
        return "Fuck";
    }

}
