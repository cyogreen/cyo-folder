package com.skycode.formocart_employee.navigation_menu;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DrawerClass;
import com.google.android.material.tabs.TabLayout;

public class RaiseARequestActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raise_arequest);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(RaiseARequestActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
        get_set_up_tab();
    }
    private void get_set_up_tab() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPager_Adapter adapter = new ViewPager_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(RaiseARequestActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }

    public class ViewPager_Adapter extends FragmentPagerAdapter {

        public ViewPager_Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0)
            {
                fragment = new Support_Solved();
            }
            else if (position == 1)
            {
                fragment = new Support_Unsolved();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            String title = null;
            if (position == 0)
            {
                title = "Solved Ticket";
            }
            else if (position == 1)
            {
                title = "UnSolved Ticket";
            }
            return title;
        }
    }
}
