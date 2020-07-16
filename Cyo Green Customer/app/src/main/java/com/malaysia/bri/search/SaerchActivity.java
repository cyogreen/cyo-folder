package com.malaysia.bri.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.dashboard.Dashboard;
import com.malaysia.bri.modelClass.CustomClass_ProductList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SaerchActivity extends AppCompatActivity {

    private RecyclerView search_recycler;
    private EditText search_et;
    private ArrayList<CustomClass_ProductList> dataArray;
    private SearchAdapterClass adapter1;
    private ProgressBar progress_bar;
    private RelativeLayout noRecordRel;
    private DrawerLayout drawer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saerch);
        get_find_view_by_id();
        search_et.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                Log.e("erjkcfkjefcjeqvf",""+s.length());
                if(s.length()>=1)
                    get_search_service(search_et.getText().toString());
                else {
                    int size = dataArray.size();
                    dataArray.clear();
                    adapter1.notifyItemRangeRemoved(0, size);
                    noRecordRel.setVisibility(View.VISIBLE);
                    progress_bar.setVisibility(View.GONE);
                }
            }
        });
    }

    private void get_search_service(String search_keyword) {
        progress_bar.setVisibility(View.VISIBLE);
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("keywrd", search_keyword);
        } catch (JSONException e) {

        }
        CustomJsonArrayRequest req = new CustomJsonArrayRequest(Request.Method.POST, A_Service_URL.getUrl + "prdsearch.php",
                jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.e("erjkcfkjefcjeqvf",""+response);
                try {
                    search_recycler.setVisibility(View.VISIBLE);
                    progress_bar.setVisibility(View.GONE);
                    noRecordRel.setVisibility(View.GONE);
//                    JSONArray jArray = new JSONArray(response.toString());
                    if(response.length()>0) {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            String prd_id = jsonObject.getString("prd_id");
                            String min_qty = jsonObject.getString("min_qty");
                            String max_qty = jsonObject.getString("max_qty");
                            String sale_price = jsonObject.getString("sale_price");
                            CustomClass_ProductList customClass_productList = new CustomClass_ProductList(
                                    prd_id, "", min_qty, max_qty, sale_price
                            );
                            dataArray.add(customClass_productList);
                        }
                        search_recycler.setLayoutManager(new GridLayoutManager(SaerchActivity.this,2));
                        adapter1 = new SearchAdapterClass(SaerchActivity.this, dataArray);
                        search_recycler.setAdapter(adapter1);
                        adapter1.notifyDataSetChanged();
                    }
                    else {
                        int size = dataArray.size();
                        dataArray.clear();
                        adapter1.notifyItemRangeRemoved(0, size);
                        noRecordRel.setVisibility(View.VISIBLE);
                        progress_bar.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(SaerchActivity.this, "" + e);
                    progress_bar.setVisibility(View.GONE);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progress_bar.setVisibility(View.GONE);
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
        RequestQueue rq = Volley.newRequestQueue(SaerchActivity.this);
        rq.add(req);
    }
    private void get_find_view_by_id() {
        search_recycler = (RecyclerView) findViewById(R.id.search_recycler);
        search_et = (EditText) findViewById(R.id.search_et);
        progress_bar=(ProgressBar)findViewById(R.id.progress_bar);
        noRecordRel=(RelativeLayout)findViewById(R.id.noRecordRel);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(SaerchActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
        dataArray = new ArrayList<>();
        dataArray.clear();
        search_recycler.setLayoutManager(new GridLayoutManager(SaerchActivity.this,2));
        adapter1 = new SearchAdapterClass(SaerchActivity.this, dataArray);
    }
    @Override
    public void onBackPressed(){
        startActivity(new Intent(SaerchActivity.this, Dashboard.class));
        finish();
    }
}
