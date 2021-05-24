package classes;

import java.sql.*;

import application.DBConnection;

import javax.security.auth.login.LoginContext;

public class Uzytkownik {
    // Lista zmiennych
    private int id;
    private String imie;
    private String nazwisko;
    private String pesel;
    private String login;
    private String haslo;
    private String mail;
    private String rola;
    private int weryfikacja;

    // Lista metod związanych z klasą
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getRola() {
        return rola;
    }

    public void setRola(String rola) {
        this.rola = rola;
    }

    public int getWeryfikacja() {
        return weryfikacja;
    }

    public void setWeryfikacja(int weryfikacja) {
        this.weryfikacja = weryfikacja;
    }

    public static Uzytkownik zaloguj(String login) {
        Uzytkownik sesja = new Uzytkownik();
        try {

            DBConnection DBpolaczenie = new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();

            ResultSet result = stat.executeQuery("SELECT * FROM uzytkownik WHERE login = '" + login + "';");

            while (result.next()) {
                sesja.setId(result.getInt("id"));
                sesja.setImie(result.getString("imie"));
                sesja.setNazwisko(result.getString("nazwisko"));
                sesja.setPesel(result.getString("pesel"));
                sesja.setLogin(result.getString("login"));
                sesja.setMail(result.getString("mail"));
                sesja.setRola(result.getString("rola"));
                sesja.setWeryfikacja(result.getInt("weryfikacja"));
            }
            stat.close();
            polaczenie.close();
            return sesja;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Uzytkownik wczytajUzytkownik_id(int id) {
        Uzytkownik uzytkownik = new Uzytkownik();
        try {

            DBConnection DBpolaczenie = new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();

            ResultSet result = stat.executeQuery("SELECT * FROM uzytkownik WHERE id = " + id + ";");

            while (result.next()) {
                uzytkownik.setId(result.getInt("id"));
                uzytkownik.setImie(result.getString("imie"));
                uzytkownik.setNazwisko(result.getString("nazwisko"));
                uzytkownik.setPesel(result.getString("pesel"));
                uzytkownik.setLogin(result.getString("login"));
                uzytkownik.setMail(result.getString("mail"));
                uzytkownik.setRola(result.getString("rola"));
                uzytkownik.setWeryfikacja(result.getInt("weryfikacja"));
            }
            stat.close();
            polaczenie.close();
            return uzytkownik;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Uzytkownik wczytajUzytkownik_login(String login) {
        Uzytkownik uzytkownik = new Uzytkownik();
        try {

            DBConnection DBpolaczenie = new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();

            ResultSet result = stat.executeQuery("SELECT * FROM uzytkownik WHERE login = '" + login + "';");

            while (result.next()) {
                uzytkownik.setId(result.getInt("id"));
                uzytkownik.setImie(result.getString("imie"));
                uzytkownik.setNazwisko(result.getString("nazwisko"));
                uzytkownik.setPesel(result.getString("pesel"));
                uzytkownik.setLogin(result.getString("login"));
                uzytkownik.setMail(result.getString("mail"));
                uzytkownik.setRola(result.getString("rola"));
                uzytkownik.setWeryfikacja(result.getInt("weryfikacja"));
            }
            stat.close();
            polaczenie.close();
            return uzytkownik;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Konstruktory
    public Uzytkownik() {
    }

    public Uzytkownik(int id, String imie, String nazwisko, String pesel, String login, String haslo, String mail, String rola, int weryfikacja) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.login = login;
        this.haslo = haslo;
        this.mail = mail;
        this.rola = rola;
        this.weryfikacja = weryfikacja;
    }

}
