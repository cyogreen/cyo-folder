package com.malaysia.bri.authentication;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
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
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.SessionManager;
import com.malaysia.bri.dashboard.Dashboard;
import com.facebook.FacebookSdk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.Locale;

import static com.google.android.gms.auth.api.credentials.CredentialPickerConfig.Prompt.SIGN_IN;

public class SignIn_Activity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    private ImageView close_email, close_pass, close_main;
    private EditText et_emailId, et_password;
    private Button login_btn;
    private RelativeLayout otp_rel;
    private TextView new_user;
    private ImageView language;
    private RadioGroup lang_radio;
    private SessionManager sessionManager;
    private A_Service_URL a_service_url;
    private String lang = "";
    private String mobile = "";
    private RelativeLayout facebookRel,gmailRel;
    private GoogleApiClient mGoogleApiClient;
    private ProgressBar progressFacebook;
    private ProgressBar progressGoogle;
    private ImageView imgGmail;
    private LoginButton loginButton;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(SignIn_Activity.this);
        setContentView(R.layout.activity_sign_in_);
        get_find_view();
        close_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_emailId.setText("");
            }
        });
        close_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_password.setText("");
            }
        });
        close_main.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignIn_Activity.this, SignUpActivity.class));
            }
        });
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {
//                    new SessionManager(SignIn_Activity.this).setLoginInfo("C1001");
//                    Toast.makeText(SignIn_Activity.this, "Login Success", Toast.LENGTH_LONG).show();
//                    startActivity(new Intent(SignIn_Activity.this, Dashboard.class));
                    get_login();
                }
            }
        });
        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_language_dialogbox();
            }
        });
        loginButton = findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));

        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                //loginResult.getAccessToken();
                //loginResult.getRecentlyDeniedPermissions()
                //loginResult.getRecentlyGrantedPermissions()
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");
            }
            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
        facebookRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(
                        SignIn_Activity.this,
                        Arrays.asList("user_photos", "email", "user_birthday", "public_profile")
                );
            }
        });
        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            //Using Graph API
            getUserProfile(AccessToken.getCurrentAccessToken());
        }
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(SignIn_Activity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        gmailRel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgGmail.setVisibility(View.GONE);
                progressGoogle.setVisibility(View.VISIBLE);
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, SIGN_IN);
            }
        });
    }
    private void handleSignInResult(GoogleSignInResult result) {
        //If the login succeed
        progressGoogle.setVisibility(View.GONE);
        imgGmail.setVisibility(View.VISIBLE);
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            assert acct != null;
            String personName = acct.getDisplayName();
            String personPhotoUrl = "";
            if (acct.getPhotoUrl() != null) {
                personPhotoUrl = acct.getPhotoUrl().toString();
            }
            String email_gmail = acct.getEmail();
            A_Service_URL.getToast(getApplicationContext(), "Success"+email_gmail+"\n"+personName);
            new SessionManager(SignIn_Activity.this).setLoginInfo("C1001");
            Toast.makeText(SignIn_Activity.this, "Login Success", Toast.LENGTH_LONG).show();
            startActivity(new Intent(SignIn_Activity.this, Dashboard.class));
//            startActivity(new Intent(SignIn_Activity.this,Dashboard.class));
        } else {
            A_Service_URL.getToast(getApplicationContext(), "Error"+result.toString());
        }
    }
    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", object.toString());
                        try {
                            String first_name = object.getString("first_name");
                            String last_name = object.getString("last_name");
                            String email_facebook = object.getString("email");
                            String id = object.getString("id");
                            String image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    private void get_language_dialogbox() {
        final Dialog dialog = new Dialog(SignIn_Activity.this);
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
                    Toast.makeText(SignIn_Activity.this, R.string.lang_change_uzbek, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.english) {
                    Locale locale = new Locale("de");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getResources().updateConfiguration(config, getResources().getDisplayMetrics());
                    startActivity(getIntent());
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(SignIn_Activity.this, R.string.lang_change_eng, Toast.LENGTH_SHORT).show();
                } else if (checkedId == R.id.py) {
                    Locale locale = new Locale("ba");
                    Locale.setDefault(locale);
                    Configuration config = new Configuration();
                    config.locale = locale;
                    getResources().updateConfiguration(config,
                            getResources().getDisplayMetrics());
                    startActivity(getIntent());
                    overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                    Toast.makeText(SignIn_Activity.this, R.string.lang_change_russia, Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
        Window window = dialog.getWindow();
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }
    private void get_login() {
        final ProgressDialog pd = new ProgressDialog(SignIn_Activity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("email_id", et_emailId.getText().toString());
            jsonObject.put("ps_wrd", et_password.getText().toString());
            Log.e("wdcmqeklfcc",""+jsonObject);
        } catch (JSONException e) {
            Log.e("wdcmqeklfcc",""+e.getMessage());
        }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "cust_login.php",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    pd.dismiss();
                    Log.e("wdcmqeklfcc",""+response);
                    String message = response.getString("message");
                    if (message.equalsIgnoreCase("Login Failed")) {
                        et_emailId.requestFocus();
                        et_emailId.setError(getString(R.string.invalid_email));
                    } else {
                        new SessionManager(SignIn_Activity.this).setLoginInfo(response.getString("Customer ID"));
                        Toast.makeText(SignIn_Activity.this, "Login Success", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(SignIn_Activity.this, Dashboard.class));
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(SignIn_Activity.this, "" + e);
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                et_emailId.requestFocus();
                et_emailId.setError(getString(R.string.invalid_email));
                pd.dismiss();
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
        if (et_emailId.getText().toString().equalsIgnoreCase("")) {
            et_emailId.requestFocus();
            et_emailId.setError("Enter Email ID");
            return false;
        } else if (et_password.getText().toString().equalsIgnoreCase("")) {
            et_password.requestFocus();
            et_password.setError("Enter Password");
            return false;
        } else {
            return true;
        }
    }

    private void get_find_view() {
        a_service_url = new A_Service_URL();
        sessionManager = new SessionManager(SignIn_Activity.this);
        et_emailId = (EditText) findViewById(R.id.et_emailId);
        et_password = (EditText) findViewById(R.id.et_password);
        login_btn = (Button) findViewById(R.id.login_btn);
        otp_rel = (RelativeLayout) findViewById(R.id.otp_rel);
        close_email = (ImageView) findViewById(R.id.close_email);
        close_pass = (ImageView) findViewById(R.id.close_pass);
        close_main = (ImageView) findViewById(R.id.close_main);
        new_user = (TextView) findViewById(R.id.new_user);
        language = (ImageView) findViewById(R.id.language);
        facebookRel = (RelativeLayout) findViewById(R.id.facebookRel);
        gmailRel = (RelativeLayout) findViewById(R.id.gmailRel);
        progressFacebook = (ProgressBar) findViewById(R.id.progressFacebook);
        progressGoogle = (ProgressBar) findViewById(R.id.progressGmail);
        imgGmail=(ImageView)findViewById(R.id.imgGmail);
        mobile = getIntent().getStringExtra("mobile");
        if (mobile != null) {
            et_emailId.setText("" + mobile);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //If signin
        if (requestCode == SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            //Calling a new function to handle signin
            handleSignInResult(result);
        }
        else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
