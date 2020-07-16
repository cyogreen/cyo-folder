package com.skycode.formocart_employee.navigation_menu.order;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DrawerClass;

public class Cod_OrderActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod__order);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(Cod_OrderActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
}
