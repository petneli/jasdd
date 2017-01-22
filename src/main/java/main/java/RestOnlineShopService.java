package main.java;

import DSEshop.*;
import dao.SerializedDAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.List;

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
        session.setAttribute("customerList", admin.getCustomerList());

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
                + "<body><h1>" + "Online Shop Jersey" + "</h1>"
                + "<h2>" + "Welcome back, " + loggedIn + "!</h2>" +
                    "<ul>" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/Login.jsp\">Login</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/Register.jsp\">Register</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/ProductList.jsp\">Productcatalogue</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/rest/onlineShop\">Hello page</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/AddProduct.jsp\">Add Product</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/Wishlist.jsp\">Wishlist</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/CustomerList.jsp\">Customer List</a></li>\n" +
                    "  </ul>" +
                    "</body>" +
                    "</html> ";
        else
            return "<html> " + "<title>" + "RestOnlineShopService Jersey" + "</title>"
                    + "<body><h1>" + "Online Shop Jersey" + "</h1>"+
                    "<ul>" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/Login.jsp\">Login</a></li>\n" +
                    "    <li><a href=\"http://localhost:8080/jasdd_war_exploded/Register.jsp\">Register</a></li>\n" +
                    "  </ul>" +
                    "</body>" +
                    "</html> ";

    }

    @GET
    @Path("/productList")
    @Produces(MediaType.APPLICATION_XML)
    public ProductCatalogue getCatalogue() {
        return ProductCatalogue.getInstance();
    }

    @GET
    @Path("/customerList")
    @Produces(MediaType.APPLICATION_XML)
    public List<Customer> getCustomerList() {
        return admin.getCustomerList();
    }


    @POST
    @Path("/add_product")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void addProduct(@FormParam("productName") String productName, @FormParam("productPrice") String productPrice, @Context HttpServletResponse response) throws IOException {
        admin.getProductCatalogue().addProduct(new Product(productName, Integer.parseInt(productPrice)));
        dao.saveData();
        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/ProductList.jsp");
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void register(@FormParam("customerName") String customerName, @FormParam("customerPass") String customerPass, @Context HttpServletResponse response) throws IOException {
        admin.register(customerName,customerPass);
        dao.saveData();
        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Login.jsp");
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    public void login(@FormParam("customerName") String customerName, @FormParam("customerPass") String customerPass, @Context HttpServletResponse response, @Context HttpServletRequest request) throws IOException {
        dao = new SerializedDAO();
        dao.getData();

        admin = Admin.getInstance();

        if(admin.login(customerName,customerPass))
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/rest/onlineShop");
        else
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Login.jsp");

        HttpSession session= request.getSession(true);
        String loggedIn = (String)session.getAttribute("loggedIn");
        if (loggedIn!=null) {
            System.out.println("Logged in: " + loggedIn);
        } else {
            session.setAttribute("loggedIn", customerName);
            Customer customer = admin.getCustomerByUsername(customerName);
            System.out.println(customer.toString());
            WishList w = admin.getWishListByUserID(customer.getUserID());
            session.setAttribute("wishList", w);

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
        Product p = admin.getProductById(Integer.parseInt(id));

        if (loggedIn!=null) {
            Customer customer = admin.getCustomerByUsername(loggedIn);
            admin.getWishListByUserID(customer.getUserID()).addToWishlist(p);

            WishList w = admin.getWishListByUserID(customer.getUserID());
            session.setAttribute("wishList", w);
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Wishlist.jsp");
        } else {
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Login.jsp");
        }
        dao.saveData();
    }

    @GET
    @Path("/editProduct")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    public void editProduct(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        HttpSession session= request.getSession(true);

        Product p = admin.getProductById(Integer.parseInt(id));

        session.setAttribute("toBeEdited", p);
        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/EditProduct.jsp");

        dao.saveData();
    }

    @POST
    @Path("/edit_product")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    public void edit_product(@FormParam("productName") String productName, @FormParam("productPrice") String productPrice, @Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        HttpSession session= request.getSession(true);
        Product p  = (Product) session.getAttribute("toBeEdited");

        admin.editProductById(p.getProductID(),productName, Double.parseDouble(productPrice));

        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/ProductList.jsp");

        dao.saveData();
    }

    @GET
    @Path("/removeProduct")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    public void removeProduct(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        HttpSession session= request.getSession(true);

        admin.removeProductById(Integer.parseInt(id));
        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/ProductList.jsp");

        dao.saveData();
    }

    @GET
    @Path("/removeCustomer")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    public void removeCustomer(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        System.out.println(username);
        HttpSession session= request.getSession(true);

        admin.removeCustomerByUsername(username);
        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/CustomerList.jsp");

        dao.saveData();
    }

    @GET
    @Path("/removeProductFromWishlist")
    @Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_HTML})
    public void removeProductFromWishlist(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        String id = request.getParameter("id");
        HttpSession session= request.getSession(true);
        Product p = admin.getProductById(Integer.parseInt(id));
        String loggedIn = (String)session.getAttribute("loggedIn");

        Customer customer = admin.getCustomerByUsername(loggedIn);
        admin.getWishListByUserID(customer.getUserID()).removeFromWishlistById(Integer.parseInt(id));

        WishList w = admin.getWishListByUserID(customer.getUserID());
        session.setAttribute("wishList", w);
        response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Wishlist.jsp");

        dao.saveData();

    }

    @GET
    @Path("/wishList")
    @Produces(MediaType.APPLICATION_XML)
    public WishList getWishList(@Context HttpServletRequest request, @Context HttpServletResponse response) throws IOException {

        HttpSession session= request.getSession(true);
        String loggedIn = (String)session.getAttribute("loggedIn");

        WishList w = new WishList();
        if (loggedIn!=null) {
            Customer customer = admin.getCustomerByUsername(loggedIn);
            w = admin.getWishListByUserID(customer.getUserID());
        } else {
            response.sendRedirect("http://localhost:8080/jasdd_war_exploded/Login.jsp");
        }
        return w;
    }



}