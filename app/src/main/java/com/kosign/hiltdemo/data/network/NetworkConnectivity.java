package com.kosign.hiltdemo.data.network;

import android.net.ConnectivityManager;

public interface NetworkConnectivity {
    ConnectivityManager getNetworkInfo();

    boolean isConnected();
}
