package com.malaysia.bri.Util;

import android.content.Context;
import android.widget.RadioGroup;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class A_Service_URL {
    public static String getUrl="https://getmlmsoftware.com/demo/cyo/admin/apis/";
    public static String getImageURl="https://getmlmsoftware.com/demo/cyo/admin/";
    public static ArrayList<CustomClassImage> dataArrayImage;
    public static JSONArray response_new;
    private RadioGroup lang_radio;

    public static void getToast(Context context, String s) {
        Toast.makeText(context,""+s,Toast.LENGTH_LONG).show();
    }
    public static void get_img(final Context context){
        dataArrayImage=new ArrayList<>();
        JsonArrayRequest code_verify_request = new JsonArrayRequest(Request.Method.GET, A_Service_URL.getUrl+"pim.php",null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        response_new=response;
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String prd_id = jsonObject.getString("prd_id");
                                String prd_img=jsonObject.getString("prd_img");
                                String prd_name=jsonObject.getString("prd_name");
                                CustomClassImage customClassImage =new CustomClassImage(prd_id,prd_img,prd_name);
                                dataArrayImage.add(customClassImage);
                            }
                        } catch (JSONException e) {

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context,"Try Again"+error.getMessage(),Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(context,"Network Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context,"Auth Failed Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context,"Parse Error",Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(context,"Time Out Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        int socketTimeout = 400000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        code_verify_request.setRetryPolicy(policy);
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.add(code_verify_request);
    }
}
