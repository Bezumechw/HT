package kg.sunrise.hightime.Lvl.Instruction;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kg.sunrise.hightime.R;

public class InstructionActivity extends AppCompatActivity {
    ImageView ins1,ins2,ins3,ins4,ins5,ins6,ins7;
    TextView ls;
    private boolean isImageScaled = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instruction);
        ls = findViewById(R.id.ls);
        ins1 = findViewById(R.id.ins1);
        ins2 = findViewById(R.id.ins2);
        ins3 = findViewById(R.id.ins3);
        ins4 = findViewById(R.id.ins4);
        ins5 = findViewById(R.id.ins5);
        ins6 = findViewById(R.id.ins6);
        ins7 = findViewById(R.id.ins7);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67459413_2301078073540538_347607994076233728_n.jpg?_nc_cat=104&_nc_oc=AQmxrrOvPRK3ShHT4xSmyUXXr2SmSNv5EiO6CJi5cwwMtzkCYVhHJNWMf3aF_f8k7PI&_nc_ht=scontent.ffru1-1.fna&oh=153ba9db100af2d1cb13592b34aa2f5b&oe=5DA176A1").into(ins1);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67355034_2301078033540542_7637445913878724608_n.jpg?_nc_cat=103&_nc_oc=AQlAlwOyWvsuZ2XE0kz_UgA1l6vbgKSVvBiFZ8UmYqmhCtsFMzUywrfTWh0JOyYrfa0&_nc_ht=scontent.ffru1-1.fna&oh=48d705704d55f887b5a5f2a1218e7a61&oe=5DEAED34").into(ins2);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67371976_2301078036873875_8586075624014086144_n.jpg?_nc_cat=103&_nc_oc=AQlRBFLH2V4NH7CZIiICQjxMJoExyLjer0eb6HZAVvS3_cwL25DPmG2s97hA_ysEd3c&_nc_ht=scontent.ffru1-1.fna&oh=ffce16282f37864e8adf156f924b4397&oe=5DEEA52E").into(ins3);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67487619_2301078140207198_2893599340822003712_n.jpg?_nc_cat=103&_nc_oc=AQmfUAW6oYHEdOimMikgQu_N9eW5lYXsQ3e86OWIC2-DvLnW5JklEw5dPA4o5Oi5AWc&_nc_ht=scontent.ffru1-1.fna&oh=4a86dfec8de5809456086d620d19d104&oe=5DED4731").into(ins4);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67333459_2301078133540532_5290214486676865024_n.jpg?_nc_cat=105&_nc_oc=AQkU6zjpQimVSSxj96jKLcJYBpERp0RL8sg8DPalgr8eIIwbshcjxucrmTMVb3R34sw&_nc_ht=scontent.ffru1-1.fna&oh=ce14e7c32abf8ee4d3b864b882645cdd&oe=5DDAE243").into(ins5);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67302320_2301078130207199_5946669322695868416_n.jpg?_nc_cat=106&_nc_oc=AQmc5ZbNQryJHLvygggzqVXpUg4rT7bfuKHTKZdqGHcKVz08rSUlFxuOmgqXSuOdtjw&_nc_ht=scontent.ffru1-1.fna&oh=7e6a895b578202e5232659161aab5162&oe=5DA111FB").into(ins6);
        Glide.with(this).load("https://scontent.ffru1-1.fna.fbcdn.net/v/t1.0-9/67589278_2301078190207193_4754473425953619968_n.jpg?_nc_cat=104&_nc_oc=AQlDtl5tRTl__q2y1Kc_BhCzrk3kTHZsYM_bhDOZhCTzOf-93D4Wxzxb9oUZr-BE0Dg&_nc_ht=scontent.ffru1-1.fna&oh=e12d79b687997d2ce4c5f2c218bffe0d&oe=5DDB8F68").into(ins7);
        String schet = getIntent().getStringExtra("schet");
        ls.setText(schet);
        ins1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
        ins2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
        ins3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
        ins4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
        ins5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
        ins6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
        ins7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isImageScaled) v.animate().scaleX(1.4f).scaleY(1.4f).setDuration(500);
                if (isImageScaled) v.animate().scaleX(1f).scaleY(1f).setDuration(500);
                isImageScaled = !isImageScaled;
            }
        });
    }
}