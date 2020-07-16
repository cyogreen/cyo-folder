package com.malaysia.bri.Util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.viewpager.widget.ViewPager;

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
import com.malaysia.bri.BannerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import static com.facebook.FacebookSdk.getApplicationContext;

public class Banner {
    private Context context;
    private LinearLayout ll_review_rate_list;
    private ArrayList<String> dataArray_banner;
    public Banner(Context context, LinearLayout ll_review_rate_list){
        this.context = context;
        this.ll_review_rate_list = ll_review_rate_list;
    }

    private String loadMore = "0";
    public Banner(Context context){
        this.context = context;
    }
    public void getHowToWorkData(final ViewPager viewPager){
        dataArray_banner=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JsonArrayRequest code_verify_request = new JsonArrayRequest(Request.Method.GET,A_Service_URL.getUrl+"banners.php",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.dismiss();
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject=response.getJSONObject(i);
                                String baneer_img = jsonObject.getString("baneer_img");
                                dataArray_banner.add(baneer_img);
                            }
                            slider(viewPager,dataArray_banner);
                        }
                        catch (JSONException e) {
                            A_Service_URL.getToast(context,""+e);
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
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.add(code_verify_request);
    }

    // show to sliding banner
    private void slider(final ViewPager view_pager_banner, final ArrayList<String> dataArray_banner) {
        view_pager_banner.setAdapter(new BannerAdapter(context, dataArray_banner));

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                view_pager_banner.post(new Runnable(){
                    @Override
                    public void run() {
                        view_pager_banner.setCurrentItem((view_pager_banner.getCurrentItem()+1) % dataArray_banner.size());
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 3000, 3000);
    }
}
