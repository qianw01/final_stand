package com.example.finalstand;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SeasonsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SeasonsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String[] seas = {"2021", "2022"};

    public SeasonsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SeasonsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SeasonsFragment newInstance(String param1, String param2) {
        SeasonsFragment fragment = new SeasonsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_seasons, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[][] seasonsInfo = readJSON();

        Spinner spin = view.findViewById(R.id.seasonsSpinner);

        List<String> spinnerArray =  new ArrayList<String>();
        for (String[] s: seasonsInfo) {
            spinnerArray.add(s[0]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        TextView sTitle = (TextView) view.findViewById(R.id.seasonTitle);
        TextView sd1 = (TextView) view.findViewById(R.id.seasonD1);
        TextView sd2 = (TextView) view.findViewById(R.id.seasonD2);
        TextView sd3 = (TextView) view.findViewById(R.id.seasonD3);
        TextView sd4 = (TextView) view.findViewById(R.id.seasonD4);
        TextView sd5 = (TextView) view.findViewById(R.id.seasonD5);
        TextView sd6 = (TextView) view.findViewById(R.id.seasonD6);
        sTitle.setText("Season: " + seasonsInfo[0][0]);
        sd1.setText("Driver's Champion: " + seasonsInfo[0][1]);
        sd2.setText("Driver's Second Place: " + seasonsInfo[0][2]);
        sd3.setText("Driver's Third Place: " + seasonsInfo[0][3]);
        sd4.setText("Constructor's Champion: " + seasonsInfo[0][4]);
        sd5.setText("Constructor's Second Place: " + seasonsInfo[0][5]);
        sd6.setText("Constructor's Third Place: " + seasonsInfo[0][6]);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int currSeas = spin.getSelectedItemPosition();

                sTitle.setText("Season: " + seasonsInfo[currSeas][0]);
                sd1.setText("Driver's Champion: " + seasonsInfo[currSeas][1]);
                sd2.setText("Driver's Second Place: " + seasonsInfo[currSeas][2]);
                sd3.setText("Driver's Third Place: " + seasonsInfo[currSeas][3]);
                sd4.setText("Constructor's Champion: " + seasonsInfo[currSeas][4]);
                sd5.setText("Constructor's Second Place: " + seasonsInfo[currSeas][5]);
                sd6.setText("Constructor's Third Place: " + seasonsInfo[currSeas][6]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    //---------------json stuff--------------------
    private String[][] readJSON() {
        try {
            JSONObject json = new JSONObject(loadJSON());
            JSONArray jArray = json.getJSONArray("Seasons");
            String[][] contentArray = new String[jArray.length()][7];

            //Log.d("log", String.valueOf(jArray.length()));
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jInitObj = jArray.getJSONObject(i);
                String key = (jInitObj.keys().next());
                JSONObject jOInside = ((JSONObject)jInitObj.get(key));
                contentArray[i][0] = jOInside.getString("Season");
                contentArray[i][1] = jOInside.getString("Driver's Champion");
                contentArray[i][2] = jOInside.getString("Driver's Second Place");
                contentArray[i][3] = jOInside.getString("Driver's Third Place");
                contentArray[i][4] = jOInside.getString("Constructor's Champion");
                contentArray[i][5] = jOInside.getString("Constructor's Second Place");
                contentArray[i][6] = jOInside.getString("Constructor's Third Place");
            }
            return contentArray;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String loadJSON() {
        String json;
        try {
            InputStream iS = getContext().getAssets().open("jsondata/seasons.json");
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