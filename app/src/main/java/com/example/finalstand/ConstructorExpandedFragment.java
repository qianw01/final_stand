package com.example.finalstand;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConstructorExpandedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConstructorExpandedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String EXTRA_ENTRY_MODEL = "entry";
    private static final String EXTRA_TRANSITION_NAME = "transition name";

    public ConstructorExpandedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ConstructorExpandedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConstructorExpandedFragment newInstance(ConstructorEntryModel eM, String transitionName) {
        ConstructorExpandedFragment fragment = new ConstructorExpandedFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_ENTRY_MODEL, eM);
        args.putString(EXTRA_TRANSITION_NAME, transitionName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_constructor_expanded, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ConstructorEntryModel cEM = getArguments().getParcelable(EXTRA_ENTRY_MODEL);
        String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);

        TextView constructorName = (TextView) view.findViewById(R.id.constructorName);
        TextView constructorDesc = (TextView) view.findViewById(R.id.constructorDesc);
        TextView constructorD1 = (TextView) view.findViewById(R.id.constructorD1);
        TextView constructorD2 = (TextView) view.findViewById(R.id.constructorD2);

        constructorName.setText(cEM.getName());
        constructorD1.setText("Driver 1: " + cEM.getDriver1());
        constructorD2.setText("Driver 2: " + cEM.getDriver2());
        constructorDesc.setText(cEM.getDesc());

        constructorName.setTransitionName(transitionName);
    }
}