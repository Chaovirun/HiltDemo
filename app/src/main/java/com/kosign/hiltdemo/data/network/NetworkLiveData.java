package com.kosign.hiltdemo.data.network;

import androidx.lifecycle.MutableLiveData;

public class NetworkLiveData<T> extends MutableLiveData<NetworkLiveData.NetworkData<T>> {

    void onSuccessResponse(T body){
        NetworkData result = new NetworkData.Success(body);
        postValue(result);
    }

    static class NetworkData<T>{
        static class Success<T> extends NetworkData<T> {

            public Success(T body) {

            }
        }


    }

}
