package com.janani123.firebaseregistrationlogin;

import android.content.Intent;
import android.os.AsyncTask;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class fetchData extends AsyncTask<Void,Void,Void> {
    private String s1,s2,d,s3,s4,s5;
    String data ="";
    String dataParsed = "";
    String adataParsed = "";
    String singleParsed ="";
    public double latitude;
    public double longitude;
    private int t,h,m,p;

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("https://api.thingspeak.com/channels/711572/feeds.json?api_key=8FRD1H3IYBZQ6WT1&results=2");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            JSONObject JA = new JSONObject(data);
            JSONArray jr = JA.getJSONArray("feeds");
            for(int i =1 ;i <jr.length(); i++){
                JSONObject jb = jr.getJSONObject(i);
                singleParsed =  "Longitude:"+"\t\t" + jb.get("field1") + "\n"+
                        "Latitude:" +"\t\t" +jb.get("field2") + "\n";

                s1 = (String) jb.get("field1");
                s2 = (String) jb.get("field2");

                dataParsed = dataParsed + s1;
                adataParsed = adataParsed + s2;



                latitude = Double.parseDouble(dataParsed);
                longitude = Double.parseDouble(adataParsed);

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        MapsActivity.lat = this.latitude;
        MapsActivity.lang = this.longitude;


    }
}