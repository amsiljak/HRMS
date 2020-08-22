package ba.unsa.etf.rpr.projekat.Pocetna;

import ba.unsa.etf.rpr.projekat.HrmsDAO;
import ba.unsa.etf.rpr.projekat.Odjel.Odjel;
import ba.unsa.etf.rpr.projekat.Odjel.OdjelController;
import ba.unsa.etf.rpr.projekat.Posao.Posao;
import ba.unsa.etf.rpr.projekat.Posao.PosaoController;
import ba.unsa.etf.rpr.projekat.Zaposlenik.Zaposlenik;
import ba.unsa.etf.rpr.projekat.Zaposlenik.ZaposlenikController;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public class HrmsController {

    public HrmsDAO dao;

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
        dao = HrmsDAO.getInstance();

        listaZaposlenih.addAll(dao.zaposleni());
        colZaposleniIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colZaposleniPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        tableViewZaposleni.setItems(listaZaposlenih);

        tableViewZaposleni.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Zaposlenik>() {
            @Override
            public void onChanged(Change<? extends Zaposlenik> change) {
                if(tableViewZaposleni.getSelectionModel().getSelectedItem() == null) return;
                dao.setTrenutniZaposleni(tableViewZaposleni.getSelectionModel().getSelectedItem());

                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/zaposlenik.fxml"));
                    ZaposlenikController ctrl = new ZaposlenikController(dao.getTrenutniZaposleni(), dao.zaposleni(), dao.poslovi(), dao.odjeli());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Zaposlenik");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            listaZaposlenih.clear();
                            listaZaposlenih.addAll(dao.zaposleni());
                            tableViewZaposleni.refresh();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        listaOdjela.addAll(dao.odjeli());
        colOdjelNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivOdjela"));
        tableViewOdjeli.setItems(listaOdjela);

        tableViewOdjeli.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Odjel>() {
            @Override
            public void onChanged(Change<? extends Odjel> change) {
                if (tableViewOdjeli.getSelectionModel().getSelectedItem() == null) return;
                dao.setTrenutniOdjel(tableViewOdjeli.getSelectionModel().getSelectedItem());
                tableViewOdjeli.refresh();
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/odjel.fxml"));
                    OdjelController ctrl = new OdjelController(dao.getTrenutniOdjel(), dao.zaposleni());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Odjel");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            listaOdjela.clear();
                            listaOdjela.addAll(dao.odjeli());
                            tableViewOdjeli.refresh();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        listaPoslova.addAll(dao.poslovi());
        colPosaoNaziv.setCellValueFactory(new PropertyValueFactory<>("nazivPosla"));
        tableViewPoslovi.setItems(listaPoslova);

        tableViewPoslovi.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<Posao>() {
            @Override
            public void onChanged(Change<? extends Posao> change) {
                if (tableViewPoslovi.getSelectionModel().getSelectedItem() == null) return;
                dao.setTrenutniPosao(tableViewPoslovi.getSelectionModel().getSelectedItem());
                tableViewPoslovi.refresh();
                try {
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/posao.fxml"));
                    PosaoController ctrl = new PosaoController(dao.getTrenutniPosao());
                    loader.setController(ctrl);
                    Parent root = null;
                    root = loader.load();
                    stage.setTitle("Posao");
                    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
                    stage.setResizable(false);
                    stage.show();
                    stage.setOnHiding(new EventHandler<WindowEvent>() {
                        public void handle(WindowEvent event) {
                            listaPoslova.clear();
                            listaPoslova.addAll(dao.poslovi());
                            tableViewPoslovi.refresh();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
