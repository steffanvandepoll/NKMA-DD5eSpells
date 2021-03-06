package com.nakamagaming.dd5espells.helpers;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Steffan on 03-May-15.
 */
public class WebHelper {

    private static final String GOOGLE_API_WEBAPP_URL = "https://script.google.com/macros/s/AKfycbycYXE0ixzCA37uBNv8HxarEU4PgKK2Z8hMAXVLPFpMosMIi7UF/exec?id=";
    private static final String GOOGLE_SPREADSHEET_SHEET = "&sheet=Spells";

    public String getGoogleSpreadsheetByID(String id) throws IOException{
        String url = GOOGLE_API_WEBAPP_URL + id + GOOGLE_SPREADSHEET_SHEET;
        String webPage = "";

        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();

        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8") );
            String data;

            while ((data = reader.readLine()) != null){
                webPage += data;
            }
            return webPage;
        }
        finally {
            urlConnection.disconnect();
            return webPage;
        }
    }

    public String getSpreadsheetFromJSONResource(InputStream is) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        i = is.read();
        while (i != -1) {
            byteArrayOutputStream.write(i);
            i = is.read();
        }
        is.close();
        return byteArrayOutputStream.toString().replaceAll("\\r\\n|\\r|\\n", " ");
    }
}
