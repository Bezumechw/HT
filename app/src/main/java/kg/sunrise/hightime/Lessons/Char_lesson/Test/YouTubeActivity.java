package kg.sunrise.hightime.Lessons.Char_lesson.Test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.session.PlaybackState;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.TranslateAnimation;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import kg.sunrise.hightime.Lessons.Char_lesson.CharActivity;
import kg.sunrise.hightime.Lessons.Char_lesson.VideoActivity;
import kg.sunrise.hightime.R;

public class YouTubeActivity extends AppCompatActivity {
    WebView webView;
    ImageView im;
    LinearLayout l2;
    Button button;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_you_tube);
//        l2 = findViewById(R.id.l2);
//        im = findViewById(R.id.im);
        webView = findViewById(R.id.youtube);
        button = findViewById(R.id.button);
        button.setEnabled(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(getIntent().getStringExtra("videos2"));

        WebSettings webSettings = webView.getSettings();
        webView.setVerticalScrollBarEnabled(false);
        webView.setHorizontalScrollBarEnabled(false);
        webView.setScrollContainer(false);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportZoom(true);
        webSettings.setPluginState(WebSettings.PluginState.OFF);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
    }

    public void landscape(View view) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void portret(View view) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    public void onBack(View view) {
        finish();
    }
}
