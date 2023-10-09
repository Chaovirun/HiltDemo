package com.kosign.hiltdemo.data.api;

import androidx.lifecycle.LiveData;

import com.kosign.hiltdemo.data.model.LoginRequest;
import com.kosign.hiltdemo.data.model.LoginResponse;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.data.network.Resource;
import com.kosign.hiltdemo.data.network.customcalladapter.MyCall;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import kotlinx.coroutines.flow.Flow;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ApiService {

//    @GET("users")
//    CompletableFuture<Response<List<User>>> getUsers();
//
//    @POST("api/v1/create")
//    CompletableFuture<Response<LoginResponse>> requestLogin(@Body LoginRequest request);

    @GET("users")
    Observable<Response<List<User>>> getUsers();

    @POST("/api/v1/auth/login")
    Observable<Response<LoginResponse>> requestLogin(@Body LoginRequest request);

//    @GET("users")
//    LiveData<Resource<List<User>>> getUsers();
//
//    @POST("api/v1/create")
//    LiveData<Resource<LoginResponse>> requestLogin(@Body LoginRequest request);

//    @GET("users")
//    Flow<Resource<List<User>>> getUsers();
//
//    @POST("api/v1/create")
//    Flow<Resource<LoginResponse>> requestLogin(@Body LoginRequest request);

//    @GET("users")
//    MyCall<List<User>> getUsers();
//
//    @POST("api/v1/create")
//    MyCall<LoginResponse> requestLogin(@Body LoginRequest request);

}
