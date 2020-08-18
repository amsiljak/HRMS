package ba.unsa.etf.rpr.projekat;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class KorisniciDAO {
    private static KorisniciDAO instance;
    private PreparedStatement sviKorisniciUpit, sviZaposleniUpit, sviOdjeliUpit,sviPosloviUpit;
    private Connection conn;
    private Zaposleni trenutniZaposleni;

    public void setTrenutniZaposleni(Zaposleni trenutniZaposleni) {
        this.trenutniZaposleni = trenutniZaposleni;
    }

    public Zaposleni getTrenutniZaposleni() {
        return trenutniZaposleni;
    }

    public static KorisniciDAO getInstance() {
        if (instance == null) {
            try {
                instance = new KorisniciDAO();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    public static void removeInstance() {
        if(instance == null) return;
        instance.close();
        instance = null;
    }
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private KorisniciDAO() throws SQLException {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:korisnici.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            sviKorisniciUpit = conn.prepareStatement("SELECT * FROM korisnici");
        } catch (SQLException e) {
            regenerisiBazu();
            try {
                sviKorisniciUpit = conn.prepareStatement("SELECT * FROM korisnici" );
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        try {
            sviZaposleniUpit = conn.prepareStatement("SELECT * FROM zaposleni");
            sviOdjeliUpit = conn.prepareStatement("SELECT * FROM odjeli");
            sviPosloviUpit = conn.prepareStatement("SELECT * FROM poslovi");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void regenerisiBazu(){
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("korisnici.db.sql")); //cita iz ove datoteke
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {

                    try {
                        Statement stmt = conn.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                } //cita upit dok ne dodje do ;, onda ga izvrsi, brise i cita dalje
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Korisnik dajKorisnikaIzResultSeta(ResultSet rs) throws SQLException {
        return new Korisnik(rs.getString(1), rs.getString(2));
    }

    public ArrayList<Korisnik> korisnici() {
        ArrayList<Korisnik> rezultat = new ArrayList<>();
        try {
            ResultSet rs = sviKorisniciUpit.executeQuery();
            while (rs.next()) {
                Korisnik korisnik = dajKorisnikaIzResultSeta(rs);
                rezultat.add(korisnik);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    private Zaposleni dajZaposlenogIzResultSeta(ResultSet rs) throws SQLException {
        return new Zaposleni(rs.getInt(1), rs.getString(2),rs.getString(3),
                rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7),
                rs.getFloat(8),rs.getFloat(9),rs.getInt(10));
    }

    public ArrayList<Zaposleni> zaposleni() {
        ArrayList<Zaposleni> rezultat = new ArrayList<>();
        try {
            ResultSet rs = sviZaposleniUpit.executeQuery();
            while (rs.next()) {
                Zaposleni zaposleni = dajZaposlenogIzResultSeta(rs);
                rezultat.add(zaposleni);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    private Odjel dajOdjelIzResutSeta(ResultSet rs) throws SQLException {
        return new Odjel(rs.getInt(1), rs.getString(2),rs.getInt(3),
                rs.getString(4),rs.getInt(5),rs.getString(6));
    }

    public ArrayList<Odjel> odjeli() {
        ArrayList<Odjel> rezultat = new ArrayList<>();
        try {
            ResultSet rs = sviOdjeliUpit.executeQuery();
            while (rs.next()) {
                Odjel odjel = dajOdjelIzResutSeta(rs);
                rezultat.add(odjel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }

    private Posao dajPosaoIzResutSeta(ResultSet rs) throws SQLException {
        return new Posao(rs.getString(1), rs.getString(2),rs.getFloat(3),
                rs.getFloat(4));
    }

    public ArrayList<Posao> poslovi() {
        ArrayList<Posao> rezultat = new ArrayList<>();
        try {
            ResultSet rs = sviPosloviUpit.executeQuery();
            while (rs.next()) {
                Posao posao = dajPosaoIzResutSeta(rs);
                rezultat.add(posao);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezultat;
    }


    public Connection getConn() {
        return conn;
    }
}
