package ba.unsa.etf.rpr.projekat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HrmsController {

    public KorisniciDAO dao;
    public TableView<Zaposleni> tableViewZaposleni;
    public ObservableList<Zaposleni> listaZaposlenih = FXCollections.observableArrayList();
    public TableColumn<Zaposleni, String> colZaposleniIme;
    public TableColumn<Zaposleni, String> colZaposleniPrezime;

    public TableView<Odjel> tableViewOdjeli;
    public ObservableList<Odjel> listaOdjela = FXCollections.observableArrayList();
    public TableColumn<Odjel, String> colOdjelNaziv;

    public TableView<Posao> tableViewPoslovi;
    public ObservableList<Posao> listaPoslova = FXCollections.observableArrayList();
    public TableColumn<Posao, String> colPosaoNaziv;

    public HrmsController() {
    }

    @FXML
    public void initialize() {
        dao = KorisniciDAO.getInstance();

        ArrayList<Zaposleni> zaposleni = dao.zaposleni();
        for(Zaposleni z: zaposleni) {
            listaZaposlenih.add(z);
        }
        colZaposleniIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colZaposleniPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tableViewZaposleni.setItems(listaZaposlenih);

        tableViewZaposleni.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            if(newKorisnik instanceof Zaposleni) {
                dao.setTrenutniZaposleni(newKorisnik);
                tableViewZaposleni.refresh();
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("zaposleni.fxml"));
                    ZaposleniController ctrl = new ZaposleniController(dao.getTrenutniZaposleni(), dao.zaposleni(), dao.poslovi(), dao.odjeli());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Zaposleni");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        ArrayList<Odjel> odjeli = dao.odjeli();
        for(Odjel o: odjeli) {
            listaOdjela.add(o);
        }
        colOdjelNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivOdjela"));
        tableViewOdjeli.setItems(listaOdjela);

        ArrayList<Posao> poslovi = dao.poslovi();
        for(Posao p: poslovi) {
            listaPoslova.add(p);
        }
        colPosaoNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivPosla"));
        tableViewPoslovi.setItems(listaPoslova);
    }
}
