package com.example.android.lendabook.Utils;

import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android.lendabook.Add.AddActivity;
import com.example.android.lendabook.Home.CameraFragment;

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

/**
 * Created by kostin on 2019-02-31.
 * Class for google API for scanning ISBN.
 */

/**
 * Fetches data from google books api.
 * This version only does it for ISBN since search isn't implemented.
 * Google books API based on https://www.youtube.com/watch?v=Vcn4OuV4Ixg https://stackoverflow.com/questions/14571478/using-google-books-api-in-android
 * Made by Kostin
 */

public class fetchData extends AsyncTask <String, Void, Void> {
    String data = "";
    public static String parsedTitle = "n";
    public static String parsedAuthor = "n";
    public static String parsedDescription = "n";
    public static String isbn = "n";

    private String[] searchResults = new String[5];
    /**
     * commands for using google API in the background
     *
     * @param parms
     */
    @Override
    protected Void doInBackground(String... parms) {
        try {
            URL url = new URL("https://www.googleapis.com/books/v1/volumes?q=isbn:" + parms[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            // fill data with each line
            while (line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }

            // make json object
            JSONObject JO = new JSONObject(data);
            // calls fill classes to fill textboxes
            searchResults[0] = JO.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
            searchResults[2] = parms[0];
            searchResults[1] = JO.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
            searchResults[4] = JO.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    /**
     * Executes and moves to a new activity.
     *
     * @param aVoid
     */
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Intent myIntent = new Intent(CameraFragment.cameraActivity, AddActivity.class);
        myIntent.putExtra("searchResults", searchResults);
        myIntent.putExtra("cameFrom", 1);
        CameraFragment.cameraActivity.startActivity(myIntent);
    }
}
