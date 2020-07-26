package com.example.dipjar;

import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

public class NullHostNameVerifier implements HostnameVerifier {
    String trustedHost = "tx-stage.djsvc.net";
    @Override
    public boolean verify(String hostname, SSLSession session) {
        if (hostname.contentEquals(trustedHost)) {
            Log.i("RestUtilImpl", "Approving certificate for " + hostname);
            return true;
        } else {
            return false;
        }
    }

}