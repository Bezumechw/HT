package kg.sunrise.hightime.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import kg.sunrise.hightime.R;

public class OurTeamActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_our_team);
    }

    public void onBack(View view) {
        finish();
    }
}
