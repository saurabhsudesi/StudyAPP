package com.example.demo.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.CheckBox;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.BATTERY_SERVICE;

public class RequiredFunction {
    public boolean isConnected(Activity a) {
        NetworkInfo networkInfo;
        try {
            ConnectivityManager connMgr = (ConnectivityManager) a.getSystemService(Activity.CONNECTIVITY_SERVICE);
            networkInfo = connMgr.getActiveNetworkInfo();

            //Toast.makeText(a, ""+networkInfo, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            networkInfo = null;
        }
        if (networkInfo != null && networkInfo.isConnected())
            return true;

        else
            return false;

    }
    //validation functions
    public boolean validEmail(String email) {
        if (!email.isEmpty() && email != null) {
            if (email.contains("@")) {
                return true;
            }
        }
        return false;
    }
    public boolean validContact(String contact) {

        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(contact);
        return (m.find() && m.group().equals(contact));
    }

}