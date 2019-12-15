package me.cangming.androidforipv6.util;

import android.util.Log;

/**
 * @date 创建时间：2019-12-15
 * @auther cangming
 * @Description 日志工具类
 */
public class LogUtil {

    public static void i(String tag, String s) {
        Log.i(tag, s);
    }

    public static void d(String tag, String s) {
        Log.d(tag, s);
    }

    public static void e(String tag, String s) {
        Log.e(tag, s);
    }
}
