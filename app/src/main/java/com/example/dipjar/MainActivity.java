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

import pojos.SwipeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        }
        catch (IOException e) {
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
//                //Request a string response from the URL resource
//                SSLSocketFactory fac = new CrtSocketFactory().SocketFactory(getApplicationContext());
//                try {
//                    HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
//                    connection.setSSLSocketFactory(fac);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                Log.i("HELLO", fac.toString());
                final SwipeResponse swipe = new SwipeResponse(testCard());
                Call<SwipeResponse> swipeCall = apiInterface.swipeCallback(swipe);
                swipeCall.enqueue(new Callback<SwipeResponse>() {
                    @Override
                    public void onResponse(Call<SwipeResponse> call, Response<SwipeResponse> response) {
                        Log.i("OK", response.toString());
                    }
                    @Override
                    public void onFailure(Call<SwipeResponse> call, Throwable t) {
                        Log.i("OHNO", t.toString());
                    }
                });
            }
        });
    }

}