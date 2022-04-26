package com.example.finalstand;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
 * Use the {@link GPFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GPFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static RecyclerView recyclerView;
    private static GPAdapter GPA;

    public GPFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GPFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GPFragment newInstance(String param1, String param2) {
        GPFragment fragment = new GPFragment();
        Bundle args = new Bundle();
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
        return inflater.inflate(R.layout.fragment_gp, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[][] gpInfo = readJSON();

        Spinner spin = view.findViewById(R.id.gPSpinner);

        List<String> spinnerArray =  new ArrayList<String>();
        for (String[] s: gpInfo) {
            spinnerArray.add(s[0]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        TextView gPTitle = (TextView) view.findViewById(R.id.gPTitle);
        TextView gPD = (TextView) view.findViewById(R.id.gPDate);
        TextView gPR = (TextView) view.findViewById(R.id.gPRound);
        gPTitle.setText("GP: " + gpInfo[0][0]);
        gPD.setText("Dates: " + gpInfo[0][1]);
        gPR.setText("Rounds: " + gpInfo[0][2]);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int currGP = spin.getSelectedItemPosition();

                gPTitle.setText("GP: " + gpInfo[currGP][0]);
                gPD.setText("Dates: " + gpInfo[currGP][1]);
                gPR.setText("Rounds: " + gpInfo[currGP][2]);

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
            JSONArray jArray = json.getJSONArray("Grand Prix");
            String[][] contentArray = new String[jArray.length()][7];

            //Log.d("log", String.valueOf(jArray.length()));
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jInitObj = jArray.getJSONObject(i);
                String key = (jInitObj.keys().next());
                JSONObject jOInside = ((JSONObject)jInitObj.get(key));
                contentArray[i][0] = jOInside.getString("Name");
                contentArray[i][1] = jOInside.getString("Date");
                contentArray[i][2] = jOInside.getString("Round");
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
            InputStream iS = getContext().getAssets().open("jsondata/gp.json");
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