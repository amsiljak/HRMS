package ba.unsa.etf.rpr.projekat.Leave;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class LeaveApplicationController {
    public HrmsDAO dao;

    public TextArea areaReason;
    public DatePicker pickerFrom;
    public DatePicker pickerTo;

    public LeaveApplicationController() {
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();
    }

    public void sendApplicationAction(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage1 = (Stage) n.getScene().getWindow();
        stage1.close();

        dao.addLeave(new Leave(-1, dao.getCurrentEmployee(), pickerFrom.getValue().toString(), pickerTo.getValue().toString(), areaReason.getText(), "pending"));
    }
}
