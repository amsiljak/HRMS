package ba.unsa.etf.rpr.projekat.Department;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Employee.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
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
    public Button buttonDelete;
    public GridPane gridPaneDepartment;

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
        else {
            buttonDelete.setVisible(false);
        }
        gridPaneDepartment.getStyleClass().add("groupBox");
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
        if(department != null) {
            dao.updateDepartment(department);
        }
        else {
            if(fieldName.getText().isEmpty() || choiceManager.getSelectionModel().getSelectedItem() == null ||
            fieldAdress.getText().isEmpty() || fieldPCode.getText().isEmpty() || fieldCity.getText().isEmpty() ||
            !(fieldName.getText().matches("[a-zA-Z]+") && fieldPCode.getText().matches("[0-9]+") &&
                    fieldCity.getText().matches("[a-zA-Z]+"))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Nedozvoljen unos!");
                alert.show();
            }

            dao.addDepartment(new Department(-1, fieldName.getText(), choiceManager.getSelectionModel().getSelectedItem().getId(),
                    fieldAdress.getText(), Integer.valueOf(fieldPCode.getText()),
                    fieldCity.getText()));

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
}
