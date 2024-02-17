package com.bugrahankaramollaoglu.catchflappy_app;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundPlayer {
    private static MediaPlayer mediaPlayer;

    public static void playSound(Context context, int soundResourceId) {
        mediaPlayer = MediaPlayer.create(context, soundResourceId);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                mediaPlayer.release();
            }
        });
        mediaPlayer.start();
    }
}