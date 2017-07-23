package com.gugutian.wifiadb.util;

import android.util.Log;

import java.io.File;

public class RootUtils {
    private static final String TAG = "RootUtils";

    public static boolean isDevicesSystemRoot() {
        return checkRootMethod1() || checkRootMethod2() || checkRootMethod4();
    }

    private static  boolean checkRootMethod1(){
        String buildTags = android.os.Build.TAGS;

        if (buildTags != null && buildTags.contains("test-keys")) {
            Log.d(TAG, "buildTags.contains test-keys is true,buildTags = "+buildTags);
            return true;
        }
        return false;
    }

    private static boolean checkRootMethod2(){
        try {
            File file = new File("/system/app/Superuser.apk");
            if (file.exists()) {
                Log.d(TAG, "/system/app/Superuser.apk is exist");
                return true;
            }
        } catch (Exception e) {
            Log.d(TAG, Log.getStackTraceString(e));
        }

        return false;
    }

    private static boolean checkRootMethod4() {
        File f;
        final String kSuSearchPaths[] = {"/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        try {
            for (String kSuSearchPath : kSuSearchPaths) {
                f = new File(kSuSearchPath + "su");
                if (f.exists()) {
                    return true;
                }
            }
        } catch (Exception e) {
            Log.d(TAG, Log.getStackTraceString(e));
        }

        return false;
    }
}
