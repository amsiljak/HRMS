package ba.unsa.etf.rpr.projekat.Login;

import javafx.beans.property.SimpleStringProperty;

public class User {
    private SimpleStringProperty username;
    private SimpleStringProperty password;
    private SimpleStringProperty privilege;

    public User() {}
    public User(String username, String password, String privilege) {
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);
        this.privilege = new SimpleStringProperty(privilege);
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

    public String getPrivilege() {
        return privilege.get();
    }

    public SimpleStringProperty privilegeProperty() {
        return privilege;
    }

    public void setPrivilege(String privilege) {
        this.privilege.set(privilege);
    }
}
