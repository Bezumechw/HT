package kg.sunrise.hightime.Level.Test;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kg.sunrise.hightime.Activity.MainActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Lessons.StudentActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FirstTestActivity extends AppCompatActivity {

    TestAdapter adapter;
    ArrayList<Test> list = new ArrayList<>();

    public static final String APP_PREFERENCES = "saveResult";
    public static final String APP_PREFERENCES_COUNTER = "keyResult";
    private SharedPreferences saveResult;

    int x = 0;
    int y = 0;
    int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_test);
        saveResult = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TestAdapter(list);
        recyclerView.setAdapter(adapter);
//        recyclerView.setNestedScrollingEnabled(false);
        getTest();
        adapter.notifyDataSetChanged();
    }
    @Override
    protected void onResume() {
        if (!isOnline()) {
            Toast toast = Toast.makeText(this, "для получения данных, требуется подключение к сети", Toast.LENGTH_SHORT);
            View view2 = toast.getView();
            toast.getView().setPadding(20, 20, 20, 20);
            view2.setBackgroundResource(R.color.Red);
            toast.show();
        }
        super.onResume();

        if (saveResult.contains(APP_PREFERENCES_COUNTER)) {
            // Получаем число из настроек
            result = saveResult.getInt(APP_PREFERENCES_COUNTER, 0);
            // Выводим на экран данные из настроек
            Toast.makeText(this, "Ваш предыдущий результат: " + result + " %", Toast.LENGTH_LONG).show();

        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor editor = saveResult.edit();
        editor.putInt(APP_PREFERENCES_COUNTER, result);
        editor.apply();
    }

    private void getTest() {
        App.getApi().getTestLevel().enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                Log.e("TAG", "response: " + response.code());
                List<Test> tests = response.body();
                if (tests != null) {
                    showData(tests);
                }
            }

            @Override
            public void onFailure(@Nullable Call<List<Test>> call,@Nullable Throwable t) {
                assert t != null;
                Log.e("TAG", "onFailure: " + t.getMessage());

            }
        });
    }

    private void showData(List<Test> tests) {
        this.list.addAll(tests);
        adapter.notifyDataSetChanged();
    }

    public void onBack(View view) {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void onTest(View view) {
        this.list = adapter.getTests();
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.get(i).getAnswers().size(); j++) {
                Answer answer = list.get(i).getAnswers().get(j);

               if (answer.isStatus()){
                    if (answer.isIs_correct() == true && answer.isStatus() == true && answer.isIs_correct() == answer.isStatus()) {
                        x = x + 1;
                        y = x * 100;
                        result = y / list.size();
                        Intent intent = new Intent(this, StudentActivity.class);
                        intent.putExtra("x", x);
                        intent.putExtra("result", result);
                        intent.putExtra("save", saveResult +"");
                        startActivity(intent);
                        finish();
                    }
                }
               else  if (!answer.isStatus()) {
//                   Toast.makeText(this, "Ответьте на все вопросы!", Toast.LENGTH_LONG).show();
               }

            }
        }
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

}
