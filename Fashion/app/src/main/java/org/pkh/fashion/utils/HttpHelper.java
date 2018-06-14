package org.pkh.fashion.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * This is http helper , thus should not call from UI thread
 */
public class HttpHelper {

    public String getDataFromServer(String reqUrl) {
        String response = null;
        try {
            URL url = new URL(reqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            // read the response stream received from GET protocol
            InputStream in = new BufferedInputStream(conn.getInputStream());
            response = convertResponseStreamToString(in);
        } catch (MalformedURLException e) {
            Logger.logE("HttpHelper MalformedURLException: " + e.getMessage());
        } catch (ProtocolException e) {
            Logger.logE("HttpHelper ProtocolException: " + e.getMessage());
        } catch (IOException e) {
            Logger.logE("HttpHelper IOException: " + e.getMessage());
        } catch (Exception e) {
            Logger.logE("HttpHelper Exception: " + e.getMessage());
        }
        return response;
    }
    private String convertResponseStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
