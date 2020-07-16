package com.skycode.formocart_employee.splashActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ProgressBar;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DashboardActivity;
import com.skycode.formocart_employee.signin.A_Service_URL;
import com.skycode.formocart_employee.signin.SessionManager;
import com.skycode.formocart_employee.signin.SignIn_Activity;

public class SplashActivity extends AppCompatActivity {

    private ProgressBar progress;
    private static final long SPALSH_TIME_OUT = 3000;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sessionManager=new SessionManager(SplashActivity.this);
        progress=(ProgressBar)findViewById(R.id.progress);
        A_Service_URL.get_img(SplashActivity.this);
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
                if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                    Intent intent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(SplashActivity.this, SignIn_Activity.class);
                    startActivity(intent);
                }
            }
        },SPALSH_TIME_OUT);
    }
}
