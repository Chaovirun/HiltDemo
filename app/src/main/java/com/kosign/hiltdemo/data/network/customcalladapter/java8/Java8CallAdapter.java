package com.kosign.hiltdemo.data.network.customcalladapter.java8;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;

public class Java8CallAdapter<R> implements CallAdapter<R, CompletableFuture<R>> {
    @Override
    public Type responseType() {
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public CompletableFuture<R> adapt(Call call) {
        final CompletableFuture<R> future =
                new CompletableFuture<R>() {
                    @Override
                    public boolean cancel(boolean mayInterruptIfRunning) {
                        if (mayInterruptIfRunning) {
                            call.cancel();
                        }
                        return super.cancel(mayInterruptIfRunning);
                    }
                };

        call.enqueue(
                new Callback<R>() {
                    @Override
                    public void onResponse(Call<R> call, Response<R> response) {
                        if (response.isSuccessful()) {
                            future.complete(response.body());
                        } else {
                            future.completeExceptionally(new HttpException(response));
                        }
                    }

                    @Override
                    public void onFailure(Call<R> call, Throwable t) {
                        future.completeExceptionally(t);
                    }
                });

        return future;
    }
}
