package classes;

import application.DBConnection;
import application.Powiadomienia;

import java.sql.*;

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


    public float getSaldo() { return saldo; }

    public void setSaldo(float saldo) { this.saldo = saldo; }


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



    public static Rachunek wczytajRachunek_numer(String numer){
        Rachunek rachunek = new Rachunek();
        try
        {

            DBConnection DBpolaczenie= new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();
            Statement stat = polaczenie.createStatement();

            ResultSet result = stat.executeQuery("SELECT * FROM rachunek WHERE numer = '"+numer+"';");

            while(result.next())
            {
                rachunek.setId(result.getInt("id"));
                rachunek.setNazwa(result.getString("nazwa"));
                rachunek.setNumer(result.getString("numer"));
                rachunek.setSaldo(result.getFloat("saldo"));
                rachunek.setUzytkownik(result.getInt("uzytkownik"));
                rachunek.setWaluta(result.getInt("waluta"));
            }
            stat.close();
            polaczenie.close();
            return rachunek;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }


    }

    public static boolean weryfikacjaSaldo(Rachunek rachunek, float kwota){
        if(kwota <= 0)
        {
            Powiadomienia.alertPrzelewWeryfikacjaSaldo2();
            return false;
        }
        if(rachunek.saldo < kwota)
        {
            Powiadomienia.alertPrzelewWeryfikacjaSaldo();
            return false;
        }
        else return true;
    }



    public static void dodajSaldo(Rachunek rachunek, float kwota)
    {
        rachunek.saldo += kwota;
        try {

            DBConnection DBpolaczenie = new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();

            Statement statement = polaczenie.createStatement();
            String query = "update rachunek set saldo = "+rachunek.getSaldo()+" where id = "+rachunek.getId();
            statement.executeUpdate(query);

            polaczenie.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }


    public static void usunSaldo(Rachunek rachunek, float kwota)
    {
        rachunek.saldo -= kwota;
        try {

            DBConnection DBpolaczenie = new DBConnection();
            Connection polaczenie = DBpolaczenie.getConnection();

            Statement statement = polaczenie.createStatement();
            String query = "update rachunek set saldo = "+rachunek.getSaldo()+" where id = "+rachunek.getId();
            statement.executeUpdate(query);

            polaczenie.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

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
