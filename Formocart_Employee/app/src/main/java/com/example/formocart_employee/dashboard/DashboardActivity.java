package com.example.formocart_employee.dashboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.navigation_menu.order.Confirmed_OrderActivity;
import com.skycode.formocart_employee.navigation_menu.payout.Pending_Payout_Activity;
import com.skycode.formocart_employee.navigation_menu.payout.Shop_Outstanding_Payout;
import com.skycode.formocart_employee.navigation_menu.shop_management.Confirmed_Shop_Activity;
import com.skycode.formocart_employee.navigation_menu.shop_management.ShopManagementListing_Activity;
import com.skycode.formocart_employee.navigation_menu.shop_management.Visitor_Shop_Activity;
import com.skycode.formocart_employee.signin.SessionManager;

public class DashboardActivity extends AppCompatActivity {
    public DrawerLayout drawer;
    public CardView con_order_card,  cod_order_card, shop_outstanding_card, con_shop_card, pending_payout_card
            ,confirmed_shop_card,total_sales_card,confirmed_order_card,visitors_shop_card,pending_order_card;
    boolean doubleBackToExitPressedOnce = false;
    private TextView tt_id;
    private SessionManager sessionManager;
    private CardView payout_card,order_card,shop_card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        get_find_view_byId();
        if(sessionManager.getID()==null){
            tt_id.setVisibility(View.GONE);
        }
        tt_id.setText("Hi "+sessionManager.getUser()+"\n"+"Your Emp ID "+sessionManager.getID());

        total_sales_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, Confirmed_OrderActivity.class));
            }
        });
        payout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, Pending_Payout_Activity.class));
            }
        });
        order_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, Confirmed_OrderActivity.class));
            }
        });
        shop_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DashboardActivity.this, ShopManagementListing_Activity.class));
            }
        });
//        visitors_shop_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DashboardActivity.this, Visitor_Shop_Activity.class));
//            }
//        });
//        confirmed_shop_card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(DashboardActivity.this, Confirmed_Shop_Activity.class));
//            }
//        });
    }

    private void get_find_view_byId() {
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(DashboardActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content), drawer);
        total_sales_card = (CardView) findViewById(R.id.total_sales_card);
        payout_card = (CardView) findViewById(R.id.payout_card);
        order_card = (CardView) findViewById(R.id.order_card);
        shop_card = (CardView) findViewById(R.id.shop_card);
        tt_id=(TextView)findViewById(R.id.tt_id);
        sessionManager=new SessionManager(DashboardActivity.this);
        get_location();

    }

    private void get_location() {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED) {
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
            }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(DashboardActivity.this);
        builder1.setMessage("Are you sure want to exit ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        finish();
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
