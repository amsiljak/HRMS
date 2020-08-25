package ba.unsa.etf.rpr.projekat.Odjel;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Zaposlenik.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
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
    public TextField fieldName;
    public ChoiceBox<Employee> choiceManager;

    private List<Employee> zaposleni = new ArrayList<>();

    public ObservableList<Employee> employeeObservableList;

    public DepartmentController(Department department, ArrayList<Employee> zaposleni) {
        this.department = department;

        this.zaposleni.addAll(zaposleni);

        employeeObservableList = FXCollections.observableList(zaposleni);
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        choiceManager.setItems(employeeObservableList);

        if(department != null) {
            fieldAdress.setText(department.getAdress());
            fieldPCode.setText(String.valueOf(department.getPostalCode()));
            fieldCity.setText(department.getCity());
            for (Employee z : zaposleni) {
                if (z.getId().equals(department.getManagerId()))
                    choiceManager.setValue(z);
            }
            fieldName.setText(department.getDepartmentName());
        }

        fieldName.textProperty().addListener((obs, oldIme, newIme) -> {
            department.setDepartmentName(newIme);
        });
        fieldAdress.textProperty().addListener((obs, oldIme, newIme) -> {
            department.setAdress(newIme);
        });
        fieldPCode.textProperty().addListener((obs, oldIme, newIme) -> {
            department.setPostalCode(Integer.valueOf(newIme));
        });
        fieldCity.textProperty().addListener((obs, oldIme, newIme) -> {
            department.setCity(newIme);
        });
        choiceManager.valueProperty().addListener((obs, oldIme, newIme) -> {
            department.setManagerId(newIme.getId());
        });
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
        dao.updateDepartment(department);

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
