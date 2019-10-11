package kg.sunrise.hightime.Lessons.Char_lesson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.protobuf.Empty;

import java.io.IOException;

import kg.sunrise.hightime.Lessons.Char_lesson.Test.YouTubeActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Lessons.Char_lesson.Test.TestLessonsActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharActivity extends AppCompatActivity {

    TextView textName;
    TextView textName2;
    CharAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);
        recyclerView = findViewById(R.id.recyclerView);
        textName = findViewById(R.id.textName);
        textName2 = findViewById(R.id.textName2);

        int id = getIntent().getIntExtra("id", 0);
        getChar(id);
    }

    private void getChar(final int id) {
        App.getApi().getChar(id).enqueue(new Callback<Char>() {
            @SuppressLint("SetTextI18n")
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<Char> call, Response<Char> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "response: " + response.code());
                    final Char char1 = response.body();
                    if (char1 != null) {
                        textName.setText("\"" + char1.getName() + "\"");
                        textName2.setText(char1.getName().replaceAll("Урок", ""));

                        recyclerView.setLayoutManager(new LinearLayoutManager(CharActivity.this));
                        adapter = new CharAdapter(char1.videos);
                        recyclerView.setAdapter(adapter);
                        recyclerView.setNestedScrollingEnabled(false);
                        adapter.notifyDataSetChanged();
                        adapter.setOnClickListener(new CharAdapter.OnClickListener() {
                            @Override
                            public void onClickItem(int pos) {
                                if (char1.getVideos().get(pos).type.length() != 5) {
                                    Intent intent = new Intent(CharActivity.this, TestLessonsActivity.class);
                                    intent.putExtra("id", char1.getVideos().get(pos).getVideo());
                                    startActivity(intent);
                                } else {
                                    Intent intent = new Intent(CharActivity.this, VideoActivity.class);
                                    intent.putExtra("videos", String.valueOf(char1.getVideos().get(pos).getUrl_low()));
                                    intent.putExtra("videos2", String.valueOf(char1.getVideos().get(pos).getUrl_youtube()));
                                    startActivity(intent);
                                    Log.i("Video", "Video Playing....");
                                }
                            }
                        });
                    }
                }

            }


            @Override
            public void onFailure(Call<Char> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }


//    public void onCancel(View view) {
////        finish();
////    }
////    public boolean isOnline() {
////        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
////        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
////        if (networkInfo != null && networkInfo.isConnected()) {
////            return true;
////        } else {
////            return false;
////        }
////    }
////
////    @Override
////    protected void onResume() {
////        if (!isOnline()) {
////            Toast toast = Toast.makeText(this, "для получения данных требуется соединение с интернетом!", Toast.LENGTH_SHORT);
////            View view2 = toast.getView();
////            toast.getView().setPadding(20, 20, 20, 20);
////            view2.setBackgroundResource(R.color.Red);
////            toast.show();
////        }
////        super.onResume();
////        return ;
////    }
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

    public void onCancel(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                finish();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }
}
