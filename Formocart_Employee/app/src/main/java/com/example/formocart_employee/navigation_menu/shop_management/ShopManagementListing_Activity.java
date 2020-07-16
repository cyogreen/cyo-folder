package com.skycode.formocart_employee.navigation_menu.shop_management;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DashboardActivity;
import com.skycode.formocart_employee.dashboard.DrawerClass;

public class ShopManagementListing_Activity extends AppCompatActivity {

    private CardView visitors_shop,confirmed_shop;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_management_listing_);
        get_find_viewById();
        visitors_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopManagementListing_Activity.this, Visitor_Shop_Activity.class));
            }
        });
        confirmed_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ShopManagementListing_Activity.this, Confirmed_Shop_Activity.class));
            }
        });
    }

    private void get_find_viewById() {
        visitors_shop=(CardView)findViewById(R.id.visitor_shop);
        confirmed_shop=(CardView) findViewById(R.id.confirmed_shop);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(ShopManagementListing_Activity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(ShopManagementListing_Activity.this,DashboardActivity.class));
    }
}
