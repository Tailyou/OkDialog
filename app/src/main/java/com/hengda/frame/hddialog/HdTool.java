package com.hengda.frame.hddialog;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.widget.Toast;

import java.util.Locale;


public class HdTool {

    public static void showShort(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showShort(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLong(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showLong(Context context, int msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static <E extends View> E getView(View view, int id) {
        try {
            return (E) view.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    public static <E> E getEntity(Intent intent, String key) {
        try {
            return (E) intent.getExtras().get(key);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

}
