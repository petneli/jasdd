package server;

import dao.SerializedDAO;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;

public class ServerGUI extends Application {

    private static Server server = new Server(1500);
    static Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception{

        SerializedDAO dao = new SerializedDAO();
        dao.getData();

        Stage window = primaryStage;

        Parent root = null;
        root = FXMLLoader.load(getClass().getClassLoader().getResource("AdminView.fxml"));

        window.setTitle("Admin View");
        window.setScene(new Scene(root, 600, 400));

        // separate non-FX thread
        new Thread() {
            // runnable for that thread
            public void run() {
                try {
                    server.start();
                    Thread.sleep(new Random().nextInt(1000));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
                Platform.runLater(new Runnable() {
                    public void run() {
                        dao.saveData();
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

    public static Stage getWindow() {
        return window;
    }
    public static Server getServer() { return server; }
    public void setWindow(Stage window) {
        this.window = window;
    }

}

