package com.example.finalstand;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.transition.Hold;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Driver;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DriverFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriverFragment extends Fragment implements ItemOnClickListener<DriverEntryModel> {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static RecyclerView recyclerView;
    private static DriverAdapter driverA;
    private static ArrayList<DriverEntryModel> data;

    public DriverFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DriverFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DriverFragment newInstance(String param1, String param2) {
        DriverFragment fragment = new DriverFragment();
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
        setExitTransition(new Hold());
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
        data = new ArrayList<DriverEntryModel>();

        //get info from json
        String[][] driverData = readJSON();

        //populate data
        for (int i = 0; i < driverData.length; i++) {
            DriverEntryModel DriverE = new DriverEntryModel(driverData[i][0], "desc", driverData[i][2], driverData[i][3], driverData[i][4], driverData[i][5], driverData[i][6], 1);
            data.add(DriverE);
        }

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_driver, container, false);

        driverA = new DriverAdapter(data, this);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclingDriver);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(driverA);

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        // this is required to animate correctly when the user returns
        // to the origin fragment, gives a chance for the layout
        // to be fully laid out before animating it
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        postponeEnterTransition();
        viewGroup
                .getViewTreeObserver()
                .addOnPreDrawListener(() -> {
                    startPostponedEnterTransition();
                    return true;
                });

        super.onViewCreated(view, savedInstanceState);

    }

    //---------------json stuff--------------------
    private String[][] readJSON() {
        try {
            JSONObject json = new JSONObject(loadJSON());
            JSONArray jArray = json.getJSONArray("Drivers");
            String[][] contentArray = new String[jArray.length()][7];

            for (int i = 0; i < jArray.length(); i++) {
                //gets proper JSON stuff
                JSONObject jInitObj = jArray.getJSONObject(i);
                String key = (jInitObj.keys().next());
                JSONObject jOInside = ((JSONObject)jInitObj.get(key));

                //populates content array
                contentArray[i][0] = jOInside.getString("Name");
                contentArray[i][2] = jOInside.getString("Team");
                contentArray[i][3] = jOInside.getString("Number");
                contentArray[i][4] = jOInside.getString("Country");
                contentArray[i][5] = jOInside.getString("Podiums");
                contentArray[i][6] = jOInside.getString("Highest Race Finish");
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
            InputStream iS = getContext().getAssets().open("jsondata/drivers.json");
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
    public void onItemClick(int pos, DriverEntryModel item, MaterialCardView cardView) {
        String transName = ViewCompat.getTransitionName(cardView);
        Fragment dEF = DriverExpandedFragment.newInstance(item, transName);
        Log.d("log", "onitemclick: " + transName);
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(cardView, transName)
                .addToBackStack(null)
                .replace(R.id.navHostFrag, dEF)
                .commit();
    }
}