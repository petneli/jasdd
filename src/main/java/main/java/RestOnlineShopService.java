package main.java;

import DSEshop.*;
import dao.SerializedDAO;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    public void addProduct(@FormParam("productName") String productName, @FormParam("productPrice") String productPrice){
        admin.getProductCatalogue().addProduct(new Product(productName, Integer.parseInt(productPrice)));
        dao.saveData();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void register(@FormParam("customerName") String customerName, @FormParam("customerPass") String customerPass){
        admin.register(customerName,customerPass);
        dao.saveData();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public void login(@FormParam("customerName") String customerName, @FormParam("customerPass") String customerPass, @Context HttpServletResponse response) throws IOException {
        if(admin.login(customerName,customerPass))
          response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Response.jsp");
        else
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/ResponseRsdf.jsp");
        dao.saveData();
    }


}