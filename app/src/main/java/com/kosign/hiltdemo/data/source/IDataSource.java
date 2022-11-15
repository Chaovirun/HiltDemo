package com.kosign.hiltdemo.data.source;

import androidx.lifecycle.LiveData;

import com.kosign.hiltdemo.data.model.LoginRequest;
import com.kosign.hiltdemo.data.model.LoginResponse;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.data.network.ApiResponse;
import com.kosign.hiltdemo.data.network.Resource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import kotlinx.coroutines.flow.Flow;
import retrofit2.Response;
import rx.Observable;

public interface IDataSource {

//    CompletableFuture<Response<List<User>>> getUsers();
//
//    CompletableFuture<Response<LoginResponse>> requestLogin(LoginRequest request);

    Observable<Response<List<User>>> getUsers();

    Observable<Response<LoginResponse>> requestLogin(LoginRequest request);

//    LiveData<Resource<List<User>>> getUsers();
//
//    LiveData<Resource<LoginResponse>> requestLogin(LoginRequest request);

//    Flow<Resource<List<User>>> getUsers();
//
//    Flow<Resource<LoginResponse>> requestLogin(LoginRequest request);

//    List<User> getUsers();
//
//    LoginResponse requestLogin(LoginRequest request);

}
