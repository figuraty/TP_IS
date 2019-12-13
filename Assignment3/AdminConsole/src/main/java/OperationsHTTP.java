import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class OperationsHTTP {

    public static String HttpRequestGet(String Url) {
        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        HttpURLConnection connection = null;

        try {

            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();



            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            while ((line = reader.readLine()) != null) {
                responseContent.append(line);
            }
        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return responseContent.toString();
    }

    public static boolean HttpRequestPost(String Url) {
        HttpURLConnection connection = null;
        try {

            URL url = new URL(Url);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();

            if(status == 500){
                return false;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));


        } catch (MalformedURLException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }

        return true;
    }
}
