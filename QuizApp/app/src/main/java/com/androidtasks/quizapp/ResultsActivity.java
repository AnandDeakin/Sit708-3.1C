package com.androidtasks.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity {
    private TextView congratsTextView;
    private TextView scoreTextView;
    private Button newQuizButton;
    private Button finishButton;
    private String playerName;
    private static final String PREFS_NAME = "QuizAppPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize UI elements
        congratsTextView = findViewById(R.id.textViewCongrats);
        scoreTextView = findViewById(R.id.textViewScore);
        newQuizButton = findViewById(R.id.buttonNewQuiz);
        finishButton = findViewById(R.id.buttonFinish);

        // Get score and player name
        Intent intent = getIntent();
        int score = intent.getIntExtra("SCORE", 0);
        int total = intent.getIntExtra("TOTAL", 0);
        
        // Get player name from SharedPreferences to ensure consistency
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        playerName = prefs.getString(MainActivity.PLAYER_NAME, "Player");

        // Display personalized congratulations and score
        congratsTextView.setText(String.format("Congratulations, %s!", playerName));
        scoreTextView.setText(String.format("YOUR SCORE:\n%d/%d", score, total));

        // Set up new quiz button click listener
        newQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start a new quiz
                Intent mainIntent = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        });

        // Set up finish button click listener
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });
    }
}