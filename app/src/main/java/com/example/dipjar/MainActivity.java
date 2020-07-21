package com.example.dipjar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

public class MainActivity extends AppCompatActivity {
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
        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://tx-stage.djsvc.net";
                //Request a string response from the URL resource
                SSLSocketFactory fac = new CrtSocketFactory().SocketFactory(getApplicationContext());
                try {
                    HttpsURLConnection connection = (HttpsURLConnection) new URL(url).openConnection();
                    connection.setSSLSocketFactory(fac);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Log.i("HELLO", fac.toString());
                StringRequest stringRequest = request(url);
                //Instantiate the RequestQueue and add the request to the queue
            }
        });
    }

    public StringRequest request(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener() {
                    @Override
                    public void onResponse(Object response) {
                        Log.i("res", response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("err", error.toString());
            }
        });

        return stringRequest;
    }
}