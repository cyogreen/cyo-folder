package com.malaysia.bri.dashboard;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.Banner;
import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.MyRecyclerView_productlist;
import com.malaysia.bri.R;
import com.malaysia.bri.modelClass.CustomClass_CategoryName;
import com.malaysia.bri.search.SaerchActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    private MyRecyclerViewAdapter_Home_Product adapter;
    private MyCategoryListingAdapter adapter1;
    private MyRecyclerView_productlist myRecyclerView_productlist;

    private RecyclerView recyclerView,catName_recycler;
    private ViewPager viewPager;
    private DrawerLayout drawer;
    private ArrayList<CustomClass_CategoryName> dataArray_catName;
    private RelativeLayout search_rel;
    private ArrayList<CustomClass_HomeProduct> dataArray_home_product;
    private EditText search_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        get_findViewById();
            try {
                PackageInfo info = Dashboard.this.getPackageManager().getPackageInfo(Dashboard.this.getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    String hashKey = new String(Base64.encode(md.digest(), 0));
                    Log.i("sdkcbkhdc", "printHashKey() Hash Key: " + hashKey);
                }
            } catch (NoSuchAlgorithmException e) {
                Log.e("sdkcbkhdc", "printHashKey()", e);
            } catch (Exception e) {
                Log.e("sdkcbkhdc", "printHashKey()", e);
            }

        drawer=(DrawerLayout)findViewById(R.id.drawer);

        Bottom_Navigation bottom_navigation = new Bottom_Navigation(Dashboard.this);
        bottom_navigation.get_bottom_functionality(this.findViewById(android.R.id.content));

        // set up the RecyclerViewrecycler_
        recyclerView = findViewById(R.id.recycler);
        catName_recycler=(RecyclerView)findViewById(R.id.catName_recycler);
        viewPager=findViewById(R.id.viewpager);

        get_category_name();
        get_home_prod();
        //Baner....................//
        Banner banner = new Banner(Dashboard.this);
        banner.getHowToWorkData(viewPager);

        //Drawer...................//
        DrawerClass drawerClass = new DrawerClass(Dashboard.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);


        // set up the RecyclerView
        viewPager=findViewById(R.id.viewpager);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        myRecyclerView_productlist = new MyRecyclerView_productlist(this, potatoName);
//        recyclerView.setAdapter(adapter);

        search_et.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, SaerchActivity.class));
            }
        });
        search_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Dashboard.this, SaerchActivity.class));
            }
        });
    }

    private void get_home_prod() {
        dataArray_home_product=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(Dashboard.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JsonArrayRequest code_verify_request = new JsonArrayRequest(Request.Method.GET, A_Service_URL.getUrl+"homeprds.php",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
//                        Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_SHORT).show();
                        pd.dismiss();
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject=response.getJSONObject(i);
                                String prd_id = jsonObject.getString("prd_id");
                                String min_qty=jsonObject.getString("min_qty");
                                String max_qty=jsonObject.getString("max_qty");
                                String sale_price=jsonObject.getString("sale_price");
                                CustomClass_HomeProduct customClass_categoryName=new CustomClass_HomeProduct(
                                        prd_id,"",min_qty,max_qty,sale_price);
                                dataArray_home_product.add(customClass_categoryName);
                            }
                            recyclerView.setLayoutManager(new GridLayoutManager(Dashboard.this,2));
                            adapter = new MyRecyclerViewAdapter_Home_Product(Dashboard.this, dataArray_home_product);
                            recyclerView.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            A_Service_URL.getToast(Dashboard.this,""+e);
                            pd.dismiss();
                            e.printStackTrace(); }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Try Again"+error,Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(),"Auth Failed Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(),"Parse Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(),"Time Out Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        code_verify_request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RequestQueue rq = Volley.newRequestQueue(Dashboard.this);
        rq.add(code_verify_request);
    }

    private void get_category_name() {
        dataArray_catName=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(Dashboard.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JsonArrayRequest code_verify_request = new JsonArrayRequest(Request.Method.GET, A_Service_URL.getUrl+"category.php",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.dismiss();
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject=response.getJSONObject(i);
                                String cat_id = jsonObject.getString("cat_id");
                                String cat_name=jsonObject.getString("cat_name");
                                String cat_img=jsonObject.getString("cat_img");
                                CustomClass_CategoryName customClass_categoryName=new CustomClass_CategoryName(
                                        cat_id,cat_name,cat_img);
                                dataArray_catName.add(customClass_categoryName);
                            }
                            catName_recycler.setLayoutManager(new GridLayoutManager(Dashboard.this,2));
                            adapter1 = new MyCategoryListingAdapter(Dashboard.this, dataArray_catName);
                            catName_recycler.setAdapter(adapter1);
                        }
                        catch (JSONException e) {
                            A_Service_URL.getToast(Dashboard.this,""+e);
                            pd.dismiss();
                            e.printStackTrace(); }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(),"Try Again"+error,Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(),"Network Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(),"Auth Failed Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(),"Parse Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(),"Time Out Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        code_verify_request.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });
        RequestQueue rq = Volley.newRequestQueue(Dashboard.this);
        rq.add(code_verify_request);
    }

    private void get_findViewById() {
        search_rel=(RelativeLayout)findViewById(R.id.search_rel);
        search_et=(EditText)findViewById(R.id.search_et);
        final SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent i = new Intent(Dashboard.this, Dashboard.class);
                startActivity(i);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                pullToRefresh.setRefreshing(false);
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(Dashboard.this);
        builder1.setMessage("Are you sure want to exit ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}
