package kg.sunrise.hightime.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

import kg.sunrise.hightime.Auth.AuthActivity;
import kg.sunrise.hightime.Auth.Login.ToComeInActivity;
import kg.sunrise.hightime.Auth.Register.CheckInActivity;
import kg.sunrise.hightime.Contact.ContactActivity;
import kg.sunrise.hightime.Lvl.LvlActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Lessons.StudentActivity;
import kg.sunrise.hightime.Level.LevelActivity;
import kg.sunrise.hightime.News.NewsActivity;
import kg.sunrise.hightime.Reviews.ReviewOne.ReviewOneActivity;
import kg.sunrise.hightime.Util.Settings;

public class MainActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isOnline();

        if (!Settings.isAuth(this)){
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        }

    }

    public void onAboutUs(View view) {
        startActivity(new Intent(this, AboutUsActivity.class));
    }

    public void onStudent(View view) {
        startActivity(new Intent(this, StudentActivity.class));
    }

    public void onInstruction(View view) {
        startActivity(new Intent(this, LevelActivity.class));
    }

    public void onOurTeam(View view) {
        startActivity(new Intent(this, OurTeamActivity.class));
    }

    public void onOurBranches(View view) {
        startActivity(new Intent(this, OurBranchesActivity.class));
    }

    public void onNews(View view) {
        startActivity(new Intent(this, NewsActivity.class));
    }

    public void onContacts(View view) {
        startActivity(new Intent(this, ContactActivity.class));
    }

    public void onPersonalArea(View view) {
        startActivity(new Intent(this, LvlActivity.class));
    }

    public void onToComeIn(View view) {
        startActivity(new Intent(this, ToComeInActivity.class));
    }

    public void onCheckIn(View view) {
        startActivity(new Intent(this, CheckInActivity.class));
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
            Toast toast = Toast.makeText(this, "нет соединения с интернетом", Toast.LENGTH_SHORT);
            View view2 = toast.getView();
            toast.getView().setPadding(20, 20, 20, 20);
            view2.setBackgroundResource(R.color.Red);
            toast.show();
        }
        super.onResume();
        return ;
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    public void onReviews(View view) {
        startActivity(new Intent(this, ReviewOneActivity.class));

    }
}
