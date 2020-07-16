package com.malaysia.bri.authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SignUpActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 101;
    private TextView already_signup;
    private ImageView language;
    private A_Service_URL a_service_url;
    private RadioGroup lang_radio;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location currentLocation;
    private EditText etname,mobile_number,et_emailId,et_location,et_passcode,et_re_enter_passcode;
    private Button register_btn;
    private EditText et_pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        get_find_viewBYID();
        already_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,SignIn_Activity.class));
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_language_dialogbox();
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    get_register();
                }
            }
        });
    }

    private void get_register() {
        final ProgressDialog pd = new ProgressDialog(SignUpActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cust_name",etname.getText().toString());
            jsonObject.put("cust_email",et_emailId.getText().toString());
            jsonObject.put("cust_mobile", mobile_number.getText().toString());
            jsonObject.put("cust_address", et_location.getText().toString());
            jsonObject.put("cust_pass", et_passcode.getText().toString());
            Log.d("jhdasgcjhsadc", "" + jsonObject);
        } catch (JSONException e) {

        }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "addshop_api.php",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("fjaecnjakfcnk",""+response);
                try {
                    pd.dismiss();
                    String message = response.getString("message");
                    if (message.equalsIgnoreCase("Registration Successfull")) {
                        Toast.makeText(getApplicationContext(), "Registration Successfull", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignUpActivity.this, SignIn_Activity.class).
                                putExtra("mobile",et_emailId.getText().toString()));
                    }
                    else if(message.equalsIgnoreCase("Your already exists please login")){
                        Toast.makeText(getApplicationContext(), "Your already exists please login", Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(SignUpActivity.this, "" + e);
                    pd.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                pd.dismiss();
                Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(getApplicationContext(), "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(getApplicationContext(), "Auth Failed Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(getApplicationContext(), "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(getApplicationContext(), "Try Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue rq = Volley.newRequestQueue(SignUpActivity.this);
        rq.add(req);
    }

    private boolean validate() {
        if(etname.getText().toString().isEmpty()){
            etname.requestFocus();
            etname.setError(getString(R.string.name_of_the_customer));
            return false;
        }
        else if(mobile_number.getText().toString().isEmpty()){
            mobile_number.requestFocus();
            mobile_number.setError(getString(R.string.mobile_number));
            return false;
        }
        else if(et_emailId.getText().toString().isEmpty()){
            et_emailId.requestFocus();
            et_emailId.setError(getString(R.string.email_id));
            return false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(et_emailId.getText().toString()).matches()){
            et_emailId.requestFocus();
            et_emailId.setError("Enter Valid Email Address");
            return false;
        }
        else if(et_location.getText().toString().isEmpty()){
            et_location.requestFocus();
            et_location.setError("Enter Location");
            return false;
        }
        else if(et_pincode.getText().toString().isEmpty()){
            et_pincode.requestFocus();
            et_pincode.setError("Enter Pin Code");
            return false;
        }
        else if(et_passcode.getText().toString().isEmpty()){
            et_emailId.requestFocus();
            et_emailId.setError("Enter Passcode");
            return false;
        }
        else if(et_re_enter_passcode.getText().toString().isEmpty()){
            et_re_enter_passcode.requestFocus();
            et_re_enter_passcode.setError("Re enter Passcode");
            return false;
        }
        else if(!et_passcode.getText().toString().equalsIgnoreCase(et_re_enter_passcode.getText().toString())){
            et_re_enter_passcode.requestFocus();
            et_re_enter_passcode.setError("Passcode and re enter passcode not matched");
            return false;
        }
        else
            return true;
    }

    private void get_language_dialogbox() {
        final Dialog dialog = new Dialog(SignUpActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_language);
        ImageView close_img = (ImageView) dialog.findViewById(R.id.close_img);
        close_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        lang_radio = (RadioGroup) dialog.findViewById(R.id.lang_radio);
        lang_radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.uz) {
                    Locale myLocale;
                    myLocale = new Locale("uz");
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    Configuration conf = res.getConfiguration();
                    conf.locale = myLocale;
                    res.updateConfiguration(conf, dm);
                    startActivity(getIntent());
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(SignUpActivity.this, "Language Changed to Uzbek Successfully", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.english) {
                    Locale locale = new Locale("de");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getResources().updateConfiguration(config,getResources().getDisplayMetrics());
                    startActivity(getIntent());
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(SignUpActivity.this, "Language Changed to English Successfully", Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.py) {
                    Locale locale = new Locale("ba");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getResources().updateConfiguration(config,
                            getResources().getDisplayMetrics());
                    startActivity(getIntent());
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(SignUpActivity.this, "Language Changed to Pynnke Successfully", Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void get_find_viewBYID() {
        a_service_url=new A_Service_URL();
        etname=(EditText)findViewById(R.id.etname);
        mobile_number=(EditText)findViewById(R.id.mobile_number);
        et_emailId=(EditText)findViewById(R.id.et_emailId);
        et_location=(EditText)findViewById(R.id.et_location);
        et_passcode=(EditText)findViewById(R.id.et_passcode);
        et_re_enter_passcode=(EditText)findViewById(R.id.et_re_enter_passcode);
        register_btn=(Button)findViewById(R.id.register_btn);
        et_pincode=(EditText)findViewById(R.id.et_pincode);
        language=(ImageView)findViewById(R.id.language);
        already_signup=(TextView)findViewById(R.id.already_signup);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (checkLocationPermission())
            fetchLocation();
    }
    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                ActivityCompat.requestPermissions(SignUpActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);

            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // location-related task you need to do.
                    if (ContextCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        //Request location updates:
                        fetchLocation();
                    }

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.

                }
                return;
            }

        }
    }
    private void fetchLocation() {
        if (ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            return;
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    currentLocation = location;
                    String cityName = null, pincode = null, state_st = null, area = null, city = null;
                    Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                    List<Address> addresses;
                    try {
                        addresses = gcd.getFromLocation(location.getLatitude(),
                                location.getLongitude(), 1);
                        if (addresses.size() > 0) {
                            System.out.println(addresses.get(0).getLocality());
                            cityName = addresses.get(0).getAddressLine(0);
                            pincode = addresses.get(0).getPostalCode();
//                            state_st = addresses.get(0).getAdminArea();
                            area = addresses.get(0).getSubAdminArea();
                            city = addresses.get(0).getLocality();
                            if (cityName != null)
                                et_location.setText("" + cityName);
                            if(pincode !=null)
                                et_pincode.setText(""+pincode);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed(){
        finishAffinity();
        finish();
    }
}
