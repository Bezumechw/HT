package kg.sunrise.hightime.Auth.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.protobuf.Empty;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import kg.sunrise.hightime.Activity.MainActivity;
import kg.sunrise.hightime.Auth.Register.CheckInActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Reviews.ReviewOne.ReviewOneActivity;
import kg.sunrise.hightime.Reviews.ReviewTwo.ReviewTwoActivity;
import kg.sunrise.hightime.Util.Settings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ToComeInActivity extends AppCompatActivity {

    TextView m1, m2;
    EditText editName, editPassword;
    ProgressBar progressBar;
    Button button;
    ImageView imageGlaz;
    private int counter = 0;
    ListView listView;
    String[] country = new String[]{"Кыргызстан", "Россия", "Казахстан", "Украина", "Белоруcсия", "Таджикистан", "нет в списке"};

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_come_in);
        this.context = ToComeInActivity.this;
        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        imageGlaz = findViewById(R.id.imageGlaz);
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        button = findViewById(R.id.vhod);
        progressBar = findViewById(R.id.progressBarV);
        progressBar.setVisibility(View.INVISIBLE);
//        AlertDialog();
        Alert();
//        int SPLASH_TIME_OUT = 3000;
//        new Handler().postDelayed(new Runnable() {
//
//        @Override
//        public void run() {
////            Toast.makeText(getApplicationContext(), "есть соединение", Toast.LENGTH_SHORT).show();
//        }
//    }, SPLASH_TIME_OUT);


//    if (isNetworkAvailable()) {
//        new CheckInternetAsyncTask(this.context).execute();
//    }
//    else {
//        Toast toast = Toast.makeText(this, "для входа требуется соединение с интернетом!", Toast.LENGTH_SHORT);
//        View view2 = toast.getView();
//        toast.getView().setPadding(20, 20, 20, 20);
//        view2.setBackgroundResource(R.color.Red);
//        toast.show();
//    }
//}
//
//    public boolean isNetworkAvailable() {
//        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        return networkInfo != null && networkInfo.isConnected();
//    }


//class CheckInternetAsyncTask extends AsyncTask<Void, Integer, Boolean> {
//
//    private Context context;
//
//    CheckInternetAsyncTask(Context context) {
//        this.context = context;
//    }
//
//    @Override
//    protected Boolean doInBackground(Void... params) {
//
//        ConnectivityManager cm =
//                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        assert cm != null;
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null &&
//                activeNetwork.isConnected();
//
//        if (isConnected) {
//            try {
//                HttpURLConnection urlc = (HttpURLConnection)
//                        (new URL("http://clients3.google.com/generate_204")
//                                .openConnection());
//                urlc.setRequestProperty("User-Agent", "Android");
//                urlc.setRequestProperty("Connection", "close");
//                urlc.setConnectTimeout(1500);
//                urlc.connect();
//                if (urlc.getResponseCode() == 204 &&
//                        urlc.getContentLength() == 0)
//                    return true;
//
//            } catch (IOException e) {
//                Log.e("TAG", "Error checking internet connection", e);
//                Toast.makeText(getApplicationContext(), "Error checking internet connection", Toast.LENGTH_LONG).show();
//                return false;
//            }
//        } else {
//            Log.d("TAG", "No network available!");
//            Toast.makeText(getApplicationContext(), "No network available!", Toast.LENGTH_LONG).show();
//            return false;
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Boolean result) {
//        super.onPostExecute(result);
//        Log.d("TAG", "result" + result);
//    }
    }
    public void Alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(ToComeInActivity.this)
                .setMessage(Html.fromHtml("<font color='#00AEEF'>для Кыргызстана вход по номеру телефона либо по Эл.адресу </font>" + "\n"+ "<font color='#00AEEF'>для остальных по Эл.адресу </font>"));
        final AlertDialog alert = builder.show();
        TextView messageText = alert.findViewById(android.R.id.message);
        messageText.setGravity(Gravity.CENTER);
        alert.show();

        Thread logoTimer = new Thread() {
            @Override
            public void run() {
                try {
                    int logoTimer = 0;
                    while (logoTimer < 10000) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    alert.cancel();
                }
            }
        };
        logoTimer.start();

    }


    public void AlertDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(ToComeInActivity.this);
        final AlertDialog alert = builder.show();
        final FrameLayout frameView = new FrameLayout(ToComeInActivity.this);
        final AlertDialog alertDialog = builder.create();
        LayoutInflater inflater = alertDialog.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.alert_auth, frameView);
        alertDialog.setView(dialoglayout);
        listView = dialoglayout.findViewById(R.id.listCountry);
        final ArrayAdapter<String> adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, country);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String country = adapter.getItem(position);
                if (position == 0){
                    m1.setText("Эл.адрес или номер телефона");
                    m2.setVisibility(View.VISIBLE);
                    Toast.makeText(ToComeInActivity.this, country, Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }else{
                    m1.setText("Эл.адрес");
                    m2.setVisibility(View.INVISIBLE);
                    Toast.makeText(ToComeInActivity.this, country, Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            }
        });
//
//        alertDialog.setCancelable(true);
//        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//            @Override
//            public void onCancel(DialogInterface dialog) {
//            }
//        });
        alertDialog.show();

    }

    public void onToComeIn(View view) {
        String username = editName.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        if (username.length() == 0){
            Toast.makeText(ToComeInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
        }
        else {
            char a = username.charAt(0);
            String out = editName.getText().toString();
//вызываем конструктор со строкой в качестве аргумента
            StringBuffer stringBuffer = new StringBuffer(out);
            stringBuffer.delete(0,1);
            out = stringBuffer.toString();
            if (username.length() == 10){
                String alfa = (a +"996").replaceAll("0",   "" );
                String email2 = alfa + out;
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(ToComeInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(ToComeInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
                } else {
                    signUp(email2, password);
                    button.setEnabled(false);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);
                }
            }
            else {
                if (username.length() == 0 || password.length() == 0) {
                    Toast.makeText(ToComeInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
                } else {
                    signUp(username, password);
                    button.setEnabled(false);
                    progressBar.setVisibility(View.VISIBLE);
                    progressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);
                }
            }
        }
    }

    private void signUp(final String username, String password) {
        App.getApi().toComeIn(username, password).enqueue(new Callback<TokenLogin>() {
            @Override
            public void onResponse(@NonNull Call<TokenLogin> call, @NonNull Response<TokenLogin> response) {
                Log.e("TAG", "response: " + response.code());
//                if (response.body().result != null) {
                if (response.body().getResult() == null) {
                    Toast.makeText(ToComeInActivity.this, "проверьте правильность введенных данных", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    button.setEnabled(true);
                }
                else if (response.isSuccessful()) {
                    assert response.body() != null;//работает
                    String token =   response.body().getResult().getSuccess().getUser_token();
                    if (token != null){
                        Log.e("TOKEN ", response.body().getResult().getSuccess().getUser_token());
                        Settings.setAccessToken(ToComeInActivity.this, token);
                        Settings.setAccessName(ToComeInActivity.this, response.body().getUser().getFullName());
                        Settings.setAccessBalanse(ToComeInActivity.this, response.body().getUser().getBalance());
                        Settings.setAccessLs(ToComeInActivity.this, response.body().getUser().getAccount_number());
                        Settings.setAccessLSName(ToComeInActivity.this, response.body().getUser().getAccount_number());
                        progressBar.setVisibility(View.INVISIBLE);
                        startActivity(new Intent(ToComeInActivity.this, MainActivity.class));
                        finish();
                    }
                    else if (response.body() == null || token == null){
                        Toast.makeText(ToComeInActivity.this, "проверьте правильность введенных данных", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    assert response.body() != null;
                    Toast.makeText(ToComeInActivity.this, response.body().error.replaceAll("Unauthorised", "Аккаунт не найден!" +
                            "Проверьте правильность веденных данных"), Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.INVISIBLE);
                    button.setEnabled(true);
                }
            }


            @Override
            public void onFailure(@Nullable Call<TokenLogin> call,@Nullable Throwable t) {
                assert t != null;
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    public void onCancel(View view) {
        finish();
    }

    public void vGlaz(View view) {
        counter++;
        if (counter >= 2) {
            editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            counter = 0;
            imageGlaz.setImageResource(R.drawable.glazoff);
        } else if (counter >= 1) {
            editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageGlaz.setImageResource(R.drawable.glaz);
        }
    }
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onResume() {

        if (!isConnected(this)) {
            Toast toast = Toast.makeText(this, "для входа требуется отличное соединение с интернетом!", Toast.LENGTH_SHORT);
            View view2 = toast.getView();
            toast.getView().setPadding(20, 20, 20, 20);
            view2.setBackgroundResource(R.color.Red);
            toast.show();
        }
        super.onResume();
    }
}

