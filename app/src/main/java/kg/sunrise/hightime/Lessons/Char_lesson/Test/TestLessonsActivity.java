package kg.sunrise.hightime.Lessons.Char_lesson.Test;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestLessonsActivity extends AppCompatActivity {

    LessonTestAdapter adapter;
    ArrayList<Tests> list = new ArrayList<>();
    String str;
    int x = 0;
    int y = 0;
    int result = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_lessons);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LessonTestAdapter(list);
        recyclerView.setAdapter(adapter);
        int id = getIntent().getIntExtra("id", 0);
        getTests(id);
        adapter.notifyDataSetChanged();
    }

    private void getTests(int id) {
        App.getApi().getTests(id).enqueue(new Callback<List<Tests>>() {
            @Override
            public void onResponse(Call<List<Tests>> call, Response<List<Tests>> response) {
                Log.e("TAG", "response: " + response.code());
                List<Tests> tests = response.body();
                if (tests != null) {
                    showData(tests);
                }
            }

            @Override
            public void onFailure(Call<List<Tests>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

    private void showData(List<Tests> tests) {
        this.list.addAll(tests);
        adapter.notifyDataSetChanged();
    }

    public void onCancel(View view) {
        finish();
    }

    public void onTest(View view) {
        this.list = adapter.getTests();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getAnswers().size(); j++) {
                Answers answers = list.get(i).getAnswers().get(j);
                if (!answers.isStatus()) {
//                    Toast.makeText(this, "Ответьте на все вопросы!", Toast.LENGTH_LONG).show();
                }else {
                    if (answers.isCorrect() == true & answers.isStatus() == true & answers.isCorrect() == answers.isStatus()) {
                        x = x + 1;
                        y = x * 100;
                        result = y / list.size();
                    }
                }
            }
        }

        final AlertDialog.Builder builder = new AlertDialog.Builder(TestLessonsActivity.this);
        builder.setTitle("Ваш результат");

        if (result == 100) {
            str = "Отлично";
        }else if (result >= 50 && result <= 99) {
            str = "Хорошо";
        }else if (result >= 20 && result <= 49) {
            str = "Плохо";
        }else if (result <= 19) {
            str = "Очень Плохо";
        }

        builder.setMessage("Количество вопросов: " + list.size() + "\n"
                + "Правельных ответов: " + x + "\n"
                + "В процентах: " + result + "%" + "\n"
                + "Наша оценка: " + str);

        builder.setCancelable(false);
        builder.setNegativeButton(Html.fromHtml("<font color='#00AEEF'>Ок</font>"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onBackPressed();

            }
        });
        AlertDialog alert = builder.create();
        alert.show();
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
            Toast toast = Toast.makeText(this, "для получения данных требуется соединение с интернетом", Toast.LENGTH_SHORT);
            View view2 = toast.getView();
            toast.getView().setPadding(20, 20, 20, 20);
            view2.setBackgroundResource(R.color.Red);
            toast.show();
        }
        super.onResume();
        return ;
    }
}
