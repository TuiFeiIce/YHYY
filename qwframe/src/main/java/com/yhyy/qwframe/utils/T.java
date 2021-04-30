package com.yhyy.qwframe.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by IceWolf on 2019/9/20.
 */
public class T {
    public static void ts(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_SHORT).show();
    }

    public static void tl(Context context, String message) {
        Toast.makeText(context, message + "", Toast.LENGTH_LONG).show();
    }
}
