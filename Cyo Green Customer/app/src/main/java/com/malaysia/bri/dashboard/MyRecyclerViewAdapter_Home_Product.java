package com.malaysia.bri.dashboard;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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
import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.Util.SessionManager;
import com.malaysia.bri.startActivity.SplashActivity;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.malaysia.bri.Util.A_Service_URL.dataArrayImage;

public class MyRecyclerViewAdapter_Home_Product extends RecyclerView.Adapter<MyRecyclerViewAdapter_Home_Product.ViewHolder> {

    private final Context context;
    private final SessionManager sessionManager;
    private ArrayList<CustomClass_HomeProduct> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter_Home_Product(Context context, ArrayList<CustomClass_HomeProduct> data) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        sessionManager=new SessionManager(context);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        String animal = mData.get(position).getPrd_name();
        holder.sale_price.setText("Rs. "+mData.get(position).getSale_price());
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sessionManager.getID() != null && !sessionManager.getID().equalsIgnoreCase("")) {
                    holder.addToCart.setVisibility(View.GONE);
                    holder.cart_add_sub.setVisibility(View.VISIBLE);
                    holder.number.setText(mData.get(position).getMin_qty());
                    get_add_to_cart(position, holder);
                }
                else {
                    Toast.makeText(context,"You must Login first",Toast.LENGTH_LONG).show();
                }
            }
        });
        if(dataArrayImage!=null) {
            for (int i = 0; i < dataArrayImage.size(); i++) {
                if (mData.get(position).getPrd_id().equalsIgnoreCase(dataArrayImage.get(i).getPrd_id())) {
                    Picasso.get().load(A_Service_URL.getImageURl + dataArrayImage.get(i).getPrd_img()).fit().noFade()
                            /*.resize(imageView.getWidth(), imageView.getHeight()).fit().centerInside().resize(70, 70)*/
                            .placeholder(R.drawable.progress_loading)
                            .error(R.drawable.warning).noFade().into(holder.img_);
                    holder.myTextView.setText(""+dataArrayImage.get(i).getPro_name());
                }
            }
        }
        else {
            context.startActivity(new Intent(context, SplashActivity.class));
        }

        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.number.getText().equals(mData.get(position).getMin_qty())){
                    holder.addToCart.setVisibility(View.VISIBLE);
                    holder.cart_add_sub.setVisibility(View.GONE);
                }
                else {
                    int count=Integer.parseInt(holder.number.getText().toString());
                    if(Integer.parseInt(mData.get(position).getMin_qty())<=count){
                        holder.number.setText(String.valueOf(count-Integer.parseInt(mData.get(position).getMin_qty())));
                        get_add_to_cart(position,holder);
                    }
                    else
                        Toast.makeText(context, "Please Select Minimum Quantity "+mData.get(position).getMin_qty(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count=Integer.parseInt(holder.number.getText().toString());
                if(Integer.parseInt(mData.get(position).getMax_qty())>count) {
                    holder.number.setText(String.valueOf(count + Integer.parseInt(mData.get(position).getMin_qty())));
                    get_add_to_cart(position,holder);
                }
                else
                    Toast.makeText(context, "Maximum Quantity should be "+mData.get(position).getMax_qty(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void get_add_to_cart(int position, final ViewHolder holder) {
        final ProgressDialog pd = new ProgressDialog(context);
        pd.setCancelable(false);
        pd.setMessage("Please Wait...!");
        pd.show();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("shop_id", sessionManager.getID());
            jsonObject.put("prd_id",mData.get(position).getPrd_id());
            jsonObject.put("order_qty",holder.number.getText().toString());
            jsonObject.put("order_amnt",mData.get(position).getSale_price());
        } catch (JSONException e) {

        }
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, A_Service_URL.getUrl + "mycart.php",
                jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e("kwjrncbjekcne",""+ response);
                try {
                    pd.dismiss();
                    String message=response.getString("message");
                    if(message.equalsIgnoreCase("Item added successfully")){
                        Toast.makeText(context,"Item Added Successfully",Toast.LENGTH_LONG).show();
                    }
                    else if(message.equalsIgnoreCase("Item Updated successfully")){
                        Toast.makeText(context,"Item Updated Successfully",Toast.LENGTH_LONG).show();
                    }
                    else if(message.equalsIgnoreCase("Currently Available Stock is 0")){
                        Toast.makeText(context,"Sorry , No Stock available",Toast.LENGTH_SHORT).show();
                        holder.addToCart.setVisibility(View.VISIBLE);
                        holder.cart_add_sub.setVisibility(View.GONE);
                    }
                } catch (JSONException e) {
                    A_Service_URL.getToast(context, "" + e);
                    pd.dismiss();
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("kwjrncbjekcne",""+ error.getMessage());
                pd.dismiss();
                Toast.makeText(context, "Try Again" + error, Toast.LENGTH_LONG).show();
                if (error instanceof NetworkError) {
                    Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof AuthFailureError) {
                    Toast.makeText(context, "Auth Failed Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof ParseError) {
                    Toast.makeText(context, "Parse Error", Toast.LENGTH_SHORT).show();
                } else if (error instanceof TimeoutError) {
                    Toast.makeText(context, "Time Out Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        RequestQueue rq = Volley.newRequestQueue(context);
        rq.add(req);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final CardView addToCart;
        private final LinearLayout cart_add_sub;
        private final TextView sub,number,add,sale_price;
        private final ImageView img_;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.catName);
            addToCart=(CardView)itemView.findViewById(R.id.addToCart);
            cart_add_sub=(LinearLayout)itemView.findViewById(R.id.cart_add_sub);
            sub=(TextView)itemView.findViewById(R.id.minus);
            number=(TextView)itemView.findViewById(R.id.number);
            add=(TextView)itemView.findViewById(R.id.add);
            sale_price=(TextView)itemView.findViewById(R.id.sale_price);
            img_=(ImageView)itemView.findViewById(R.id.img_);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}