package server;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import misc.Settings;
import DSEshop.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the admin view panel.
 */
public class Controller implements Initializable {

    @FXML public TextField price;
    @FXML public TextField productName;
    @FXML public TableView<Product> productTableView;
    @FXML public TableColumn<Product, String> pname;
    @FXML public TableColumn<Product, Double> pprice;

    private Admin admin = Admin.getInstance();

    /**
     * Event handler for the add button. When the add button is pressed, the product will be added to the list.
     * @param event
     */
    @FXML protected void handleAddProductButtonAction(ActionEvent event) {
        double priceDouble= Double.parseDouble(price.getText());
        admin.createProduct(productName.getText(),priceDouble);

        pname.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        pprice.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        productTableView.getItems().setAll(admin.getProductCatalogue().getProductList());
    }

    /**
     * Initializes the product list when opening the admin panel.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pname.setCellValueFactory(new PropertyValueFactory<Product, String>("productName"));
        pprice.setCellValueFactory(new PropertyValueFactory<Product, Double>("productPrice"));

        productTableView.getItems().setAll(admin.getProductCatalogue().getProductList());
    }



}
