package com.example.ghopkins.retrofitsample;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//Class having OnItemClickListener to handle the clicks on list
public class MainActivity extends AppCompatActivity  {

    final String grant_type="password";
    final String password="testPassword";
    final String username="test1@beaconsoftco.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

        public void login(View view){

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
// set your desired log level
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
// add your other interceptors …
// add logging as last interceptor
            httpClient.interceptors().add(logging);


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ServiceGenerator.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();

            AuthClient authClient = retrofit.create(AuthClient.class);

            Call<TokenResponse> call = authClient.getToken(grant_type, username, password);

            call.enqueue(new Callback<TokenResponse>() {

            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {

                Log.i("Responde", response.raw().toString());
                if(response.isSuccessful()){
                    TokenResponse tokenResponse = response.body();
                    Log.i("successful", tokenResponse.getAccessToken());
                }
                else{Log.i("NOT", "Successful");}
            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {
                Log.i("FAIL", "FAIL FAIL FAIL" + t.toString());
            }
        });
    }
}