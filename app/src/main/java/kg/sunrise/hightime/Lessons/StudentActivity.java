package kg.sunrise.hightime.Lessons;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import kg.sunrise.hightime.Activity.MainActivity;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Lessons.Char_lesson.Test.AnswersActivity;
import kg.sunrise.hightime.Util.Settings;
import kg.sunrise.hightime.model.CheckPayment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {
    ImageView imageAlphabet, imageInter1, imageInter2, imageBeginner, imageInter3, imageAdvanced, imageGlide;

    public static final String APP_PREFERENCES = "saveResult";
    public static final String APP_PREFERENCES_R = "keyResult";
    public static final String APP_PREFERENCES_S = "keyStr";
    public static final String APP_PREFERENCES_X = "keyX";
    private SharedPreferences saveResult;

    String str;
    private int counter = 0;
    int result;
    int x;
    int promo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        saveResult = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        x = getIntent().getIntExtra("x", 0);
        promo = getIntent().getIntExtra("promo", 0);

        imageAlphabet = findViewById(R.id.imageAlphabet);
        imageInter1 = findViewById(R.id.imageInter1);
        imageInter2 = findViewById(R.id.imageInter2);
        imageBeginner = findViewById(R.id.imageBeginner);
        imageInter3 = findViewById(R.id.imageInter3);
        imageAdvanced = findViewById(R.id.imageAdvanced);
        imageGlide = findViewById(R.id.imageGlide);

        if (x != 0) {
            result = saveResult.getInt(APP_PREFERENCES_R, 0);
            final AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
            builder.setTitle("Ваш результат");

            if (result >= 85) {
                str = "Advanced";
                imageAdvanced.setImageResource(R.drawable.color_img6);

            } else if (result >= 75 && result <= 84) {
                str = "Upper-Intermediate";
                imageInter2.setImageResource(R.drawable.color_img5);


            } else if (result >= 65 && result <= 74) {
                str = "Intermediate";
                imageInter3.setImageResource(R.drawable.color_img4);

            } else if (result >= 55 && result <= 64) {
                str = "Pre-Intermediate";
                imageInter1.setImageResource(R.drawable.color_img3);

            } else if (result >= 45 && result <= 54) {
                str = "Beginner";
                imageBeginner.setImageResource(R.drawable.color_img2);

            } else if (result <= 44) {
                str = "Alphabet";
                imageAlphabet.setImageResource(R.drawable.color_img1);
            }
            builder.setMessage("Правельных ответов: " + x + "\n"
                    + "В процентах: " + result + "%" + "\n"
                    + "Ваш Уровень: " + str);

            builder.setCancelable(false);
            builder.setNegativeButton(Html.fromHtml("<font color='#00AEEF'>Ок</font>"), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            final AlertDialog alert = builder.create();
            alert.show();

            Thread logoTimer = new Thread() {
                @Override
                public void run() {
                    try {
                        int logoTimer = 0;
                        while (logoTimer < 2000) {
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
        } else if (x == 0){
            onResume();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (saveResult.contains(APP_PREFERENCES_R)) {
            str = "Alphabet";
            int Alphabet = 2;
            App.getApi().getCheckPayment(Alphabet, Settings.getAccessToken(StudentActivity.this)).enqueue(new Callback<CheckPayment>() {
                @Override
                public void onResponse(@Nullable Call<CheckPayment> call,@Nullable Response<CheckPayment> response) {
                    assert response != null;
                    Log.e("TAG", "response  CheckPayment: " + response.code());
                    CheckPayment checkPayment = response.body();
                    if (response.isSuccessful()){
                        assert checkPayment != null;
                        if (checkPayment.isIs_buyed()){
                            imageAlphabet.setImageResource(R.drawable.color_img1);
                        }
                        if (!checkPayment.isIs_buyed()){
                            imageAlphabet.setImageResource(R.drawable.grey_img1);
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<CheckPayment> call,@Nullable Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                }
            });
            str = "Beginner";
            int Beginner = 3;
            App.getApi().getCheckPayment(Beginner, Settings.getAccessToken(StudentActivity.this)).enqueue(new Callback<CheckPayment>() {
                @Override
                public void onResponse(@Nullable Call<CheckPayment> call,@Nullable Response<CheckPayment> response) {
                    assert response != null;
                    Log.e("TAG", "response  CheckPayment: " + response.code());
                    CheckPayment checkPayment = response.body();
                    if (response.isSuccessful()){
                        assert checkPayment != null;
                        if (checkPayment.isIs_buyed()){
                            imageBeginner.setImageResource(R.drawable.color_img2);
                        }
                        if (!checkPayment.isIs_buyed()){
                            imageBeginner.setImageResource(R.drawable.grey_img2);
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<CheckPayment> call,@Nullable Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                }
            });
            str = "Pre-Intermediate";
            int PreIntermediate = 4;
            App.getApi().getCheckPayment(PreIntermediate, Settings.getAccessToken(StudentActivity.this)).enqueue(new Callback<CheckPayment>() {
                @Override
                public void onResponse(@Nullable Call<CheckPayment> call,@Nullable Response<CheckPayment> response) {
                    assert response != null;
                    Log.e("TAG", "response  CheckPayment: " + response.code());
                    CheckPayment checkPayment = response.body();
                    if (response.isSuccessful()){
                        assert checkPayment != null;
                        if (checkPayment.isIs_buyed()){
                            imageInter1.setImageResource(R.drawable.color_img3);
                        }
                        if (!checkPayment.isIs_buyed()){
                            imageInter1.setImageResource(R.drawable.grey_img3);
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<CheckPayment> call,@Nullable Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                }
            });
            str = "Intermediate";
            int Intermediate = 5;
            App.getApi().getCheckPayment(Intermediate, Settings.getAccessToken(StudentActivity.this)).enqueue(new Callback<CheckPayment>() {
                @Override
                public void onResponse(@Nullable Call<CheckPayment> call,@Nullable Response<CheckPayment> response) {
                    assert response != null;
                    Log.e("TAG", "response  CheckPayment: " + response.code());
                    CheckPayment checkPayment = response.body();
                    if (response.isSuccessful()){
                        assert checkPayment != null;
                        if (checkPayment.isIs_buyed()){
                            imageInter2.setImageResource(R.drawable.color_img4);
                        }
                        if (!checkPayment.isIs_buyed()){
                            imageInter2.setImageResource(R.drawable.grey_img4);
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<CheckPayment> call,@Nullable  Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                }
            });
            str = "Upper-Intermediate";
            int UpperIntermediate = 6;
            App.getApi().getCheckPayment(UpperIntermediate, Settings.getAccessToken(StudentActivity.this)).enqueue(new Callback<CheckPayment>() {
                @Override
                public void onResponse(@Nullable Call<CheckPayment> call,@Nullable Response<CheckPayment> response) {
                    assert response != null;
                    Log.e("TAG", "response  CheckPayment: " + response.code());
                    CheckPayment checkPayment = response.body();
                    if (response.isSuccessful()){
                        assert checkPayment != null;
                        if (checkPayment.isIs_buyed()){
                            imageInter3.setImageResource(R.drawable.color_img5);
                        }
                        if (!checkPayment.isIs_buyed()){
                            imageInter3.setImageResource(R.drawable.grey_img5);
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<CheckPayment> call,@Nullable  Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                }
            });
            str = "Upper-Intermediate";
            int Advanced = 7;
            App.getApi().getCheckPayment(Advanced, Settings.getAccessToken(StudentActivity.this)).enqueue(new Callback<CheckPayment>() {
                @Override
                public void onResponse(@Nullable Call<CheckPayment> call,@Nullable  Response<CheckPayment> response) {
                    assert response != null;
                    Log.e("TAG", "response  CheckPayment: " + response.code());
                    CheckPayment checkPayment = response.body();
                    if (response.isSuccessful()){
                        assert checkPayment != null;
                        if (checkPayment.isIs_buyed()){
                            imageAdvanced.setImageResource(R.drawable.color_img6);
                        }
                        if (!checkPayment.isIs_buyed()){
                            imageAdvanced.setImageResource(R.drawable.grey_img6);
                        }
                    }
                }

                @Override
                public void onFailure(@Nullable Call<CheckPayment> call,@Nullable Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                }
            });
        }
    }
    @Override
    protected void onPause() {
        SharedPreferences.Editor editor = saveResult.edit();
        editor.putInt(APP_PREFERENCES_R, result);
        editor.putInt(APP_PREFERENCES_X, x);
        editor.putString(APP_PREFERENCES_S, str);
        editor.apply();
        super.onPause();
    }

    public void onBack(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onAlphabet(View view) {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("id", 2);
        intent.putExtra("background", String.valueOf(R.color.background_Alphabet));
        startActivityForResult(intent, 2);
    }

    public void onBeginner(View view) {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("id", 3);
        intent.putExtra("background", String.valueOf(R.color.background_Beginner));
        startActivityForResult(intent, 2);
    }

    public void onInter1(View view) {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("id", 4);
        intent.putExtra("background", String.valueOf(R.color.background_Pre_Intermediate));
        startActivityForResult(intent, 2);
    }

    public void onInter3(View view) {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("id", 5);
        intent.putExtra("background", String.valueOf(R.color.background_Intermediate));
        startActivityForResult(intent, 2);
    }

    public void onInter2(View view) {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("id", 6);
        intent.putExtra("background", String.valueOf(R.color.background_Upper_Intermediate));
        startActivityForResult(intent, 2);
    }

    public void onAdvanced(View view) {
        Intent intent = new Intent(this, LessonsActivity.class);
        intent.putExtra("id", 7);
        intent.putExtra("background", String.valueOf(R.color.background_Advanced));
        startActivityForResult(intent, 2);
    }









































    public void onInter4(View view) {
        counter++; if (counter >= 30) {
            Toast.makeText(this, "это приложение создали М.Мухамед и Т.Урмат" +"\n" + "при поддержке С.Дастана", Toast.LENGTH_SHORT).show();
        counter = 0;

        new AlertDialog.Builder(this)
                .setIcon(R.drawable.logo)
                .setTitle("\n")
                .setMessage("Хо-Хо)тите открыть окно о разработчиках")
                .setPositiveButton(Html.fromHtml("<font color='#00AEEF'>"+"ДА"+"</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        startActivity(new Intent(StudentActivity.this, AnswersActivity.class));
                    }
                })

                .setNeutralButton("", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton(Html.fromHtml("<font color='#00AEEF'>"+"НЕТ"+"</font>"), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .show();
    }
    else if (counter >= 29) {
        Toast.makeText(this, "1", Toast.LENGTH_SHORT).show();
    }
    else if (counter >= 28) {
        Toast.makeText(this, "2", Toast.LENGTH_SHORT).show();
    }
    else if (counter >= 27) {
        Toast.makeText(this, "3", Toast.LENGTH_SHORT).show();
    }
    else if (counter >= 26) {
        Toast.makeText(this, "4", Toast.LENGTH_SHORT).show();
    }
    else if (counter >= 25) {
        Toast.makeText(this, "5", Toast.LENGTH_SHORT).show();
    }
    }
}
