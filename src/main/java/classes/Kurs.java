package classes;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;

    // **************************
    //  Przykładowe użycie:
    //  var kursik = new Kurs();
    //  double euro = kursik.getKurs("EUR");
    // **************************

public class Kurs {
    private  float mnoznik;
    public static String BaseURL = "http://api.nbp.pl/api";

    public static double getKurs(String skrotWaluty) throws IOException, InterruptedException {
        Gson gson = new Gson();
        var client = HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(URI.create(BaseURL+"/exchangerates/rates/A/"+skrotWaluty)).build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String odpowiedz = response.body();
        KursWaluta kurs = gson.fromJson(odpowiedz, KursWaluta.class);

        return kurs.rates[0].mid;
    }

}
