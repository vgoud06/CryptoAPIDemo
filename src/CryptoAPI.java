import com.google.gson.JsonElement;
import java.util.Scanner;
import java.io.IOException;

public class CryptoAPI {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("Which cryptocurrency would you like information about.");
        String crypto = input.nextLine();
        getCryptoInfo(crypto);
    }

    public static void getCryptoInfo(String crypto) throws IOException {
        crypto = crypto.toUpperCase();
        JsonElement cryptoJson = Tools.getJson("https://min-api.cryptocompare.com/data/pricemultifull?fsyms=" + crypto + "&tsyms=USD&api_key={a8b0a450f40acd04435fbedc7d54dac907468d9b934f7b1b84dd601c2a04b8fc}");
        String symbol = Tools.get(cryptoJson, "RAW", crypto, "USD", "FROMSYMBOL").getAsString();
        double price = Tools.get(cryptoJson, "RAW", crypto, "USD", "PRICE").getAsDouble();
        double changePCT24 = Tools.get(cryptoJson, "RAW", crypto, "USD", "CHANGEPCT24HOUR").getAsDouble();
        double changePrice24 = Tools.get(cryptoJson, "RAW", crypto, "USD", "CHANGE24HOUR").getAsDouble();
        double changePCT1 = Tools.get(cryptoJson, "RAW", crypto, "USD", "CHANGEPCTHOUR").getAsDouble();
        double changePrice1 = Tools.get(cryptoJson, "RAW", crypto, "USD", "CHANGEHOUR").getAsDouble();

        System.out.println("\nSymbol: " + symbol);
        System.out.println("Price: $" + price);
        if (changePCT1 < 0)
            System.out.println("Price percentage change in the last hour: " + changePCT1 + "%");
        else
            System.out.println("Price percentage change in the last hour: +" + changePCT1 + "%");
        if (changePrice1 < 0)
            System.out.println("Price change in the last hour:" + changePrice1 + " USD");
        else
            System.out.println("Price change in the last hour: +" + changePrice1 + " USD");
        if (changePCT24 < 0)
            System.out.println("Price percentage change in the last 24 hours: " + changePCT24 + "%");
        else
            System.out.println("Price percentage change in the last 24 hours: +" + changePCT24 + "%");
        if (changePrice24 < 0)
            System.out.println("Price change in the last 24 hours: " + changePrice24 + " USD");
        else
            System.out.println("Price change in the last 24 hours: +" + changePrice24 + " USD");
    }



}