package com.skycode.formocart_employee.profile;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
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
import com.skycode.formocart_employee.dashboard.DashboardActivity;
import com.skycode.formocart_employee.dashboard.DrawerClass;
import com.skycode.formocart_employee.signin.A_Service_URL;
import com.skycode.formocart_employee.signin.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private EditText pro_name,designation,mobNo,emailId,alternateNo,emergencyNo, address,bankName,acName,acNo,ifscCode,branchName;
    private SessionManager sessionManager;
    private CircleImageView profile_img;
    private Intent pictureActionIntent = null;
    Bitmap bitmap;
    String selectedImagePath;
    private static final int GALLERY_PICTURE = 101;
    private static final int CAMERA_REQUEST = 102;
    private ImageView pencil;
    private TextView save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_profile);
        get_find_view_ByID();
        get_pro_details();
        pencil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pencil.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                alternateNo.setEnabled(true);
                emergencyNo.setEnabled(true);
                address.setEnabled(true);
                bankName.setEnabled(true);
                acName.setEnabled(true);
                acNo.setEnabled(true);
                ifscCode.setEnabled(true);
                branchName.setEnabled(true);
            }
        });
        profile_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get_dialog();
            }
        });
    }
    private void get_dialog() {
        if(isStoragePermission())
            get_Image();
    }
    private void get_pro_details() {
        final ProgressDialog pd = new ProgressDialog(ProfileActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("emp_id", sessionManager.getID());
        } catch (JSONException e) {

        }
        CustomJsonArrayRequest req = new CustomJsonArrayRequest(Request.Method.POST, A_Service_URL.getUrl + "emplprf.php",
                jsonObject, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    pd.dismiss();
                    JSONArray jArray = new JSONArray(response.toString());
                    for (int i = 0; i < jArray.length(); i++) {
                        JSONObject jsonObject = jArray.getJSONObject(i);
                        String name = jsonObject.getString("name");
                        String mobile = jsonObject.getString("mobile");
                        String email = jsonObject.getString("email");
                        String role = jsonObject.getString("role");
                        String bank_name = jsonObject.getString("bank_name");
                        String acnt_no = jsonObject.getString("acnt_no");
                        String ifsc_code = jsonObject.getString("ifsc_code");
                        pro_name.setText(""+name);
                        mobNo.setText(""+mobile);
                        emailId.setText(""+email);
                        designation.setText(""+role);
                        bankName.setText(""+bank_name);
                        acNo.setText(""+acnt_no);
                        ifscCode.setText(""+ifsc_code);
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(ProfileActivity.this, "" + e);
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
        RequestQueue rq = Volley.newRequestQueue(ProfileActivity.this);
        rq.add(req);
    }
    private void get_find_view_ByID() {
        sessionManager=new SessionManager(ProfileActivity.this);
        pro_name=(EditText)findViewById(R.id.userName);
        designation=(EditText)findViewById(R.id.designation);
        mobNo=(EditText)findViewById(R.id.mobNo);
        emailId=(EditText)findViewById(R.id.emailId);
        alternateNo=(EditText)findViewById(R.id.alternateNo);
        emergencyNo=(EditText)findViewById(R.id.emergencyNo);
        address=(EditText)findViewById(R.id.address);
        bankName=(EditText)findViewById(R.id.bankName);
        acName=(EditText)findViewById(R.id.acName);
        acNo=(EditText)findViewById(R.id.acNo);
        ifscCode=(EditText)findViewById(R.id.ifscCode);
        branchName=(EditText)findViewById(R.id.branchName);
        drawer=(DrawerLayout)findViewById(R.id.drawer);
        profile_img=(CircleImageView)findViewById(R.id.profile_image);
        pencil=(ImageView)findViewById(R.id.pencil);
        save=(TextView)findViewById(R.id.save);
        DrawerClass drawerClass = new DrawerClass(ProfileActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content),drawer);
    }
    private void get_Image() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(ProfileActivity.this);
        myAlertDialog.setTitle("Upload Pictures Option");
        myAlertDialog.setMessage("How do you want to set your picture?");
        myAlertDialog.setPositiveButton("Gallery",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent pictureActionIntent = null;
                        pictureActionIntent = new Intent(
                                Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(
                                pictureActionIntent,
                                GALLERY_PICTURE);
                    }
                });
        myAlertDialog.setNegativeButton("Camera",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent intent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment
                                .getExternalStorageDirectory(), "temp.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                                Uri.fromFile(f));
                        startActivityForResult(intent,
                                CAMERA_REQUEST);
                    }
                });
        myAlertDialog.show();
    }
    private boolean isStoragePermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.CAMERA)==PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        bitmap = null;
        selectedImagePath = null;
        if (resultCode == RESULT_OK && requestCode == CAMERA_REQUEST) {
            File f = new File(Environment.getExternalStorageDirectory()
                    .toString());
            for (File temp : f.listFiles()) {
                if (temp.getName().equals("temp.jpg")) {
                    f = temp;
                    break;
                }
            }
            if (!f.exists()) {
                Toast.makeText(getBaseContext(),
                        "Error while capturing image", Toast.LENGTH_LONG)
                        .show();
                return;
            }
            try {
                bitmap = BitmapFactory.decodeFile(f.getAbsolutePath());

                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, true);

                int rotate = 0;
                try {
                    ExifInterface exif = new ExifInterface(f.getAbsolutePath());
                    int orientation = exif.getAttributeInt(
                            ExifInterface.TAG_ORIENTATION,
                            ExifInterface.ORIENTATION_NORMAL);

                    switch (orientation) {
                        case ExifInterface.ORIENTATION_ROTATE_270:
                            rotate = 270;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_180:
                            rotate = 180;
                            break;
                        case ExifInterface.ORIENTATION_ROTATE_90:
                            rotate = 90;
                            break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Matrix matrix = new Matrix();
                matrix.postRotate(rotate);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                        bitmap.getHeight(), matrix, true);
                profile_img.setImageBitmap(bitmap);
                //storeImageTosdCard(bitmap);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {
                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage, filePath,
                        null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                selectedImagePath = c.getString(columnIndex);
                c.close();
                if (selectedImagePath != null) {

                }
                bitmap = BitmapFactory.decodeFile(selectedImagePath); // load
                // preview image
                bitmap = Bitmap.createScaledBitmap(bitmap, 400, 400, false);
                profile_img.setImageBitmap(bitmap);
            } else {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(ProfileActivity.this);
        builder1.setMessage("Are you sure want to leave this page without filling all details ?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                     startActivity(new Intent(ProfileActivity.this, DashboardActivity.class));
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
