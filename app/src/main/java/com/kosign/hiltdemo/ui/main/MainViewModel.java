package com.kosign.hiltdemo.ui.main;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kosign.hiltdemo.data.model.LoginRequest;
import com.kosign.hiltdemo.data.model.LoginResponse;
import com.kosign.hiltdemo.data.model.User;
import com.kosign.hiltdemo.data.network.Resource;
import com.kosign.hiltdemo.data.network.Status;
import com.kosign.hiltdemo.data.repository.IUserRepository;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final static String TAG = MainViewModel.class.getName();

    private IUserRepository repository;


    MutableLiveData<Boolean> _dataLoading = new MutableLiveData<>(false);
    public LiveData<Boolean> dataLoading = _dataLoading;

//    public Boolean getDataLoading() {
//        return dataLoading.getValue();
//    }

    MutableLiveData<List<User>> _user = new MutableLiveData<>();
    public LiveData<List<User>> user = _user;

    MutableLiveData<String> _userError = new MutableLiveData<>();
    public LiveData<String> userError = _userError;

    private MutableLiveData<Resource<List<User>>> _listMutableLiveData= new MutableLiveData<>();
    public LiveData<Resource<List<User>>> listMutableLiveData = _listMutableLiveData;

    public List<User> userList;

    @Inject
    MainViewModel(IUserRepository repository){
        this.repository = repository;
        userList = new ArrayList<>();
    }

    Subscriber<List<User>> userSubscribe = new Subscriber<List<User>>() {
        @Override
        public void onCompleted() {
            _dataLoading.postValue(false);
        }

        @Override
        public void onError(Throwable e) {
            Log.d(">>>", "onError:  " + e);
            _userError.postValue(e.getMessage());
        }

        @Override
        public void onNext(List<User> users) {
            _dataLoading.postValue(false);
            _user.postValue(users);
//            userList.addAll(users);
        }
    };


    void login(LoginRequest request){
//        Log.d(TAG, "login: " + repository.requestLogin(request)
//                );

        repository.requestLogin(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<LoginResponse>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d(">>>", "requestLogin >>> onError:  " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<LoginResponse> loginResponse) {
                        if (loginResponse.isSuccessful()){
                            if (loginResponse.code() == 200){
                                Log.d(">>>", "response: success " + loginResponse.body());
                            }else {
                                Log.d(">>>", "response: error " + loginResponse.errorBody());
                            }
                        }
                        else{
                            Log.d(">>>", "response: error " + loginResponse.code());
                        }
                    }
                });

//        try {
//            Response<LoginResponse> res = repository.requestLogin(request).get();
//            Log.d(TAG, "fetchUsers: " + res.code());
//            if (res.isSuccessful()){
////                _user.postValue(res.body());
//            }
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

    }

    void fetchUsers() {
        _dataLoading.setValue(true);

        repository.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response<List<User>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>", "getUsers >>> onError:  " + e.getMessage());
                    }

                    @Override
                    public void onNext(Response<List<User>> users) {
//                        _user.postValue(users);
                        _dataLoading.postValue(false);
                        if (users.isSuccessful()){
                            _user.postValue(users.body());
                        }
                        Log.d(TAG, "fetchUsers: " + users);
                    }
                });

//        _listMutableLiveData.postValue(repository.getUsers());
//        _user.postValue(repository.getUsers().getValue().getData());

//        try {
//            Response<List<User>> res = repository.getUsers().get();
//            Log.d(TAG, "fetchUsers: " + res.code());
//            if (res.isSuccessful()){
//                _user.postValue(res.body());
//            }
//        } catch (ExecutionException e) {
//            Log.d(TAG, "ExecutionException: " + e);
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            Log.d(TAG, "InterruptedException: " + e);
//            e.printStackTrace();
//        }


//        listMutableLiveData = repository.getUsers();

//        repository.getUsers();


    }


}
