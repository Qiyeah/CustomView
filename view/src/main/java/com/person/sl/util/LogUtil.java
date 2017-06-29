package com.person.sl.util;

import android.util.Log;

/**
 * Created by sl on 2017/6/22.
 */

public class LogUtil {
    private static final String TAG = "Test";
    private static boolean flag = true;
    public static void e(Object msg) {
        if (flag){
            Log.e(TAG, "e: "+msg);
        }
    }

}
