package com.malaysia.bri;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.malaysia.bri.Util.A_Service_URL;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Life's Good on 07-Sep-18.
 */

public class BannerAdapter extends PagerAdapter {

    private ArrayList<String> images;
    private Context context;

    public BannerAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);
        ImageView imageView = new ImageView(context);
        imageView.setLayoutParams(layoutParams);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        if(images.get(position) != null && !images.get(position).equals("")) {
            Picasso.get().load(A_Service_URL.getImageURl+images.get(position)).fit().noFade().centerCrop()
                    /*.resize(imageView.getWidth(), imageView.getHeight()).fit().centerInside().resize(70, 70)*/
                    .placeholder(R.drawable.progress_loading)
                    .error(R.drawable.warning).noFade().into(imageView);
        }
        else
            imageView.setVisibility(View.GONE);
//            imageView.setImageResource(images.get(position));
        view.addView(imageView);
        return imageView;
    }
}