package com.skycode.formocart_employee.navigation_menu.payout;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DrawerClass;

import java.util.ArrayList;

public class Shop_Outstanding_Payout extends AppCompatActivity {

    private ArrayList<String> dataArray;
    private Spinner filter_spin;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop__outstanding__payout);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(Shop_Outstanding_Payout.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
        dataArray=new ArrayList<String>();
        dataArray.add("Filter");
        dataArray.add("Today Reports");
        dataArray.add("Weekly Reports");
        dataArray.add("Monthly Reports");
        dataArray.add("Custom Date");
        filter_spin= (Spinner) findViewById(R.id.filter_spin);//fetch the spinner from layout file
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Shop_Outstanding_Payout.this,
                android.R.layout.simple_spinner_item,dataArray);//setting the country_array to spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        filter_spin.setAdapter(adapter);
    }
}
