package kg.sunrise.hightime.Lessons.Char_lesson.Test;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import kg.sunrise.hightime.R;

public class AnswersActivity extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answers);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        DotsIndicator dotsIndicator = findViewById(R.id.dots_indicator);
        mViewPager =  findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt("page", sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_check, container, false);
            Button button = rootView.findViewById(R.id.button);
            ImageView imageView = rootView.findViewById(R.id.imageView);
            TextView textView = rootView.findViewById(R.id.section_label);
            int page = getArguments().getInt("page");
            switch (page){
                case 0:
                    textView.setText("Привяу");
                    imageView.setImageResource(R.drawable.instagram);
                    button.setVisibility(View.INVISIBLE);
                    break;
                case 1:
                    textView.setText("Как дела?");
                    imageView.setImageResource(R.drawable.urm);
                    button.setVisibility(View.INVISIBLE);
                    break;
                case 2:
                    textView.setText("Что делаешь?");
                    imageView.setImageResource(R.drawable.das);
                    button.setVisibility(View.VISIBLE);
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://money.yandex.ru/to/410019331161938"));
                            startActivity(intent);
                        }
                    });
                    break;
            }
            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}




