package com.bugrahankaramollaoglu.catchflappy_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class GameScreen extends AppCompatActivity {
    ImageView birdImage;

    SharedPreferences sharedPreferences;

    TextView timeText;
    TextView scoreText;
    TextView difficultyText;

    // you cannot use the same handler/runnable for two thread-needing jobs
    Handler timeHandler;
    Handler birdHandler;
    Runnable timeRunnable;
    Runnable birdRunnable;

    int timeCount;
    int scoreCount;

    Intent intent;

    int difficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        timeCount = -1;
        scoreCount = 0;

        timeText = findViewById(R.id.timeCounter);
        scoreText = findViewById(R.id.scoreCounter);
        birdImage = findViewById(R.id.flappy);

        intent = getIntent();
        difficulty = intent.getIntExtra("difficulty-level", 2000);
        difficultyText = findViewById(R.id.difficultyText);
        switch (difficulty) {
            case 400:
                difficultyText.setText("HARD");
                break;
            case 600:
                difficultyText.setText("MEDIUM");
                break;
            case 800:
                difficultyText.setText("EASY");
                break;
        }

        sharedPreferences = this.getSharedPreferences("com.bugrahankaramollaoglu.catchflappy_app", Context.MODE_PRIVATE);

        setTimeCount(null);
        startUpdatingBirdPosition();
    }

    public void setTimeCount(View view) {
        timeHandler = new Handler();
        timeRunnable = new Runnable() {
            @Override
            public void run() {
                timeCount++;
                timeText.setText("Time: " + timeCount);
                timeHandler.postDelayed(timeRunnable, 1000);
            }
        };
        timeHandler.post(timeRunnable);
    }

    public void updateBirdPosition() {
        ConstraintLayout.LayoutParams layoutParams;
        layoutParams = (ConstraintLayout.LayoutParams) birdImage.getLayoutParams();

        // Get the dimensions of the screen
        int screenWidth = getWindow().getDecorView().getWidth();
        int screenHeight = getWindow().getDecorView().getHeight();

        // Get the dimensions of the bird image
        int birdWidth = birdImage.getWidth();
        int birdHeight = birdImage.getHeight();

        // Calculate the maximum X and Y positions to stay within screen bounds
        int maxX = screenWidth - birdWidth;
        int maxY = screenHeight - birdHeight;

        // Set random X and Y positions within the constraints of your layout
        int randomX = (int) (Math.random() * maxX);
        int randomY = (int) (Math.random() * maxY);

        layoutParams.leftMargin = randomX;
        layoutParams.topMargin = randomY;

        birdImage.setLayoutParams(layoutParams);
    }

    public void startUpdatingBirdPosition() {
        birdHandler = new Handler();
        birdRunnable = new Runnable() {
            @Override
            public void run() {
                updateBirdPosition();

                birdHandler.postDelayed(birdRunnable, difficulty); // Update every 2 seconds
            }
        };
        birdHandler.post(birdRunnable);
    }

    public void finishGame() {

    }

    public void onImageClick(View view) {
        if (scoreCount <= 2) {
            scoreCount++;
            scoreText.setText("Score: " + scoreCount);
        } else if (scoreCount == 3) {
            finishGame();
        }
        // when clicked, reposition the bird immediately
        updateBirdPosition();

        // Delay the reset of isBirdMoving to allow the bird to move again after a short pause
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, difficulty); // Adjust the delay as needed
    }
}