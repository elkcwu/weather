import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.net.MalformedURLException;
        import java.net.URL;
        import java.net.URLConnection;
        import java.util.Scanner;

public class WeatherRestAPI {
    public static void main(String[] args) {

        System.out.println("please input an argument like these: \"39.7456,-97.0892\" or \"TOP,31,80\" ");
        String inputStr = new Scanner(System.in).nextLine();

        String [] arg = inputStr.split(",");
        if (arg.length <= 1 || arg.length>3) {
            System.out.println("No input");
            return;
        } else {
            if(arg.length == 2) {
                getWeatherPoints(inputStr);
            }
            if(arg.length == 3) {
                getWeatherForecast(inputStr);
            }
        }
    }

    public static void getWeatherPoints(String input) {
        try {
            String urlStr ="https://api.weather.gov/points/" + input;
            URL weatherUrl = new URL(urlStr);
            URLConnection weatherConn = weatherUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(weatherConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getWeatherForecast(String input) {
        try {
            String urlStr = "https://api.weather.gov/gridpoints/";
            String [] args = input.split(",");
            StringBuilder sb = new StringBuilder(urlStr);
            sb.append(args[0] + "/" + args[1] + "," + args[2] + "/forecast");

            URL weatherUrl = new URL(sb.toString());
            URLConnection weatherConn = weatherUrl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(weatherConn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

