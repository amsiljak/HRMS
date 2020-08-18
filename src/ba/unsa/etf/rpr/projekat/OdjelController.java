package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class OdjelController {
    private Odjel odjel;
    public KorisniciDAO dao;

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
        dao = KorisniciDAO.getInstance();

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
}
