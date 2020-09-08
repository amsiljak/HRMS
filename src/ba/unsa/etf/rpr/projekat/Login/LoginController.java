package ba.unsa.etf.rpr.projekat.Login;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Pocetna.HrmsController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginController {
    public HrmsDAO k;
    public TextField usernameField;
    public TextField fieldPassword;
    public GridPane gridPaneLogin;

    public LoginController() {
    }

    @FXML
    public void initialize() {
        k = HrmsDAO.getInstance();
        gridPaneLogin.getStyleClass().add("groupBox");
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
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
