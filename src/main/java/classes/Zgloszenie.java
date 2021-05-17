package classes;

import java.sql.Date;

public class Zgloszenie
{
    // Lista zmiennych
    private int id;
    private Date data;
    private String tresc_user;
    private String tresc_admin;
    private String status;
    private int uzytkownik;



    // Lista metod związanych z klasą
    public int getId() { return id; }

    public void setId(int id)
    {
        this.id = id;
    }



    public Date getData()
    {
        return data;
    }

    public void setData(Date data)
    {
        this.data = data;
    }



    public String getTresc_user()
    {
        return tresc_user;
    }

    public void setTresc_user(String tresc_user)
    {
        this.tresc_user = tresc_user;
    }



    public String getTresc_admin()
    {
        return tresc_admin;
    }

    public void setTresc_admin(String tresc_admin)
    {
        this.tresc_admin = tresc_admin;
    }



    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }



    public int getUzytkownik()
    {
        return uzytkownik;
    }

    public void setUzytkownik(int uzytkownik)
    {
        this.uzytkownik = uzytkownik;
    }



    // Konstruktory
    public Zgloszenie() { }

    public Zgloszenie(int id, Date data, String tresc_user, String tresc_admin, String status, int uzytkownik)
    {
        this.id = id;
        this.data = data;
        this.tresc_user = tresc_user;
        this.tresc_admin = tresc_admin;
        this.status = status;
        this.uzytkownik = uzytkownik;
    }



}
