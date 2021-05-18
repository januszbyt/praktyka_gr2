package classes;

public class KursWaluta {
    public String table;
    public String currency;
    public String code;
    public Rate[] rates;
}
class Rate {
    public String no;
    public String effectiveDate;
    public double mid;
}
