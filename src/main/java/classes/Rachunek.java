package classes;

import application.Powiadomienia;

import java.sql.*;

import static classes.DBManager.select;
import static classes.DBManager.update;

public class Rachunek {
    // Lista zmiennych
    private int id;
    private String nazwa;
    private String numer;
    private float saldo;
    private int uzytkownik;
    private int waluta;

    // Lista metod związanych z klasą
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public int getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(int uzytkownik) {
        this.uzytkownik = uzytkownik;
    }

    public int getWaluta() {
        return waluta;
    }

    public void setWaluta(int waluta) {
        this.waluta = waluta;
    }

    public static Rachunek wczytajRachunek_numer(String numer) {
        Rachunek rachunek = new Rachunek();
        try {
            ResultSet result = select("SELECT * FROM rachunek WHERE numer = '" + numer + "';");

            while (result.next()) {
                rachunek.setId(result.getInt("id"));
                rachunek.setNazwa(result.getString("nazwa"));
                rachunek.setNumer(result.getString("numer"));
                rachunek.setSaldo(result.getFloat("saldo"));
                rachunek.setUzytkownik(result.getInt("uzytkownik"));
                rachunek.setWaluta(result.getInt("waluta"));
            }
            return rachunek;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static Rachunek wczytajRachunek_id(int id) {
        Rachunek rachunek = new Rachunek();
        try {
            ResultSet result = select("SELECT * FROM rachunek WHERE id = " + id + ";");

            while (result.next()) {
                rachunek.setId(result.getInt("id"));
                rachunek.setNazwa(result.getString("nazwa"));
                rachunek.setNumer(result.getString("numer"));
                rachunek.setSaldo(result.getFloat("saldo"));
                rachunek.setUzytkownik(result.getInt("uzytkownik"));
                rachunek.setWaluta(result.getInt("waluta"));
            }
            return rachunek;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }


    public static boolean weryfikacjaSaldo(Rachunek rachunek, float kwota) {

        if (rachunek.saldo < kwota) {
            Powiadomienia.alertPrzelewWeryfikacjaSaldo();
            return false;
        } else return true;
    }

    public static void dodajSaldo(Rachunek rachunek, float kwota) {
        rachunek.saldo += kwota;
        update("update rachunek set saldo = " + rachunek.getSaldo() + " where id = " + rachunek.getId());
    }

    public static void usunSaldo(Rachunek rachunek, float kwota) {
        rachunek.saldo -= kwota;
            update("update rachunek set saldo = " + rachunek.getSaldo() + " where id = " + rachunek.getId());
    }

    // Konstruktory
    public Rachunek() {
    }

    public Rachunek(int id, String nazwa, String numer, float saldo, int uzytkownik, int waluta) {
        this.id = id;
        this.nazwa = nazwa;
        this.numer = numer;
        this.saldo = saldo;
        this.uzytkownik = uzytkownik;
        this.waluta = waluta;
    }

}
