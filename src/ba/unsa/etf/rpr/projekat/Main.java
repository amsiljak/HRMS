package ba.unsa.etf.rpr.projekat;

import ba.unsa.etf.rpr.projekat.Login.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
        loader.setController(new LoginController());
        Parent root = loader.load();
        primaryStage.setTitle("Prijava");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
