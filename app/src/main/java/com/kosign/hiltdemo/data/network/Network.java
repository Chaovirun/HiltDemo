package com.kosign.hiltdemo.data.network;

public interface Network {

    void onResponse(Resource res);
    void onError(Resource res);

}
