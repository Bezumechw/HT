package kg.sunrise.hightime.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import kg.sunrise.hightime.R;

public class AboutUsActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        TextView textView = findViewById(R.id.textView);
        textView.setText("Крупнейшая языковая школа Кыргызстана" +
                " по изучению английского языка." +
                " HighTime был создан 13 января 2006 года." +
                " Основателем школы HighTime является" +
                " Гульзат Мамытбек." +
                " Цель создания школы - открыть" +
                " бизнес и забрать родителей из села," +
                " из села Ала-Бука," +
                " Жалал-Абадской области.");

        TextView textView2 = findViewById(R.id.textView2);
        textView2.setText("Позвольте выразить Вам искреннюю" +
                " благодарность от своего лица и от всего" +
                " коллектива «Navat» за обучение." +
                " Все приобретенные знания используются" +
                " нами во время работы, ведь у нас много" +
                " иностранных гостей из разных стран." +
                " И им требуется обращаться" +
                " на английском языке." +
                " Надеемся на дальнейшее столь" +
                " же плодотворное сотрудничество.");

        TextView textView3 = findViewById(R.id.textView3);
        textView3.setText("Компания MegaCom (ЗАО \"Альфа" +
                " Телеком\") выражает благодарность" +
                " сотрудникам языковой школы “High Time”" +
                " за отлично проведенные уроки. \n" +
                "\n" +
                "Обучение персонала должно проходить " +
                "таким образом, чтобы мотивировать " +
                "сотрудников на дальнейшее развитие и на" +
                "достижение результатов. Особенно" +
                " приятно, когда" +
                " обучение проходит легко и интересно." +
                " Знание английского языка в наше время" +
                " является необходимым." +
                " Мы благодарим за" +
                " высокопрофессиональный подход и" +
                " качественную подачу материала!");

        TextView textView4 = findViewById(R.id.textView4);
        textView4.setText("Этно-комплекс “Супара ” выражает" +
                " благодарность за обучение. Хотим" +
                " отметить высокую квалификацию" +
                " преподавательского состава, четкую и" +
                " профессиональную работу сотрудников.\n" +
                "\n" +
                " Желаем дальнейшего процветания и" +
                " профессиональных побед! Вместе с Вами" +
                " всегда есть к чему стремиться и расти" +
                " молодому поколению, которое стоит на" +
                " пути выбора своей жизненной" +
                " профессии. Желаем Вам и Вашему" +
                " коллективу благополучия и больших" +
                " творческих успехов в нелегком и" +
                " благородном труде!");
    }

    public void onBack(View view) {
        finish();
    }
}
