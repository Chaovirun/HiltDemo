package com.kosign.hiltdemo.di;

import static rx.schedulers.Schedulers.io;

import android.util.Log;

import androidx.annotation.NonNull;

import com.kosign.hiltdemo.data.api.ApiService;
import com.kosign.hiltdemo.data.network.customcalladapter.ErrorHandlingAdapter;
import com.kosign.hiltdemo.data.network.customcalladapter.java8.Java8CallAdapterFactory;
import com.kosign.hiltdemo.data.network.customcalladapter.livedata.LiveDataCallAdapterFactory;
import com.kosign.hiltdemo.data.network.customcalladapter.rx.RxJavaObservableCallAdapterFactory;
import com.kosign.hiltdemo.ui.Constant;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    String fireBasetoken;

    @Provides
    String providesBaseUrl() {
        return "https://5e510330f2c0d300147c034c.mockapi.io/";
    }

//    @Provides
//    String providesBaseUrl() {
//        return "https://weeat.kosign.dev/";
//    }

    @Provides
    @Singleton
    HttpLoggingInterceptor providesLoggingInterceptor() {
        return new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    @Singleton
    Interceptor provideHeaderInterceptor(){
        Log.d(">>>", "provideHeaderInterceptor: " + Constant.token);
        return chain -> {
            String tokenBearer = "BEARER " + Constant.token;
            Request request = chain.request();

            Log.d(">>>", "chain: " + tokenBearer + Constant.new_base_url);
            if (!Constant.new_base_url.isEmpty()){
                HttpUrl httpUrl = request.url().newBuilder()
                        .scheme("https")
                        .host(Constant.new_base_url).build();
                request = request.newBuilder()
                        .url(httpUrl)
                        .addHeader("Accept-Language",  "en" )
                        .addHeader("X-App-Type", "")
                        .addHeader("Authorization", tokenBearer)
                        .build();
                Log.d(">>>", "url: " + Constant.new_base_url);
            }else {
                request = request.newBuilder()
                        .addHeader("Accept-Language",  "en" )
                        .addHeader("X-App-Type", "")
                        .addHeader("Authorization", tokenBearer)
                        .build();
            }

            Log.d(">>>", "chain: " + tokenBearer + Constant.new_base_url);

            return chain.proceed(request);
        };
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor, Interceptor interceptor) {
        Log.d(">>>", "provideOkHttpClient: " + interceptor);
        OkHttpClient.Builder okHttpClient = new OkHttpClient().newBuilder();

        okHttpClient.addInterceptor(loggingInterceptor);
        okHttpClient.addInterceptor(interceptor);
        okHttpClient.build();
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    Converter.Factory provideConverterFactory() {
        return GsonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitClient(OkHttpClient okHttpClient, String baseUrl){
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
//                .addCallAdapterFactory(new ErrorHandlingAdapter.ErrorHandlingCallAdapterFactory())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
//                .addCallAdapterFactory(Java8CallAdapterFactory.create())
//                .addCallAdapterFactory(new RxJavaObservableCallAdapterFactory(Schedulers.io()))
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(io()))
//                .addCallAdapterFactory(LiveDataCallAdapterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    ApiService provideRestApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

}
