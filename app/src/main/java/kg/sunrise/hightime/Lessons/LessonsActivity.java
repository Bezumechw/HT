package kg.sunrise.hightime.Lessons;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Lessons.Char_lesson.CharActivity;
import kg.sunrise.hightime.model.CheckPayment;
import kg.sunrise.hightime.model.Pay24;
import kg.sunrise.hightime.model.Payment;
import kg.sunrise.hightime.model.PaymentAndPromocode;
import kg.sunrise.hightime.Util.Settings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class LessonsActivity extends AppCompatActivity {
    RelativeLayout relativeLayout;
    ImageView imageTitle;
    TextView textPrice, textDuration, textContent, textLess, textTitle, textLessons;
    EditText editPromocode;
    ProgressBar pB;
    Button buy;

    ButtonAdapter adapter;
    ArrayList<ButtonNames> list = new ArrayList<>();
    int level;
    boolean saver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lessons);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));


        adapter = new ButtonAdapter(list);
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);

        relativeLayout = findViewById(R.id.relativeLayout);
        buy = findViewById(R.id.buy);
        textTitle = findViewById(R.id.textTitle);
        imageTitle = findViewById(R.id.imageTitle);
        textPrice = findViewById(R.id.textPrice);
        textDuration = findViewById(R.id.textDuration);
        textContent = findViewById(R.id.textContent);
        textLess = findViewById(R.id.textLess);
        textLessons = findViewById(R.id.textLessons);
        editPromocode = findViewById(R.id.editPromocode);
        pB = findViewById(R.id.pB);
        pB.setVisibility(View.VISIBLE);
        pB.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);


        final int id = getIntent().getIntExtra("id", 0);
        relativeLayout.setBackgroundResource(Integer.parseInt(getIntent().getStringExtra("background")));
//        App.getApi().getCheckPayment(id, Settings.getAccessToken(LessonsActivity.this)).enqueue(new Callback<CheckPayment>() {
//            @Override
//            public void onResponse(Call<CheckPayment> call, Response<CheckPayment> response) {
//                CheckPayment checkPayment = response.body();
//                if (checkPayment.isIs_buyed()){
//                    buy.setOnClickListener(new View.OnClickListener() {
//                                               @Override
//                                               public void onClick(View v) {
//                                                   final AlertDialog.Builder builder = new AlertDialog.Builder(LessonsActivity.this);
//                                                   final AlertDialog alert = builder.show();
//                                                   final FrameLayout frameView = new FrameLayout(LessonsActivity.this);
//                                                   final AlertDialog alertDialog = builder.create();
//                                                   LayoutInflater inflater = alertDialog.getLayoutInflater();
//                                                   View dialoglayout = inflater.inflate(R.layout.alert_payment, frameView);
//                                                   alertDialog.setView(dialoglayout);
//                                                   ImageView visa = dialoglayout.findViewById(R.id.visa);
//                                                   visa.setOnClickListener(new View.OnClickListener() {
//                                                       @Override
//                                                       public void onClick(View v) {
////                                                           Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://money.yandex.ru/to/410019331161938/699.00"));
////                                                           startActivity(intent);
//                                                           //виса
//
//                                                       }
//                                                   });
//
//                                                   ImageView image = dialoglayout.findViewById(R.id.pay24);
//                                                   image.setOnClickListener(new View.OnClickListener() {
//                                                       @Override
//                                                       public void onClick(View v) {
//                                                          //pay24
//                                                       }
//                                                   });
//                                                   alertDialog.setCancelable(true);
//                                                   alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                                                       @Override
//                                                       public void onCancel(DialogInterface dialog) {
//                                                           finish();
//                                                       }
//                                                   });
//                                                   alertDialog.show();
////
//
//                                               }
//                                           });
//                    buy.setText("Купить");
////                    buy.setEnabled(false);
//                }
//                else {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CheckPayment> call, Throwable t) {
//
//            }
//        });

        if (id == 2) {
            textLessons.setText("27 уроков");
            Intent intent = new Intent();
            intent.putExtra("lvl", 2);
        } else if (id == 3) {
            textLessons.setText("44 урока");
            Intent intent = new Intent();
            intent.putExtra("lvl", 3);
        } else if (id == 4) {
            textLessons.setText("26 урока");
            Intent intent = new Intent();
            intent.putExtra("lvl", 4);
        } else if (id == 5) {
            textLessons.setText("18 уроков");
            Intent intent = new Intent();
            intent.putExtra("lvl", 5);
        } else if (id == 6) {
            textLessons.setText("22 уроков");
            Intent intent = new Intent();
            intent.putExtra("lvl", 6);
        } else if (id == 7) {
            textLessons.setText("20 уроков");
            Intent intent = new Intent();
            intent.putExtra("lvl", 7);
        }
        level = id;

        adapter.setOnClickListener(new ButtonAdapter.OnClickListener() {
            @Override
            public void onClickItem(final int pos) {
                App.getApi().getCheckPayment(id, Settings.getAccessToken(LessonsActivity.this)).enqueue(new Callback<CheckPayment>() {
                    @Override
//                    @EverythingIsNonNull
                    public void onResponse(Call<CheckPayment> call, Response<CheckPayment> response) {
                        Log.e("TAG", "response  CheckPayment: " + response.code());
                        CheckPayment checkPayment = response.body();
                        if (response.isSuccessful()) {
                            assert checkPayment != null;
                            if (!checkPayment.isIs_buyed()) {
                                if (list.get(pos).getId() == 1 || list.get(pos).getId() == 2 ||
                                        list.get(pos).getId() == 54 || list.get(pos).getId() == 55 ||
                                        list.get(pos).getId() == 28 || list.get(pos).getId() == 29 ||
                                        list.get(pos).getId() == 101 || list.get(pos).getId() == 102 ||
                                        list.get(pos).getId() == 124 || list.get(pos).getId() == 125 ||
                                        list.get(pos).getId() == 146 || list.get(pos).getId() == 147) {
                                    Intent intent = new Intent(LessonsActivity.this, CharActivity.class);
                                    intent.putExtra("id", list.get(pos).getId());
                                    intent.putExtra("level", saver);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LessonsActivity.this, "Купите Уровень!", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Intent intent = new Intent(LessonsActivity.this, CharActivity.class);
                                intent.putExtra("id", list.get(pos).getId());
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<CheckPayment> call, Throwable t) {
                        Log.e("TAG", "onFailure CheckPayment: " + t.getMessage());
                    }
                });
            }
        });

        if (id != 2) {
            textLess.setText("Уроки");
        } else {
            textLess.setText("Уроки по Буквам");
        }
        getLessons(id);
        getAlfa(id);
        adapter.notifyDataSetChanged();
    }
    public void onBack(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                finish();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

    }

    private void getLessons(final int id) {
        App.getApi().getLessons(id).enqueue(new Callback<Lessons>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Lessons> call, Response<Lessons> response) {

                Log.e("TAG", "response: " + response.code());
                Lessons lessons = response.body();
                if (response.isSuccessful()) {
                    assert lessons != null;
                    textTitle.setText("Уровень " + lessons.name);
                    Picasso.get().load(lessons.thumbnail).into(imageTitle);
                    textPrice.setText(lessons.price + " USD");
                    textDuration.setText(lessons.duration + " часа видео");
                    textContent.setText(lessons.content.replaceAll("&amp;amp;nbsp;", "")
                            .replaceAll("&amp;amp;bull;", ""));
                    pB.setVisibility(View.INVISIBLE);
                }
            }
            @Override
            public void onFailure(Call<Lessons> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }


    private void getAlfa(int id) {
        App.getApi().getAlfa(id).enqueue(new Callback<List<ButtonNames>>() {
            @Override
            public void onResponse(Call<List<ButtonNames>> call, Response<List<ButtonNames>> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "response  Alfa: " + response.code());
                    adapter.notifyDataSetChanged();
                    List<ButtonNames> buttonNames = response.body();
                    if (buttonNames != null) {
                        showData(buttonNames);
                    }
                }
            }
            @Override
            public void onFailure(Call<List<ButtonNames>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private void showData(List<ButtonNames> buttonNames) {
        this.list.addAll(buttonNames);
        this.list.removeAll(Collections.singleton(null));

        adapter.notifyDataSetChanged();
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

    public void onBuy(View view) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LessonsActivity.this);
        final AlertDialog alert = builder.show();
        final FrameLayout frameView = new FrameLayout(LessonsActivity.this);
        final AlertDialog alertDialog = builder.create();
        LayoutInflater inflater = alertDialog.getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.alert_payment, frameView);
        alertDialog.setView(dialoglayout);
        ImageView visa = dialoglayout.findViewById(R.id.visa);
        visa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getApi().getPayment(level, Settings.getAccessToken(LessonsActivity.this)).enqueue(new Callback<Payment>() {
            @Override
            public void onResponse(@Nullable Call<Payment> call,@Nullable Response<Payment> response) {
                assert response != null;
                Log.e("TAG", "response  Payment: " + response.code() + " " + "уровень" + level + "  " + response.body());
                    Payment payment = response.body();
                    if (response.isSuccessful()) {
                    assert payment != null;
                    Toast.makeText(LessonsActivity.this, payment.getMessage(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(payment.getPayment_link())));
                    }else {
                    try {
                        assert response.errorBody() != null;
                        Log.e("TAG", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(@Nullable Call<Payment> call,@Nullable Throwable t) {
                assert t != null;
                Log.e("TAG", "onFailure Payment: " + t.getMessage()+level);

            }
        });

            }
        });

        ImageView image = dialoglayout.findViewById(R.id.pay24);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.getApi().getPay24(level, Settings.getAccessToken(LessonsActivity.this)).enqueue(new Callback<Pay24>() {
            @Override
            public void onResponse(@Nullable Call<Pay24> call,@Nullable Response<Pay24> response) {
                assert response != null;
                Log.e("TAG", "response  Payment: " + response.code());
                Log.e("TAG", "response  Payment: " + response.code()+" " + "уровень"+ level);
                Pay24 pay24 = response.body();
                if (response.isSuccessful()){
                    assert pay24 != null;
                    Toast.makeText(LessonsActivity.this, pay24.getMessage(), Toast.LENGTH_LONG).show();
//                    pay24.getResult();
//                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(pay24.getMessage())));
                }

            }

            @Override
            public void onFailure(@Nullable Call<Pay24> call,@Nullable Throwable t) {
            }
        });
            }
        });
        alertDialog.setCancelable(true);
        alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                finish();
            }
        });
        alertDialog.show();
    }


    public void onToSend(View view) {
        if (editPromocode.length() == 0) {
            Toast.makeText(LessonsActivity.this, "Введите промокод!", Toast.LENGTH_LONG).show();
        } else {
            App.getApi().getPaymentAndPromocode(level, editPromocode.getText().toString(), Settings.getAccessToken(LessonsActivity.this)).enqueue(new Callback<PaymentAndPromocode>() {
                @Override
                public void onResponse(@Nullable Call<PaymentAndPromocode> call, @Nullable Response<PaymentAndPromocode> response) {
                    assert response != null;
                    Log.e("TAG", "response  PaymentAndPromocode: " + response.code());
                    PaymentAndPromocode paymentAndPromocode = response.body();
                    if (response.isSuccessful()){
                        assert paymentAndPromocode != null;
                        Log.e("TAG", "Промокод: " + paymentAndPromocode.getMessage());
                        String promocode = editPromocode.getText().toString();
//                    if (response.isSuccessful()) {
//                        Toast.makeText(LessonsActivity.this, "Уровень активирован", Toast.LENGTH_LONG).show();
//                    }else if (promocode.length() != 0){
//                        Toast.makeText(LessonsActivity.this, "Введите промокод!", Toast.LENGTH_LONG).show();
//                    }else {
//                        Toast.makeText(LessonsActivity.this, paymentAndPromocode.getMessage().replaceAll(null, "Уровень активирован"), Toast.LENGTH_LONG).show();
//                    }
                        if (promocode.length() != 0) {
//                        Toast.makeText(LessonsActivity.this, paymentAndPromocode.getMessage().replaceAll("", "уровень активирован"), Toast.LENGTH_LONG).show();
                            Toast.makeText(LessonsActivity.this, paymentAndPromocode.getMessage(), Toast.LENGTH_LONG).show();
                            if (paymentAndPromocode.getMessage() == null){
                                Toast.makeText(LessonsActivity.this, "Уровень активирован", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent();
                                intent.putExtra("promo", level );
                            }

                        } else {
                            Toast.makeText(LessonsActivity.this, "Введите промокод!", Toast.LENGTH_LONG).show();
                        }
                    }

                }

                @Override
                public void onFailure(@Nullable Call<PaymentAndPromocode> call,@Nullable Throwable t) {
                    assert t != null;
                    Log.e("TAG", "onFailure PaymentAndPromocode: " + t.getMessage());
                }
            });
        }
    }

    public void onOtherLevel(View view) {
        startActivity(new Intent(LessonsActivity.this, StudentActivity.class));
    }
}
