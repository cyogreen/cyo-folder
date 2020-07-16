package com.malaysia.bri.navigationActivity.support_solved_and_unsolved;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.malaysia.bri.R;

public class Support_Unsolved extends Fragment {

    public Support_Unsolved() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.unsolved_fragment, container, false);
        return view;
    }
}
