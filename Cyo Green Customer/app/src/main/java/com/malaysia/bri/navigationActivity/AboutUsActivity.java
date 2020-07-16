package com.malaysia.bri.navigationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.R;

public class AboutUsActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(AboutUsActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
}
