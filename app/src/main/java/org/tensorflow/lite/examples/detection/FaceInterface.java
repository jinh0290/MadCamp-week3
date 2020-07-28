package org.tensorflow.lite.examples.detection;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface FaceInterface {
    @GET("/api/face")
    Call<InitData> executeGet();

    @POST("/api/face")
    Call<ResponseBody> executePost(@Body HashMap<String, Object> map);
}
