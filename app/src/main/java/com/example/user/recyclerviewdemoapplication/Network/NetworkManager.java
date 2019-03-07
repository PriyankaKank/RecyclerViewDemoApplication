package com.example.user.recyclerviewdemoapplication.Network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created on 07-03-2019.
 * @author Priyanka Gole
 * Class to check the availability of network
 */
public class NetworkManager {

    public static boolean isNetworkAvailable(Context context) {

        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService ( Context.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = manager.getActiveNetworkInfo ();
        if ( networkInfo == null ) {
            return false;
        }
        return networkInfo.isConnected ();
    }
}
