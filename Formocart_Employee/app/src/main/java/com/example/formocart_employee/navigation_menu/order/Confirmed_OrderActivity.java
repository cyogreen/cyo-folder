package com.skycode.formocart_employee.navigation_menu.order;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DrawerClass;
import com.skycode.formocart_employee.profile.CustomJsonArrayRequest;
import com.skycode.formocart_employee.signin.A_Service_URL;
import com.skycode.formocart_employee.signin.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Confirmed_OrderActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private SessionManager sessionManager;
    public ArrayList<Custom_Order> dataArray;
    private RelativeLayout no_Record_Rel;
    private TableLayout ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmed__order);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        sessionManager=new SessionManager(Confirmed_OrderActivity.this);
        DrawerClass drawerClass = new DrawerClass(Confirmed_OrderActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
        no_Record_Rel=(RelativeLayout)findViewById(R.id.noRecord);
        ll = (TableLayout) findViewById(R.id.tabla_cuerpo);
        get_order();
    }
    private void get_order() {
        dataArray=new ArrayList<>();
        final ProgressDialog pd = new ProgressDialog(Confirmed_OrderActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("emp_id", sessionManager.getID());
        }
        catch (JSONException e) {
        }
        CustomJsonArrayRequest req = new CustomJsonArrayRequest(Request.Method.POST, A_Service_URL.getUrl + "orders_empl.php",
                jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    pd.dismiss();
                    JSONArray jArray = new JSONArray(response.toString());
                    if(jArray.length()>0) {
                        no_Record_Rel.setVisibility(View.GONE);
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jsonObject = jArray.getJSONObject(i);
                            String prd_id = jsonObject.getString("prd_id");
                            String order_id = jsonObject.getString("order_id");
                            String amount = jsonObject.getString("amount");
                            String status = jsonObject.getString("status");
                            String order_qty = jsonObject.getString("order_qty");
                            String orders_amnt = jsonObject.getString("orders_amnt");
                            Custom_Order customClass_confirmed_shop = new Custom_Order(prd_id,
                                    order_id, amount, status, order_qty, orders_amnt);
                            dataArray.add(customClass_confirmed_shop);
                        }
                        design();
                    }
                    else {
                        no_Record_Rel.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(Confirmed_OrderActivity.this, "" + e);
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
        RequestQueue rq = Volley.newRequestQueue(Confirmed_OrderActivity.this);
        rq.add(req);
    }
    private void design() {
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Order ID ");
        tv0.setTextColor(Color.BLACK);
        tv0.setGravity(Gravity.CENTER);
        tv0.setPadding(15,10,15,10);
        tv0.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Total Amount ");
        tv1.setTextColor(Color.BLACK);
        tv1.setGravity(Gravity.CENTER);
        tv1.setPadding(15,10,15,10);
        tv1.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Status ");
        tv2.setTextColor(Color.BLACK);
        tv2.setGravity(Gravity.CENTER);
        tv2.setPadding(15,10,15,10);
        tv2.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" Raise A Request ");
        tv3.setTextColor(Color.BLACK);
        tv3.setGravity(Gravity.CENTER);
        tv3.setPadding(15,10,15,10);
        tv3.setBackgroundResource(R.drawable.cell_shape);
        tbrow0.addView(tv3);
//        TextView tv4 = new TextView(this);
//        tv4.setText(" Contact ");
//        tv4.setTextColor(Color.BLACK);
//        tv4.setGravity(Gravity.CENTER);
//        tv4.setPadding(15,10,15,10);
//        tv4.setBackgroundResource(R.drawable.cell_shape);
//        tbrow0.addView(tv4);
//        TextView tv5 = new TextView(this);
//        tv5.setText(" Area ");
//        tv5.setTextColor(Color.BLACK);
//        tv5.setGravity(Gravity.CENTER);
//        tv5.setPadding(15,10,15,10);
//        tv5.setBackgroundResource(R.drawable.cell_shape);
//        tbrow0.addView(tv5);
//        TextView tv6 = new TextView(this);
//        tv6.setText(" Status ");
//        tv6.setTextColor(Color.BLACK);
//        tv6.setGravity(Gravity.CENTER);
//        tv6.setPadding(15,10,15,10);
//        tv6.setBackgroundResource(R.drawable.cell_shape);
//        tbrow0.addView(tv6);
        ll.addView(tbrow0);
        for (int i = 0; i < dataArray.size(); i++) {
            TableRow tbrow = new TableRow(this);
            TextView t1v = new TextView(this);
            t1v.setText(""+dataArray.get(i).getOrder_id()+"    >");
//            t1v.setTextColor(Color.BLACK);
            t1v.setBackgroundResource(R.drawable.cell_shape);
            t1v.setGravity(Gravity.CENTER);
            t1v.setPadding(25,20,25,20);
            tbrow.addView(t1v);
            TextView t2v = new TextView(this);
            t2v.setText("Rs. "+dataArray.get(i).getAmount());
//            t2v.setTextColor(Color.BLACK);
            t2v.setBackgroundResource(R.drawable.cell_shape);
            t2v.setGravity(Gravity.CENTER);
            t2v.setPadding(25,20,25,20);
            tbrow.addView(t2v);
            TextView t3v = new TextView(this);
            t3v.setText(dataArray.get(i).getStatus());
//            t3v.setTextColor(Color.BLACK);
            t3v.setBackgroundResource(R.drawable.cell_shape);
            t3v.setGravity(Gravity.CENTER);
            t3v.setPadding(25,20,25,20);
            tbrow.addView(t3v);
            TextView t4v = new TextView(this);
            t4v.setText("Raise A Request");
            t4v.setTextColor(Color.WHITE);
            t4v.setBackgroundResource(R.color.colorRed);
            t4v.setGravity(Gravity.CENTER);
            t4v.setPadding(25,20,25,20);
            tbrow.addView(t4v);
            final int finalI = i;
            tbrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Confirmed_OrderActivity.this,OrderDetailsActivity.class).putExtra("prd_id",
                            dataArray.get(finalI).getPrd_id()).putExtra("per_kg_amount",dataArray.get(finalI).getOrders_amnt())
                            .putExtra("qty",dataArray.get(finalI).getOrder_qty()));
                }
            });
//            TextView t5v = new TextView(this);
//            t5v.setText(dataArray.get(i).getContact_mobile());
//            t5v.setTextColor(Color.BLACK);
//            t5v.setPadding(15,10,15,10);
//            t5v.setBackgroundResource(R.drawable.cell_shape);
//            t5v.setGravity(Gravity.CENTER);
//            tbrow.addView(t5v);
//            TextView t6v = new TextView(this);
//            t6v.setText(dataArray.get(i).getShop_area());
//            t6v.setTextColor(Color.BLACK);
//            t6v.setBackgroundResource(R.drawable.cell_shape);
//            t6v.setGravity(Gravity.CENTER);
//            t6v.setPadding(15,10,15,10);
//            tbrow.addView(t6v);
//            TextView t7v = new TextView(this);
//            t7v.setText(dataArray.get(i).getStatus());
//            t7v.setTextColor(Color.BLACK);
//            t7v.setBackgroundResource(R.drawable.cell_shape);
//            t7v.setGravity(Gravity.CENTER);
//            t7v.setPadding(15,10,15,10);
//            tbrow.addView(t7v);
            ll.addView(tbrow);
        }
    }
}
