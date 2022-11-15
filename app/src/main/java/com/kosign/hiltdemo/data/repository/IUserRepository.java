package com.kosign.hiltdemo.data.repository;


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
import rx.Observable;
import rx.Observer;


public interface IUserRepository {

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
