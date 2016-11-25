package main.java;

import DSEshop.Admin;
import DSEshop.Customer;
import DSEshop.Product;
import dao.SerializedDAO;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.GenericType;

// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/onlineShop")
public class Hello {

    Admin admin;
    SerializedDAO dao;

    public Hello() {
        dao = new SerializedDAO();
        dao.getData();

        admin = Admin.getInstance();
    }

    // This method is called if TEXT_PLAIN is request
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayPlainTextHello() {
        return "Hello Jersey";
    }

    // This method is called if XML is request
    @GET
    @Produces(MediaType.TEXT_XML)
    public String sayXMLHello() {
        return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
    }

    // This method is called if HTML is request
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String sayHtmlHello() {
        return "<html> " + "<title>" + "Hello Jersey" + "</title>"
                + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
    }

    @GET
    @Path("/get_products")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProducts() {
        /*
        List<Product> list = admin.getProductCatalogue().getProductList();
        GenericEntity<List<Product>> entity = new GenericEntity<List<Product>>(list) {};
        Response response = Response.ok(entity).build();
*/

        //use GenericType instead
        //List<Product> list = Client.create().resource(admin.getProductCatalogue().getProductList())
         //       .get(new GenericType<List<Product>>(){});


        List<Product> persons = admin.getProductCatalogue().getProductList();
        return Response.ok(persons).build();


    }

    //

    //@Produces(MediaType.APPLICATION_XML)

    //public List<Customer> getCustomers() {

    //return admin.getCustomerList();

    //}

    //
/*
    @GET
    @Path("/s")
    @Produces(MediaType.APPLICATION_XML)
    public Response getCusomters() {
        List<Customer> list = admin.getCustomerList();

        //use GenericType instead
        List<Customer> list1 = new GenericType<List<Customer>>(list){};
        return
    }

*/

    @GET
    @Path("/g")
    @Produces(MediaType.APPLICATION_XML)
    public Response getProd() {
        Response.ResponseBuilder rb = Response.ok().entity(admin.getProductCatalogue().getProductList());
        return rb.build();
    }
}