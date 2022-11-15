package com.kosign.hiltdemo.data.network.customcalladapter.java8;

import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.kosign.hiltdemo.data.network.Resource;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.HttpException;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

public class Java8CallAdapterFactory extends CallAdapter.Factory {

    public static Java8CallAdapterFactory create(){
        return new Java8CallAdapterFactory();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != CompletableFuture.class) {
            return null;
        }
        if (!(returnType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "CompletableFuture return type must be parameterized"
                            + " as CompletableFuture<Foo> or CompletableFuture<? extends Foo>");
        }
        Type innerType = getParameterUpperBound(0, (ParameterizedType) returnType);

        // Generic type is Response<T>. Extract T and create the Response version of the adapter.
        if (!(innerType instanceof ParameterizedType)) {
            throw new IllegalStateException(
                    "Response must be parameterized" + " as Response<Foo> or Response<? extends Foo>");
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) innerType);
        return new ResponseCallAdapter<>(responseType);
    }

    private static final class ResponseCallAdapter<R>
            implements CallAdapter<R, CompletableFuture<Response<R>>> {
        private final Type responseType;

        ResponseCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            return responseType;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public CompletableFuture<Response<R>> adapt(final Call<R> call) {
            final CompletableFuture<Response<R>> future =
                    new CompletableFuture<Response<R>>() {
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
                            future.complete(response);
                        }

                        @Override
                        public void onFailure(Call<R> call, Throwable t) {
                            future.completeExceptionally(t);
                        }
                    });
            return future;
        }
    }

}
