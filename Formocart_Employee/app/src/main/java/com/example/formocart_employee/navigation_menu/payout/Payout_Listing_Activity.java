package com.skycode.formocart_employee.navigation_menu.payout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DrawerClass;

public class Payout_Listing_Activity extends AppCompatActivity {

    private CardView pending_payout,shop_outstanding_payout;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout__listing_);
        get_find_viewById();
        pending_payout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Payout_Listing_Activity.this,Pending_Payout_Activity.class));
            }
        });
        shop_outstanding_payout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Payout_Listing_Activity.this,Shop_Outstanding_Payout.class));
            }
        });
    }

    private void get_find_viewById() {
        pending_payout=(CardView)findViewById(R.id.pending_payout);
        shop_outstanding_payout=(CardView) findViewById(R.id.shop_outstanding_payout);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(Payout_Listing_Activity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
}
