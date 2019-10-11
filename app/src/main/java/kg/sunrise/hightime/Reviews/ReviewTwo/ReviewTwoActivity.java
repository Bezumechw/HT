package kg.sunrise.hightime.Reviews.ReviewTwo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ViewTarget;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import kg.sunrise.hightime.Api.ServiceApi;
import kg.sunrise.hightime.BuildConfig;
import kg.sunrise.hightime.Lessons.LessonsActivity;
import kg.sunrise.hightime.R;
import kg.sunrise.hightime.Api.App;
import kg.sunrise.hightime.Reviews.ReviewOne.ReviewOneActivity;
import kg.sunrise.hightime.Util.Settings;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ReviewTwoActivity extends AppCompatActivity {

    EditText editText;
    Button send;
    TextView textImage, textClock, textCalendar;
    ImageView image;
    Uri uri;
    File fileHome;
    MultipartBody.Part fileToUpload = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme2);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_two);

        send = findViewById(R.id.send);
        editText = findViewById(R.id.editText);
        textImage = findViewById(R.id.textImage);
        textClock = findViewById(R.id.textClock);
        textCalendar = findViewById(R.id.textCalendar);
        image = findViewById(R.id.image);
        TextView textName = findViewById(R.id.textName);
        textName.setText(Settings.getAccessName(ReviewTwoActivity.this));
        String currentTimeString = DateFormat.getTimeInstance().format(new Date());
        textClock.setText(currentTimeString);
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy");
        String date = df.format(Calendar.getInstance().getTime());
        textCalendar.setText(date);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title, text;
                title = Settings.getAccessName(ReviewTwoActivity.this);
                text = editText.getText().toString();
                if (editText.length() == 0){
                    Toast.makeText(ReviewTwoActivity.this, "поле пустое", Toast.LENGTH_LONG).show();
                }
                else {
                    toReview(title, text);
                    Log.e("SENDDATA", "onClick: " + title + text);
                }
            }
        });
    }


    public void onImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(intent, 100);
        Thread logoTimer = new Thread() {
            @Override
            public void run() {
                try {
                    int logoTimer = 0;
                    while (logoTimer < 1000) {
                        sleep(100);
                        logoTimer = logoTimer + 100;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
//                    image.setImageResource(R.drawable.ic_add_a_photo);
                }
            }
        };
        logoTimer.start();
        Glide.with(ReviewTwoActivity.this).load(R.drawable.ic_fotik).into(image);
    }


    public void toReview(String titleDisc, String textDisc) {
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(titleDisc));
        RequestBody text = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(textDisc));
        if (fileHome == null){
            toRev(titleDisc, textDisc);
        }
        else {
            RequestBody thumbnail = RequestBody.create(MediaType.parse("image/*"), fileHome);
            fileToUpload = MultipartBody.Part.createFormData("thumbnail", fileHome.getName(), thumbnail);
            App.getApi().toReview(title, text, fileToUpload).enqueue(new Callback<ReviewTwo>() {
                @Override
                public void onResponse(Call<ReviewTwo> call, Response<ReviewTwo> response) {
                    Log.e("TAG", "response: " + fileToUpload);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ReviewTwoActivity.this)
                                    .setMessage(Html.fromHtml("<font color='#00AEEF'>Отзыв добавлен ✔</font>"));
                            final AlertDialog alert = builder.show();
                            TextView messageText = alert.findViewById(android.R.id.message);
                            assert messageText != null;
                            messageText.setGravity(Gravity.CENTER);
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
                                    startActivity(new Intent(ReviewTwoActivity.this, ReviewOneActivity.class));
                                }
                            };
                            logoTimer.start();

                        }
                    }

                }

                @Override
                public void onFailure(Call<ReviewTwo> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                }

            });
        }
    }
    public void toRev(String titleDisc, String textDisc) {
        RequestBody title = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(titleDisc));
        RequestBody text = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(textDisc));
//        RequestBody thumbnail = RequestBody.create(MediaType.parse("image/*"), fileHome);
//        fileToUpload = MultipartBody.Part.createFormData("thumbnail", fileHome.getName(), thumbnail);
        App.getApi().toRev(title, text).enqueue(new Callback<ReviewTwo>() {
                @Override
                public void onResponse(Call<ReviewTwo> call, Response<ReviewTwo> response) {
                    Log.e("TAG", "response: " + fileToUpload);
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(ReviewTwoActivity.this)
                                    .setMessage(Html.fromHtml("<font color='#00AEEF'>Отзыв добавлен ✔</font>"));
                            final AlertDialog alert = builder.show();
                            TextView messageText = alert.findViewById(android.R.id.message);
                            assert messageText != null;
                            messageText.setGravity(Gravity.CENTER);
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
                                    startActivity(new Intent(ReviewTwoActivity.this, ReviewOneActivity.class));
                                }
                            };
                            logoTimer.start();

                        }
                    }

                }

                @Override
                public void onFailure(Call<ReviewTwo> call, Throwable t) {
                    Log.e("TAG", "onFailure: " + t.getMessage());
                }

            });
    }


    public static Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
    public static void notifySystemLoadImageThumb(Context context, String absolutePath, String fileName) {
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(), absolutePath, fileName, null);//图片插入到系统图库
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + absolutePath)));//通知图库刷新
        Log.e("битмап", "file://" + absolutePath);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == 100) {
            assert data != null;
            uri = data.getData();
            if (data.getData() != null) {
                Log.e("TAG", data.getData() + "");
                InputStream imageStream;
                try {
                    imageStream = getContentResolver().openInputStream(uri);
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                    Log.e("битмап", "" + selectedImage);
                    Uri uri1 = getImageUri(ReviewTwoActivity.this, selectedImage);
                    Log.e("битмап", "" + uri1);

                    String path = getRealPathFromURI(ReviewTwoActivity.this, uri1);
                    fileHome = null;
                    fileHome = new File(path);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                image.setImageResource(R.drawable.ic_add_a_photo);
            }

        }
    }


    public static String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
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

}

