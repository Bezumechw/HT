package kg.sunrise.hightime.Lvl;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import kg.sunrise.hightime.Auth.AuthActivity;
import kg.sunrise.hightime.Lessons.StudentActivity;
import kg.sunrise.hightime.Lvl.Instruction.InstructionActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Lessons.LessonsActivity;
import kg.sunrise.hightime.model.CheckPayment;
import kg.sunrise.hightime.model.UserInfo;
import kg.sunrise.hightime.Util.Settings;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LvlActivity extends AppCompatActivity {

    LvlAdapter adapter;
    ArrayList<Lvl> list = new ArrayList<>();
    RecyclerView recyclerView;
    private int counter = 0;
    private int coun = 0;
    TextView name, mail, ls, balanse1;
    LinearLayout LL;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_area);
        LL = findViewById(R.id.LL);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        ls = findViewById(R.id.ls);
        balanse1 = findViewById(R.id.balanse);
        recyclerView = findViewById(R.id.recyclerPersonalArea);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LvlAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new LvlAdapter.OnClickListener() {
            @Override
            public void onClickItem(int pos) {
                Intent intent = new Intent(LvlActivity.this, LessonsActivity.class);
                intent.putExtra("id", list.get(pos).getId());
                int r = list.get(pos).getId();
                if (r == 2){
                    intent.putExtra("background", String.valueOf(R.color.background_Alphabet));
                }else if (r == 3){
                    intent.putExtra("background", String.valueOf(R.color.background_Beginner));
                }else if (r == 4){
                    intent.putExtra("background", String.valueOf(R.color.background_Pre_Intermediate));
                }else if (r == 5){
                    intent.putExtra("background", String.valueOf(R.color.background_Intermediate));
                }else if (r == 6){
                    intent.putExtra("background", String.valueOf(R.color.background_Upper_Intermediate));
                }else if (r == 7){
                    intent.putExtra("background", String.valueOf(R.color.background_Advanced));
                }
                startActivity(intent);
            }
        });
        String toktok = Settings.getAccessToken(LvlActivity.this);
        getInfo(toktok);
        lvl();

    }



    public void getInfo(final String toktok){
        App.getApi().getUserInfo(toktok).enqueue(new Callback<UserInfo>() {
            @Override
            public void onResponse(Call<UserInfo> call, Response<UserInfo> response) {
                if (response.isSuccessful()) {
                    Log.e("TAG", "response: " + response.code());
                    UserInfo userInfo = response.body();
                    assert userInfo != null;
                    name.setText(userInfo.getFullName());
                    mail.setText(userInfo.getEmail());
                    ls.setText(userInfo.getAccount_number());
                    balanse1.setText(userInfo.getBalance());
                }
            }

            @Override
            public void onFailure(Call<UserInfo> call, Throwable t) {

            }
        });
    }


    public void Start(){
        App.getApi().getLvl(Settings.getAccessToken(LvlActivity.this)).enqueue(new Callback<List<Lvl>>() {
            @Override
            public void onResponse(Call<List<Lvl>> call, Response<List<Lvl>> response) {
                if (response.isSuccessful()) {
                    final List<Lvl> lvls = response.body();
                    final int id = getIntent().getIntExtra("id", 0);
                    App.getApi().getCheckPayment(id, Settings.getAccessToken(LvlActivity.this)).enqueue(new Callback<CheckPayment>() {
                        @Override
                        public void onResponse(Call<CheckPayment> call, Response<CheckPayment> response) {
                            if (response.isSuccessful()) {
                                CheckPayment checkPayment = response.body();
                                assert checkPayment != null;
                                if (!checkPayment.isIs_buyed()) {
                                    showData(lvls);
                                }
                            }
                            else {
                            }
                        }
                        @Override
                        public void onFailure(Call<CheckPayment> call, Throwable t) {
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Lvl>> call, Throwable t) {
            }
        });
    }
    private void showData(List<Lvl> lvls) {
        this.list.addAll(lvls);
        adapter.notifyDataSetChanged();

    }
    public void onBack(View view) {
        finish();
    }

    public void onExit(View view) {
        Settings.deleteToken(this);
        Intent intent = new Intent(this, AuthActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish(); }
        public  void lvl(){
            Start();
            adapter.notifyDataSetChanged();
        }
//    public void onLevel(View view){
//        counter++; if (counter >= 2) {
//            recyclerView.setVisibility(View.INVISIBLE);
//            list.clear();
//            adapter.notifyDataSetChanged();
//            counter = 0; }
//        else if (counter >= 1) {
//            recyclerView.setVisibility(View.VISIBLE);
//            Start();
//            adapter.notifyDataSetChanged(); }
//    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void onIns(View view) {
        String schet = ls.getText().toString().trim();
        Intent intent = new Intent(this, InstructionActivity.class);
        intent.putExtra("schet", schet);
        startActivity(intent);
    }

    public void onClick(View view) {

    }
}

