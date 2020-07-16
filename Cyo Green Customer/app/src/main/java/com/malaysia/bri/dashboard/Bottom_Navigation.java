package com.malaysia.bri.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.malaysia.bri.R;
import com.malaysia.bri.category.Category_Activity;
import com.malaysia.bri.navigationActivity.CartActivity;
import com.malaysia.bri.search.SaerchActivity;

public class Bottom_Navigation {
    private final Context context;
    private LinearLayout home_ll,category_LL,search_LL, cartLL;

    public Bottom_Navigation(Context context) {
        this.context=context;
    }

    public void get_bottom_functionality(View view) {
        home_ll=(LinearLayout)view.findViewById(R.id.home_LL);
        category_LL=(LinearLayout)view.findViewById(R.id.category_LL);
        search_LL=(LinearLayout)view.findViewById(R.id.search_LL);
        cartLL=(LinearLayout)view.findViewById(R.id.cartLL);
        home_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(context instanceof Dashboard)){
                    context.startActivity(new Intent(context,Dashboard.class));
                }
            }
        });
        search_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(context instanceof SaerchActivity)){
                    context.startActivity(new Intent(context,SaerchActivity.class));
                }
            }
        });
        category_LL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(context instanceof Category_Activity)){
                    context.startActivity(new Intent(context,Category_Activity.class).putExtra("type","all"));
                }
            }
        });
        cartLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(context instanceof CartActivity)){
                    context.startActivity(new Intent(context,CartActivity.class));
                }
            }
        });
    }
}
