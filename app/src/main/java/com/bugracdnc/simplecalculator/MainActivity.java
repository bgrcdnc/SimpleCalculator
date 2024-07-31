package com.bugracdnc.simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    EditText number1Text;
    EditText number2Text;
    TextView resultText;
    HashMap<Integer, Calculation> operationMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        number1Text = findViewById(R.id.number1Input);
        number2Text = findViewById(R.id.number2Input);
        resultText = findViewById(R.id.resultOutput);

        operationMap = new HashMap<>();
        operationMap.put(R.id.sumButton, Integer::sum);
        operationMap.put(R.id.deductButton, (a, b) -> a - b);
        operationMap.put(R.id.multiplyButton, (a, b) -> a * b);
        operationMap.put(R.id.divideButton, (a, b) -> a / b);
    }

    public int[] getValues() {
        int[] values = new int[2];

        values[0] = Integer.parseInt(number1Text.getText().toString());
        values[1] = Integer.parseInt(number2Text.getText().toString());

        return values;
    }

    String performCalculation(int a, int b, Calculation calculation) {
        return String.valueOf(calculation.calculate(a, b));
    }

    public void calculate(View view) {
        int[] values = getValues();
        resultText.setText(performCalculation(values[0], values[1], Objects.requireNonNull(operationMap.get(view.getId()))));
    }
}