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

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HrmsController {

    public KorisniciDAO dao;

    public TableView<Zaposlenik> tableViewZaposleni;
    public ObservableList<Zaposlenik> listaZaposlenih = FXCollections.observableArrayList();
    public TableColumn<Zaposlenik, String> colZaposleniIme;
    public TableColumn<Zaposlenik, String> colZaposleniPrezime;

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

        ArrayList<Zaposlenik> zaposleni = dao.zaposleni();
        for(Zaposlenik z: zaposleni) {
            listaZaposlenih.add(z);
        }
        colZaposleniIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colZaposleniPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tableViewZaposleni.setItems(listaZaposlenih);

        tableViewZaposleni.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            dao.setTrenutniZaposleni(newKorisnik);
            tableViewZaposleni.refresh();
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("zaposlenik.fxml"));
                ZaposlenikController ctrl = new ZaposlenikController(dao.getTrenutniZaposleni(), dao.zaposleni(), dao.poslovi(), dao.odjeli());
                loader.setController(ctrl);
                Parent root = null;
                root = loader.load();
                stage.setTitle("Zaposlenik");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ArrayList<Odjel> odjeli = dao.odjeli();
        for(Odjel o: odjeli) {
            listaOdjela.add(o);
        }
        colOdjelNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivOdjela"));
        tableViewOdjeli.setItems(listaOdjela);

        tableViewOdjeli.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            dao.setTrenutniOdjel(newKorisnik);
            tableViewOdjeli.refresh();
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("odjel.fxml"));
                OdjelController ctrl = new OdjelController(dao.getTrenutniOdjel(), dao.zaposleni());
                loader.setController(ctrl);
                Parent root = null;
                root = loader.load();
                stage.setTitle("Odjel");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        ArrayList<Posao> poslovi = dao.poslovi();
        for(Posao p: poslovi) {
            listaPoslova.add(p);
        }
        colPosaoNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivPosla"));
        tableViewPoslovi.setItems(listaPoslova);

        tableViewPoslovi.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            dao.setTrenutniPosao(newKorisnik);
            tableViewPoslovi.refresh();
            try {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("posao.fxml"));
                PosaoController ctrl = new PosaoController(dao.getTrenutniPosao());
                loader.setController(ctrl);
                Parent root = null;
                root = loader.load();
                stage.setTitle("Posao");
                stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                stage.setResizable(false);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
