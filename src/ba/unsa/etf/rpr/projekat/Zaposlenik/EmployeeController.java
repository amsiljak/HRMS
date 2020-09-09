package ba.unsa.etf.rpr.projekat.Zaposlenik;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Odjel.Department;
import ba.unsa.etf.rpr.projekat.Posao.Job;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeController {
    private Employee employee;
    public HrmsDAO dao;

    public TextField fieldEmail;
    public TextField fieldPhone;
    public ChoiceBox<Job> choiceJob;
    public TextField fieldSalary;
    public DatePicker pickerHireDate;
    public TextField fieldCommisionPct;
    public ChoiceBox<Department> choiceDepartment;
    public Label labelManager;
    public TextField fieldFirstName;
    public TextField fieldLastName;
    public Button buttonDelete;
    public GridPane gridPaneContact;
    public GridPane gridPaneJob;
    public GridPane gridPaneDepartment;

    private List<Employee> employees = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();

    public ObservableList<Department> departmentObservableList;
    public ObservableList<Job> jobObservableList;

    private boolean deleteButtonEnabled;

    public EmployeeController(Employee employee, ArrayList<Employee> employees, ArrayList<Job> jobs, ArrayList<Department> departments, boolean deleteButtonEnabled) {
        this.employee = employee;

        this.employees.addAll(employees);
        this.jobs.addAll(jobs);
        this.departments.addAll(departments);

        departmentObservableList = FXCollections.observableList(departments);
        jobObservableList = FXCollections.observableList(jobs);

        this.deleteButtonEnabled = deleteButtonEnabled;
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        choiceDepartment.setItems(departmentObservableList);
        choiceJob.setItems(jobObservableList);

        if(!deleteButtonEnabled) {
            buttonDelete.setVisible(false);
        }

        if (employee != null) {
            fieldEmail.setText(employee.getEmail());
            fieldPhone.setText(String.valueOf(employee.getPhoneNumber()));
            fieldSalary.setText(String.valueOf(employee.getSalary()));
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(employee.getHireDate(), formatter);
            pickerHireDate.setValue(localDate);
            fieldCommisionPct.setText(String.valueOf(employee.getCommissionPct()));
            fieldFirstName.setText(employee.getFirstName());
            fieldLastName.setText(employee.getLastName());

            for(Job p: jobs) {
                if(p.getId().equals(employee.getJobId()))
                    choiceJob.setValue(p);
            }
            for(Department o: departments) {
                if(o.getId().equals(employee.getDepartmentId())) {
                    choiceDepartment.setValue(o);
                    for(Employee z: employees) {
                        if(z.getId().equals(o.getManagerId()))
                            labelManager.setText(z.getFirstName() + " " + z.getLastName());
                    }
                }
            }
            fieldFirstName.textProperty().addListener((obs, oldIme, newIme) -> {
                employee.setFirstName(newIme);
            });
            fieldLastName.textProperty().addListener((obs, oldIme, newIme) -> {
                employee.setLastName(newIme);
            });
            fieldEmail.textProperty().addListener((obs, oldIme, newIme) -> {
                employee.setEmail(newIme);
            });
            fieldPhone.textProperty().addListener((obs, oldIme, newIme) -> {
                employee.setPhoneNumber(newIme);
            });
            fieldSalary.textProperty().addListener((obs, oldIme, newIme) -> {
                employee.setSalary(Float.valueOf(newIme));
            });
            fieldCommisionPct.textProperty().addListener((obs, oldIme, newIme) -> {
                employee.setCommissionPct(Float.valueOf(newIme));
            });
            choiceJob.valueProperty().addListener((obs, oldIme, newIme) -> {
                employee.setJobId(newIme.getId());
            });
            choiceDepartment.valueProperty().addListener((obs, oldIme, newIme) -> {
                employee.setDepartmentId(newIme.getId());
                for(Employee z: employees) {
                    if(z.getId().equals(newIme.getManagerId()))
                        labelManager.setText(z.getFirstName() + " " + z.getLastName());
                }
            });
            pickerHireDate.valueProperty().addListener((obs, oldIme, newIme) -> {
                employee.setHireDate(newIme.toString());
            });
        }
        else {
            buttonDelete.setVisible(false);
            labelManager.setVisible(false);
        }

        gridPaneContact.getStyleClass().add("groupBox");
        gridPaneJob.getStyleClass().add("groupBox");
        gridPaneDepartment.getStyleClass().add("groupBox");
    }

    public void deleteAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrdni dijalog");
        alert.setHeaderText("Potvrdni dijalog");
        alert.setContentText("Da li ste sigurni da želite obrisati ovog zaposlenika?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            dao.deleteEmployee(employee.getId());

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }

    public void saveAction(ActionEvent actionEvent) {
        if(employee != null) dao.updateEmployee(employee);

        else {
            dao.addEmployee(new Employee(-1, fieldFirstName.getText(), fieldLastName.getText(), fieldEmail.getText(), fieldPhone.getText(),
                    pickerHireDate.getValue().toString(), choiceJob.getSelectionModel().getSelectedItem().getId(), Float.valueOf(fieldSalary.getText()),
                    Float.valueOf(fieldCommisionPct.getText()), Integer.valueOf(choiceDepartment.getSelectionModel().getSelectedItem().getId())));
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
}
