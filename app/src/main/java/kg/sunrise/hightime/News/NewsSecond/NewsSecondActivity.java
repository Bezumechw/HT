package kg.sunrise.hightime.News.NewsSecond;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.io.IOException;

import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsSecondActivity extends AppCompatActivity {

    TextView textName, textContent;
    TextView textNameKg, textContentKg;
    TextView textNameEn, textContentEn;
    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_news_second);
        textName = findViewById(R.id.textName);
        textContent = findViewById(R.id.textShort);
        textNameKg = findViewById(R.id.textNameKg);
        textContentKg = findViewById(R.id.textShortKg);
        textNameEn = findViewById(R.id.textNameEn);
        textContentEn = findViewById(R.id.textShortEn);
        imageView = findViewById(R.id.imageView);
        int id = getIntent().getIntExtra("id", 0);
        getNews(id);
    }

    private void getNews(final int id) {
        App.getApi().getNewsById(id).enqueue(new Callback<NewsSecond>() {
            @Override
            public void onResponse(Call<NewsSecond> call, Response<NewsSecond> response) {
                Log.e("TAG", "response: " + response.code());
                NewsSecond newsSecond = response.body();
                Picasso.get().load(newsSecond.thumbmailL).into(imageView);
                textName.setText(newsSecond.name + "");
                textContent.setText((newsSecond.content +" "+ newsSecond.contentrus +"")
                        .replaceAll("&amp;amp;laquo;", "")
                        .replaceAll("&amp;laquo;", "")
                        .replaceAll("&amp;amp;nbsh;", "")
                        .replaceAll("&amp;amp;ndash;", "")
                        .replaceAll("&amp;amp;nbsp;", "")
                        .replaceAll("&amp;amp;raquo;", "")
                        .replaceAll("&amp;raquo;", "")
                        .replaceAll("&amp;ndash;", "")
                        .replaceAll("&amp;amp;ldquo;", "")
                        .replaceAll("&amp;amp;rdquo;", "")
                        .replaceAll("&amp;raquo;nbsp;", ""));
                textNameKg.setText((newsSecond.name_kg + ""));
                textContentKg.setText((newsSecond.short_kg + newsSecond.content_kg +"")
                        .replaceAll("&amp;amp;laquo;", "")
                        .replaceAll("&amp;amp;nbsh;", "")
                        .replaceAll("&amp;amp;nbsp;", "")
                        .replaceAll("&amp;amp;ndash;", "")
                        .replaceAll("&amp;raquo;", "")
                        .replaceAll("&amp;amp;ldquo;", "")
                        .replaceAll("&amp;amp;rdquo;", "")
                        .replaceAll("&amp;raquo;nbsp;", ""));
                textNameEn.setText((newsSecond.name_en + ""));
                textContentEn.setText((newsSecond.short_en + newsSecond.content_en +"")
                        .replaceAll("&amp;amp;laquo;", "")
                        .replaceAll("&amp;amp;nbsh;", "")
                        .replaceAll("&amp;raquo;", "")
                        .replaceAll("&amp;amp;ldquo;", "")
                        .replaceAll("&amp;amp;rdquo;", "")
                        .replaceAll("&amp;raquo;nbsp;", ""));
            }

            @Override
            public void onFailure(Call<NewsSecond> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());

            }
        });
    }



    public void onBack(View view) {
        finish();
    }

    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onResume() {

        if (!isConnected(this)) {
            Toast toast = Toast.makeText(this, "для получения данных требуется соединение с интернетом", Toast.LENGTH_SHORT);
            View view2 = toast.getView();
            toast.getView().setPadding(20, 20, 20, 20);
            view2.setBackgroundResource(R.color.Red);
            toast.show();
        }
        super.onResume();
    }

}
