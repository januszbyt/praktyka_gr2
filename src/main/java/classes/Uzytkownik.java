package classes;

import java.sql.*;
import application.DBConnection;

public class Uzytkownik
{
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
    public int getId() { return id; }

    public void setId(int id)
    {
        this.id = id;
    }



    public String getImie()
    {
        return imie;
    }

    public void setImie(String imie)
    {
        this.imie = imie;
    }



    public String getNazwisko()
    {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko)
    {
        this.nazwisko = nazwisko;
    }



    public String getPesel()
    {
        return pesel;
    }

    public void setPesel(String pesel)
    {
        this.pesel = pesel;
    }



    public String getLogin()
    {
        return login;
    }

    public void setLogin(String login)
    {
        this.login = login;
    }



    public String getHaslo()
    {
        return haslo;
    }

    public void setHaslo(String haslo)
    {
        this.haslo = haslo;
    }



    public String getMail()
    {
        return mail;
    }

    public void setMail(String mail)
    {
        this.mail = mail;
    }



    public String getRola()
    {
        return rola;
    }

    public void setRola(String rola)
    {
        this.rola = rola;
    }



    public int getWeryfikacja()
    {
        return weryfikacja;
    }

    public void setWeryfikacja(int weryfikacja)
    {
        this.weryfikacja = weryfikacja;
    }



/*
    public void zaloguj(String login)
    {

         polaczenie= new DBConnection();
        Connection statusDB= polaczenie.getConnection();

        try
        {
            ResultSet result = stat.executeQuery("SELECT * FROM uczen");
            int id, idKlasy;
            String imie, nazwisko, pesel;
            while(result.next())
            {
                id = result.getInt("id");
                imie = result.getString("imie");
                nazwisko = result.getString("nazwisko");
                pesel = result.getString("pesel");
                idKlasy = result.getInt("idKlasy");
                uczniowie.add(new Uczen(id, imie, nazwisko, pesel, idKlasy));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }



    }



*/


    // Konstruktory
    public Uzytkownik() { }

    public Uzytkownik(int id, String imie, String nazwisko, String pesel, String login, String haslo, String mail, String rola, int weryfikacja)
    {
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
