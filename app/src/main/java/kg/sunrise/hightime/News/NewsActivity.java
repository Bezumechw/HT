package kg.sunrise.hightime.News;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.News.NewsSecond.NewsSecondActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsActivity extends AppCompatActivity {

    NewsAdapter adapter;
    ArrayList<News> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        adapter.setOnClickListener(new NewsAdapter.OnClickListener() {
            @Override
            public void onClickItem(int pos) {
                Intent intent = new Intent(NewsActivity.this, NewsSecondActivity.class);
                intent.putExtra("id", list.get(pos).getId());
                startActivity(intent);
            }
        });

        getNews();
        adapter.notifyDataSetChanged();
    }

    private void getNews() {
        App.getApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(Call<List<News>> call, Response<List<News>> response) {
                Log.e("TAG", "response: " + response.code());
                List<News> news = response.body();
                if (news != null) {
                    showData(news);
                }
            }

            @Override
            public void onFailure(Call<List<News>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

    private void showData(List<News> news) {
        this.list.addAll(news);
        adapter.notifyDataSetChanged();
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
