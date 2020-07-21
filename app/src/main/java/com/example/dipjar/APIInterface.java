package com.example.dipjar;

import pojos.SwipeResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface APIInterface {
    @POST("/api/v1/swipe")
    Call<SwipeResponse> swipeCallback(@Body SwipeResponse callback);
}
