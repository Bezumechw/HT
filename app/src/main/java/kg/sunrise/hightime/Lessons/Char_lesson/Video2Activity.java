package kg.sunrise.hightime.Lessons.Char_lesson;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kg.sunrise.hightime.R;

public class Video2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video2);
        startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(getIntent().getStringExtra("videos2"))));
    }
}
