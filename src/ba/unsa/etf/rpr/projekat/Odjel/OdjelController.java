package ba.unsa.etf.rpr.projekat.Odjel;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Zaposlenik.Zaposlenik;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class OdjelController {
    private Odjel odjel;
    public HrmsDAO dao;

    public TextField fieldAdresa;
    public TextField fieldPBroj;
    public TextField fieldGrad;
    public TextField fieldMenadzer;
    public Label labelNaziv;

    private List<Zaposlenik> zaposleni = new ArrayList<>();

    public OdjelController(Odjel odjel, ArrayList<Zaposlenik> zaposleni) {
        this.odjel = odjel;
        this.zaposleni.addAll(zaposleni);
    }

    @FXML
    public void initialize() {
        dao = HrmsDAO.getInstance();

        if(odjel != null) {
            fieldAdresa.setText(odjel.getAdresa());
            fieldPBroj.setText(String.valueOf(odjel.getPostnanskiBroj()));
            fieldGrad.setText(odjel.getGrad());
            for (Zaposlenik z : zaposleni) {
                if (z.getId().equals(odjel.getMenadzerId()))
                    fieldMenadzer.setText(z.getIme() + " " + z.getPrezime());
            }
            labelNaziv.setText(odjel.getNazivOdjela());
        }
    }
    public void obrisiAction(ActionEvent actionEvent) {

    }

    public void spasiAction(ActionEvent actionEvent) {

    }
}