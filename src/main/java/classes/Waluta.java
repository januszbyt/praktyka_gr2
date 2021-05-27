package classes;

import java.sql.ResultSet;
import java.sql.SQLException;

import static classes.DBManager.select;

public class Waluta {
    // Lista zmiennych
    private int id;
    private String nazwa;
    private String skrot;
    private float wartosc;

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

    public String getSkrot() {
        return skrot;
    }

    public void setSkrot(String skrot) {
        this.skrot = skrot;
    }

    public float getWartosc() {
        return wartosc;
    }

    public void setWartosc(float wartosc) {
        this.wartosc = wartosc;
    }

    public static Waluta wczytajWaluta_id(int id) {
        Waluta waluta = new Waluta();
        try {
            ResultSet result = select("SELECT * FROM waluta WHERE id = " + id + ";");

            while (result.next()) {
                waluta.setId(result.getInt("id"));
                waluta.setNazwa(result.getString("nazwa"));
                waluta.setSkrot(result.getString("skrot"));
                waluta.setWartosc(result.getFloat("wartosc"));
            }
            return waluta;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Waluta wczytajWaluta_nazwa(String nazwa) {
        Waluta waluta = new Waluta();
        try {
            ResultSet result = select("SELECT * FROM waluta WHERE nazwa = '" + nazwa + "';");

            while (result.next()) {
                waluta.setId(result.getInt("id"));
                waluta.setNazwa(result.getString("nazwa"));
                waluta.setSkrot(result.getString("skrot"));
                waluta.setWartosc(result.getFloat("wartosc"));
            }
            return waluta;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static Waluta wczytajWaluta_skrot(String skrot) {
        Waluta waluta = new Waluta();
        try {
            ResultSet result = select("SELECT * FROM waluta WHERE skrot = '" + skrot + "';");

            while (result.next()) {
                waluta.setId(result.getInt("id"));
                waluta.setNazwa(result.getString("nazwa"));
                waluta.setSkrot(result.getString("skrot"));
                waluta.setWartosc(result.getFloat("wartosc"));
            }
            return waluta;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    // Konstruktory
    public Waluta() {
    }

    public Waluta(int id, String nazwa, String skrot, float wartosc) {
        this.id = id;
        this.nazwa = nazwa;
        this.skrot = skrot;
        this.wartosc = wartosc;
    }

}
