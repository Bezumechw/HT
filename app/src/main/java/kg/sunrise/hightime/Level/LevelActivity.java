package kg.sunrise.hightime.Level;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Level.Test.FirstTestActivity;

public class LevelActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level);

        TextView textView = findViewById(R.id.textView);
        textView.setText("High Time даст вам возможность быстро и " +
                "легко выучить английский язык." +
                " В этих курсах есть 6 уровней, в каждом" +
                " уровне по 25-30 уроков." +
                " Один урок включает в себя несколько" +
                " видео уроков, которые дадут вам" +
                " возможность максимально " +
                "хорошо усвоить тему." +
                " Вы можете изучать английский язык с " +
                "помощью снятых нами видео-роликов, " +
                " с использованием различных пластилинов," +
                " очень доступное объяснение различных" +
                " тем. Это приложение эффективно улучшит" +
                " ваши знания по грамматике, " +
                "расширит словарный запас и поможет " +
                "подготовиться к экзаменам, путешествию, " +
                "где не будет языковых барьеров(преград).");
    }

    public void onTest(View view) {
        startActivity(new Intent(this, FirstTestActivity.class));
    }

    public void onBack(View view) {
        finish();
    }
}
