package com.kosign.hiltdemo.data.network.customcalladapter.flow;

import androidx.annotation.Nullable;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import kotlinx.coroutines.flow.Flow;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class FlowCallAdapterFactory extends CallAdapter.Factory {
    @Nullable
    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        if (getRawType(returnType) != Flow.class){
            return null;
        }
        Type observeType = getParameterUpperBound(0, ((ParameterizedType) returnType));
        return new FlowCallAdapter<Object>(observeType);
    }
}
