package com.malaysia.bri.navigationActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.Util.SessionManager;
import com.malaysia.bri.dashboard.Dashboard;
import com.malaysia.bri.search.CustomJsonArrayRequest;
import com.malaysia.bri.search.SaerchActivity;
import com.malaysia.bri.startActivity.SplashActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.malaysia.bri.Util.A_Service_URL.dataArrayImage;

public class CartActivity extends AppCompatActivity implements PaymentResultListener {

    private LinearLayout cartMainLL;
    private Button checkout;
    private SessionManager sessionManager;
    private ArrayList<CartList_Model_View> dataArray;
    private CardView cardMain;
    private RelativeLayout noRecordRel;
    private TextView totalBefore, vat, total, deliveryCharge;
    private double totalBefore_st = 0.00, vat_st = 0.00, deliveryCharge_st = 0.00, total_st = 0.00;
    private Button shopNow;
    private TextView items;
    private ImageView search;
    private ImageView back;
    private Button continue_;
    private DrawerLayout drawer;
    private String from = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        from = getIntent().getStringExtra("from");
        sessionManager = new SessionManager(getApplicationContext());
        shopNow = (Button) findViewById(R.id.shopNow);
        items = (TextView) findViewById(R.id.items);
        cardMain = (CardView) findViewById(R.id.cardMain);
        back = (ImageView) findViewById(R.id.menu_img);
        noRecordRel = (RelativeLayout) findViewById(R.id.noRecord);
        checkout = (Button) findViewById(R.id.checkout);
        cartMainLL = (LinearLayout) findViewById(R.id.custMainLL);
        vat = (TextView) findViewById(R.id.vat);
        deliveryCharge = (TextView) findViewById(R.id.deliveryCharge);
        total = (TextView) findViewById(R.id.total);
        continue_ = (Button) findViewById(R.id.continue_);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(CartActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content), drawer);
        getCartlist();
        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (from != null && from.equalsIgnoreCase("order")) {
                    get_payment_gateway();
//                    get_order();

                } else
                    startActivity(new Intent(CartActivity.this, ProfileActivity.class).
                            putExtra("from", "cart"));
//                Intent intent=new Intent(getApplicationContext(),PaymentActivity.class);
//                intent.putExtra("total",total.getText().toString());
//                intent.putExtra("totalBefore",totalBefore.getText().toString());
//                intent.putExtra("vat",vat.getText().toString());
//                Bundle args = new Bundle();
//                args.putSerializable("ARRAYLIST",(Serializable)dataArray);
//                intent.putExtra("BUNDLE",args);
//                startActivity(intent);
            }
        });
        shopNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Dashboard.class));
            }
        });
        search = (ImageView) findViewById(R.id.search);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SaerchActivity.class));
            }
        });
    }

    private void get_payment_gateway() {
            final Activity activity = this;
            final Checkout co = new Checkout();
            try {
                JSONObject options = new JSONObject();
                options.put("name", "Salakart");
                options.put("description", "Ecommerce");
                options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
                options.put("currency", "INR");
                options.put("amount", total_st * 100);
                JSONObject preFill = new JSONObject();
                options.put("prefill", preFill);
                preFill.put("email", "");
                preFill.put("contact", "");
                options.put("prefill", preFill);
                co.open(activity, options);
            } catch (Exception e) {
                Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }

    private void get_order() {
        final ProgressDialog pd = new ProgressDialog(CartActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject obj = null;
        String product_id = "", order_qty = "", order_amount = "";
        obj = new JSONObject();

        for (int i = 0; i < dataArray.size(); i++) {
            product_id = product_id + dataArray.get(i).getProdId() + ",";
            order_qty = order_qty + dataArray.get(i).getOrder_qty() + ",";
            order_amount = order_amount + dataArray.get(i).getPriceperqty() + ",";
        }
        try {
            obj.put("prd_ids", product_id);
            obj.put("order_qtys", order_qty);
            obj.put("orders_amnt", order_amount);
            obj.put("shp_id", sessionManager.getID());
            obj.put("total_amount", String.valueOf(total_st));
            Log.e("whjxdbjhwwdcwsdc", "" + obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginUser_ObjectRequest = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "myorder_create.php", obj,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Toast.makeText(getApplicationContext(),""+response,
//                                Toast.LENGTH_LONG).show();
                        try {
                            String message = response.getString("message");
//                            if(message.equalsIgnoreCase("Your order has been placed successfully")){
                            Toast.makeText(getApplicationContext(), "Your order has been placed successfully",
                                    Toast.LENGTH_LONG).show();
                            startActivity(new Intent(CartActivity.this, Dashboard.class));
//                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            A_Service_URL.getToast(getApplicationContext(), "" + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    A_Service_URL.getToast(CartActivity.this, "ServerError " + error.getMessage());
                } else if (error instanceof AuthFailureError) {
                    A_Service_URL.getToast(CartActivity.this, "AuthFailureError " + error.getMessage());
                } else if (error instanceof ParseError) {
                    A_Service_URL.getToast(CartActivity.this, "ParseError " + error.getMessage());
                } else if (error instanceof NoConnectionError) {
                    A_Service_URL.getToast(CartActivity.this, "Error " + error.getMessage());
                } else if (error instanceof TimeoutError) {
                    A_Service_URL.getToast(CartActivity.this, "TimeoutError " + error.getMessage());
                }
                pd.dismiss();
            }
        });
        int socketTimeout = 400000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        loginUser_ObjectRequest.setRetryPolicy(policy);
        RequestQueue rq = Volley.newRequestQueue(CartActivity.this);
        rq.add(loginUser_ObjectRequest);
    }

    private void getCartlist() {
        final ProgressDialog pd = new ProgressDialog(CartActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        dataArray = new ArrayList<CartList_Model_View>();
        JSONObject param = new JSONObject();
        try {
            param.put("shop_id", sessionManager.getID());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        CustomJsonArrayRequest loginUser_ObjectRequest = new CustomJsonArrayRequest(Request.Method.POST, A_Service_URL.getUrl + "mycart_show.php", param,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        pd.dismiss();
                        try {
                            JSONArray jArray = new JSONArray(response.toString());
                            if (jArray.length() > 0) {
                                cardMain.setVisibility(View.VISIBLE);
                                noRecordRel.setVisibility(View.GONE);
                                checkout.setVisibility(View.VISIBLE);
                                items.setVisibility(View.VISIBLE);
                                for (int i = 0; i < jArray.length(); i++) {
                                    JSONObject jsonObject = jArray.getJSONObject(i);
                                    String shop_id = jsonObject.getString("shop_id");
                                    String prodId = jsonObject.getString("prd_id");
                                    String order_qty = jsonObject.getString("order_qty");
                                    String total_amount = jsonObject.getString("total_amount");
                                    String perkg_price = jsonObject.getString("priceperqty");
                                    CartList_Model_View customClass = new CartList_Model_View(shop_id, prodId,
                                            order_qty, total_amount, perkg_price);
                                    dataArray.add(customClass);
                                    design(i);
                                }
                                items.setText("Total No. Of Items : " + dataArray.size());
                                sessionManager.setCartItem(String.valueOf(dataArray.size()));
                            } else {
                                cardMain.setVisibility(View.GONE);
                                noRecordRel.setVisibility(View.VISIBLE);
                                checkout.setVisibility(View.GONE);
                                items.setVisibility(View.GONE);
                                continue_.setVisibility(View.GONE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            A_Service_URL.getToast(getApplicationContext(), "" + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    A_Service_URL.getToast(CartActivity.this, "ServerError " + error.getMessage());
                } else if (error instanceof AuthFailureError) {
                    A_Service_URL.getToast(CartActivity.this, "AuthFailureError " + error.getMessage());
                } else if (error instanceof ParseError) {
                    A_Service_URL.getToast(CartActivity.this, "ParseError " + error.getMessage());
                } else if (error instanceof NoConnectionError) {
                    A_Service_URL.getToast(CartActivity.this, "Error " + error.getMessage());
                } else if (error instanceof TimeoutError) {
                    A_Service_URL.getToast(CartActivity.this, "TimeoutError " + error.getMessage());
                }
                pd.dismiss();
            }
        });
        int socketTimeout = 400000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        loginUser_ObjectRequest.setRetryPolicy(policy);
        RequestQueue rq = Volley.newRequestQueue(CartActivity.this);
        rq.add(loginUser_ObjectRequest);
    }

    //
    private void design(final int i) {
        LayoutInflater layoutInflater = (LayoutInflater) CartActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.view_cart_list, null);
        cartMainLL.addView(v);
        ImageView img = (ImageView) v.findViewById(R.id.img_main);
        TextView proName = (TextView) v.findViewById(R.id.proName);
        TextView qty = (TextView) v.findViewById(R.id.qty);
        ImageView delete = (ImageView) v.findViewById(R.id.delete);
        TextView rate = (TextView) v.findViewById(R.id.rate);
//        address.setText(dataArray.get(i).getAddress());
        qty.setText("Quantity " + dataArray.get(i).getOrder_qty());
        rate.setText("Total Price Rs. " + dataArray.get(i).getTotal_amount());

//        price.setText("Rs. " + Double.parseDouble(dataArray.get(i).getPrice().replace("Rs.", "")) * Double.parseDouble(dataArray.get(i).getQuantity()));
//        if (!dataArray.get(i).getTotal_amount().equals("")) {
//            totalBefore_st = totalBefore_st + Double.parseDouble(price.getText().toString().replace("Rs.", ""));
//            totalBefore.setText("Rs. " + totalBefore_st);
//        }
//        if (!dataArray.get(i).getVat().equals(""))
//            vat_st = vat_st + Double.parseDouble(dataArray.get(i).getVat());
//        if (!dataArray.get(i).getVat().equals("")) {
//            deliveryCharge_st = deliveryCharge_st + Double.parseDouble(dataArray.get(i).getVat());
//            deliveryCharge.setText("Rs. " + dataArray.get(i).getVat());
//        } else {
//            deliveryCharge.setText("Free");
//        }
        total_st = total_st + Double.parseDouble(dataArray.get(i).getTotal_amount());
        if (dataArrayImage != null) {
            for (int j = 0; j < dataArrayImage.size(); j++) {
                if (dataArray.get(i).getProdId().equalsIgnoreCase(dataArrayImage.get(j).getPrd_id())) {
                    Picasso.get().load(A_Service_URL.getImageURl + dataArrayImage.get(j).getPrd_img()).fit().noFade()
                            /*.resize(imageView.getWidth(), imageView.getHeight()).fit().centerInside().resize(70, 70)*/
                            .placeholder(R.drawable.progress_loading)
                            .error(R.drawable.warning).noFade().into(img);
                    proName.setText("" + dataArrayImage.get(j).getPro_name());
                }
            }
        } else {
            startActivity(new Intent(CartActivity.this, SplashActivity.class));
        }
//        if (dataArray.get(i).getVat().equalsIgnoreCase("0.0") && dataArray.get(i).getVat().equalsIgnoreCase(""))
//            vat.setText("Rs. 0.00");
//        else
//            vat.setText("Rs. " + dataArray.get(i).getVat());
        total.setText("Rs. " + total_st);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(CartActivity.this);
                builder1.setMessage("Are you sure want to delete this product from cart list");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                get_Cart_delete(i);
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
        });
    }

    private void get_Cart_delete(int i) {
        final ProgressDialog pd = new ProgressDialog(CartActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject param = new JSONObject();
        try {
            param.put("shop_id", sessionManager.getID());
            param.put("prd_id", dataArray.get(i).getProdId());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest loginUser_ObjectRequest = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "mycart_remove.php", param,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        pd.dismiss();
                        try {
                            String message = response.getString("message");
                            if (message.equalsIgnoreCase("Item deleted successfully")) {
                                Toast.makeText(getApplicationContext(), "Item Deleted Successfully", Toast.LENGTH_LONG).show();
                                startActivity(getIntent());
                                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            A_Service_URL.getToast(getApplicationContext(), "" + e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof ServerError) {
                    A_Service_URL.getToast(CartActivity.this, "ServerError " + error.getMessage());
                } else if (error instanceof AuthFailureError) {
                    A_Service_URL.getToast(CartActivity.this, "AuthFailureError " + error.getMessage());
                } else if (error instanceof ParseError) {
                    A_Service_URL.getToast(CartActivity.this, "ParseError " + error.getMessage());
                } else if (error instanceof NoConnectionError) {
                    A_Service_URL.getToast(CartActivity.this, "Error " + error.getMessage());
                } else if (error instanceof TimeoutError) {
                    A_Service_URL.getToast(CartActivity.this, "TimeoutError " + error.getMessage());
                }
                pd.dismiss();
            }
        });
        int socketTimeout = 400000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        loginUser_ObjectRequest.setRetryPolicy(policy);
        RequestQueue rq = Volley.newRequestQueue(CartActivity.this);
        rq.add(loginUser_ObjectRequest);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), Dashboard.class));
    }

    @Override
    public void onPaymentSuccess(String s) {
        get_order();
    }

    @Override
    public void onPaymentError(int i, String s) {
        get_order();
    }
}
