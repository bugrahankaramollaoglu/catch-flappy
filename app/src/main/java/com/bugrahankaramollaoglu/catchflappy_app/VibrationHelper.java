package com.bugrahankaramollaoglu.catchflappy_app;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;

public class VibrationHelper {

    // Vibrate for a specified amount of milliseconds
    public static void vibrate(Context context, long milliseconds) {
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(milliseconds, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                // Deprecated in API 26
                vibrator.vibrate(milliseconds);
            }
        }
    }
}
