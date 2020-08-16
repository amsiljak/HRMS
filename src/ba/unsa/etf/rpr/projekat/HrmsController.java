package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class HrmsController {

    public KorisniciDAO k;
    public TableView<Zaposleni> tableViewZaposleni;
    public ObservableList<Zaposleni> lista = FXCollections.observableArrayList();
    public TableColumn<Zaposleni, String> colZaposleniIme;
    public TableColumn<Zaposleni, String> colZaposleniPrezime;
    public HrmsController() {
    }

    @FXML
    public void initialize() {
        k = KorisniciDAO.getInstance();
        ArrayList<Zaposleni> zaposleni = k.zaposleni();
        for(Zaposleni z: zaposleni) {
            lista.add(z);
        }
        colZaposleniIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colZaposleniPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));

        tableViewZaposleni.setItems(lista);

        tableViewZaposleni.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
//            dao.setTrenutniGrad(newKorisnik);
//            tableViewGradovi.refresh();
        });
    }
}
