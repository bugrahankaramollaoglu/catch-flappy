package com.bugrahankaramollaoglu.catchflappy_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.bugrahankaramollaoglu.catchflappy_app.databinding.ActivityGameScreenBinding;

public class GameScreen extends AppCompatActivity {

    private ActivityGameScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGameScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }

}