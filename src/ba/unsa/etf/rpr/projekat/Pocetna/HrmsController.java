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
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

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

    public HrmsController() {
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

                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/employee.fxml"));
                    EmployeeController ctrl = new EmployeeController(dao.getCurrentEmployee(), dao.employees(), dao.jobs(), dao.departments());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Zaposlenik");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            employeesList.clear();
                            employeesList.addAll(dao.employees());
                            tableViewEmployees.refresh();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                tableViewDepartments.refresh();
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/department.fxml"));
                    DepartmentController ctrl = new DepartmentController(dao.getCurrentDepartment(), dao.employees());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Odjel");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            departmentsList.clear();
                            departmentsList.addAll(dao.departments());
                            tableViewDepartments.refresh();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
                tableViewJobs.refresh();
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/job.fxml"));
                    JobController ctrl = new JobController(dao.getCurrentJob());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Posao");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            jobsList.clear();
                            jobsList.addAll(dao.jobs());
                            tableViewJobs.refresh();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
