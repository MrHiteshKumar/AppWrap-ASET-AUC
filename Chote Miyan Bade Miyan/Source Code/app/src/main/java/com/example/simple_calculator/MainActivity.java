package com.example.simple_calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;
    private EditText output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        output = findViewById(R.id.outpit);
        output.setShowSoftInputOnFocus(false);

        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())) {
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if (getString(R.string.display).equals(display.getText().toString())) {
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
    }

    public void zeroBTN(View view) {
        updateText("0");
    }
    public void oneBTN(View view) {
        updateText("1");
    }
    public void twoBTN(View view) {
        updateText("2");
    }
    public void threeBTN(View view) {
        updateText("3");
    }
    public void fourBTN(View view) {
        updateText("4");
    }
    public void fiveBTN(View view) {
        updateText("5");
    }
    public void sixBTN(View view) {
        updateText("6");
    }
    public void sevenBTN(View view) {
        updateText("7");
    }
    public void eightBTN(View view) {
        updateText("8");
    }
    public void nineBTN(View view) {
        updateText("9");
    }
    public void clearBTN(View view) {
        display.setText("");
        output.setText("");
    }
    public void exponentBTN(View view) {
        updateText("^");
    }
    public void parenthesisBTN(View view) {
        int cursorPos = display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i = 0; i < cursorPos; i++) {
            if (display.getText().toString().substring(i, i+1).equals("(")) {
                openPar += 1;
            }
            if (display.getText().toString().substring(i, i+1).equals(")")) {
                closePar += 1;
            }
        }

        if (openPar == closePar || display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if (closePar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")) {
            updateText(")");
        }
        display.setSelection(cursorPos + 1);
    }
    public void divideBTN(View view) {
        updateText("÷");
    }
    public void multiplyBTN(View view) {
        updateText("×");
    }
    public void addBTN(View view) {
        updateText("+");
    }
    public void subtractBTN(View view) {
        updateText("-");
    }
    public void plusMinusBTN(View view) {
        updateText("-");
    }
    public void pointBTN(View view) {
        updateText(".");
    }
    public void equalsBTN(View view) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        output.setText(" = " + result);
        output.setSelection(result.length());
    }

    public void backspace(View view) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

}