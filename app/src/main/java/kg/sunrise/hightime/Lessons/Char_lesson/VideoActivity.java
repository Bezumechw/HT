package kg.sunrise.hightime.Lessons.Char_lesson;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import cn.jzvd.Jzvd;
import kg.sunrise.hightime.R;


public class VideoActivity extends AppCompatActivity {

    Jzvd video_view;
    ImageView download;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_video);
        download = findViewById(R.id.download);
        download.setVisibility(View.GONE);
        video_view = findViewById(R.id.video_view);
        initializePlayer();
//        YouTube();
//        video_view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                initializePlayer();
//            }
//        });

    }

//    @SuppressLint("WrongViewCast")
    private void initializePlayer() {
        video_view.setUp(getIntent().getStringExtra("videos"), "" , Jzvd.SCREEN_NORMAL);
        video_view.startVideo();
    }
    public  void YouTube(){
        startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(getIntent().getStringExtra("videos2"))));

    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        initializePlayer();
//        Jzvd.goOnPlayOnPause();
        super.onPause();
        Jzvd.resetAllVideos();

        Log.e("TAG", "сворачивание");
    }

    public void onBack(View view) {
        onBackPressed();
//        startActivity(new Intent(VideoActivity.this, CharActivity.class));
    }

    public void onYoutube(View view) {
        if (getIntent().getStringExtra("videos2").isEmpty()){
            initializePlayer();
        }else
            YouTube();
    }
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onResume() {
        if (!isOnline()) {
            Toast toast = Toast.makeText(this, "нет подключения", Toast.LENGTH_SHORT);
            View view2 = toast.getView();
            toast.getView().setPadding(20, 20, 20, 20);
            view2.setBackgroundResource(R.color.Red);
            toast.show();
        }
        super.onResume();
        return ;
    }
}
