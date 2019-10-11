package kg.sunrise.hightime.Contact;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactActivity extends AppCompatActivity {

    public static String FACEBOOK_URL = "https://www.facebook.com/hightime.kg/";
    public static String FACEBOOK_PAGE_ID = "YourPageName";


    EditText editName, editPhone;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        editName = findViewById(R.id.editName);
        editPhone = findViewById(R.id.editPhone);
        button = findViewById(R.id.longClick);
    }

    public void onBack(View view) {
        finish();
    }

    public void onSMS(View view) {
        String name = editName.getText().toString().trim();
        String phone = editPhone.getText().toString().trim();

        if (name.length() == 0 || phone.length() == 0) {
            Toast.makeText(ContactActivity.this, "Заполните поля!", Toast.LENGTH_SHORT).show();
        } else {
//            signUp(name, phone);
            String smsNumber = "996505444415";

            PackageManager pm=getPackageManager();
            try {

                Intent waIntent = new Intent(Intent.ACTION_SEND);
                waIntent.setType("text/plain");
                String text = "Hello, I want to get advice! my data: " + "\n" + "Name:  " + name + "\n" + "Phone:  " + phone;

                PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                //Check if package exists or not. If not then code
                //in catch block will be called
                waIntent.putExtra("jid", smsNumber + "@s.whatsapp.net");
                waIntent.setPackage("com.whatsapp");

                waIntent.putExtra(Intent.EXTRA_TEXT, text);
                startActivity(Intent.createChooser(waIntent, "Share with"));

            } catch (PackageManager.NameNotFoundException e) {
                Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                        .show();
            }
//            try {
//                Intent sendIntent = new Intent("android.intent.action.MAIN");
//
//                sendIntent.setAction(Intent.ACTION_SEND);
////                sendIntent.setType("text/plain");
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello, I want to get advice! my data: " + "\n" + "Name:  " + name + "\n" + "Phone:  " + phone);
//                sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net"); //phone number without "+" prefix
//                sendIntent.setPackage("com.whatsapp");
//                startActivity(sendIntent);
//
//                Toast.makeText(this, "перед отправкой формы, сохраните контакт - выйдите и удерживайте кнопку <<Отправить>>" , Toast.LENGTH_LONG).show();
//
//            } catch (Exception e) {
//                Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show();
//            }
        }
        button.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                intent.putExtra(ContactsContract.Intents.Insert.PHONE, "+996505444415");
                intent.putExtra(ContactsContract.Intents.Insert.NAME, "Hightime");
                startActivity(intent);
                Toast.makeText(ContactActivity.this, "быстрый клик для оправки сообщения", Toast.LENGTH_SHORT).show();
                // сохранение контакта
                return true;
            }
        });
    }


//    private void signUp(String name, String phone) {
//        App.getApi().toContact(name, phone).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Log.e("TAG", "response: " + response.code());
//                Log.e("RESULT ", String.valueOf(response.body()));
//                if (response.isSuccessful()){
//                }else {
//                    Toast.makeText(ContactActivity.this, "Сообщение не отправлено!", Toast.LENGTH_LONG).show();
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Log.e("TAG", "onFailure: " + t.getMessage());
//
//            }
//        });
//    }

    public void Whatsap(View view) {
        String url = "https://api.whatsapp.com/send?phone="+"996505444415";
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
//        Uri uri = Uri.parse("smsto:" + "+996505444415");
//        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, uri);
//        sendIntent.setPackage("com.whatsapp");
//        startActivity(sendIntent);
    }

    public void FaceBook(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/hightime.kg/"));
        startActivity(browserIntent);
//        Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
//        String facebookUrl = getFacebookPageURL(this);
//        facebookIntent.setData(Uri.parse(facebookUrl));
//        startActivity(facebookIntent);
    }

//    private String getFacebookPageURL(ContactActivity contactActivity) {
//
//        PackageManager packageManager = contactActivity.getPackageManager();
//        try {
//            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
//            if (versionCode >= 3002850) { //newer versions of fb app
//                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
//            } else { //older versions of fb app
//                return "fb://page/" + FACEBOOK_PAGE_ID;
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            return FACEBOOK_URL; //normal web url
//        }
//    }

    public void Instagram(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/hightime_world/"));
        startActivity(browserIntent);
//        Uri uri = Uri.parse("https://www.instagram.com/hightime_world/");
//        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
//
//        likeIng.setPackage("com.instagram.android");
//
//        try {
//            startActivity(likeIng);
//        } catch (ActivityNotFoundException e) {
//            startActivity(new Intent(Intent.ACTION_VIEW,
//                    Uri.parse("https://www.instagram.com/hightime_world/")));
//        }
    }
    public void YouTube (View view){
        String id = "UCkzrjgeGDtRNRwA-5dQpX3w";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/" + id));
        intent.putExtra("VIDEO_ID", id);
        startActivity(intent);
    }

    public void onFocuseEditText(View view) {
        Toast.makeText(this, "перед отправкой формы, сохраните контакт - выйдите и удерживайте кнопку <<Отправить>>" , Toast.LENGTH_LONG).show();
    }
}
