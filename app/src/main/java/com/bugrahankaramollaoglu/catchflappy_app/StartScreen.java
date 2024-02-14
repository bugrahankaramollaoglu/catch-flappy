package com.bugrahankaramollaoglu.catchflappy_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class StartScreen extends AppCompatActivity {
    MaterialCardView easyButton;
    MaterialCardView mediumButton;
    MaterialCardView hardButton;

    private RecyclerView recyclerView;
    private ScoreAdapter adapter;
    private List<Integer> scoreList;

    Intent intent;
    int difficulty = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        easyButton = findViewById(R.id.easyButton);
        mediumButton = findViewById(R.id.mediumButton);
        hardButton = findViewById(R.id.hardButton);

        recyclerView = findViewById(R.id.recycler_view);
        scoreList = new ArrayList<>();
        scoreList.add(10);
        scoreList.add(8);
        scoreList.add(9);

        adapter = new ScoreAdapter(scoreList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    public void setEasy(View view) {
        difficulty = 600;
        easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.color1));
        easyButton.setStrokeWidth(10);
        mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        mediumButton.setStrokeWidth(1);
        hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        hardButton.setStrokeWidth(1);
    }

    public void setMedium(View view) {
        difficulty = 500;
        mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.color1));
        mediumButton.setStrokeWidth(10);
        easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        easyButton.setStrokeWidth(1);
        hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        hardButton.setStrokeWidth(1);

    }

    public void setHard(View view) {
        difficulty = 400;
        hardButton.setStrokeColor(ContextCompat.getColor(this, R.color.color1));
        hardButton.setStrokeWidth(10);
        easyButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        easyButton.setStrokeWidth(1);
        mediumButton.setStrokeColor(ContextCompat.getColor(this, R.color.black));
        mediumButton.setStrokeWidth(1);
    }

    public void startGame(View view) {
        intent = new Intent(StartScreen.this, GameScreen.class);
        intent.putExtra("difficulty-level", difficulty);
        startActivity(intent);
    }
}