package kg.sunrise.hightime.Api;

import android.app.Application;
import android.content.Context;

import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private static ServiceApi serviceApi;
    private static final String BASE_URL = "http://apiprod.htlife.biz/";
//    private static final String BASE_URL = "http://apitest.htlife.biz/";

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        serviceApi = retrofit.create(ServiceApi.class);
    }

    public static ServiceApi getApi() {
        return serviceApi;
    }

    private static OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request().newBuilder()
                    .addHeader("Authorization", "sadasd")
                    .build();
            return chain.proceed(request);
        }
    }).build();

}
