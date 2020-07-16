package com.malaysia.bri;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public MyRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String animal = mData.get(position);
        holder.myTextView.setText(animal);
        holder.addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.addToCart.setVisibility(View.GONE);
                holder.cart_add_sub.setVisibility(View.VISIBLE);
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.number.getText().equals("1")){
                    holder.addToCart.setVisibility(View.VISIBLE);
                    holder.cart_add_sub.setVisibility(View.GONE);
                }
                else {
                    int count=Integer.parseInt(holder.number.getText().toString());
                    holder.number.setText(String.valueOf(count-1));
                }
            }
        });
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int count=Integer.parseInt(holder.number.getText().toString());
                    holder.number.setText(String.valueOf(count+1));

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
        private final CardView addToCart;
        private final LinearLayout cart_add_sub;
        private final TextView sub,number,add,sale_price;
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}