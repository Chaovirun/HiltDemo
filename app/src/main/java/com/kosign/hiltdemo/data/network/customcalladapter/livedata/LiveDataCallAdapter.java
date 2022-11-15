package com.kosign.hiltdemo.data.network.customcalladapter.livedata;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kosign.hiltdemo.data.api.ApiService;
import com.kosign.hiltdemo.data.network.Network;
import com.kosign.hiltdemo.data.network.Resource;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.atomic.AtomicBoolean;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveDataCallAdapter<R> implements CallAdapter<R, LiveData<Resource<R>>> {

    private Type responseType;

    LiveDataCallAdapter(Type responseType) {
        this.responseType = responseType;
    }

    @Override
    public Type responseType() {
        return responseType;
    }

    @Override
    public LiveData<Resource<R>> adapt(Call<R> call) {
        return new MutableLiveData<Resource<R>>() {
            boolean isSuccess = false;
            AtomicBoolean started = new AtomicBoolean(false);

            @Override
            protected void onActive() {
                super.onActive();
                if (!isSuccess){
                    enqueue();
                }

            }

            @Override
            protected void onInactive() {
                super.onInactive();
                dequeue();
            }

            void dequeue() {
                if (call.isExecuted()) call.cancel();
            }

            void enqueue() {
                Log.d(">>>", "onActive: " + started);
                call.enqueue(new Callback<R>() {
                    @Override
                    public void onResponse(Call<R> call, Response<R> response) {

                        Log.d(">>", "onResponse: " + response.code() + response.isSuccessful() + response.body());

                        if (response.isSuccessful())
                            postValue(new Resource.Success(response.body()));
                        else {
                            String errorMsg = "-1";
                            String errorCode = "";
                            try {
                                JSONObject jsonObject = new JSONObject(response.errorBody().string());
//                                JSONObject jsonError = jsonObject.getJSONObject("error");

//                                errorCode = jsonError.getString("code");
                                errorMsg = jsonObject.getString("error_description");

                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }
                            postValue(new Resource.Error(response.code(),errorMsg));
                        }
                    }

                    @Override
                    public void onFailure(Call<R> call, Throwable t) {
                        Log.d(">>", "onFailure: " + t.getMessage());
                        try {
                            postValue(new Resource.Error(-2, t.getMessage()));
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
    }
}
