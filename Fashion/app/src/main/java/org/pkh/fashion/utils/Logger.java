package org.pkh.fashion.utils;

import android.util.Log;

/**
 * All type of logs will be handled for debugging
 * Note: For release apk, disable the logs
 */
public class Logger {
    /**
     * Debug object to display log in console
     */
    public static boolean DEBUG = true;

    /**
     * Error log for showing exception
     * @param str : Information to be displayed
     */
    public static void logE(String str){
        if(DEBUG) {
            Log.e(FashionUtil.TAG ,"Error:"+ str);
        }
    }
    /**
     * Debug log for debugging the app
     * @param str : Information to be displayed
     */
    public static void logD(String str){
        if(DEBUG) {
            Log.d(FashionUtil.TAG ,"Debug:"+ str);
        }
    }
    /**
     * Info log for debugging the app
     * @param str : Information to be displayed
     */
    public static void logI(String str){
        if(DEBUG) {
            Log.i(FashionUtil.TAG ,"Debug:"+ str);
        }
    }
    /**
     * Warning log for showing exception
     * @param str : Information to be displayed
     */
    public static void logW(String str){
        if(DEBUG) {
            Log.w(FashionUtil.TAG ,"Warn:"+ str);
        }
    }
}
