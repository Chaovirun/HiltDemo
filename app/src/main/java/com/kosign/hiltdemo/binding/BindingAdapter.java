package com.kosign.hiltdemo.binding;

import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class BindingAdapter {

    @androidx.databinding.BindingAdapter("app:imgUlr")
    public static void setUserImage(ImageView image , String url){
        if (!url.isEmpty()) {
            Glide.with(image).load(url).into(image);
        }
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
            textView.setText(originContent);
        }
    }

}