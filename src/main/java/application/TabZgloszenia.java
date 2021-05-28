package application;

import java.util.Date;

public class TabZgloszenia {
    String tytul, tresc_user, tresc_admin, status;
    Integer id, uzytkownik;
    Date data;

    public TabZgloszenia(String tytul, String tresc_user, String tresc_admin, String status, Integer id, Integer uzytkownik) {
        this.tytul = tytul;
        this.tresc_user = tresc_user;
        this.tresc_admin = tresc_admin;
        this.status = status;
        this.id = id;
        this.uzytkownik = uzytkownik;

    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc_user() {
        return tresc_user;
    }

    public void setTresc_user(String tresc_user) {
        this.tresc_user = tresc_user;
    }

    public String getTresc_admin() {
        return tresc_admin;
    }

    public void setTresc_admin(String tresc_admin) {
        this.tresc_admin = tresc_admin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUzytkownik() {
        return uzytkownik;
    }

    public void setUzytkownik(Integer uzytkownik) {
        this.uzytkownik = uzytkownik;
    }


}
