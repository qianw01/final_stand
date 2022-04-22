package com.example.finalstand;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DriverExpandedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DriverExpandedFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String EXTRA_ENTRY_MODEL = "entry";
    private static final String EXTRA_TRANSITION_NAME = "transition name";

    public DriverExpandedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DriverExpandedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DriverExpandedFragment newInstance(DriverEntryModel eM, String transitionName) {
        DriverExpandedFragment fragment = new DriverExpandedFragment();
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
        return inflater.inflate(R.layout.fragment_driver_expanded, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DriverEntryModel animalItem = getArguments().getParcelable(EXTRA_ENTRY_MODEL);
        String transitionName = getArguments().getString(EXTRA_TRANSITION_NAME);

        TextView driverName = (TextView) view.findViewById(R.id.driverName);
        driverName.setTransitionName(transitionName);
    }
}