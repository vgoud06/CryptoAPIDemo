import com.google.gson.JsonElement;
import java.io.IOException;
import java.util.Scanner;

public class IpInfo {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);
        String ip = "";

        System.out.println("Would you like to see information about your IP or a different IP?: ('m', 'd')");
        String command = input.nextLine();
        if (command.equals("m")) {
            JsonElement ipJson = Tools.getJson("https://api.ipify.org?format=json");
            ip = Tools.get(ipJson, "ip").getAsString();
        } else if (command.equals("d")) {
            System.out.println("Please enter the IP address that you want information about: ");
            ip = input.nextLine();
        }
        else {
            System.out.println("Invalid command.");
        }
        printIP(ip);

        //keep scanning in main. process user input and call printIP method based on that.

    }

    // create method that prints IP details given IP address
    public static void printIP(String ipAddress) throws IOException {
        JsonElement ipInfoJson = Tools.getJson("https://ipinfo.io/" + ipAddress + "/geo");
        String ipInfoCity = Tools.get(ipInfoJson, "city").getAsString();
        String ipInfoState = Tools.get(ipInfoJson, "region").getAsString();
        String ipInfoCountry = Tools.get(ipInfoJson, "country").getAsString();
        String ipInfoLocation = Tools.get(ipInfoJson, "loc").getAsString();
        String ipInfoPostal = Tools.get(ipInfoJson, "postal").getAsString();
        String ipInfoTimeZone = Tools.get(ipInfoJson, "timezone").getAsString();
        System.out.println("\nHere is information about your IP address:");
        System.out.println("IP Address: " + ipAddress);
        System.out.println("City: " + ipInfoCity);
        System.out.println("State: " + ipInfoState);
        System.out.println("Country: " + ipInfoCountry);
        System.out.println("Longitude and Latitude: " + ipInfoLocation);
        System.out.println("Area/Postal code: " + ipInfoPostal);
        System.out.println("Timezone: " + ipInfoTimeZone);
    }
}