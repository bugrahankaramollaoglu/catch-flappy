package com.bugrahankaramollaoglu.catchflappy_app;

import static com.bugrahankaramollaoglu.catchflappy_app.VibrationHelper.vibrate;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bugrahankaramollaoglu.catchflappy_app.databinding.ActivityGameScreenBinding;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AppCompatActivity {

    private ActivityGameScreenBinding binding;
    private SoundPool soundPool;
    private int soundID;


    private String selectedBird = "yellow";
    private int time = 15;
    private boolean isVibration = true;
    private boolean isSound = true;
    int difficulty = 500;

    private ArrayList<ImageView> imageArray = new ArrayList<>();
    Intent intent;
    private int currentScore = 0;
    private Runnable runnable = () -> {

    };
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_GAME)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        } else {
            soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        }
        soundID = soundPool.load(this, R.raw.ding, 1);

        intent = getIntent();

        if (intent != null && intent.hasExtra("difficulty-level")) {
            difficulty = intent.getIntExtra("difficulty-level", 500);
            selectedBird = intent.getStringExtra("chosen-bird");
            time = intent.getIntExtra("selected-time", 15);
            isVibration = intent.getBooleanExtra("vibration-status", true);
            isSound = intent.getBooleanExtra("sound-status", true);
        } else {
            // Handle the case when the "difficulty-level" extra is not found
        }

        binding.goBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        int[] birdImageViews = {R.id.bird1, R.id.bird2, R.id.bird3, R.id.bird4, R.id.bird5, R.id.bird6,
                R.id.bird7,
                R.id.bird8,
                R.id.bird9,
                R.id.bird10,
                R.id.bird11,
                R.id.bird12,
                R.id.bird13,
                R.id.bird14,
                R.id.bird15,
                R.id.bird16,
                R.id.bird17,
                R.id.bird18,
                R.id.bird19
        };

        int birdDrawableResource = 0;

        switch (selectedBird) {
            case "red":
                birdDrawableResource = R.drawable.red_bird;
                break;
            case "blue":
                birdDrawableResource = R.drawable.blue_bird;
                break;
            case "yellow":
                birdDrawableResource = R.drawable.yellow_bird;
                break;
        }

        for (int birdImageViewId : birdImageViews) {
            ImageView imageView = findViewById(birdImageViewId);
            if (imageView != null) {
                imageView.setImageResource(birdDrawableResource);
            }
        }


        imageArray.add(binding.bird1);
        imageArray.add(binding.bird2);
        imageArray.add(binding.bird3);
        imageArray.add(binding.bird4);
        imageArray.add(binding.bird5);
        imageArray.add(binding.bird6);
        imageArray.add(binding.bird7);
        imageArray.add(binding.bird8);
        imageArray.add(binding.bird9);
        imageArray.add(binding.bird10);
        imageArray.add(binding.bird11);
        imageArray.add(binding.bird12);
        imageArray.add(binding.bird13);
        imageArray.add(binding.bird14);
        imageArray.add(binding.bird15);
        imageArray.add(binding.bird16);
        imageArray.add(binding.bird17);
        imageArray.add(binding.bird18);
        imageArray.add(binding.bird19);
        imageArray.add(binding.bird20);

        hideImages();

        new CountDownTimer(time * 1000L, 1000) {
            @Override
            public void onFinish() {
                binding.timeCounter.setText("Time: 0");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                if (!isFinishing()) {
                    AlertDialog.Builder alert = new AlertDialog.Builder(GameScreen.this);
                    alert.setTitle("Game Over");
                    alert.setMessage("Restart The Game?");
                    alert.setPositiveButton("Yes", (dialog, which) -> {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    });

                    alert.setNegativeButton("No", (dialog, which) -> {
                        finish();
                    });

                    alert.show();
                }
            }


            @Override
            public void onTick(long millisUntilFinished) {
                binding.timeCounter.setText("Time: " + millisUntilFinished / 1000);
            }
        }.start();
    }


    public void increaseScore(View view) {
        currentScore++;
        if (isVibration) {
            vibrate(this, 80);
        }
        if (isSound) {
            soundPool.play(soundID, 1, 1, 1, 0, 1);
        }

        binding.scoreCounter.setText("Score: " + currentScore);
    }


    public void hideImages() {
        runnable = new Runnable() {
            @Override
            public void run() {
                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

                Random random = new Random();
                int randomIndex = random.nextInt(9);
                imageArray.get(randomIndex).setVisibility(View.VISIBLE);

                handler.postDelayed(runnable, difficulty);
            }
        };

        handler.post(runnable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (soundPool != null) {
            soundPool.release();
            soundPool = null;
        }
    }
}