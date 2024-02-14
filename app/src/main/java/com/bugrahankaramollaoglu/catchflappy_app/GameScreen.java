package com.bugrahankaramollaoglu.catchflappy_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bugrahankaramollaoglu.catchflappy_app.databinding.ActivityGameScreenBinding;

import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends AppCompatActivity {

    private ActivityGameScreenBinding binding;

    private ArrayList<ImageView> imageArray = new ArrayList<>();
    private int difficulty = 0;
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

        intent = getIntent();

        if (intent != null && intent.hasExtra("difficulty-level")) {
            difficulty = intent.getIntExtra("difficulty-level", 500);
        } else {
            // Handle the case when the "difficulty-level" extra is not found
        }

        binding.goBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Log.d("mesaj", String.valueOf(difficulty));

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

        new CountDownTimer(15500, 1000) {
            @Override
            public void onFinish() {
                binding.timeCounter.setText("Time: 0");
                handler.removeCallbacks(runnable);

                for (ImageView image : imageArray) {
                    image.setVisibility(View.INVISIBLE);
                }

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

            @Override
            public void onTick(long millisUntilFinished) {
                binding.timeCounter.setText("Time: " + millisUntilFinished / 1000);
            }
        }.start();
    }


    public void increaseScore(View view) {
        currentScore++;
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

}