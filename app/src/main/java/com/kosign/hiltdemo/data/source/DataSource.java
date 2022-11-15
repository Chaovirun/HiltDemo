package com.kosign.hiltdemo.data.source;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.kosign.hiltdemo.data.api.ApiService;
import com.kosign.hiltdemo.data.model.LoginRequest;
import com.kosign.hiltdemo.data.model.LoginResponse;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.data.network.ApiResponse;
import com.kosign.hiltdemo.data.network.Resource;
import com.kosign.hiltdemo.data.network.customcalladapter.MyCallback;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import javax.inject.Inject;
import javax.inject.Singleton;

import kotlinx.coroutines.flow.Flow;
import retrofit2.Response;
import retrofit2.Retrofit;
import rx.Observable;

@Singleton
public class DataSource implements IDataSource {

    private final static String TAG = "DATASOURCE";
    private final ApiService service;

    public @Inject
    DataSource(Retrofit retrofit) {
        this.service = retrofit.create(ApiService.class);
    }

//    @Override
//    public CompletableFuture<Response<List<User>>> getUsers() {
//        return service.getUsers();
//
//    }
//
//    @Override
//    public CompletableFuture<Response<LoginResponse>> requestLogin(LoginRequest request) {
//        return service.requestLogin(request);
//    }

    @Override
    public Observable<Response<List<User>>> getUsers() {
        return service.getUsers();

    }

    @Override
    public Observable<Response<LoginResponse>> requestLogin(LoginRequest request) {
        return service.requestLogin(request);
    }

//    @Override
//    public LiveData<Resource<List<User>>> getUsers() {
//        return service.getUsers();
//
//    }
//
//    @Override
//    public LiveData<Resource<LoginResponse>> requestLogin(LoginRequest request) {
//        return service.requestLogin(request);
//    }

//    @Override
//    public Flow<Resource<List<User>>> getUsers() {
//        return service.getUsers();
//
//    }
//
//    @Override
//    public Flow<Resource<LoginResponse>> requestLogin(LoginRequest request) {
//        return service.requestLogin(request);
//    }

//    @Override
//    public List<User> getUsers() {
//        service.getUsers().enqueue(new MyCallback<List<User>>() {
//            @Override
//            public void success(Response<List<User>> response) {
//                System.out.println("SUCCESS! " + response.body());
//            }
//
//            @Override
//            public void unauthenticated(Response<?> response) {
//                System.out.println("UNAUTHENTICATED");
//            }
//
//            @Override
//            public void clientError(Response<?> response) {
//                System.out.println("CLIENT ERROR " + response.code() + " " + response.message());
//            }
//
//            @Override
//            public void serverError(Response<?> response) {
//                System.out.println("SERVER ERROR " + response.code() + " " + response.message());
//            }
//
//            @Override
//            public void networkError(IOException e) {
//                System.err.println("NETWORK ERROR " + e.getMessage());
//            }
//
//            @Override
//            public void unexpectedError(Throwable t) {
//                System.err.println("FATAL ERROR " + t.getMessage());
//            }
//        });
//        return null;
//    }
//
//    @Override
//    public LoginResponse requestLogin(LoginRequest request) {
//        service.requestLogin(request).enqueue(new MyCallback<LoginResponse>() {
//            @Override
//            public void success(Response<LoginResponse> response) {
//                System.out.println("SUCCESS! " + response.body());
//            }
//
//            @Override
//            public void unauthenticated(Response<?> response) {
//                System.out.println("UNAUTHENTICATED");
//            }
//
//            @Override
//            public void clientError(Response<?> response) {
//                System.out.println("CLIENT ERROR " + response.code() + " " + response.message());
//            }
//
//            @Override
//            public void serverError(Response<?> response) {
//                System.out.println("SERVER ERROR " + response.code() + " " + response.message());
//            }
//
//            @Override
//            public void networkError(IOException e) {
//                System.err.println("NETWORK ERROR " + e.getMessage());
//            }
//
//            @Override
//            public void unexpectedError(Throwable t) {
//                System.err.println("FATAL ERROR " + t.getMessage());
//            }
//        });
//        return null;
//    }
}
