package com.malaysia.bri.navigationActivity.support_solved_and_unsolved;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.R;
import com.google.android.material.tabs.TabLayout;

public class Support_Ticket extends AppCompatActivity {

    private ViewPager viewPager;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solved__ticket_);
        get_set_up_tab();
    }

    private void get_set_up_tab() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        ViewPager_Adapter adapter = new ViewPager_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(Support_Ticket.this);
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
