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
import javafx.stage.Stage;

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
    public TextField fieldDate;
    public TextField fieldCommisionPct;
    public ChoiceBox<Department> choiceDepartment;
    public Label labelManager;
    public TextField fieldFirstName;
    public TextField fieldLastName;
    public Button buttonDelete;

    private List<Employee> employees = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();

    public ObservableList<Department> departmentObservableList;
    public ObservableList<Job> jobObservableList;

    public EmployeeController(Employee employee, ArrayList<Employee> employees, ArrayList<Job> jobs, ArrayList<Department> departments) {
        this.employee = employee;

        this.employees.addAll(employees);
        this.jobs.addAll(jobs);
        this.departments.addAll(departments);

        departmentObservableList = FXCollections.observableList(departments);
        jobObservableList = FXCollections.observableList(jobs);
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        choiceDepartment.setItems(departmentObservableList);
        choiceJob.setItems(jobObservableList);

        if (employee != null) {
            fieldEmail.setText(employee.getEmail());
            fieldPhone.setText(String.valueOf(employee.getPhoneNumber()));
            fieldSalary.setText(String.valueOf(employee.getSalary()));
            fieldDate.setText(employee.getHireDate());
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
        }
        else {
            buttonDelete.setVisible(false);
            labelManager.setVisible(false);
        }
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
                    fieldDate.getText(), choiceJob.getSelectionModel().getSelectedItem().getId(), Float.valueOf(fieldSalary.getText()),
                    Float.valueOf(fieldCommisionPct.getText()), Integer.valueOf(choiceDepartment.getSelectionModel().getSelectedItem().getId())));
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
}
