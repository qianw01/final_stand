package com.example.finalstand;

import android.os.Bundle;

import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConstructorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstructorFragment extends Fragment implements ItemOnClickListener<ConstructorEntryModel> {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static RecyclerView recyclerView;
    private static ConstructorAdapter constructorA;
    private static ArrayList<ConstructorEntryModel> data;

    public ConstructorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConstructorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConstructorFragment newInstance(String param1, String param2) {
        ConstructorFragment fragment = new ConstructorFragment();
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

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();
                        return true;
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        data = new ArrayList<ConstructorEntryModel>();

        //get info from json
        String[][] constructorData = readJSON();

        //populate data
        for (int i = 0; i < constructorData.length; i++) {
            ConstructorEntryModel ConstructorE = new ConstructorEntryModel(constructorData[i][0], constructorData[i][1], constructorData[i][2], i);
            data.add(ConstructorE);
        }

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_constructor, container, false);

        constructorA = new ConstructorAdapter(data, this);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclingConstructor);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(constructorA);

        return v;
    }

    //---------------json stuff--------------------
    private String[][] readJSON() {
        try {
            JSONObject json = new JSONObject(loadJSON());
            JSONArray jArray = json.getJSONArray("Constructors");
            String[][] contentArray = new String[jArray.length()][3];

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject jInitObj = jArray.getJSONObject(i);
                String key = (jInitObj.keys().next());
                JSONObject jOInside = ((JSONObject)jInitObj.get(key));
                contentArray[i][0] = jOInside.getString("Name");
                contentArray[i][1] = jOInside.getString("Driver_1");
                contentArray[i][2] = jOInside.getString("Driver_2");
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
            InputStream iS = getContext().getAssets().open("jsondata/constructors.json");
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

    //------------ItemOnClickListener stuff-------------------
    @Override
    public void onItemClick(int pos, ConstructorEntryModel item, TextView textView) {
        Fragment dEF = ConstructorExpandedFragment.newInstance(item, ViewCompat.getTransitionName(textView));
        Log.d("log", ViewCompat.getTransitionName(textView));
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(textView, ViewCompat.getTransitionName(textView))
                .addToBackStack(null)
                .replace(R.id.navHostFrag, dEF)
                .commit();
    }
}