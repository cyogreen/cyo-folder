package com.skycode.formocart_employee.navigation_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.skycode.formocart_employee.R;

public class Support_Solved extends Fragment {

    public Support_Solved() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.solved_fragment, container, false);
        return view;
    }
}
