package ba.unsa.etf.rpr.projekat.Login;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Pocetna.HrmsController;
import ba.unsa.etf.rpr.projekat.Posao.JobController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class LoginController {
    public HrmsDAO k;
    public TextField usernameField;
    public TextField fieldPassword;

    public LoginController() {
    }

    @FXML
    public void initialize() {
        k = HrmsDAO.getInstance();
    }

    public void loginAction(ActionEvent actionEvent) {
        ArrayList<User> korisnici = k.users();
        for(User k: korisnici) {
            if(k.getUsername().equals(usernameField.getText()) && k.getPassword().equals(fieldPassword.getText())) {
                Node n = (Node) actionEvent.getSource();
                Stage stage1 = (Stage) n.getScene().getWindow();
                stage1.close();
            }
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/hrms.fxml"));
                loader.setController(new HrmsController());
                Parent root = null;
                root = loader.load();
                stage.setTitle("HRMS");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
