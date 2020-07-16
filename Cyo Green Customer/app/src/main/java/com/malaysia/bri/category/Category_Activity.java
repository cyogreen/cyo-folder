package com.malaysia.bri.category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
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
import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.dashboard.Bottom_Navigation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Category_Activity extends AppCompatActivity {

    private String type;
    private TextView cat_tt;
    private RecyclerView recycler_cat;
    private MyRecyclerViewAdapter_CategoryDetails adapter;
    private DrawerLayout drawer;
    private RelativeLayout noRecord;
    private ArrayList<CustomClass_Fruits> dataArray_fruits;
    private String url="";
    private TextView coming_tt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_);
        get_find_view_byId();
        Bottom_Navigation bottom_navigation = new Bottom_Navigation(Category_Activity.this);
        bottom_navigation.get_bottom_functionality(this.findViewById(android.R.id.content));
        type=getIntent().getStringExtra("type");
        if(type!=null && type.equalsIgnoreCase("Grocery")){
           cat_tt.setText("Grocery");
           get_category("fruits");
        }
        else if(type!=null && type.equalsIgnoreCase("Fast Food")){
            cat_tt.setText("Fast Food");
            get_category("vegetables");
        }
        else if(type!=null && type.equalsIgnoreCase("Mobile Accessories")){
            cat_tt.setText("Mobile Accessories");
            get_category("mobile");
        }
        else {
            noRecord.setVisibility(View.VISIBLE);
        }
    }

    private void get_category(String type) {
        dataArray_fruits=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(Category_Activity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        if(type.equalsIgnoreCase("fruits")){
            url=A_Service_URL.getUrl+"fruits.php";
        }
        else if(type.equalsIgnoreCase("vegetables"))
            url=A_Service_URL.getUrl+"vegetables.php";
        else if(type.equalsIgnoreCase("mobile"))
            url=A_Service_URL.getUrl+"mobile.php";

        JsonArrayRequest code_verify_request = new JsonArrayRequest(Request.Method.GET, url,null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.dismiss();
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject=response.getJSONObject(i);
                                String prd_id = jsonObject.getString("prd_id");
                                String min_qty=jsonObject.getString("min_qty");
                                String max_qty=jsonObject.getString("max_qty");
                                String sale_price=jsonObject.getString("sale_price");
                                CustomClass_Fruits customClass_categoryName=new CustomClass_Fruits(
                                        prd_id,"",min_qty,max_qty,sale_price);
                                dataArray_fruits.add(customClass_categoryName);
                            }
                            recycler_cat.setLayoutManager(new GridLayoutManager(Category_Activity.this,2));
                            adapter = new MyRecyclerViewAdapter_CategoryDetails(Category_Activity.this, dataArray_fruits);
                            recycler_cat.setAdapter(adapter);
                        }
                        catch (JSONException e) {
                            A_Service_URL.getToast(Category_Activity.this,""+e);
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
        int socketTimeout = 400000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        code_verify_request.setRetryPolicy(policy);
        RequestQueue rq = Volley.newRequestQueue(Category_Activity.this);
        rq.add(code_verify_request);
    }

    private void get_find_view_byId() {
        cat_tt=(TextView)findViewById(R.id.cat_tt);
        recycler_cat=(RecyclerView)findViewById(R.id.recycler_cat);
        noRecord=(RelativeLayout)findViewById(R.id.noRecord);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(Category_Activity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
        coming_tt=(TextView)findViewById(R.id.coming_tt);
    }
}
