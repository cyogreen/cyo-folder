package com.skycode.formocart_employee.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.navigation_menu.CreateShopActivity;
import com.skycode.formocart_employee.navigation_menu.order.Confirmed_OrderActivity;
import com.skycode.formocart_employee.navigation_menu.order.Order_Management_Listing;
import com.skycode.formocart_employee.navigation_menu.RaiseARequestActivity;
import com.skycode.formocart_employee.navigation_menu.TransactionActivity;
import com.skycode.formocart_employee.navigation_menu.payout.Payout_Listing_Activity;
import com.skycode.formocart_employee.navigation_menu.payout.Pending_Payout_Activity;
import com.skycode.formocart_employee.navigation_menu.shop_management.ShopManagementListing_Activity;
import com.skycode.formocart_employee.navigation_menu.shop_management.Visitor_Shop_Activity;
import com.skycode.formocart_employee.profile.ProfileActivity;
import com.skycode.formocart_employee.signin.SessionManager;
import com.skycode.formocart_employee.signin.SignIn_Activity;

public class DrawerClass {
    private Context context;
    private View viewById;
    private DrawerLayout drawer;
    private ImageView menu_img,profile_img;
    private LinearLayout profileLL,create_shopLL,shop_managementLL,order_managementLL,payoutLL,transactionLL,home,loginLL;
    private LinearLayout raiseAReq;
    private SessionManager sessionManager;
    private LinearLayout logoutLL;

    public DrawerClass(Context context){
        this.context = context;
    }
    public void getDrawerFunctionality(View viewById, final DrawerLayout drawer) {
        get_findViewById(viewById);
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                    context.startActivity(new Intent(context, ProfileActivity.class));
                }
                else {
                    context.startActivity(new Intent(context, SignIn_Activity.class));
                }
            }
        });
        menu_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
                loginLL=(LinearLayout)drawer.findViewById(R.id.loginLL);
                home=(LinearLayout)drawer.findViewById(R.id.home);
                profileLL=(LinearLayout)drawer.findViewById(R.id.profileLL);
                create_shopLL=(LinearLayout)drawer.findViewById(R.id.create_shopLL);
                shop_managementLL=(LinearLayout)drawer.findViewById(R.id.shop_managementLL);
                order_managementLL=(LinearLayout)drawer.findViewById(R.id.order_managementLL);
                payoutLL=(LinearLayout)drawer.findViewById(R.id.payoutLL);
                transactionLL=(LinearLayout)drawer.findViewById(R.id.transactionLL);
                raiseAReq=(LinearLayout)drawer.findViewById(R.id.raiseAReq);
                logoutLL=(LinearLayout)drawer.findViewById(R.id.logoutLL);
                home.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(!(context instanceof DashboardActivity))
                            context.startActivity(new Intent(context, DashboardActivity.class));
                    }
                });
                profileLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof ProfileActivity))
                                context.startActivity(new Intent(context, ProfileActivity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                transactionLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof TransactionActivity))
                                context.startActivity(new Intent(context, TransactionActivity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                raiseAReq.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof RaiseARequestActivity))
                                context.startActivity(new Intent(context, RaiseARequestActivity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                payoutLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof Payout_Listing_Activity))
                                context.startActivity(new Intent(context, Pending_Payout_Activity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                order_managementLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof Order_Management_Listing))
                                context.startActivity(new Intent(context, Confirmed_OrderActivity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                shop_managementLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof ShopManagementListing_Activity))
                                context.startActivity(new Intent(context, ShopManagementListing_Activity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                create_shopLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")) {
                            if(!(context instanceof CreateShopActivity))
                                context.startActivity(new Intent(context, CreateShopActivity.class));
                        }
                        else {
                            Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                        }

                    }
                });
                logoutLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sessionManager.logoutUser();
                        context.startActivity(new Intent(context,SignIn_Activity.class));
                    }
                });
                if(sessionManager.getID()!=null && !sessionManager.getID().equalsIgnoreCase("")){
                    loginLL.setVisibility(View.GONE);
                }
                else {
                    loginLL.setVisibility(View.VISIBLE);
                }
                loginLL.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        drawer.closeDrawer(GravityCompat.START);
                        context.startActivity(new Intent(context, SignIn_Activity.class));
                    }
                });
            }
        });
    }

    private void get_findViewById(View viewById) {
        menu_img=(ImageView)viewById.findViewById(R.id.menu_img);
        profile_img=(ImageView)viewById.findViewById(R.id.profile);
        sessionManager =new SessionManager(context);
    }
}