package com.kosign.hiltdemo.data.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkUtil implements NetworkConnectivity{

    private Context context;
    public NetworkUtil(Context context){
        this.context = context;
    }


    @Override
    public ConnectivityManager getNetworkInfo() {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean isConnected() {
        ConnectivityManager connectivityManager = getNetworkInfo();
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED
                || connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }
}