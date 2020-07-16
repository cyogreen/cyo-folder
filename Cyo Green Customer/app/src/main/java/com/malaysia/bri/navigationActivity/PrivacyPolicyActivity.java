package com.malaysia.bri.navigationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;

import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.R;

public class PrivacyPolicyActivity extends AppCompatActivity {

    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(PrivacyPolicyActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
}
