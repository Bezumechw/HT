package kg.sunrise.hightime.Api;

import java.io.File;
import java.util.List;

import kg.sunrise.hightime.Auth.Login.TokenLogin;
import kg.sunrise.hightime.Auth.Register.TokenRegister;
import kg.sunrise.hightime.Lvl.Lvl;
import kg.sunrise.hightime.Lessons.ButtonNames;
import kg.sunrise.hightime.Lessons.Lessons;
import kg.sunrise.hightime.Lessons.Char_lesson.Char;
import kg.sunrise.hightime.Lessons.Char_lesson.Test.Tests;
import kg.sunrise.hightime.Level.Test.Test;
import kg.sunrise.hightime.model.CheckPayment;
import kg.sunrise.hightime.model.Pay24;
import kg.sunrise.hightime.model.Payment;
import kg.sunrise.hightime.model.PaymentAndPromocode;
import kg.sunrise.hightime.model.UserInfo;
import kg.sunrise.hightime.News.News;
import kg.sunrise.hightime.News.NewsSecond.NewsSecond;
import kg.sunrise.hightime.Reviews.ReviewOne.ReviewOne;
import kg.sunrise.hightime.Reviews.ReviewTwo.ReviewTwo;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServiceApi {

    @FormUrlEncoded
    @POST("api/auth/register")
    Call<TokenRegister> signUp(@Field("name") String name,
                               @Field("email") String email,
                               @Field("password") String password);

    @FormUrlEncoded
    @POST("api/auth/login")
    Call<TokenLogin> toComeIn(@Field("username") String username,
                              @Field("password") String password);


    @FormUrlEncoded
    @POST("api/feedback/login")
    Call<ResponseBody> toContact(@Field("name") String name,
                                 @Field("phone") String phone);

    @GET("/api/level/{id}")
    Call<Lessons> getLessons(@Path("id") int id);

    @GET("/api/lesson/get-by-level/{id}")
    Call<List<ButtonNames>> getAlfa(@Path("id") int id);

    @GET("/api/lesson/show/{id}")
    Call<Char> getChar(@Path("id") int id);

    @GET("/api/test/get-by-video/{id}")
    Call<List<Tests>> getTests(@Path("id") int id);


    @GET("api/user/payment/buy-with-paybox/{level}")
    Call<Payment> getPayment(@Path("level") int level, @Header("token") String token);

    @GET("/api/user/payment/buy-with-balance/{level}")
    Call<Pay24> getPay24(@Path("level") int level, @Header("token") String token);

    @GET("/api/user/payment/buy-with-promocode/{level}/{promocode}")
    Call<PaymentAndPromocode> getPaymentAndPromocode(@Path("level") int level, @Path("promocode") String promocode, @Header("token") String token);

    @GET("/api/user/payment/check-payment/{id}")
    Call<CheckPayment> getCheckPayment(@Path("id") int id, @Header("token") String token);


    @GET("/api/news/")
    Call<List<News>> getNews();

    @GET("/api/news/{id}")
    Call<NewsSecond> getNewsById(@Path("id") int id);


    @GET("api/level-test/get-tests")
    Call<List<Test>> getTestLevel();


    @GET("/api/reviews/list")
    Call<List<ReviewOne>> getReviewOne();


    @GET("/api/user/levels")
    Call<List<Lvl>> getLvl(@Header("token") String token);

    @GET("/api/user/info")
    Call<UserInfo> getUserInfo(@Header("token") String token);

    @POST("/api/reviews/create")
    @Multipart
    Call<ReviewTwo> toReview(
            @Part("title") RequestBody titleDisc,
            @Part("text") RequestBody textDisc,
            @Part() MultipartBody.Part thumbnail);

    @POST("/api/reviews/create")
    @Multipart
    Call<ReviewTwo> toRev(
            @Part("title") RequestBody titleDisc,
            @Part("text") RequestBody textDisc);
}