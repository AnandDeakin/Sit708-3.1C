package com.androidtasks.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText nameEditText;
    private Button startButton;
    public static final String PLAYER_NAME = "player_name";
    private static final String PREFS_NAME = "QuizAppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        nameEditText = findViewById(R.id.editTextName);
        startButton = findViewById(R.id.buttonStart);

        // Get player name from SharedPreferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedPlayerName = prefs.getString(PLAYER_NAME, "");
        
        if (!savedPlayerName.isEmpty()) {
            nameEditText.setText(savedPlayerName);
        }

        // Set up start button click listener
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String playerName = nameEditText.getText().toString().trim();
                if (playerName.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                } else {
                    // Save player name to SharedPreferences
                    SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(PLAYER_NAME, playerName);
                    editor.apply();

                    // Start the quiz activity
                    Intent quizIntent = new Intent(MainActivity.this, QuizActivity.class);
                    quizIntent.putExtra(PLAYER_NAME, playerName);
                    startActivity(quizIntent);
                }
            }
        });
    }
}