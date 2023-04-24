package com.kosign.hiltdemo.binding;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.kosign.hiltdemo.R;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;


public class BindingAdapter {

    @androidx.databinding.BindingAdapter("app:imgUlr")
    public static void setUserImage(ImageView image , String url){
        if (!url.isEmpty()) {
//            Glide.with(image).load(url).into(image);
            Picasso picasso = new Picasso.Builder(image.getContext())
                    .memoryCache(new LruCache(24000)).build();
            picasso.load(url).into(image);
        }
    }

    @androidx.databinding.BindingAdapter("app:visibility")
    public static void setVisibilityView(View view , boolean isShow){
        view.setVisibility(isShow ? View.VISIBLE : View.GONE);
    }

    @androidx.databinding.BindingAdapter({
            "app:searchKey", "app:searchOrigin"
    })
    public static void setSearchKeyColor(TextView textView, String keyFound, String originContent){
        if (!keyFound.isEmpty()) {
            Spannable s = new SpannableString(originContent);
            int start = originContent.toLowerCase().indexOf(keyFound.toLowerCase());
            int end = start + keyFound.length();
            s.setSpan(new ForegroundColorSpan(Color.BLUE), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            textView.setText(s, TextView.BufferType.SPANNABLE);
        }else {
            textView.setTextColor(ContextCompat.getColor(textView.getContext(), R.color.black));
            textView.setText(originContent);
        }
    }

}