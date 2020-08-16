package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public KorisniciDAO k;
    public TextField usernameField;
    public TextField passwordField;

    public LoginController() {
    }
    @FXML
    public void initialize() {
        k = KorisniciDAO.getInstance();
    }

    public void loginAction(ActionEvent actionEvent) {
        ArrayList<Korisnik> korisnici = k.korisnici();
        for(Korisnik k: korisnici) {
            if(k.getUsername().equals(usernameField.getText()) && k.getPassword().equals(passwordField.getText())) {
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("hrms.fxml"));
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("HRMS");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(true);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
