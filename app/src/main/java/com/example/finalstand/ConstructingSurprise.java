package com.example.finalstand;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class ConstructingSurprise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constructing_surprise);
    }

    private void readJSON() {
        try {
            JSONObject json = new JSONObject(loadJSON());
            JSONArray jArray = json.getJSONArray("constructors");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String loadJSON() {
        String json;
        try {
            InputStream iS = getAssets().open("constructing.json");
            int size = iS.available();
            byte[] buffer = new byte[size];
            iS.read(buffer);
            iS.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return json;
    }
}