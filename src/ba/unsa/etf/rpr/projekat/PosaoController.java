package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class PosaoController {
    private Posao posao;
    public KorisniciDAO dao;

    public TextField fieldMinPlata;
    public TextField fieldMaxPlata;
    public Label labelNaziv;

    public PosaoController(Posao posao) {
        this.posao = posao;
    }

    @FXML
    public void initialize() {
        dao = KorisniciDAO.getInstance();

        if(posao != null) {
            fieldMinPlata.setText(String.valueOf(posao.getMinPlata()));
            fieldMaxPlata.setText(String.valueOf(posao.getMaxPlata()));
        }
        labelNaziv.setText(posao.getNazivPosla());
    }
}
