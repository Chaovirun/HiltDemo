package com.kosign.hiltdemo.data.network.customcalladapter.rx;

import android.util.Log;

import com.kosign.hiltdemo.data.network.Resource;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;

public class RxJavaObservableCallAdapter<R> implements CallAdapter<R, Observable<?>> {

    private Type responseType;

    RxJavaObservableCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public Observable<?> adapt(Call<R> call) {

        call.enqueue(new Callback<R>() {
            @Override
            public void onResponse(Call<R> call, Response<R> response) {

                Log.d(">>", "onResponse: " + response.code() + response.isSuccessful() + response.body());

                if (response.isSuccessful()){

                }
//                    o(new Resource.Success(response.body()));
                else {
//                    postValue(new Resource.Error(response.code(),response.message()));
                }
            }

            @Override
            public void onFailure(Call<R> call, Throwable t) {
                Log.d(">>", "onFailure: " + t.getMessage());
                try {
//                    postValue(new Resource.Error(-2, null));
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        return null;
    }
}
