package com.example.dipjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    APIInterface apiInterface;

    public String testCard() {
        StringBuilder text = new StringBuilder();
        try {
            InputStream is = getResources().openRawResource(R.raw.testcard);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {
            Log.i("err", e.toString());
        }
        return text.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Request request = new Request.Builder()
                        .url("https://tx-stage.djsvc.net")
                        .build();

                OkHttpClient client = null;
                try {
                    client = new CustomTrust(getBaseContext()).getClient();
                } catch (CertificateException e) {
                    e.printStackTrace();
                }
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i("OOP", "aw man");
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (!response.isSuccessful()) {
                            throw new IOException("Unexpected code " + response);
                        } else {
                            // do something wih the result
                        }
                    }
                });
//                final SwipeResponse swipe = new SwipeResponse(testCard());
//                Call<SwipeResponse> swipeCall = apiInterface.swipeCallback(swipe);
//                swipeCall.enqueue(new Callback<SwipeResponse>() {
//                    @Overrides
//                    public void onResponse(Call<SwipeResponse> call, Response<SwipeResponse> response) {
//                        Log.i("OK", response.toString());
//                    }
//                    @Override
//                    public void onFailure(Call<SwipeResponse> call, Throwable t) {
//                        Log.i("OHNO", t.toString());
//                    }
//                });
            }
        });
    }

}