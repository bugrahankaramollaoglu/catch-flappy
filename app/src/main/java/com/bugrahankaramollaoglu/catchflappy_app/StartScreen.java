package com.bugrahankaramollaoglu.catchflappy_app;

import static com.bugrahankaramollaoglu.catchflappy_app.VibrationHelper.vibrate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bugrahankaramollaoglu.catchflappy_app.databinding.ActivityMainBinding;

public class StartScreen extends AppCompatActivity {

    private ActivityMainBinding binding;

    private String selectedBird = "yellow";
    private int time = 15;
    private boolean isVibration = true;
    private boolean isSound = true;
    int difficulty = 500;

    private SoundPool soundPool;
    private int soundID;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences sharedPreferences = getSharedPreferences("BEST_SCORE_SHARED", Context.MODE_PRIVATE);

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

        binding.birdLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("mesaj", difficulty + "\n" + selectedBird + "\n" + time + "\n" + isVibration + " " + isSound);
            }
        });


        binding.yellowBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImageView imageView = findViewById(R.id.yellowBird);

                int selectedYellow = R.drawable.yellow_bird_selected;

                if (imageView.getDrawable() != null && binding.yellowBird.getDrawable().getConstantState() != null) {
                    if (!imageView.getDrawable().getConstantState().equals(getResources().getDrawable(selectedYellow).getConstantState())) {
                        imageView.setImageResource(R.drawable.yellow_bird_selected);
                    }
                }
                binding.blueBird.setImageResource(R.drawable.blue_bird);
                binding.redBird.setImageResource(R.drawable.red_bird);
                selectedBird = "yellow";

                if (isVibration) {
                    vibrate(getApplicationContext(), 80);
                }
            }
        });

        binding.redBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImageView imageView = findViewById(R.id.redBird);

                int selectedRed = R.drawable.red_bird_selected;

                if (imageView.getDrawable() != null && imageView.getDrawable().getConstantState() != null) {
                    if (!imageView.getDrawable().getConstantState().equals(getResources().getDrawable(selectedRed).getConstantState())) {
                        imageView.setImageResource(R.drawable.red_bird_selected);
                    }
                }
                binding.yellowBird.setImageResource(R.drawable.yellow_bird);
                binding.blueBird.setImageResource(R.drawable.blue_bird);
                selectedBird = "red";

                if (isVibration) {
                    vibrate(getApplicationContext(), 80);
                }
            }
        });

        binding.blueBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView = findViewById(R.id.blueBird);

                int selectedBlue = R.drawable.yellow_bird_selected;

                if (imageView.getDrawable() != null && imageView.getDrawable().getConstantState() != null) {
                    if (!imageView.getDrawable().getConstantState().equals(getResources().getDrawable(selectedBlue).getConstantState())) {
                        imageView.setImageResource(R.drawable.blue_bird_selected);
                    }
                }
                binding.yellowBird.setImageResource(R.drawable.yellow_bird);
                binding.redBird.setImageResource(R.drawable.red_bird);
                selectedBird = "blue";

                if (isVibration) {
                    vibrate(getApplicationContext(), 80);
                }
            }
        });

        binding.vibration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView = findViewById(R.id.vibrationImage);


                if (isVibration == true) {
                    imageView.setImageResource(R.drawable.non_vibration_light);
                    isVibration = false;
                } else if (isVibration == false) {
                    imageView.setImageResource(R.drawable.vibration_light);
                    isVibration = true;
                    vibrate(getApplicationContext(), 80);
                }

            }
        });


        binding.sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ImageView imageView = findViewById(R.id.soundImage);


                if (isSound == true) {
                    imageView.setImageResource(R.drawable.non_bell);
                    isSound = false;
                } else if (isSound == false) {
                    imageView.setImageResource(R.drawable.bell);
                    isSound = true;
                    soundPool.play(soundID, 1, 1, 1, 0, 1);
                }

            }
        });

        binding.score.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int best_score = sharedPreferences.getInt("best-score", 0);
                Toast.makeText(getApplicationContext(), "BEST SCORE: " + best_score + "!", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void setEasy(View view) {
        difficulty = 600;
        binding.easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.easyButton.setStrokeWidth(10);
        binding.mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.mediumButton.setStrokeWidth(1);
        binding.hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.hardButton.setStrokeWidth(1);

        if (isVibration) {
            vibrate(getApplicationContext(), 80);
        }
    }

    public void setMedium(View view) {
        difficulty = 500;
        binding.mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.mediumButton.setStrokeWidth(10);
        binding.easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.easyButton.setStrokeWidth(1);
        binding.hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.hardButton.setStrokeWidth(1);

        if (isVibration) {
            vibrate(getApplicationContext(), 80);
        }

    }

    public void setHard(View view) {
        difficulty = 400;
        binding.hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.hardButton.setStrokeWidth(10);
        binding.easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.easyButton.setStrokeWidth(1);
        binding.mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.mediumButton.setStrokeWidth(1);

        if (isVibration) {
            vibrate(getApplicationContext(), 80);
        }
    }

    public void startGame(View view) {
        intent = new Intent(StartScreen.this, GameScreen.class);
        intent.putExtra("difficulty-level", difficulty);
        intent.putExtra("chosen-bird", selectedBird);
        intent.putExtra("selected-time", time);
        intent.putExtra("vibration-status", isVibration);
        intent.putExtra("sound-status", isSound);
        startActivity(intent);
    }

    public void set10(View view) {
        time = 10;
        binding.sec10.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec10.setStrokeWidth(10);
        binding.sec15.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec15.setStrokeWidth(1);
        binding.sec30.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec30.setStrokeWidth(1);

        if (isVibration) {
            vibrate(getApplicationContext(), 80);
        }
    }

    public void set15(View view) {
        time = 15;
        binding.sec10.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec10.setStrokeWidth(1);
        binding.sec15.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec15.setStrokeWidth(10);
        binding.sec30.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec30.setStrokeWidth(1);

        if (isVibration) {
            vibrate(getApplicationContext(), 80);
        }
    }

    public void set30(View view) {
        time = 30;
        binding.sec10.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec10.setStrokeWidth(1);
        binding.sec15.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec15.setStrokeWidth(1);
        binding.sec30.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.sec30.setStrokeWidth(10);

        if (isVibration) {
            vibrate(getApplicationContext(), 80);
        }
    }
}