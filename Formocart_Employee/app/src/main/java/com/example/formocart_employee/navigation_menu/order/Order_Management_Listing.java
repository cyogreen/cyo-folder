package com.skycode.formocart_employee.navigation_menu.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DrawerClass;

public class Order_Management_Listing extends AppCompatActivity {

    private CardView confirmed_orders,cod_orders;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__management_);
        get_find_viewById();
        confirmed_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order_Management_Listing.this, Confirmed_OrderActivity.class));
            }
        });
        cod_orders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Order_Management_Listing.this, Cod_OrderActivity.class));
            }
        });
    }

    private void get_find_viewById() {
        confirmed_orders=(CardView)findViewById(R.id.confirmed_order);
        cod_orders=(CardView) findViewById(R.id.cod_orders);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(Order_Management_Listing.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
}
