package com.kosign.hiltdemo.data.repository;

import androidx.lifecycle.LiveData;

import com.kosign.hiltdemo.data.api.ApiService;
import com.kosign.hiltdemo.data.model.LoginRequest;
import com.kosign.hiltdemo.data.model.LoginResponse;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.data.network.Resource;
import com.kosign.hiltdemo.data.network.customcalladapter.MyCall;
import com.kosign.hiltdemo.data.source.IDataSource;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import kotlinx.coroutines.flow.Flow;
import retrofit2.Response;
import rx.Observable;

public class UserRepository implements IUserRepository {

//    private IDataSource dataSource;

//    @Inject
    IDataSource dataSource;
    public UserRepository(IDataSource dataSource){
        this.dataSource = dataSource;
    }

//    @Override
//    public CompletableFuture<Response<List<User>>> getUsers() {
//        return dataSource.getUsers();
//    }
//
//    @Override
//    public CompletableFuture<Response<LoginResponse>> requestLogin(LoginRequest request) {
//        return dataSource.requestLogin(request);
//    }

    @Override
    public Observable<Response<List<User>>> getUsers() {
        return dataSource.getUsers();
    }

    @Override
    public Observable<Response<LoginResponse>> requestLogin(LoginRequest request) {
        return dataSource.requestLogin(request);
    }

//    @Override
//    public LiveData<Resource<List<User>>> getUsers() {
//        return dataSource.getUsers();
//    }
//
//    @Override
//    public LiveData<Resource<LoginResponse>> requestLogin(LoginRequest request) {
//        return dataSource.requestLogin(request);
//    }

//    @Override
//    public Flow<Resource<List<User>>> getUsers() {
//        return dataSource.getUsers();
//    }
//
//    @Override
//    public Flow<Resource<LoginResponse>> requestLogin(LoginRequest request) {
//        return dataSource.requestLogin(request);
//    }

//    @Override
//    public List<User> getUsers() {
//        return dataSource.getUsers();
//    }
//
//    @Override
//    public LoginResponse requestLogin(LoginRequest request) {
//        return dataSource.requestLogin(request);
//    }

}
