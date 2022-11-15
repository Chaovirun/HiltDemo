package com.kosign.hiltdemo.data.network.customcalladapter.flow;

import android.util.Log;

import com.kosign.hiltdemo.R;

import java.lang.reflect.Type;

import kotlinx.coroutines.flow.Flow;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class FlowCallAdapter<R> implements CallAdapter<R, Flow<R>> {

    private Type responseType;

    FlowCallAdapter(Type responseType){
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Flow<R> adapt(Call<R> call) {

        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {
                if (response.isSuccessful()){
                    Log.d(">>", "onResponse: " + response.body());
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                Log.d(">>>", "onFailure: " + t.getMessage());
            }
        });
        return null;
    }
}
