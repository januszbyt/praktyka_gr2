package classes;

import java.sql.Date;

public class Logi {
    // Lista zmiennych
    private int id;
    private Date data;
    private String typ;
    private int rachunek;
    private String tresc;

    // Lista metod związanych z klasą
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTyp() {
        return typ;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }

    public int getRachunek() {
        return rachunek;
    }

    public void setRachunek(int rachunek) {
        this.rachunek = rachunek;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    // Konstruktory
    public Logi() {
    }

    public Logi(int id, Date data, String typ, int rachunek, String tresc) {
        this.id = id;
        this.data = data;
        this.typ = typ;
        this.rachunek = rachunek;
        this.tresc = tresc;
    }

    public static String przelewWykonujacyLog(String rachunek/*do kogo*/, String kwota, String tresc, String id /* tutaj bedzie id zmiennej sesyjnej*/) {
        String sql = "INSERT INTO logi(typ,rachunek,kwota,tresc,uzytkownik) VALUES('Przelew wychodzacy','" + rachunek + "','" + kwota + "','" + tresc + "','" + id + "')";
        return sql;
    }

    public static String przelewPrzyjmujacyLog(String rachunek /*od kogo przelew*/,String rachunek2 /*na jaki konkretnie rachunek*/, String kwota, String tresc, String id) {
        String sql = "INSERT INTO logi(typ,rachunek,rachunek2,kwota,tresc,uzytkownik) VALUES('Przelew przychodzacy','" + rachunek + "','"+rachunek2+"','"+ kwota + "','" + tresc + "','" +id + "')";
        return sql;
    }



    public static String logLogowanie(String id) {
        String sql = "INSERT INTO logi (typ,tresc,uzytkownik,kwota) VALUES ('Logowanie','Udane logowanie','" + id + "','0.0')";
        return sql;
    }

}
