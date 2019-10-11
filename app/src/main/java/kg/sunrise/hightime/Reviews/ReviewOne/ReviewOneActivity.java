package kg.sunrise.hightime.Reviews.ReviewOne;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Reviews.ReviewTwo.ReviewTwoActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReviewOneActivity extends AppCompatActivity {

    ReviewOneAdapter adapter;
    ArrayList<ReviewOne> list = new ArrayList<>();
    private ProgressBar progressRev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_one);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        progressRev = findViewById(R.id.progressRev);
        progressRev.setVisibility(View.VISIBLE);
        progressRev.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReviewOneAdapter(list);
        recyclerView.setAdapter(adapter);
        getReview();
        adapter.notifyDataSetChanged();
    }

    private void getReview() {
        App.getApi().getReviewOne().enqueue(new Callback<List<ReviewOne>>() {
            @Override
            public void onResponse(Call<List<ReviewOne>> call, Response<List<ReviewOne>> response) {
                Log.e("TAG", "response: " + response.code());
                List<ReviewOne> reviewOnes = response.body();
                if (reviewOnes != null) {
                    showData(reviewOnes);
                    progressRev.setVisibility(View.INVISIBLE);
                    progressRev.getIndeterminateDrawable().setColorFilter(Color.rgb(0, 191, 255), PorterDuff.Mode.MULTIPLY);
                }
            }

            @Override
            public void onFailure(Call<List<ReviewOne>> call, Throwable t) {
                Log.e("TAG", "onFailure: " + t.getMessage());
            }
        });
    }

    private void showData(List<ReviewOne> reviewOnes) {
        this.list.addAll(reviewOnes);
        adapter.notifyDataSetChanged();
    }

    public void onBack(View view) {
        finish();
    }

    public void onReview(View view) {
        startActivity(new Intent(this, ReviewTwoActivity.class));
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
}
