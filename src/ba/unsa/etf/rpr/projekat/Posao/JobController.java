package ba.unsa.etf.rpr.projekat.Posao;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.Optional;

public class JobController {
    private Job job;
    public HrmsDAO dao;

    public TextField fieldMinSalary;
    public TextField fieldMaxSalary;
    public TextField fieldName;

    public JobController(Job job) {
        this.job = job;
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        if(job != null) {
            fieldMinSalary.setText(String.valueOf(job.getMinSalary()));
            fieldMaxSalary.setText(String.valueOf(job.getMaxSalary()));
        }
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

    public void saveAction(ActionEvent actionEvent) {
        dao.updateJob(job);

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
