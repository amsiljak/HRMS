package ba.unsa.etf.rpr.projekat.Zaposlenik;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Odjel.Odjel;
import ba.unsa.etf.rpr.projekat.Posao.Posao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ZaposlenikController {
    private Zaposlenik zaposlenik;
    public HrmsDAO dao;

    public TextField fieldEmail;
    public TextField fieldBrTel;
    public TextField fieldPosao;
    public TextField fieldPlata;
    public TextField fieldDatum;
    public TextField fieldDodatak;
    public TextField fieldOdjel;
    public TextField fieldMenadzer;
    public Label labelIme;
    public Button buttonObrisi;
    public Button buttonSpasi;

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
        dao = HrmsDAO.getInstance();

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

    public void obrisiAction(ActionEvent actionEvent) {
        dao.obrisiZaposlenika(zaposlenik.getId());

        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void spasiAction(ActionEvent actionEvent) {

    }
}
