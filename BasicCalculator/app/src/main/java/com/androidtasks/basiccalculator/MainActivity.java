package com.androidtasks.basiccalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText firstNumberEditText;
    private EditText secondNumberEditText;
    private Button addButton;
    private Button subtractButton;
    private Button clearButton;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        firstNumberEditText = findViewById(R.id.editTextFirstNumber);
        secondNumberEditText = findViewById(R.id.editTextSecondNumber);
        addButton = findViewById(R.id.buttonAdd);
        subtractButton = findViewById(R.id.buttonSubtract);
        clearButton = findViewById(R.id.buttonClear);
        resultTextView = findViewById(R.id.textViewResult);

        // Set up add button click listener
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult(OperationType.ADDITION);
            }
        });

        // Set up subtract button click listener
        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateResult(OperationType.SUBTRACTION);
            }
        });
        // Set up clear button click listener
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }
    
    // Enum for operation types
    private enum OperationType {
        ADDITION,
        SUBTRACTION
    }

    @SuppressLint("DefaultLocale")
    private void calculateResult(OperationType operation) {
        try {
            // Get input values
            String firstInput = firstNumberEditText.getText().toString();
            String secondInput = secondNumberEditText.getText().toString();
            
            // Check if inputs are empty
            if (firstInput.isEmpty() || secondInput.isEmpty()) {
                Toast.makeText(this, "Please enter both numbers", Toast.LENGTH_SHORT).show();
                return;
            }
            
            double firstNumber = Double.parseDouble(firstInput);
            double secondNumber = Double.parseDouble(secondInput);
            
            // Calculate result based on operation type
            double result;
            String operationText;
            
            switch (operation) {
                case ADDITION:
                    result = firstNumber + secondNumber;
                    operationText = "+";
                    break;
                case SUBTRACTION:
                    result = firstNumber - secondNumber;
                    operationText = "-";
                    break;
                default:
                    return;
            }
            
            // Display result with the operation performed
            resultTextView.setText(String.format("%s %s %s = %s",
                                  firstNumber,
                                  operationText, 
                                  secondNumber,
                                  result));
                                  
        } catch (NumberFormatException e) {
            // Handle invalid input
            Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show();
        }
    }

    // Clear all input fields and result
    @SuppressLint("SetTextI18n")
    private void clearFields() {
        firstNumberEditText.setText("");
        secondNumberEditText.setText("");
        resultTextView.setText("Result: ");
        firstNumberEditText.requestFocus();
    }
}
