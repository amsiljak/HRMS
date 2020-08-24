package ba.unsa.etf.rpr.projekat.Odjel;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Zaposlenik.Employee;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentController {
    private Department department;
    public HrmsDAO dao;

    public TextField fieldAdress;
    public TextField fieldPCode;
    public TextField fieldCity;
    public TextField fieldManager;
    public Label labelName;

    private List<Employee> zaposleni = new ArrayList<>();

    public DepartmentController(Department department, ArrayList<Employee> zaposleni) {
        this.department = department;
        this.zaposleni.addAll(zaposleni);
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        if(department != null) {
            fieldAdress.setText(department.getAdress());
            fieldPCode.setText(String.valueOf(department.getPostalCode()));
            fieldCity.setText(department.getCity());
            for (Employee z : zaposleni) {
                if (z.getId().equals(department.getManagerId()))
                    fieldManager.setText(z.getFirstName() + " " + z.getLastName());
            }
            labelName.setText(department.getDepartmentName());
        }
    }

    public void deleteAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrdni dijalog");
        alert.setHeaderText("Potvrdni dijalog");
        alert.setContentText("Da li ste sigurni da želite obrisati ovaj odjel?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            dao.deleteDepartment(department.getId());

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }

    public void saveAction(ActionEvent actionEvent) {

    }
}
