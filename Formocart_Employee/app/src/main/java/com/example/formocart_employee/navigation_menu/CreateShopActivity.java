package com.skycode.formocart_employee.navigation_menu;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.skycode.formocart_employee.R;
import com.skycode.formocart_employee.dashboard.DashboardActivity;
import com.skycode.formocart_employee.dashboard.DrawerClass;
import com.skycode.formocart_employee.signin.A_Service_URL;
import com.skycode.formocart_employee.signin.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class CreateShopActivity extends AppCompatActivity {

    private DrawerLayout drawer;
    private TextView save;
    private EditText shop_name, contact_number, alternate_number, email_id, address, area_name, landmark, pin_code, current_location, gst_fln;
    private SessionManager sessionManager;
    private CircleImageView profile_img_addShop;
    private Intent pictureActionIntent = null;
    Bitmap bitmap;
    String selectedImagePath;
    private static final int GALLERY_PICTURE = 101;
    private static final int CAMERA_REQUEST = 102;
    private RelativeLayout shop_front_pic_rel, shop_inner_pic_rel;
    private ImageView shop_front_pic_img, shop_inner_pic_img;
    private String type = "";
    private ImageView close_front, close_inner;
    private RelativeLayout main_inner_rel, main_front_rel;
    private String image_profile, image_front, image_inner;
    private String status = "false";

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        setContentView(R.layout.activity_create_shop);
        get_viewById();
        close_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_front_rel.setVisibility(View.GONE);
                shop_front_pic_rel.setVisibility(View.VISIBLE);
            }
        });
        close_inner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_inner_rel.setVisibility(View.GONE);
                shop_inner_pic_rel.setVisibility(View.VISIBLE);
            }
        });
        profile_img_addShop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermission()) {
                    type = "Profile";
                    get_Image();
                }
            }
        });
        shop_front_pic_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermission()) {
                    type = "Shop_Front";
                    get_Image();
                }
            }
        });
        shop_inner_pic_rel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isStoragePermission()) {
                    type = "Shop_Inner";
                    get_Image();
                }
            }
        });
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                status = "true";
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shop_name.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Shop Name", Toast.LENGTH_LONG).show();
                } else if (contact_number.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Contact Number", Toast.LENGTH_LONG).show();
                } else if (email_id.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Email ID", Toast.LENGTH_LONG).show();
                } else if (address.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please Enter Address", Toast.LENGTH_LONG).show();
                } else {
                    get_create_shop();
                }
            }
        });
    }

    private void get_create_shop() {
        final ProgressDialog pd = new ProgressDialog(CreateShopActivity.this);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("creby", sessionManager.getID());
            jsonObject.put("shpnme", shop_name.getText().toString());
            jsonObject.put("smobile", contact_number.getText().toString());
            jsonObject.put("amobile", alternate_number.getText().toString());
            jsonObject.put("semail", email_id.getText().toString());
            jsonObject.put("saddrs", address.getText().toString());
            jsonObject.put("sarea", area_name.getText().toString());
            jsonObject.put("spincde", pin_code.getText().toString());
            jsonObject.put("sgst", gst_fln.getText().toString());
            jsonObject.put("img1", image_profile);
            jsonObject.put("img2", image_front);
            jsonObject.put("img3", image_inner);
            Log.d("jhdasgcjhsadc", "" + jsonObject);
        } catch (JSONException e) {

        }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "addshop_api.php",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(getApplicationContext(), "" + response, Toast.LENGTH_LONG).show();
                try {
                    pd.dismiss();
                    String message = response.getString("message");
                    if (message.equalsIgnoreCase("Shop added successfully")) {
                        Toast.makeText(getApplicationContext(), "Shop Added Successfully", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(CreateShopActivity.this, DashboardActivity.class));
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(CreateShopActivity.this, "" + e);
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
        RequestQueue rq = Volley.newRequestQueue(CreateShopActivity.this);
        rq.add(req);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void get_viewById() {
        sessionManager = new SessionManager(CreateShopActivity.this);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        save = (TextView) findViewById(R.id.save);
        DrawerClass drawerClass = new DrawerClass(CreateShopActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content), drawer);
        shop_name = (EditText) findViewById(R.id.shop_name);
        contact_number = (EditText) findViewById(R.id.contact_number);
        alternate_number = (EditText) findViewById(R.id.alternateNo);
        email_id = (EditText) findViewById(R.id.email_id);
        address = (EditText) findViewById(R.id.address);
        area_name = (EditText) findViewById(R.id.area_name);
        landmark = (EditText) findViewById(R.id.landmark);
        pin_code = (EditText) findViewById(R.id.pin_code);
        current_location = (EditText) findViewById(R.id.current_location);
        profile_img_addShop = (CircleImageView) findViewById(R.id.profile_image_addShop);
        gst_fln = (EditText) findViewById(R.id.gst_fln);
        shop_front_pic_rel = (RelativeLayout) findViewById(R.id.shop_front_pic_rel);
        shop_front_pic_img = (ImageView) findViewById(R.id.shop_front_pic_img);
        shop_inner_pic_rel = (RelativeLayout) findViewById(R.id.shop_inner_pic_rel);
        shop_inner_pic_img = (ImageView) findViewById(R.id.shop_inner_pic_img);
        close_front = (ImageView) findViewById(R.id.close_front);
        close_inner = (ImageView) findViewById(R.id.close_inner);
        main_front_rel = (RelativeLayout) findViewById(R.id.shop_front_mainRel);
        main_inner_rel = (RelativeLayout) findViewById(R.id.shop_inner_mainRel);

        // Define a listener that responds to location updates
        if (check_locationPermission()) {
            get_location();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void get_location() {
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

// Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {

            public void onLocationChanged(Location location) {

                // Called when a new location is found by the network location provider.
                String cityName = null;
                Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
                List<Address> addresses;
                try {
                    addresses = gcd.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                    if (addresses.size() > 0) {
                        System.out.println(addresses.get(0).getLocality());
                        cityName = addresses.get(0).getAddressLine(0);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (status != null && status.equalsIgnoreCase("false"))
                    address.setText(cityName);
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// Register the listener with the Location Manager to receive location updates
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    }

    private boolean check_locationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }

    private void get_Image() {
        AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(CreateShopActivity.this);
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
                    && checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
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

                try {
                    bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                            bitmap.getHeight(), matrix, true);
                    if (type.equalsIgnoreCase("Profile")) {
                        profile_img_addShop.setImageBitmap(bitmap);
                        image_profile = getEncoded64ImageStringFromBitmap(bitmap);
                    } else if (type.equalsIgnoreCase("Shop_Front")) {
                        main_front_rel.setVisibility(View.VISIBLE);
                        shop_front_pic_rel.setVisibility(View.GONE);
                        shop_front_pic_img.setImageBitmap(bitmap);
                        image_front = getEncoded64ImageStringFromBitmap(bitmap);
                    } else if (type.equalsIgnoreCase("Shop_Inner")) {
                        main_inner_rel.setVisibility(View.VISIBLE);
                        shop_inner_pic_rel.setVisibility(View.GONE);
                        shop_inner_pic_img.setImageBitmap(bitmap);
                        image_inner = getEncoded64ImageStringFromBitmap(bitmap);
                    }
                } catch (Exception e) {
                    Log.e("Exception", e.toString());
                }
                //storeImageTosdCard(bitmap);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else if (resultCode == RESULT_OK && requestCode == GALLERY_PICTURE) {
            if (data != null) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getContentResolver().query(selectedImage, filePath,
                        null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                selectedImagePath = c.getString(columnIndex);
                c.close();
                if (selectedImagePath != null) {

                }
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
                    bitmap = BitmapFactory.decodeFile(selectedImagePath); // load
                    // preview image
                    try {
                        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                                bitmap.getHeight(), matrix, true);
                        if (type.equalsIgnoreCase("Profile")) {
                            profile_img_addShop.setImageBitmap(bitmap);
                            image_profile = getEncoded64ImageStringFromBitmap(bitmap);
                        } else if (type.equalsIgnoreCase("Shop_Front")) {
                            main_front_rel.setVisibility(View.VISIBLE);
                            shop_front_pic_rel.setVisibility(View.GONE);
                            shop_front_pic_img.setImageBitmap(bitmap);
                            image_front = getEncoded64ImageStringFromBitmap(bitmap);
                        } else if (type.equalsIgnoreCase("Shop_Inner")) {
                            main_inner_rel.setVisibility(View.VISIBLE);
                            shop_inner_pic_rel.setVisibility(View.GONE);
                            shop_inner_pic_img.setImageBitmap(bitmap);
                            image_inner = getEncoded64ImageStringFromBitmap(bitmap);
                        }
                    } catch (Exception e) {
                        Log.e("Exception", e.toString());
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Cancelled",
                        Toast.LENGTH_SHORT).show();
            }
        }

    }

    public String getEncoded64ImageStringFromBitmap(Bitmap bitmap) {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String imgString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);
        return imgString;
    }
}
