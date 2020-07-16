package com.malaysia.bri.dashboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.malaysia.bri.R;
import com.malaysia.bri.Util.A_Service_URL;
import com.malaysia.bri.category.Category_Activity;
import com.malaysia.bri.modelClass.CustomClass_CategoryName;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyCategoryListingAdapter extends RecyclerView.Adapter<MyCategoryListingAdapter.ViewHolder> {

    private final Context context;
    private ArrayList<CustomClass_CategoryName> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyCategoryListingAdapter(Context context, ArrayList<CustomClass_CategoryName> data) {
        this.context=context;
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycler_category_dashboard, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
      holder.catname_tt.setText(mData.get(position).getCat_name());
        if(mData.get(position).getCat_img() != null && !mData.get(position).getCat_img().equals("")) {
            Picasso.get().load(A_Service_URL.getImageURl+mData.get(position).getCat_img()).fit().noFade()
                    /*.resize(imageView.getWidth(), imageView.getHeight()).fit().centerInside().resize(70, 70)*/
                    .placeholder(R.drawable.progress_loading)
                    .error(R.drawable.warning).noFade().into(holder.cat_img);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Category_Activity.class).putExtra("type",
                        mData.get(position).getCat_name()));
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final ImageView cat_img;
        private final TextView catname_tt;
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            catname_tt = itemView.findViewById(R.id.catname_tt);
            cat_img=(ImageView)itemView.findViewById(R.id.cat_img);
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