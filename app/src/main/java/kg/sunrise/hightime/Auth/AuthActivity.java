package kg.sunrise.hightime.Auth;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import kg.sunrise.hightime.Auth.Login.ToComeInActivity;
import kg.sunrise.hightime.Auth.Register.CheckInActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Reviews.ReviewTwo.ReviewTwoActivity;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class AuthActivity extends AppCompatActivity {
    private static final int MULTIPLE_PERMISSION_REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        onPermishion();
    }

    public void onCheckIn(View view) {
        startActivity(new Intent(this, CheckInActivity.class));

    }

    public void onToComeIn(View view) {
        startActivity(new Intent(this, ToComeInActivity.class));

    }
    public void onPermishion(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("дать разрешение на чтение")
                .setMessage("")
                //.setIcon(R.drawable.ic_android_cat)
                .setCancelable(true)
                .setPositiveButton("нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .setNegativeButton("да",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                int currentApiVersion = Build.VERSION.SDK_INT;

                                if (currentApiVersion >= Build.VERSION_CODES.M) {
                                    if (ContextCompat.checkSelfPermission(AuthActivity.this, READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
                                            ContextCompat.checkSelfPermission(AuthActivity.this, WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                                    } else {
                                        checkPermissionsState();

                                    }
                                }
                            }
                        });
        AlertDialog alert = builder.create();
        alert.show();
    }
    private void checkPermissionsState() {
        int cameraStatePermissionCheck = ContextCompat.checkSelfPermission(this,
                CAMERA);

        int writeExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        int readExternalStoragePermissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE);


        if (cameraStatePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                writeExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED &&
                readExternalStoragePermissionCheck == PackageManager.PERMISSION_GRANTED) {
            Log.e("CheckPermission", "TRUE");
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            CAMERA,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    },
                    MULTIPLE_PERMISSION_REQUEST_CODE);
        }
    }
}
