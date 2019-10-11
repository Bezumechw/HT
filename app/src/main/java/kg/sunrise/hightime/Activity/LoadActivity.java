package kg.sunrise.hightime.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import kg.sunrise.hightime.R;

public class LoadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Thread logoTimer = new Thread() {
            @Override
            public void run() {
                try {
                    int logoTimer = 0;
                    while (logoTimer < 1500) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }

                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
                finally {
                    startActivity(new Intent(LoadActivity.this, MainActivity.class));
                    finish();
                }
            }
        };
        logoTimer.start();
    }
}





