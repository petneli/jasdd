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
import misc.Settings;

import java.io.IOException;

/**
 * Controller fot the register scene.
 */
public class CustomerRegisterController {

    @FXML
    public TextField customerRegisterUsername;
    @FXML public PasswordField customerRegisterPassword;
    @FXML Pane root;

    private Client client;

    /**
     * Event handler for the register button. Registers the customer and returns to the login view.
     * If the registration fails, an according message will be printed to the console.
     * @param event
     * @throws IOException
     */
    @FXML
    protected void handleRegisterButtonAction(ActionEvent event) throws IOException {
       if(Admin.getInstance().register(customerRegisterUsername.getText(), customerRegisterPassword.getText())) {

           Stage stage = (Stage) root.getScene().getWindow();
           stage.setTitle("Login");
           Pane myPane = null;
           myPane = FXMLLoader.load(getClass().getClassLoader().getResource("CustomerLogin.fxml"));
           Scene scene = new Scene(myPane);
           stage.setScene(scene);

           client = ClientGUI.getClient();

           Client2server msg = new Client2server(Settings.REGISTER_EVENT, customerRegisterUsername.getText() + "-" + customerRegisterPassword.getText());
           client.sendMessage(msg);

           stage.show();


       }
       else {
           System.out.println("Fail");
       }
    }

    public void setClient(Client c){
        this.client = c;
    }

}
