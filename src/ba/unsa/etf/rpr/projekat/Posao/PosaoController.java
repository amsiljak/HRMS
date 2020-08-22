package ba.unsa.etf.rpr.projekat.Posao;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

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

    }

    public void spasiAction(ActionEvent actionEvent) {

    }
}
