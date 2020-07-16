package com.malaysia.bri.navigationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.R;
import com.malaysia.bri.Util.SessionManager;
import com.malaysia.bri.dashboard.Dashboard;
import com.malaysia.bri.search.CustomJsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private CircleImageView profile_image;
    private SessionManager sessionManager;
    private EditText shopName_pro, contactNo_pro, alternameNo_pro, email_id_pro, shopAddress_pro, areaname_pro, landmark_pro, pinCode_pro,
            current_loc_pro, bankName_pro, ac_name_pro, acNo_pro, gst_pro, ifsc_pro, branchname_pro;
    private CircleImageView profile_img;
    private ImageView pencil;
    private TextView save;
    private ImageView shop_front_pic_img, shop_inner_pic_img;
    private String from="";
    private TextView confirm_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        get_findViewById();
        get_shopView();
        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pencil.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                shopName_pro.setEnabled(true);
                email_id_pro.setEnabled(true);
                shopAddress_pro.setEnabled(true);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shopName_pro.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Shop Name", Toast.LENGTH_LONG).show();
                } else if (contactNo_pro.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Contact Number", Toast.LENGTH_LONG).show();
                } else if (email_id_pro.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Email ID", Toast.LENGTH_LONG).show();
                } else if (shopAddress_pro.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_LONG).show();
                } else {
                    get_save_detail();
                }
            }
        });
    }

    private void get_save_detail() {
        final ProgressDialog pd = new ProgressDialog(ProfileActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shop_id", sessionManager.getID());
            jsonObject.put("shpnme", shopName_pro.getText().toString());
            jsonObject.put("smobile", contactNo_pro.getText().toString());
            jsonObject.put("semail", email_id_pro.getText().toString());
            jsonObject.put("saddrs", shopAddress_pro.getText().toString());
        } catch (JSONException e) {

        }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "shop_upd.php",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("hcbajsdcbajs", "" + response);
                    pd.dismiss();
                    String message = response.getString("message");
                    if (message.equalsIgnoreCase("Your shop updated successfully")) {
                        Toast.makeText(getApplicationContext(), "Shop Updated Successfully", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(ProfileActivity.this, "" + e);
                    pd.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Try Again" + error, Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Auth Failed Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "Time Out Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue rq = Volley.newRequestQueue(ProfileActivity.this);
        rq.add(req);
    }

    private void get_shopView() {
        final ProgressDialog pd = new ProgressDialog(ProfileActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shp_id", sessionManager.getID());
            Log.e("jsfbcjks",""+sessionManager.getID());
        } catch (JSONException e) {

        }
        CustomJsonArrayRequest req = new CustomJsonArrayRequest(Request.Method.POST, A_Service_URL.getUrl + "shopview.php",
                jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    pd.dismiss();
                    JSONArray jArray = new JSONArray(response.toString());
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        String shop_name = jsonObject.getString("cust_name");
                        String contact_mobile = jsonObject.getString("cust_mobile");
                        String shop_email = jsonObject.getString("cust_email");
                        String address = jsonObject.getString("address");
                        shopName_pro.setText("" + shop_name);
                        contactNo_pro.setText("" + contact_mobile);
                        email_id_pro.setText("" + shop_email);
                        shopAddress_pro.setText("" + address);
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(ProfileActivity.this, "" + e);
                    pd.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Try Again" + error, Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Auth Failed Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "Time Out Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue rq = Volley.newRequestQueue(ProfileActivity.this);
        rq.add(req);
    }

    private void get_findViewById() {
        sessionManager = new SessionManager(ProfileActivity.this);
        shopName_pro = (EditText) findViewById(R.id.shopName_pro);
        contactNo_pro = (EditText) findViewById(R.id.contactNo_pro);
        alternameNo_pro = (EditText) findViewById(R.id.alternameNo_pro);
        email_id_pro = (EditText) findViewById(R.id.email_id_pro);
        shopAddress_pro = (EditText) findViewById(R.id.shopAddress_pro);
        profile_img = (CircleImageView) findViewById(R.id.profile_image);
        confirm_add=(TextView)findViewById(R.id.confirm_add);
        pencil = (ImageView) findViewById(R.id.pencil);
        save = (TextView) findViewById(R.id.save);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(ProfileActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content), drawer);
        from = getIntent().getStringExtra("from");
        if(from!=null && from.equalsIgnoreCase("cart"))
            confirm_add.setVisibility(View.VISIBLE);
        else
            confirm_add.setVisibility(View.GONE);
        confirm_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileActivity.this,CartActivity.class).putExtra("from","order"));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(from!=null && from.equalsIgnoreCase("cart"))
            startActivity(new Intent(ProfileActivity.this, CartActivity.class).
                    putExtra("from","order"));
        else
            startActivity(new Intent(ProfileActivity.this, Dashboard.class));
    }
}
