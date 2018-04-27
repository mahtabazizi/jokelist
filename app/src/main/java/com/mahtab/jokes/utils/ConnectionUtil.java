package com.mahtab.jokes.utils;

import android.content.Context;
import android.net.NetworkInfo;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class ConnectionUtil {

    private Context mContext;

    public ConnectionUtil(Context mContext) {
        this.mContext = mContext;
    }

    public boolean isConnected() {
        try {
            android.net.ConnectivityManager e = (android.net.ConnectivityManager) mContext.getSystemService(
                    Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = e.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        } catch (Exception e) {
            Log.w(TAG, e.toString());
        }

        return false;

    }
}
