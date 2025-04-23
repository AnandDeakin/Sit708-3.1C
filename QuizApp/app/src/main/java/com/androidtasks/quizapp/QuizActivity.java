package com.androidtasks.quizapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.radiobutton.MaterialRadioButton;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    private TextView questionTextView;
    private MaterialButton submitButton;
    private MaterialButton nextButton;
    private LinearProgressIndicator progressBar;
    private TextView welcomeTextView;
    
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private String playerName;
    private MaterialButton[] answerButtons;
    private int selectedAnswerIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Get player name from intent
        Intent intent = getIntent();
        playerName = intent.getStringExtra(MainActivity.PLAYER_NAME);

        // Initialize UI elements
        questionTextView = findViewById(R.id.textViewQuestion);
        submitButton = findViewById(R.id.buttonSubmit);
        nextButton = findViewById(R.id.buttonNext);
        progressBar = findViewById(R.id.progressBar);
        welcomeTextView = findViewById(R.id.textViewWelcome);

        // Initialize answer buttons
        answerButtons = new MaterialButton[4];
        answerButtons[0] = findViewById(R.id.buttonAnswer1);
        answerButtons[1] = findViewById(R.id.buttonAnswer2);
        answerButtons[2] = findViewById(R.id.buttonAnswer3);
        answerButtons[3] = findViewById(R.id.buttonAnswer4);

        // Set up answer button click listeners
        for (int i = 0; i < answerButtons.length; i++) {
            final int index = i;
            answerButtons[i].setOnClickListener(v -> handleAnswerSelection(index));
        }

        // Display welcome message
        welcomeTextView.setText("Welcome, " + playerName + "!");

        // Initialize quiz questions from QuizQuestions class
        questions = QuizQuestions.getRandomQuestions(5);
        progressBar.setMax(questions.size());

        // Set up the first question
        displayQuestion();

        // Set up submit button click listener
        submitButton.setOnClickListener(v -> handleSubmission());

        // Set up next button click listener
        nextButton.setOnClickListener(v -> moveToNextQuestion());
    }

    private void handleAnswerSelection(int index) {
        // Reset all buttons to default state
        for (MaterialButton button : answerButtons) {
            button.setStrokeColor(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.default_button_stroke)));
        }

        // Highlight selected button
        answerButtons[index].setStrokeColor(ColorStateList.valueOf(
            ContextCompat.getColor(this, R.color.selected_button_stroke)));
        selectedAnswerIndex = index;
    }

    private void handleSubmission() {
        if (selectedAnswerIndex == -1) {
            Toast.makeText(this, "Please select an answer", Toast.LENGTH_SHORT).show();
            return;
        }

        // Disable answer buttons
        for (MaterialButton button : answerButtons) {
            button.setEnabled(false);
        }

        // Get correct answer
        int correctAnswerIndex = questions.get(currentQuestionIndex).getCorrectAnswerIndex();

        // Show correct and incorrect answers
        for (int i = 0; i < answerButtons.length; i++) {
            MaterialButton button = answerButtons[i];
            
            if (i == correctAnswerIndex) {
                // Correct answer - show green
                button.setBackgroundTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.correct_answer)));
                button.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            } 
            else if (i == selectedAnswerIndex) {
                // Selected wrong answer - show red
                button.setBackgroundTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(this, R.color.wrong_answer)));
                button.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            }
        }

        // Update score if correct
        if (selectedAnswerIndex == correctAnswerIndex) {
            score++;
        }

        // Show next button and hide submit
        submitButton.setVisibility(View.GONE);
        nextButton.setVisibility(View.VISIBLE);
    }

    private void moveToNextQuestion() {
        currentQuestionIndex++;
        
        if (currentQuestionIndex < questions.size()) {
            displayQuestion();
        } else {
            showResults();
        }
    }

    private void displayQuestion() {
        // Reset UI elements
        selectedAnswerIndex = -1;
        submitButton.setVisibility(View.VISIBLE);
        nextButton.setVisibility(View.GONE);

        // Reset answer buttons
        for (MaterialButton button : answerButtons) {
            button.setEnabled(true);
            button.setBackgroundTintList(ColorStateList.valueOf(
                ContextCompat.getColor(this, android.R.color.white)));
            button.setTextColor(ContextCompat.getColor(this, R.color.default_text));
            button.setStrokeColor(ColorStateList.valueOf(
                ContextCompat.getColor(this, R.color.default_button_stroke)));
        }

        // Set question text and options
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionTextView.setText(currentQuestion.getQuestionText());
        
        String[] options = currentQuestion.getOptions();
        for (int i = 0; i < answerButtons.length; i++) {
            answerButtons[i].setText(options[i]);
        }

        // Update progress
        progressBar.setProgress(currentQuestionIndex + 1);
    }

    private void showResults() {
        Intent resultsIntent = new Intent(QuizActivity.this, ResultsActivity.class);
        resultsIntent.putExtra("SCORE", score);
        resultsIntent.putExtra("TOTAL", questions.size());
        resultsIntent.putExtra(MainActivity.PLAYER_NAME, playerName);
        startActivity(resultsIntent);
        finish();
    }
}