package main.java;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.client.ClientConfig;


public class RestClient {
    public static void main(String[] args) {


        ClientConfig config = new ClientConfig();
        Client client = ClientBuilder.newClient(config);
        WebTarget service = client.target(getBaseURI());

        System.out.println(service.path("rest").path("onlineShop/productList").request().accept(MediaType.APPLICATION_XML).get(String.class));


        Form form =new Form();
        form.param("productName", "daria");
        form.param("productPrice", "56356");


        service.path("rest").path("onlineShop/add_product").request().post(Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED));

        System.out.println("----------");
        System.out.println(service.path("rest").path("onlineShop/productList").request().accept(MediaType.APPLICATION_XML).get(String.class));

    }

    private static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost:8080/jasdd_war_exploded").build();
    }
}