package com.kosign.hiltdemo.ui.base;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.kosign.hiltdemo.R;

public abstract class BaseActivity<T extends ViewDataBinding> extends AppCompatActivity {

    protected T binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind();
    }

    protected void bind(){
        binding = DataBindingUtil.setContentView(this, getLayoutRes());
        binding.setLifecycleOwner(this);
        binding.executePendingBindings();
    }

    @LayoutRes
    protected abstract int getLayoutRes();

}
