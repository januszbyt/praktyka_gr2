package classes;

public class Rachunek
{
    // Lista zmiennych
    private int id;
    private String nazwa;
    private String numer;
    private int uzytkownik;
    private int waluta;



    // Lista metod związanych z klasą
    public int getId() { return id; }

    public void setId(int id)
    {
        this.id = id;
    }



    public String getNazwa()
    {
        return nazwa;
    }

    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }



    public String getNumer()
    {
        return numer;
    }

    public void setNumer(String numer)
    {
        this.numer = numer;
    }



    public int getUzytkownik()
    {
        return uzytkownik;
    }

    public void setUzytkownik(int uzytkownik)
    {
        this.uzytkownik = uzytkownik;
    }



    public int getWaluta()
    {
        return waluta;
    }

    public void setWaluta(int waluta)
    {
        this.waluta = waluta;
    }




    // Konstruktory
    public Rachunek() { }

    public Rachunek(int id, String nazwa, String numer, int uzytkownik, int waluta)
    {
        this.id = id;
        this.nazwa = nazwa;
        this.numer = numer;
        this.uzytkownik = uzytkownik;
        this.waluta = waluta;
    }



}
