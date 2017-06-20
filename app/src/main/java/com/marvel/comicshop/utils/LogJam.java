package com.marvel.comicshop.utils;

import android.util.Log;

import com.marvel.comicshop.BuildConfig;

/**
 * Created by mbenitez
 */
public class LogJam {

    /**
     * Get log tag
     * @param caller From where is being called
     * @return Log tag
     */
    private static String getTag(Object caller) {
        Class aClass = caller instanceof Class ? (Class) caller : caller.getClass();
        return aClass.getSimpleName();
    }

    /**
     * Show log at error level
     * @param caller from where is being called
     * @param t
     * @param message error message
     */
    public static void e(Object caller, Throwable t, String message) {
        e(getTag(caller), t, message);
    }

    /**
     * Show log at warning level
     * @param caller from where is being called
     * @param message error message
     */
    public static void w(Object caller, String message) {
        w(getTag(caller), message);
    }

    /**
     * Show log at debug level
     * @param caller from where is being called
     * @param message error message
     */
    public static void d(Object caller, String message) {
        d(getTag(caller), message);
    }

    /**
     * Show log at verbose level
     * @param caller from where is being called
     * @param message error message
     */
    public static void v(Object caller, String message) {
        v(getTag(caller), message);
    }

    /**
     * Show log at error level
     * @param tag tag of the message
     * @param t
     * @param message error message
     */
    public static void e(String tag, Throwable t, String message) {
        log(Log.ERROR,
                tag,
                message,
                t);
    }

    /**
     * Show log at warning level
     * @param tag tag of the message
     * @param message error message
     */
    public static void w(String tag, String message) {
        log(Log.WARN,
                tag,
                message,
                null);
    }

    /**
     * Show log at debug level
     * @param tag tag of the message
     * @param message error message
     */
    public static void d(String tag, String message) {
        log(Log.DEBUG,
                tag,
                message,
                null);
    }

    /**
     * Show log at verbose level
     * @param tag tag of the message
     * @param message error message
     */
    public static void v(String tag, String message) {
        log(Log.VERBOSE,
                tag,
                message,
                null);
    }

    /**
     * Show log message
     * @param priority priority level
     * @param tag tag of the message
     * @param message error message
     * @param throwable
     */
    private static void log(int priority, String tag, String message, Throwable throwable) {
        if (BuildConfig.DEBUG) {
            tag = "LogJam *** " + tag;
            if (throwable != null) {
                Log.println(priority, tag, message + "\n" + throwable);
                throwable.printStackTrace();
            } else if (message != null) {
                Log.println(priority, tag, message.toString());
            }
        }
    }

}
