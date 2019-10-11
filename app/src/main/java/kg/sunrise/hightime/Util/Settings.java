package kg.sunrise.hightime.Util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class Settings {
   //токен
    private static final String ACCESS_TOKEN = "access-token";

    private static final String ACCESS_NAME = "access-name";

    private static final String SETTINGS_STORAGE_NAME = "com.sunrise.hightime";
    //баланс

    private static final String ACCESS_BALANSE = "access-balanse";

    private static final String ACCESS_BNAME = "access-bname";

    private static final String SETTINGS_STORAGE_BNAME = "com.sunrise.hightime";
    //счетp
    private  static final String ACCESS_LS = "access-ls";

    private static final String ACCESS_LSNAME = "access-lsname";

    private static final String SETTINGS_STORAGE_LSNAME = "com.sunrise.hightime";
    //счет


    public static boolean initIntertnet(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        @SuppressLint("MissingPermission") NetworkInfo networkInfo = cm.getActiveNetworkInfo();
//        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }


    public static String getString(Context context, String key, String defValue) {
        return getPrefs(context).getString(key, defValue);
    }
    public static String getString2(Context context, String key, String defValue) {
        return getPrefs2(context).getString(key, defValue);
    }
    public static String getString3(Context context, String key, String defValue) {
        return getPrefs3(context).getString(key, defValue);
    }

    private static SharedPreferences getPrefs(Context context) {
        return context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE);
    }
    private static SharedPreferences getPrefs2(Context context) {
        return context.getSharedPreferences(SETTINGS_STORAGE_BNAME, Context.MODE_PRIVATE);
    }
    private static SharedPreferences getPrefs3(Context context) {
        return context.getSharedPreferences(SETTINGS_STORAGE_LSNAME, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getEditor(Context context) {
        return context.getSharedPreferences(SETTINGS_STORAGE_NAME, Context.MODE_PRIVATE).edit();
    }

    public static String getAccessToken(Context context) {
        return getString(context, ACCESS_TOKEN, "");
    }
    public static String getAccessBalanse(Context context) {
        return getString2(context, ACCESS_BALANSE, "");
    }
    public static String getAccessLs (Context context) {
        return getString3(context, ACCESS_LS, "");
    }

    public static boolean isAuth(Context context) {
        boolean isAuth = false;
        if (getAccessToken(context)==null || getAccessToken(context).isEmpty()){
            isAuth = false;
        }else {
            isAuth = true;
        }
        return isAuth;
    }

    public static String setAccessToken(Context context, String token) {
        getEditor( context).putString(ACCESS_TOKEN, token).commit();
        return token;
    }
    public static void setAccessBalanse(Context context, String balanse){
        getEditor(context).putString(ACCESS_BALANSE, balanse).commit();
    }
    public static void setAccessLs (Context context, String ls){
        getEditor(context).putString(ACCESS_LS, ls).commit();
    }

    public static void deleteToken(Context context) {
        SharedPreferences settings;
        SharedPreferences settings2;
        SharedPreferences settings3;
        SharedPreferences.Editor editor;
        SharedPreferences.Editor editor2;
        SharedPreferences.Editor editor3;
        settings = context.getSharedPreferences(ACCESS_TOKEN, Context.MODE_PRIVATE);
        settings2 = context.getSharedPreferences(ACCESS_BALANSE, Context.MODE_PRIVATE);
        settings3 = context.getSharedPreferences(ACCESS_LS, Context.MODE_PRIVATE);
        editor = settings.edit();
        editor2 = settings2.edit();
        editor3 = settings3.edit();
        editor.remove(ACCESS_TOKEN);
        editor2.remove(ACCESS_BALANSE);
        editor3.remove(ACCESS_LS);
        getPrefs(context).edit().remove("access-token").commit();
        getPrefs2(context).edit().remove("access-balanse").commit();
        getPrefs3(context).edit().remove("access-ls").commit();
    }


    public static String getAccessName(Context context) {
        return getString(context, ACCESS_NAME, "");
    }

    public static void setAccessName(Context context, String token) {
        getEditor( context).putString(ACCESS_NAME, token).commit();
    }
    public static String getAccessBName(Context context) {
        return getString(context, ACCESS_BNAME, "");
    }

    public static void setAccessBName(Context context, String balanse) {
        getEditor( context).putString(ACCESS_BNAME, balanse).commit();
    }
    public static void setAccessLSName(Context context, String ls) {
        getEditor( context).putString(ACCESS_LSNAME, ls).commit();
    }

    public static Object getAccessToken2(Context context) {
        return getString(context, ACCESS_TOKEN, "");
    }
}
