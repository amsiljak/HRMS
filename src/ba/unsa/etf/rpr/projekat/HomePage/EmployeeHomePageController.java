package ba.unsa.etf.rpr.projekat.HomePage;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Login.LoginController;
import ba.unsa.etf.rpr.projekat.Employee.EmployeeController;
import ba.unsa.etf.rpr.projekat.Leave.LeaveApplicationController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class EmployeeHomePageController {
    public HrmsDAO dao;

    public Label labelWelcome;

    public EmployeeHomePageController() {
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        labelWelcome.setText("Dobro došli " + dao.getCurrentEmployee().getFirstName() + "!");
    }

    public void logoutAction(ActionEvent actionEvent) {
        try {
            Node n = (Node) actionEvent.getSource();
            Stage stage1 = (Stage) n.getScene().getWindow();
            stage1.close();

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/login.fxml"));
            loader.setController(new LoginController());
            Parent root = null;
            root = loader.load();
            stage.setTitle("Prijava");
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void profileAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader;
            Stage stage = new Stage();
            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/employee.fxml"));
            loader.setController(new EmployeeController(dao.getCurrentEmployee(), dao.employees(), dao.jobs(), dao.departments(), false));
            Parent root = null;
            root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leaveApplicationAction(ActionEvent actionEvent) {
        try {
            FXMLLoader loader;
            Stage stage = new Stage();
            loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/leaveApplication.fxml"));
            loader.setController(new LeaveApplicationController());
            Parent root = null;
            root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
