package com.geeks.calculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    private boolean isMultiplication;
    private boolean isPlus;
    private boolean containsDot;
    private boolean isMinus;
    private boolean isDivision;

    private TextView textView;
    private int a, b;
    private boolean isOperationClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textView = findViewById(R.id.text_view);

    }

    public void onNumberClick(View view) {
        String text = ((MaterialButton) view).getText().toString();
        if (text.equals("AC")) {
            textView.setText("0");
            a = 0;
            b = 0;
        } else if (textView.getText().toString().equals("0") || isOperationClick) {
            textView.setText(text);
        } else if(text.contains(".") || containsDot) {
            textView.append(".");
        } else {
            textView.append(text);
        }
        containsDot = false;
        isOperationClick = false;
    }

    public void onOperationClick(View view) {
        if (view.getId() == R.id.btn_plus) {
            isPlus = true;
            isDivision = false;
            isMinus = false;
            isMultiplication = false;
            a = Integer.parseInt(textView.getText().toString());
        } else if (view.getId() == R.id.btn_equal && isMinus == false && isDivision == false && isMultiplication == false) {
            b = Integer.parseInt(textView.getText().toString());
            int result = a + b;
            textView.setText(String.valueOf(result));
        }
        if (view.getId() == R.id.btn_minus) {
            isMultiplication = false;
            isPlus = false;
            isDivision = false;
            isMinus = true;
            a = Integer.parseInt(textView.getText().toString());
        } else if (view.getId() == R.id.btn_equal && isDivision == false && isPlus == false && isMultiplication == false) {
            b = Integer.parseInt(textView.getText().toString());
            int result = a - b;
            textView.setText(String.valueOf(result));
        }
        if (view.getId() == R.id.btn_division) {
            isDivision = true;
            isMinus = false;
            isPlus = false;
            isMultiplication = false;
            a = Integer.parseInt(textView.getText().toString());
        } else if (view.getId() == R.id.btn_equal && isMinus == false && isPlus == false && isMultiplication == false) {
            b = Integer.parseInt(textView.getText().toString());
            double result = a / b;
            textView.setText(String.valueOf(result));
        }
        if(view.getId() == R.id.btn_multiplication) {
            isDivision = false;
            isMinus = false;
            isPlus = false;
            isMultiplication = true;
            a = Integer.parseInt(textView.getText().toString());
        } else if (view.getId() == R.id.btn_equal && isMinus == false && isPlus == false && isDivision == false){
            b = Integer.parseInt(textView.getText().toString());
            double result = a * b;
            textView.setText(String.valueOf(result));
        }
        isOperationClick = true;
    }
}

