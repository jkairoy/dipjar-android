package com.example.dipjar;

import android.content.Context;

import java.security.cert.CertificateException;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static final String BASE_URL = "https://tx-stage.djsvc.net";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) throws CertificateException {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL).client(new CustomTrust(context).getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}