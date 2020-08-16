package ba.unsa.etf.rpr.projekat;

import javafx.beans.property.SimpleStringProperty;

public class Korisnik {
    private SimpleStringProperty username;
    private SimpleStringProperty password;

    public Korisnik() {}
    public Korisnik(String user, String pass) {
        username = new SimpleStringProperty(user);
        password = new SimpleStringProperty(pass);
    }

    public String getUsername() {
        return username.get();
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }
}
