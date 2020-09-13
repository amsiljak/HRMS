package ba.unsa.etf.rpr.projekat.Job;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.InvalidEntryException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.Optional;

public class JobController {
    private Job job;
    public HrmsDAO dao;

    public TextField fieldMinSalary;
    public TextField fieldMaxSalary;
    public TextField fieldName;
    public Button buttonDelete;
    public GridPane gridPaneSalaries;

    public JobController(Job job) {
        this.job = job;
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        if(job != null) {
            fieldMinSalary.setText(String.valueOf(job.getMinSalary()));
            fieldMaxSalary.setText(String.valueOf(job.getMaxSalary()));
            fieldName.setText(job.getJobTitle());

            fieldName.textProperty().addListener((obs, oldIme, newIme) -> {
                job.setJobTitle(newIme);
            });
            fieldMinSalary.textProperty().addListener((obs, oldIme, newIme) -> {
                job.setMinSalary(Float.valueOf(newIme));
            });
            fieldMaxSalary.textProperty().addListener((obs, oldIme, newIme) -> {
                job.setMaxSalary(Float.valueOf(newIme));
            });
        }
        else {
            buttonDelete.setVisible(false);
        }

        gridPaneSalaries.getStyleClass().add("groupBox");
    }

    public void deleteAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrdni dijalog");
        alert.setHeaderText("Potvrdni dijalog");
        alert.setContentText("Da li ste sigurni da želite obrisati ovaj posao?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            dao.deleteJob(job.getId());

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }

    public void saveAction(ActionEvent actionEvent)  {
        if(job != null) {
            dao.updateJob(job);
        }

        else {
            if(fieldName.getText().isEmpty() || fieldMinSalary.getText().isEmpty() || fieldMaxSalary.getText().isEmpty() ||
            !(fieldName.getText().matches("[a-zA-Z]+") && fieldMaxSalary.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")
            && fieldMinSalary.getText().matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+"))) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Nedozvoljen unos!");
                alert.show();
            }

            dao.addJob(new Job(-1, fieldName.getText(), Float.parseFloat(fieldMinSalary.getText()), Float.parseFloat(fieldMaxSalary.getText())));

            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
    }
}
