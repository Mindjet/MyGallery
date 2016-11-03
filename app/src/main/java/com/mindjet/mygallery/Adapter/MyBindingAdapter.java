package com.mindjet.mygallery.Adapter;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by mindjet on 03/11/2016.
 */

public class MyBindingAdapter {

    @BindingAdapter("glide:showImage")
    public static void setImageByGlide(ImageView view, String imgUrl) {

        Glide.with(view.getContext())
                .load(imgUrl)
                .thumbnail(0.1f)
                .into(view);

    }

}
