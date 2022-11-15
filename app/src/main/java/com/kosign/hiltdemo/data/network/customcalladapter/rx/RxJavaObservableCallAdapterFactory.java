package com.kosign.hiltdemo.data.network.customcalladapter.rx;

import android.util.Log;

import androidx.annotation.Nullable;

import com.kosign.hiltdemo.data.network.Resource;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Scheduler;

public class RxJavaObservableCallAdapterFactory extends CallAdapter.Factory {

    final Scheduler scheduler;
    public RxJavaObservableCallAdapterFactory(Scheduler scheduler){
        this.scheduler = scheduler;
    }

    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != Observable.class) {
            return null; // Ignore non-Observable types.
        }

//        Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
//        Class<?> rawObservableType = getRawType(observableType);
//        if (rawObservableType != Resource.class) {
//            throw new IllegalArgumentException("type must be a resource");
//        }
//        if (!(observableType instanceof ParameterizedType)) {
//            throw new IllegalArgumentException("resource must be parameterized");
//        }
//        Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);
//
//        return new RxJavaObservableCallAdapter<Object>(bodyType);

        // Look up the next call adapter which would otherwise be used if this one was not present.
        //noinspection unchecked returnType checked above to be Observable.
        final CallAdapter<Object, Observable<Resource<Object>>> delegate =
                (CallAdapter<Object, Observable<Resource<Object>>>)
                        retrofit.nextCallAdapter(this, returnType, annotations);

        return new CallAdapter<Object, Object>() {

            @Override
            public Object adapt(Call<Object> call) {
                // Delegate to get the normal Observable...
                Observable<Resource<Object>> o = delegate.adapt(call);
                // ...and change it to send notifications to the observer on the specified scheduler.
                call.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {
                        Log.d(">>>>", "CallAdapter >>> onResponse: "+ response.isSuccessful());
                    }

                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {
                        Log.d(">>>", "onFailure: " + t.getMessage());
                    }
                });
                return o.observeOn(scheduler);
            }

            @Override
            public Type responseType() {
                return delegate.responseType();
            }
        };
    }
}
