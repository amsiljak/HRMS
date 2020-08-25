package ba.unsa.etf.rpr.projekat.Zaposlenik;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Odjel.Department;
import ba.unsa.etf.rpr.projekat.Posao.Job;
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
    public TextField fieldJob;
    public TextField fieldSalary;
    public TextField fieldDate;
    public TextField fieldCommisionPct;
    public TextField fieldDepartment;
    public Label labelManager;
    public TextField fieldFirstName;
    public TextField fieldLastName;

    private List<Employee> employees = new ArrayList<>();
    private List<Department> departments = new ArrayList<>();
    private List<Job> jobs = new ArrayList<>();

    public EmployeeController(Employee employee, ArrayList<Employee> employees, ArrayList<Job> jobs, ArrayList<Department> departments) {
        this.employee = employee;
        this.employees.addAll(employees);
        this.jobs.addAll(jobs);
        this.departments.addAll(departments);
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

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
                    fieldJob.setText(p.getJobTitle());
            }
            for(Department o: departments) {
                if(o.getId().equals(employee.getDepartmentId())) {
                    fieldDepartment.setText(o.getDepartmentName());
                    for(Employee z: employees) {
                        if(z.getId().equals(o.getManagerId()))
                            labelManager.setText(z.getFirstName() + " " + z.getLastName());
                    }
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
        dao.updateEmployee(employee);

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
