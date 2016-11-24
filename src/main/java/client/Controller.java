package client;

import DSEshop.Admin;
import DSEshop.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import DSEshop.*;
import misc.Settings;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Contoler for the main view of the user.
 */
public class Controller implements Initializable{

    @FXML public TableView<Product> product_all;
    @FXML public TableColumn<Product, Integer> productID_all;
    @FXML public TableColumn<Product, String> productName_all;
    @FXML public TableColumn<Product, Double> productPrice_all;

    @FXML public TableView<Product> product_wish;
    @FXML public TableColumn<Product, Integer> productID_wish;
    @FXML public TableColumn<Product, String> productName_wish;
    @FXML public TableColumn<Product, Double> productPrice_wish;

    private Admin admin = Admin.getInstance();
    private WishList customerWishlist;
    private Customer loggedIn;

    /**
     * Gets the list of items at initialization and sets the products into a table.
     * @param location
     * @param resources
     */

    private Client client;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productID_all.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productName_all.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productPrice_all.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        product_all.getItems().setAll(admin.getProductCatalogue().getProductList());
    }

    /**
     * Event handler for the add button. Adds an item to the users Wishlist and updates the wishlist view.
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleAddButtonAction(ActionEvent event) throws IOException {

        Product p = product_all.getSelectionModel().getSelectedItem();

        client = ClientGUI.getClient();
        Client2server msg = new Client2server(Settings.ADDTOWISH_EVENT, Integer.toString(p.getProductID()));
        client.sendMessage(msg);

        customerWishlist.addToWishlist(p);

        productID_wish.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productName_wish.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productPrice_wish.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        product_wish.getItems().setAll(customerWishlist.getWishList());
    }

    public void setLoggedIn(Customer c){

        this.loggedIn = c;

        customerWishlist = loggedIn.getWishList();

        productID_wish.setCellValueFactory(new PropertyValueFactory<Product, Integer>("productID"));
        productName_wish.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        productPrice_wish.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        product_wish.getItems().setAll(customerWishlist.getWishList());
    }


}
