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

//sauce https://www.youtube.com/watch?v=Vcn4OuV4Ixg https://stackoverflow.com/questions/14571478/using-google-books-api-in-android

public class fetchData extends AsyncTask <String, Void, Void> {
    String data = "";
    public static String parsedTitle = "n";
    public static String parsedAuthor = "n";
    public static String parsedDescription = "n";
    public static String isbn = "n";

    private String[] searchResults = new String[5];
    @Override
    protected Void doInBackground(String... parms) {
        try {
            Log.d("999:", "trying: " + parms[0]);
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
            Log.d("999", "done searching");
            searchResults[0] = JO.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("title");
            searchResults[2] = parms[0];
            searchResults[1] = JO.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getJSONArray("authors").getString(0);
            searchResults[4] = JO.getJSONArray("items").getJSONObject(0).getJSONObject("volumeInfo").getString("description");
            Log.d("999", "found: " + searchResults[0]);
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
        Intent myIntent = new Intent(CameraFragment.cameraActivity, AddActivity.class);
        myIntent.putExtra("searchResults", searchResults);
        myIntent.putExtra("cameFrom", 1);
        CameraFragment.cameraActivity.startActivity(myIntent);
    }
}
