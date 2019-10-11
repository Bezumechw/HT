package kg.sunrise.hightime.Auth.Register;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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


//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.FirebaseException;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.PhoneAuthCredential;
//import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kg.sunrise.hightime.Activity.MainActivity;
import kg.sunrise.hightime.Auth.Login.ToComeInActivity;
import kg.sunrise.hightime.Auth.Login.TokenLogin;
import kg.sunrise.hightime.Auth.Register.Dogovor.DogovorActivity;
import kg.sunrise.hightime.Lessons.LessonsActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Util.Settings;
import kg.sunrise.hightime.model.Pay24;
import kg.sunrise.hightime.model.Payment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CheckInActivity extends AppCompatActivity {

    private EditText editName, editEmail, editPassword, editPassword2;
    private ProgressBar progressBar;
    private TextView condition, m1, m2;
    private Button button;
    private ImageView imageDrugoyGlaz, imageDrugoyGlaz1;
    private int counter = 0;
    private int condit = 0;
    ListView listView;
    String[] country = new String[]{"Кыргызстан", "Россия", "Казахстан", "Украина", "Белоруcсия", "Таджикистан", "нет в списке"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);
        m1 = findViewById(R.id.m1);
        m2 = findViewById(R.id.m2);
        imageDrugoyGlaz = findViewById(R.id.imageGlaz);
        editName = findViewById(R.id.editName);
        editEmail = findViewById(R.id.editEmail);
        imageDrugoyGlaz1 = findViewById(R.id.imageGlaz1);
        editPassword = findViewById(R.id.editPassword);
        editPassword2 = findViewById(R.id.editPassword2);
        button = findViewById(R.id.onToSend1);
        progressBar = findViewById(R.id.progressBar);
        condition = findViewById(R.id.condition);
        progressBar.setVisibility(View.INVISIBLE);
//        AlertDialog();
        Alert();



    }
    public void Alert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(CheckInActivity.this)
                .setMessage(Html.fromHtml("<font color='#00AEEF'>для Кыргызстана регистрация по номеру телефона либо по Эл.адресу </font>" + "\n"+ "<font color='#00AEEF'>для остальных по Эл.адресу </font>"));
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
        final AlertDialog.Builder builder = new AlertDialog.Builder(CheckInActivity.this);
        final AlertDialog alert = builder.show();
        final FrameLayout frameView = new FrameLayout(CheckInActivity.this);
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
                    Toast.makeText(CheckInActivity.this, country, Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }else{
                    m1.setText("Эл.адрес");
                    m2.setVisibility(View.INVISIBLE);
                    Toast.makeText(CheckInActivity.this, country, Toast.LENGTH_SHORT).show();
                    alertDialog.cancel();
                }
            }
        });

        alertDialog.setCancelable(true);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
//                finish();
//                alertDialog.show();
            }
        });
        alertDialog.show();

    }

    public void onToSend(View view) {
        String name = editName.getText().toString().trim();
        String email = editEmail.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        String password2 = editPassword2.getText().toString().trim();
        if (name.length() == 0 || email.length() == 0){
            Toast.makeText(CheckInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
        }
        else {
            char a = email.charAt(0);
            String out = editEmail.getText().toString();
//вызываем конструктор со строкой в качестве аргумента
            StringBuffer stringBuffer = new StringBuffer(out);
            stringBuffer.delete(0,1);
            out = stringBuffer.toString();
            if (email.length() == 10) {
                String alfa = (a + "996").replaceAll("0", "");
                String email2 = alfa + out;
                if (name.length() == 0 || email2.length() == 0) {
                    Toast.makeText(CheckInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
                } else {
                    if (password.length() < 8) {
                        Toast.makeText(CheckInActivity.this, "Пароль не должен быть меньше 8 символов!", Toast.LENGTH_SHORT).show();
                    }else if (password != password2) {
                        Toast.makeText(CheckInActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    } else {
                        condit++;
                        if (condit >= 2) {
                            signUp(name, email2, password);
                            button.setEnabled(false);
                            Toast.makeText(CheckInActivity.this, "Немного подождите!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.VISIBLE);
                            progressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);
                            condit = 0;
                        } else if (condit >= 1) {
                            InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            Toast.makeText(CheckInActivity.this, "Нажимая на кнопку <<ОТПРАВИТЬ>>, Вы соглашаетесь с условиями конфиденциальности!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }else {
                if (name.length() == 0 || email.length() == 0) {
                    Toast.makeText(CheckInActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (password.length() < 8) {
                        Toast.makeText(CheckInActivity.this, "Пароль не должен быть меньше 8 символов!", Toast.LENGTH_SHORT).show();
                    }
                    else if (!password.equals(password2)) {
                        Toast.makeText(CheckInActivity.this, "Пароли не совпадают!", Toast.LENGTH_SHORT).show();
                    }else {
                        condit++;
                        if (condit >= 2) {
                            signUp(name, email, password);
                            button.setEnabled(false);
                            Toast.makeText(CheckInActivity.this, "Немного подождите!", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.VISIBLE);
                            progressBar.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);
                            condit = 0;
                        } else if (condit >= 1) {
                            InputMethodManager inputManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                            inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            Toast.makeText(CheckInActivity.this, "Нажимая на кнопку <<ОТПРАВИТЬ>>, Вы соглашаетесь с условиями конфиденциальности!", Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            }
        }
    }

    private void signUp(final String name, final String email, final String password) {
        App.getApi().signUp(name, email, password).enqueue(new Callback<TokenRegister>() {
            @Override
//            @EverythingIsNonNull
            public void onResponse(@Nullable Call<TokenRegister> call,@Nullable Response<TokenRegister> response) {
                assert response != null;
                Log.e("TAG", "response: " + response.code());
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().result != null) {
                        App.getApi().toComeIn(email, password).enqueue(new Callback<TokenLogin>() {
                            @Override
                            public void onResponse(@Nullable Call<TokenLogin> call,@Nullable Response<TokenLogin> response) {
                                assert response != null;
                                Log.e("TAG", "response: " + response.code());
                                if (response.isSuccessful()) {
                                    assert response.body() != null;
                                    Log.e("TOKEN ", response.body().getResult().getSuccess().getUser_token());
                                    Settings.setAccessToken(CheckInActivity.this, response.body().getResult().getSuccess().getUser_token());
                                    Settings.setAccessName(CheckInActivity.this, response.body().getUser().getFullName());
                                    Settings.setAccessBalanse(CheckInActivity.this, response.body().getUser().getBalance());
                                    Settings.setAccessBName(CheckInActivity.this, response.body().getUser().getBalance());
                                    Settings.setAccessLs(CheckInActivity.this, response.body().getUser().getAccount_number());
                                    progressBar.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(CheckInActivity.this, MainActivity.class));
                                    Toast.makeText(CheckInActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            }


                            @Override
                            public void onFailure(@Nullable Call<TokenLogin> call,@Nullable Throwable t) {
                                assert t != null;
                                Log.e("TAG", "onFailure: " + t.getMessage());
                            }
                        });
                    }
                }
            }


            @Override
            public void onFailure(@Nullable Call<TokenRegister> call,@Nullable Throwable t) {
                assert t != null;
                Log.e("TAG", "onFailure: " + t.getMessage());
                Toast.makeText(CheckInActivity.this, " Такой E-mail уже зарегистрирован! или введены неправильные данные", Toast.LENGTH_SHORT).show();
//                Toast.makeText(CheckInActivity.this, " Проверьте правильность данных!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
                button.setEnabled(true);

            }
        });
    }

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
        finish();
    }

    public void vDrugoyGlaz(View view) {
        counter++;
        if (counter >= 2) {
            editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            counter = 0;
            imageDrugoyGlaz.setImageResource(R.drawable.glazoff);
        } else if (counter >= 1) {
            editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageDrugoyGlaz.setImageResource(R.drawable.glaz);
        }
    }

    public void onCondition(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/open?id=1HCJ6zrZLKeZj1eD3cE1mxKleZ7xqGpqE"));
        startActivity(browserIntent);


//        condition.setTextColor(Color.BLUE);
//        Thread logoTimer = new Thread() {
//            @Override
//            public void run() {
//                try {
//                    int logoTimer = 0;
//                    while (logoTimer < 300) {
//                        sleep(100);
//                        logoTimer = logoTimer + 100;
//                    }
//
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } finally {
////                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://web.telegram.org/"));
////                    startActivity(intent);
//                    startActivity(new Intent(CheckInActivity.this, DogovorActivity.class));
//                    condition.setTextColor(Color.rgb(113, 113, 113));
//                }
//            }
//        };
//        logoTimer.start();

    }

    public void vDrugoyGlaz1(View view) {
        counter++;
        if (counter >= 2) {
            editPassword2.setTransformationMethod(PasswordTransformationMethod.getInstance());
            counter = 0;
            imageDrugoyGlaz1.setImageResource(R.drawable.glazoff);
        } else if (counter >= 1) {
            editPassword2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            imageDrugoyGlaz1.setImageResource(R.drawable.glaz);
        }
    }
}


