package client;

import dao.SerializedDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class ClientGUI extends Application {

    Stage window;
    private static Client client = new Client("localhost",1500);

    Scene logInScene, registerScene, viewScene;

    @Override
    public void start(Stage primaryStage) throws Exception{

        SerializedDAO dao = new SerializedDAO();
        dao.getData();

        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("CustomerLogin.fxml"));
        window.setTitle("Login");
        window.setScene(new Scene(root, 600, 400));

        new Thread() {
            // runnable for that thread
            public void run() {
                try {
                    client.start();
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        dao.getData();
                    }
                });
            }
        }.start();
        window.show();
    }


    public static void main(String[] args) {
        launch(args);

        SerializedDAO dao = new SerializedDAO();
        dao.saveData();
    }

    public Stage getWindow() {
        return window;
    }

    public void setWindow(Stage window) {
        this.window = window;
    }

    public static Client getClient(){
        return client;
    }
}
