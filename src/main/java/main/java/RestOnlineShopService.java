package main.java;

import DSEshop.*;
import dao.SerializedDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/onlineShop")
public class RestOnlineShopService {

    Admin admin;
    SerializedDAO dao;

    public RestOnlineShopService() {
        dao = new SerializedDAO();
        dao.getData();

        admin = Admin.getInstance();
    }

    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "RestOnlineShopService Jersey";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Online Shop Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "RestOnlineShopService Jersey" + "</title>"
                + "<body><h1>" + "Online Shop Jersey" + "</body></h1>" + "</html> ";
    }

    @GET
    @Path("/productList")
    @Produces(MediaType.APPLICATION_XML)
    public ProductCatalogue getCatalogue() {
        return ProductCatalogue.getInstance();
    }


    @POST
    @Path("/add_product")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void newTodo(@FormParam("productName") String productName, @FormParam("productPrice") String productPrice){
        admin.getProductCatalogue().addProduct(new Product(productName, Integer.parseInt(productPrice)));
        dao.saveData();
    }
}