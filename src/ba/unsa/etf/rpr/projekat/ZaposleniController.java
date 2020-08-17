package ba.unsa.etf.rpr.projekat;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

public class ZaposleniController {
    private Zaposleni zaposleniIzmjena;
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
    private List<Zaposleni> zaposleni = new ArrayList<>();

    public ZaposleniController(Zaposleni zaposleniIzmjena, ArrayList<Zaposleni> zaposleni, ArrayList<Posao> poslovi, ArrayList<Odjel> odjeli) {
        this.zaposleniIzmjena = zaposleniIzmjena;
        this.zaposleni.addAll(zaposleni);
        this.poslovi.addAll(poslovi);
        this.odjeli.addAll(odjeli);
    }
    @FXML
    public void initialize() {
        dao = KorisniciDAO.getInstance();

        if (zaposleniIzmjena != null) {
            fieldEmail.setText(zaposleniIzmjena.getEmail());
            fieldBrTel.setText(String.valueOf(zaposleniIzmjena.getBrojTelefona()));
            fieldPlata.setText(String.valueOf(zaposleniIzmjena.getPlata()));
            fieldDatum.setText(zaposleniIzmjena.getDatumZaposlenja());
            fieldDodatak.setText(String.valueOf(zaposleniIzmjena.getDodatakNaPlatu()));
            labelIme.setText(zaposleniIzmjena.getIme() + " " + zaposleniIzmjena.getPrezime());

            for(Posao p: poslovi) {
                if(p.getId().equals(zaposleniIzmjena.getPosaoId())) fieldPosao.setText(p.getNazivPosla());
            }
            for(Odjel o: odjeli) {
                if(o.getId() == zaposleniIzmjena.getOdjelId()) {
                    fieldOdjel.setText(o.getNazivOdjela());
                    for(Zaposleni z: zaposleni) {
                        if(z.getId() == o.getMenadzerId()) fieldMenadzer.setText(z.getIme() + " " + z.getPrezime());
                    }
                }
            }
        }
    }
}
