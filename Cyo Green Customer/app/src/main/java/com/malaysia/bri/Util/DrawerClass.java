package com.malaysia.bri.Util;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.malaysia.bri.R;
import com.malaysia.bri.authentication.SignIn_Activity;
import com.malaysia.bri.dashboard.Dashboard;
import com.malaysia.bri.navigationActivity.AboutUsActivity;
import com.malaysia.bri.navigationActivity.CartActivity;
import com.malaysia.bri.navigationActivity.ContactUsActivity;
import com.malaysia.bri.navigationActivity.OrderActivity;
import com.malaysia.bri.navigationActivity.PrivacyPolicyActivity;
import com.malaysia.bri.navigationActivity.ProfileActivity;
import com.malaysia.bri.navigationActivity.support_solved_and_unsolved.Support_Ticket;

import java.util.Locale;

public class DrawerClass {
    private Context context;
    private View viewById;
    private DrawerLayout drawer;
    private ImageView menu_img, profile_img;
    private LinearLayout profile, cart, order, support, privacy_policy, contact_us, about_us, home, loginLL;
    private SessionManager sessionManager;
    private LinearLayout logoutLL;
    private LinearLayout contact_usLL;

    public DrawerClass(Context context) {
        this.context = context;
    }

    public void getDrawerFunctionality(View viewById, final DrawerLayout drawer) {
        get_findViewById(viewById);
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profile_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                            context.startActivity(new Intent(context, ProfileActivity.class));
                        } else {
                            context.startActivity(new Intent(context, SignIn_Activity.class));
                        }
                    }
                });
            }
        });
        menu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
                profile = (LinearLayout) drawer.findViewById(R.id.profileLL);
                cart = (LinearLayout) drawer.findViewById(R.id.cart_list);
                order = (LinearLayout) drawer.findViewById(R.id.order_page);
                support = (LinearLayout) drawer.findViewById(R.id.support);
                contact_us = (LinearLayout) drawer.findViewById(R.id.contact_us);
                logoutLL = (LinearLayout) drawer.findViewById(R.id.logoutLL);
                privacy_policy = (LinearLayout) drawer.findViewById(R.id.privacy_policy);
                about_us = (LinearLayout) drawer.findViewById(R.id.about_us);
                home = (LinearLayout) drawer.findViewById(R.id.home);
                contact_usLL=(LinearLayout)drawer.findViewById(R.id.contact_usLL);
                loginLL = (LinearLayout) drawer.findViewById(R.id.loginLL);
                LinearLayout langLL = (LinearLayout) drawer.findViewById(R.id.langLL);
                about_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (!(context instanceof AboutUsActivity))
                            context.startActivity(new Intent(context, AboutUsActivity.class));
                    }
                });
                privacy_policy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (!(context instanceof PrivacyPolicyActivity))
                            context.startActivity(new Intent(context, PrivacyPolicyActivity.class));
                    }
                });
                contact_usLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (!(context instanceof ContactUsActivity))
                            context.startActivity(new Intent(context, ContactUsActivity.class));
                    }
                });
                contact_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                            if (Build.VERSION.SDK_INT < 23) {
                                phoneCall();
                            }else {
                                if (ActivityCompat.checkSelfPermission(context,
                                        Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                                    phoneCall();
                                }else {
                                    final String[] PERMISSIONS_STORAGE = {Manifest.permission.CALL_PHONE};
                                    //Asking request Permissions
                                    ActivityCompat.requestPermissions((Activity) context, PERMISSIONS_STORAGE, 9);
                                }
                            }

                    }
                });
                support.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if (!(context instanceof Support_Ticket))
                                context.startActivity(new Intent(context, Support_Ticket.class));
                        } else {
                            Toast.makeText(context, "You must Login first", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                order.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if (!(context instanceof OrderActivity))
                                context.startActivity(new Intent(context, OrderActivity.class));
                        } else {
                            Toast.makeText(context, "You must Login first", Toast.LENGTH_LONG).show();
                        }

                    }
                });
                profile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if (!(context instanceof ProfileActivity))
                                context.startActivity(new Intent(context, ProfileActivity.class));
                        } else {
                            Toast.makeText(context, "You must Login first", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (!(context instanceof Dashboard))
                            context.startActivity(new Intent(context, Dashboard.class));
                    }
                });
                if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                    loginLL.setVisibility(View.GONE);
                    logoutLL.setVisibility(View.VISIBLE);
                } else {
                    loginLL.setVisibility(View.VISIBLE);
                    logoutLL.setVisibility(View.GONE);
                }
                logoutLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sessionManager.logoutUser();
                        context.startActivity(new Intent(context, SignIn_Activity.class));
                    }
                });
                loginLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, SignIn_Activity.class));
                    }
                });
                langLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        get_language();
                    }
                });
                cart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if (!(context instanceof CartActivity))
                                context.startActivity(new Intent(context, CartActivity.class));
                        } else {
                            Toast.makeText(context, "You must Login first", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void phoneCall(){
        if (ActivityCompat.checkSelfPermission(context,
                Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:12345678900"));
            context.startActivity(callIntent);
        }else{
            Toast.makeText(context, "You don't assign permission.", Toast.LENGTH_SHORT).show();
        }
    }

    private void get_language() {
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_language);
        ImageView close_img = (ImageView) dialog.findViewById(R.id.close_img);
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        RadioGroup lang_radio = (RadioGroup) dialog.findViewById(R.id.lang_radio);
        lang_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.uz) {
                    Locale myLocale;
                    myLocale = new Locale("uz");
                    Resources res = context.getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    conf.locale = myLocale;
                    res.updateConfiguration(conf, dm);
                    context.startActivity(new Intent(context,Dashboard.class));
                    Toast.makeText(context, "Language Changed to Uzbek Successfully", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.english) {
                    Locale locale = new Locale("de");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
                    context.startActivity(new Intent(context,Dashboard.class));
                    Toast.makeText(context, "Language Changed to English Successfully", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.py) {
                    Locale locale = new Locale("ba");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    context.getResources().updateConfiguration(config,
                            context.getResources().getDisplayMetrics());
                    context.startActivity(new Intent(context,Dashboard.class));
                    Toast.makeText(context, "Language Changed to Pynnke Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void get_findViewById(View viewById) {
        menu_img = (ImageView) viewById.findViewById(R.id.menu_img);
        profile_img = (ImageView) viewById.findViewById(R.id.profile);
        sessionManager = new SessionManager(context);
    }
}
