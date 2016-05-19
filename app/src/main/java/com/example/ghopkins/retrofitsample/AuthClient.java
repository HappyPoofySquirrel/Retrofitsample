package com.example.ghopkins.retrofitsample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by ghopkins on 5/18/2016.
 */

public interface AuthClient {
    @Headers("AppName: TowMagic")
    @FormUrlEncoded
    @POST("/Token")
    Call<TokenResponse> getToken(@Field("grant_type") String grant_type,
                                @Field("username") String username,
                                @Field("password") String password);
}
