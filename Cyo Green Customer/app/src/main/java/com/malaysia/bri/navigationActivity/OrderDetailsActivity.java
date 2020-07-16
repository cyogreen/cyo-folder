package com.malaysia.bri.navigationActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.DrawerClass;
import com.malaysia.bri.startActivity.SplashActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static com.malaysia.bri.Util.A_Service_URL.dataArrayImage;

public class OrderDetailsActivity extends AppCompatActivity {

    private String prd_id,per_kg_amount,qty;
    private ArrayList<Custom_OrderDetail> dataArray_OrderDetail;
    private TextView items;
    private LinearLayout cartMainLL;
    private DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        items = (TextView) findViewById(R.id.items);
        drawer = (DrawerLayout) findViewById(R.id.drawer);
        DrawerClass drawerClass = new DrawerClass(OrderDetailsActivity.this);
        drawerClass.getDrawerFunctionality(this.findViewById(android.R.id.content), drawer);
        dataArray_OrderDetail=new ArrayList<Custom_OrderDetail>();
        cartMainLL = (LinearLayout) findViewById(R.id.custMainLL);
        prd_id=getIntent().getStringExtra("prd_id");
        per_kg_amount=getIntent().getStringExtra("per_kg_amount");
        qty=getIntent().getStringExtra("qty");
        String product_id_st = prd_id;
        String per_kg_amount_st = per_kg_amount;
        String qty_st = qty;
        String[] proid_array = product_id_st.split(",");
        String[] per_kg_array = per_kg_amount_st.split(",");
        String[] qty_st_array = qty_st.split(",");
        for (int i = 0; i < proid_array.length; i++) {
            String prdarr = proid_array[i];
            String perarr = per_kg_array[i];
            String qtyarr = qty_st_array[i];
            Custom_OrderDetail customClass = new Custom_OrderDetail(prdarr, perarr,
                    qtyarr);
            dataArray_OrderDetail.add(customClass);
            design(i);
        }
        items.setText("Total No. Of Items : " + dataArray_OrderDetail.size());
//        sessionManager.setCartItem(String.valueOf(dataArray_OrderDetail.size()));
        }

    private void design(int i) {
        LayoutInflater layoutInflater = (LayoutInflater) OrderDetailsActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.view_cart_list, null);
        cartMainLL.addView(v);
        ImageView img = (ImageView) v.findViewById(R.id.img_main);
        TextView proName = (TextView) v.findViewById(R.id.proName);
        TextView qty = (TextView) v.findViewById(R.id.qty);
        ImageView delete = (ImageView) v.findViewById(R.id.delete);
        TextView rate = (TextView) v.findViewById(R.id.rate);
//        address.setText(dataArray.get(i).getAddress());
        qty.setText("Quantity " + dataArray_OrderDetail.get(i).getQtyarr());
        rate.setText("Price Per Kg Rs. " + dataArray_OrderDetail.get(i).getPerarr());

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
//        total_st = total_st+ Double.parseDouble(dataArray_OrderDetail.get(i).getTotal_amount()) ;
        if (dataArrayImage != null) {
            for (int j = 0; j < dataArrayImage.size(); j++) {
                if (dataArray_OrderDetail.get(i).getPrdarr().equalsIgnoreCase(dataArrayImage.get(j).getPrd_id())) {
                    Picasso.get().load(A_Service_URL.getImageURl + dataArrayImage.get(j).getPrd_img()).fit().noFade()
                            /*.resize(imageView.getWidth(), imageView.getHeight()).fit().centerInside().resize(70, 70)*/
                            .placeholder(R.drawable.progress_loading)
                            .error(R.drawable.warning).noFade().into(img);
                    proName.setText(""+dataArrayImage.get(j).getPro_name());
                }
            }
        } else {
            startActivity(new Intent(OrderDetailsActivity.this, SplashActivity.class));
        }
//        if (dataArray.get(i).getVat().equalsIgnoreCase("0.0") && dataArray.get(i).getVat().equalsIgnoreCase(""))
//            vat.setText("Rs. 0.00");
//        else
//            vat.setText("Rs. " + dataArray.get(i).getVat());
//        total.setText("Rs. " +total_st);
        delete.setVisibility(View.GONE);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder1 = new AlertDialog.Builder(CartActivity.this);
//                builder1.setMessage("Are you sure want to delete this product from cart list");
//                builder1.setCancelable(true);
//                builder1.setPositiveButton(
//                        "Yes",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                get_Cart_delete(i);
//                            }
//                        });
//                builder1.setNegativeButton(
//                        "No",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        });
//                AlertDialog alert11 = builder1.create();
//                alert11.show();
            }
//        });
//    }
}
