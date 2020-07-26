package com.example.dipjar;

import android.content.Context;
import android.util.Log;

import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.tls.HandshakeCertificates;

public final class CustomTrust {

    private final OkHttpClient client;
    private final Context context;

    public CustomTrust(Context context) throws CertificateException {
        this.context = context;
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        InputStream certStream = context.getResources().openRawResource(R.raw.djca);
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        try {
            X509Certificate cert = (X509Certificate)certFactory.generateCertificate(certStream);
            HandshakeCertificates certificates = new HandshakeCertificates.Builder().addTrustedCertificate(cert).build();
            trustManager = certificates.trustManager();
            Log.i("KEY", trustManager.getAcceptedIssuers().toString());
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[] { trustManager }, null);
            sslSocketFactory = sslContext.getSocketFactory();

        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }

        client = (OkHttpClient) new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager).hostnameVerifier(new NullHostNameVerifier())
                .build();
    }

    public OkHttpClient getClient() {
        return client;
    }

}