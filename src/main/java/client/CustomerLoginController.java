package client;

import DSEshop.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import DSEshop.*;
import misc.Settings;
import server.Server;

import java.io.IOException;

/**
 * Controller for the login scene.
 */
public class CustomerLoginController {


    private Customer loggedIn;
    @FXML public TextField customerName;
    @FXML public PasswordField customerPass;
    @FXML Pane root;

    @FXML Controller controller;
    private Client client;
    private Server server;


    /**
     * Event handler fot the login button. If the login succeeds the client will be directed to the scene containing
     * his wishlist and product catalogue. If the login fails, an according message will be printed to the console.
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleLoginButtonAction(ActionEvent event) throws IOException {


        client = ClientGUI.getClient();

        Client2server msg = new Client2server(Settings.LOGIN_EVENT, customerName.getText() + "-" + customerPass.getText());
        client.sendMessage(msg);

        if (Admin.getInstance().login(customerName.getText(), customerPass.getText())) {

            loggedIn = Admin.getInstance().getCustomerByUsername(customerName.getText());

            Stage stage = (Stage) root.getScene().getWindow();
            stage.setTitle("View");
            Pane myPane = null;
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("CustomerView.fxml"));
            myPane = (Pane) loader.load();
            Scene scene = new Scene(myPane);
            stage.setScene(scene);

            controller = loader.<Controller>getController();
            controller.setLoggedIn(loggedIn);

            stage.show();
        }
        else {
            System.out.println("Passwort oder Username ist falsch!");
        }
    }

    /**
     * Event handler for the register button. Sends the user to the register scene.
     * @param event
     * @throws IOException
     */
    @FXML
    protected  void handleOpenRegisterButtonAction(ActionEvent event) throws IOException{
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("View");
        Pane myPane = null;
        myPane = FXMLLoader.load(getClass().getClassLoader().getResource("CustomerRegister.fxml"));
        Scene scene = new Scene(myPane);
        stage.setScene(scene);


        stage.show();
    }


    public Customer getLoggedIn(){
        return loggedIn;
    }
}
