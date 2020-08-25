package ba.unsa.etf.rpr.projekat.Pocetna;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Odjel.Department;
import ba.unsa.etf.rpr.projekat.Odjel.DepartmentController;
import ba.unsa.etf.rpr.projekat.Posao.Job;
import ba.unsa.etf.rpr.projekat.Posao.JobController;
import ba.unsa.etf.rpr.projekat.Zaposlenik.Employee;
import ba.unsa.etf.rpr.projekat.Zaposlenik.EmployeeController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.stream.Collectors;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HrmsController {

    public HrmsDAO dao;

    public TableView<Employee> tableViewEmployees;
    public ObservableList<Employee> employeesList = FXCollections.observableArrayList();
    public TableColumn<Employee, String> colEmployeeName;
    public TableColumn<Employee, String> colEmployeeSurname;

    public TableView<Department> tableViewDepartments;
    public ObservableList<Department> departmentsList = FXCollections.observableArrayList();
    public TableColumn<Department, String> colDepartmentName;

    public TableView<Job> tableViewJobs;
    public ObservableList<Job> jobsList = FXCollections.observableArrayList();
    public TableColumn<Job, String> colJobTitle;

    public TextField fieldSearchEmployees;
    public TextField fieldSearchDepartments;
    public TextField fieldSearchJobs;

    public HrmsController() {
    }

    private void openStage(String type) {
        try {
            FXMLLoader loader;
            ModuleLayer.Controller ctrl;
            Stage stage = new Stage();
            if (type.equals("Employee")) {
                loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/employee.fxml"));
                loader.setController(new EmployeeController(dao.getCurrentEmployee(), dao.employees(), dao.jobs(), dao.departments()));
            } else if (type.equals("Department")) {
                loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/department.fxml"));
                loader.setController(new DepartmentController(dao.getCurrentDepartment(), dao.employees()));
            } else {
                loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/job.fxml"));
                loader.setController(new JobController(dao.getCurrentJob()));
            }
            Parent root = null;
            root = loader.load();
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.setResizable(false);
            stage.show();
            stage.setOnHiding(event -> {
                if (type.equals("Employee")) {
                    employeesList.clear();
                    employeesList.addAll(dao.employees());
                    tableViewEmployees.refresh();
                } else if (type.equals("Department")) {
                    departmentsList.clear();
                    departmentsList.addAll(dao.departments());
                    tableViewDepartments.refresh();
                } else {
                    jobsList.clear();
                    jobsList.addAll(dao.jobs());
                    tableViewJobs.refresh();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        employeesList.addAll(dao.employees());
        colEmployeeName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        colEmployeeSurname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        tableViewEmployees.setItems(employeesList);

        tableViewEmployees.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Employee>() {
            @Override
            public void onChanged(Change<? extends Employee> change) {
                if(tableViewEmployees.getSelectionModel().getSelectedItem() == null) return;
                dao.setCurrentEmployee(tableViewEmployees.getSelectionModel().getSelectedItem());

                openStage("Employee");
            }
        });

        departmentsList.addAll(dao.departments());
        colDepartmentName.setCellValueFactory(new PropertyValueFactory<>("departmentName"));
        tableViewDepartments.setItems(departmentsList);

        tableViewDepartments.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Department>() {
            @Override
            public void onChanged(Change<? extends Department> change) {
                if (tableViewDepartments.getSelectionModel().getSelectedItem() == null) return;
                dao.setCurrentDepartment(tableViewDepartments.getSelectionModel().getSelectedItem());

                openStage("Department");
            }
        });

        jobsList.addAll(dao.jobs());
        colJobTitle.setCellValueFactory(new PropertyValueFactory<>("jobTitle"));
        tableViewJobs.setItems(jobsList);

        tableViewJobs.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Job>() {
            @Override
            public void onChanged(Change<? extends Job> change) {
                if (tableViewJobs.getSelectionModel().getSelectedItem() == null) return;
                dao.setCurrentJob(tableViewJobs.getSelectionModel().getSelectedItem());

                openStage("Job");
            }
        });

        fieldSearchEmployees.textProperty().addListener((obs, oldIme, newIme) -> {
            employeesList.clear();
            employeesList.addAll(dao.employees().stream().filter(s -> (s.getFirstName() + " " + s.getLastName()).toLowerCase().contains(newIme.toLowerCase())).collect(Collectors.toList()));
            tableViewEmployees.refresh();
        });
        fieldSearchDepartments.textProperty().addListener((obs, oldIme, newIme) -> {
            departmentsList.clear();
            departmentsList.addAll(dao.departments().stream().filter(s -> s.getDepartmentName().toLowerCase().contains(newIme.toLowerCase())).collect(Collectors.toList()));
            tableViewDepartments.refresh();
        });
        fieldSearchJobs.textProperty().addListener((obs, oldIme, newIme) -> {
            jobsList.clear();
            jobsList.addAll(dao.jobs().stream().filter(s -> s.getJobTitle().toLowerCase().contains(newIme.toLowerCase())).collect(Collectors.toList()));
            tableViewJobs.refresh();
        });
    }
    public void addEmployeeAction(ActionEvent actionEvent) {
        openStage("Employee");
    }
    public void addDepartmentAction(ActionEvent actionEvent) {
        openStage("Department");
    }
    public void addJobAction(ActionEvent actionEvent) {
        openStage("Job");
    }
}
