package com.bugrahankaramollaoglu.catchflappy_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bugrahankaramollaoglu.catchflappy_app.databinding.ActivityMainBinding;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class StartScreen extends AppCompatActivity {

    private ActivityMainBinding binding;

    MaterialCardView easyButton;
    MaterialCardView mediumButton;
    MaterialCardView hardButton;

    private String selectedBird = "yellow";

    private ImageView yellowBird;
    private ImageView redBird;
    private ImageView blueBird;

    private RecyclerView recyclerView;
    private List<Integer> scoreList;

    Intent intent;
    int difficulty = 500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.yellowBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ImageView imageView = findViewById(R.id.yellowBird);

                int selectedYellow = R.drawable.yellow_bird_selected;

                if (binding.yellowBird.getDrawable() != null && binding.yellowBird.getDrawable().getConstantState() != null) {
                    if (!binding.yellowBird.getDrawable().getConstantState().equals(getResources().getDrawable(selectedYellow).getConstantState())) {
                        binding.yellowBird.setImageResource(R.drawable.yellow_bird_selected);
                    }
                }
                binding.blueBird.setImageResource(R.drawable.blue_bird);
                binding.redBird.setImageResource(R.drawable.red_bird);
                selectedBird = "yellow";
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
    }

    public void setMedium(View view) {
        difficulty = 500;
        binding.mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.mediumButton.setStrokeWidth(10);
        binding.easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.easyButton.setStrokeWidth(1);
        binding.hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.hardButton.setStrokeWidth(1);

    }

    public void setHard(View view) {
        difficulty = 400;
        binding.hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.hardButton.setStrokeWidth(10);
        binding.easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.easyButton.setStrokeWidth(1);
        binding.mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        binding.mediumButton.setStrokeWidth(1);
    }

    public void startGame(View view) {
        intent = new Intent(StartScreen.this, GameScreen.class);
        intent.putExtra("difficulty-level", difficulty);
        startActivity(intent);
    }
}