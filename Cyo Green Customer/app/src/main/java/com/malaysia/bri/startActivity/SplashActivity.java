package com.malaysia.bri.startActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.SessionManager;
import com.malaysia.bri.authentication.SignIn_Activity;
import com.malaysia.bri.dashboard.Dashboard;

public class SplashActivity extends AppCompatActivity {

    private static final long SPALSH_TIME_OUT = 3000;
    private ProgressBar progress;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        progress=(ProgressBar)findViewById(R.id.progress);
        A_Service_URL.get_img(SplashActivity.this);
        sessionManager=new SessionManager(SplashActivity.this);
        ValueAnimator animator = ValueAnimator.ofInt(0, progress.getMax());
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation){
                progress.setProgress((Integer)animation.getAnimatedValue());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                // start your activity here
            }
        });
        animator.start();
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
//                    Intent intent = new Intent(SplashActivity.this, Dashboard.class);
//                    startActivity(intent);
//                }
//                else {
                    Intent intent = new Intent(SplashActivity.this, Dashboard.class);
                    startActivity(intent);
//                }
            }
        },SPALSH_TIME_OUT);
    }
}
