package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ZaposlenikController {
    private Zaposlenik zaposlenik;
    public KorisniciDAO dao;

    public TextField fieldEmail;
    public TextField fieldBrTel;
    public TextField fieldPosao;
    public TextField fieldPlata;
    public TextField fieldDatum;
    public TextField fieldDodatak;
    public TextField fieldOdjel;
    public TextField fieldMenadzer;
    public Label labelIme;

    private List<Posao> poslovi = new ArrayList<>();
    private List<Odjel> odjeli = new ArrayList<>();
    private List<Zaposlenik> zaposleni = new ArrayList<>();

    public ZaposlenikController(Zaposlenik zaposlenik, ArrayList<Zaposlenik> zaposleni, ArrayList<Posao> poslovi, ArrayList<Odjel> odjeli) {
        this.zaposlenik = zaposlenik;
        this.zaposleni.addAll(zaposleni);
        this.poslovi.addAll(poslovi);
        this.odjeli.addAll(odjeli);
    }

    @FXML
    public void initialize() {
        dao = KorisniciDAO.getInstance();

        if (zaposlenik != null) {
            fieldEmail.setText(zaposlenik.getEmail());
            fieldBrTel.setText(String.valueOf(zaposlenik.getBrojTelefona()));
            fieldPlata.setText(String.valueOf(zaposlenik.getPlata()));
            fieldDatum.setText(zaposlenik.getDatumZaposlenja());
            fieldDodatak.setText(String.valueOf(zaposlenik.getDodatakNaPlatu()));
            labelIme.setText(zaposlenik.getIme() + " " + zaposlenik.getPrezime());

            for(Posao p: poslovi) {
                if(p.getId().equals(zaposlenik.getPosaoId()))
                    fieldPosao.setText(p.getNazivPosla());
            }
            for(Odjel o: odjeli) {
                if(o.getId().equals(zaposlenik.getOdjelId())) {
                    fieldOdjel.setText(o.getNazivOdjela());
                    for(Zaposlenik z: zaposleni) {
                        if(z.getId().equals(o.getMenadzerId()))
                            fieldMenadzer.setText(z.getIme() + " " + z.getPrezime());
                    }
                }
            }
        }
    }
}
