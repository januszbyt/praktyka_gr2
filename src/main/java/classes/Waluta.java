package classes;

public class Waluta
{
    // Lista zmiennych
    private int id;
    private String nazwa;
    private String skrot;
    private float wartosc;



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



    public String getSkrot()
    {
        return skrot;
    }

    public void setSkrot(String skrot)
    {
        this.skrot = skrot;
    }



    public float getWartosc()
    {
        return wartosc;
    }

    public void setWartosc(float wartosc)
    {
        this.wartosc = wartosc;
    }




    // Konstruktory
    public Waluta() { }

    public Waluta(int id, String nazwa, String skrot, float wartosc)
    {
        this.id = id;
        this.nazwa = nazwa;
        this.skrot = skrot;
        this.wartosc = wartosc;
    }



}
