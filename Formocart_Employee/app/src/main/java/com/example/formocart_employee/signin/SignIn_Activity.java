package com.skycode.formocart_employee.signin;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DashboardActivity;
import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

public class SignIn_Activity extends AppCompatActivity {

    private ImageView close;
    private EditText et_emailId,et_password;
    private Button login_btn;
    private RelativeLayout otp_rel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(SignIn_Activity.this);
        setContentView(R.layout.activity_sign_in_);
        get_find_view();
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    new SessionManager(SignIn_Activity.this).setLoginInfo("E1001");
                    new SessionManager(SignIn_Activity.this).setLoginInfoName("Demo");
                    Toast.makeText(SignIn_Activity.this,"Login Success",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignIn_Activity.this, DashboardActivity.class));
//                    get_login();
                }
            }
        });
    }

    private void get_login() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email_id", et_emailId.getText().toString());
            jsonObject.put("ps_wrd",et_password.getText().toString());
        } catch (JSONException e) {

        }
        final ProgressDialog pd = new ProgressDialog(SignIn_Activity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl +"emp_login.php",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pd.dismiss();
                    String message=response.getString("message");
                    if(message.equalsIgnoreCase("Login Failed")){
                        et_emailId.requestFocus();
                        et_emailId.setError("Invalid Email ID or Passward");
                    }
                    else {
                        new SessionManager(SignIn_Activity.this).setLoginInfo(response.getString("emp_id"));
                        new SessionManager(SignIn_Activity.this).setLoginInfoName(response.getString("emp_name"));
                        Toast.makeText(SignIn_Activity.this,"Login Success",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignIn_Activity.this, DashboardActivity.class));
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(SignIn_Activity.this, "" + e);
                    pd.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                et_emailId.requestFocus();
                et_emailId.setError("Invalid Email ID or Passward");
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
        RequestQueue rq = Volley.newRequestQueue(SignIn_Activity.this);
        rq.add(req);
    }

    private boolean validate() {
        if(et_emailId.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(SignIn_Activity.this,"Please Enter Email Id",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(et_emailId.getText().toString()).matches()){
            Toast.makeText(SignIn_Activity.this,"Please Enter Valid Email Id",Toast.LENGTH_LONG).show();
            return false;
        }
        else if(et_password.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(SignIn_Activity.this,"Please Enter Password",Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }

    private void get_find_view() {
        close=(ImageView)findViewById(R.id.close);
        et_emailId=(EditText)findViewById(R.id.et_emailId);
        et_password=(EditText)findViewById(R.id.et_password);
        login_btn=(Button)findViewById(R.id.login_btn);
        otp_rel=(RelativeLayout)findViewById(R.id.otp_Rel);
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        finishAffinity();
        finish();
    }
}
