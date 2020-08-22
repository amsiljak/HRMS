package ba.unsa.etf.rpr.projekat.Posao;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PosaoController {
    private Posao posao;
    public HrmsDAO dao;

    public TextField fieldMinPlata;
    public TextField fieldMaxPlata;
    public Label labelNaziv;

    public PosaoController(Posao posao) {
        this.posao = posao;
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        if(posao != null) {
            fieldMinPlata.setText(String.valueOf(posao.getMinPlata()));
            fieldMaxPlata.setText(String.valueOf(posao.getMaxPlata()));
        }
        labelNaziv.setText(posao.getNazivPosla());
    }

    public void obrisiAction(ActionEvent actionEvent) {
        dao.obrisiPosao(posao.getId());

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void spasiAction(ActionEvent actionEvent) {

    }
}
