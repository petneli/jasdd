package main.java;

import DSEshop.*;
import dao.SerializedDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Path("/onlineShop")
public class RestOnlineShopService {

    Admin admin;
    SerializedDAO dao;

    public RestOnlineShopService(@Context HttpServletRequest request) {
        dao = new SerializedDAO();
        dao.getData();

        admin = Admin.getInstance();

        HttpSession session= request.getSession(true);
        session.setAttribute("productList", ProductCatalogue.getInstance().getProductList());

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
    public String sayHtmlHello(@Context HttpServletRequest request) {
        HttpSession session= request.getSession(true);
        String loggedIn = (String)session.getAttribute("loggedIn");
        if(loggedIn != null)
            return "<html> " + "<title>" + "RestOnlineShopService Jersey" + "</title>"
                + "<body><h1>" + "Online Shop Jersey" + "</body></h1>"
                + "<h2>" + "Welcome back, " + loggedIn + "!</h2>"
                + "</html> ";
        else
            return "<html> " + "<title>" + "RestOnlineShopService Jersey" + "</title>"
                    + "<body><h1>" + "Online Shop Jersey" + "</body></h1>"
                    + "</html> ";

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
    public void login(@FormParam("customerName") String customerName, @FormParam("customerPass") String customerPass, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException {
        if(admin.login(customerName,customerPass))
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/rest/onlineShop");
        else
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/ResponseRsdf.jsp");

        HttpSession session= request.getSession(true);
        String loggedIn = (String)session.getAttribute("loggedIn");
        if (loggedIn!=null) {
            System.out.println("Logged in: " + loggedIn);
        } else {
            session.setAttribute("loggedIn", customerName);

            System.out.println("Setting session param: " + customerName);
        }
        dao.saveData();
    }

    @GET
    @Path("/addToWishList")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    public void addToWishList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        HttpSession session= request.getSession(true);
        String loggedIn = (String)session.getAttribute("loggedIn");
        Product p = ProductCatalogue.getInstance().getProductById(Integer.parseInt(id));

        if (loggedIn!=null) {
            Customer customer = admin.getCustomerByUsername(loggedIn);
            customer.getWishList().addToWishlist(p);
        } else {
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Login.jsp");
        }
        dao.saveData();
    }

    @GET
    @Path("/wishList")
    @Produces(MediaType.APPLICATION_XML)
    public WishList getWishlist(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        HttpSession session= request.getSession(true);
        String loggedIn = (String)session.getAttribute("loggedIn");

        WishList w = new WishList();
        if (loggedIn!=null) {
            Customer customer = admin.getCustomerByUsername(loggedIn);
            w = customer.getWishList();
        } else {
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Login.jsp");
        }
        return w;
    }



}