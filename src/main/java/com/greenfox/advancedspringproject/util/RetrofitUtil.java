package com.greenfox.advancedspringproject.util;

import com.greenfox.advancedspringproject.config.Constants;
import com.greenfox.advancedspringproject.service.MovieApi;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create());

    public static Retrofit retrofit = builder.build();
    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <T> T getRetrofitInstance(Class<T> serviceClass){
        if(retrofit == null) {
            httpClient.interceptors().clear();
            httpClient.addInterceptor(chain -> {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("Authorization", Constants.API_TOKEN)
                        .build();
                return chain.proceed(request);
            });
            builder.client(httpClient.build());
            retrofit = builder.build();

        }
        return retrofit.create(serviceClass);
    }
}
