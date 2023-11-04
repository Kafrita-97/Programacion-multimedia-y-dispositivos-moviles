package com.example.calculadora_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonClear, buttonBracket, buttonPercent, buttonDiv, buttonMult, buttonRest, buttonSum, buttonResult, buttonDel, buttonDot;
    String operation;
    boolean checkBracket = false;
    boolean checkDot = false;
    boolean checkNewOperation = false;
    boolean checkSum = false;
    boolean checkRest = false;
    boolean checkMult = false;
    boolean checkDiv = false;
    boolean checkPercent  = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = (TextView) findViewById(R.id.input_text);
        outputText = (TextView) findViewById(R.id.output_text);
        button0 = (Button) findViewById(R.id.value0_button);
        button1 = (Button) findViewById(R.id.value1_button);
        button2 = (Button) findViewById(R.id.value2_button);
        button3 = (Button) findViewById(R.id.value3_button);
        button4 = (Button) findViewById(R.id.value4_button);
        button5 = (Button) findViewById(R.id.value5_button);
        button6 = (Button) findViewById(R.id.value6_button);
        button7 = (Button) findViewById(R.id.value7_button);
        button8 = (Button) findViewById(R.id.value8_button);
        button9 = (Button) findViewById(R.id.value9_button);
        buttonClear = (Button) findViewById(R.id.clear_button);
        buttonBracket = (Button) findViewById(R.id.bracket_button);
        buttonPercent = (Button) findViewById(R.id.percent_button);
        buttonDiv = (Button) findViewById(R.id.div_button);
        buttonMult = (Button) findViewById(R.id.mult_button);
        buttonRest = (Button) findViewById(R.id.rest_button);
        buttonSum = (Button) findViewById(R.id.sum_button);
        buttonResult = (Button) findViewById(R.id.resul_button);
        buttonDel = (Button) findViewById(R.id.del_button);
        buttonDot = (Button) findViewById(R.id.dot_button);

    }

    //resetea todos los valores
    private void resetValues () {
        operation = "";
        inputText.setText("");
        outputText.setText("");
        checkBracket = false;
        checkDot = false;
        checkNewOperation = false;
        checkSum = false;
        checkRest = false;
        checkMult = false;
        checkDiv = false;
        checkPercent  = false;
    }

    //evalua si se ha mostrado un resultado en el estado anterior para resetear todos los valores
    private  void checkNewOperation () {
        if (checkNewOperation) {
            resetValues();
        }
    }

    //actualiza los valores en pantalla al pulsar un boton
    @SuppressLint("SetTextI18n")
    private void refresh (String value) {
        checkNewOperation();
        operation = inputText.getText().toString();
        inputText.setText(inputText.getText() + value);
    }

    //esto es para que no se pinten dos simbolos iguales seguidos
    //habria que mirar que no se puedan poner dos operadores seguidos
    @SuppressLint("SetTextI18n")
    private void evalueSimbolsConstraints (char simbol) {
        switch (simbol){
            case '.':
                if (!checkDot) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + ".");
                    checkDot = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case '+':
                if (!checkSum) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "+");
                    checkSum = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case '-':
                if (!checkRest) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "-");
                    checkRest = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case 'x':
                if (!checkMult) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "x");
                    checkMult = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case '/':
                if (!checkDiv) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "/");
                    checkDiv = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case '%':
                if (!checkPercent) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "%");
                    checkPercent = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
        }
    }

    public void button0 (View vista) {
        refresh("0");
    }

    public void button1 (View vista) {
        refresh("1");
    }

    public void button2 (View vista) {
        refresh("2");
    }

    public void button3 (View vista) {
        refresh("3");
    }

    public void button4 (View vista) {
        refresh("4");
    }

    public void button5 (View vista) {
        refresh("5");
    }

    public void button6 (View vista) {
        refresh("6");
    }

    public void button7 (View vista) {
        refresh("7");
    }

    public void button8 (View vista) {
        refresh("8");
    }

    public void button9 (View vista) {
        refresh("9");
    }

    public void buttonClear (View vista) {
        resetValues();
    }

    //habria que echarle un ojo porque no va fino
    @SuppressLint("SetTextI18n")
    public void buttonBracket (View vista) {
        if (checkBracket) {
            operation = inputText.getText().toString();
            inputText.setText(inputText.getText() + ")");
            checkBracket = false;
        } else {
            operation = inputText.getText().toString();
            inputText.setText(inputText.getText() + "(");
            checkBracket = true;
        }

    }

    public void buttonPercent (View vista) {
        evalueSimbolsConstraints('%');
        checkDot = true;
    }

    public void buttonDiv (View vista) {
        evalueSimbolsConstraints('/');
        checkDot = false;
    }

    public void buttonMult (View vista) {
        evalueSimbolsConstraints('x');
        checkDot = false;
    }

    public void buttonRest (View vista) {
        evalueSimbolsConstraints('-');
        checkDot = false;
    }

    public void buttonSum (View vista) {
        evalueSimbolsConstraints('+');
        checkDot = false;
    }

    public void buttonResult (View vista) {
        operation = inputText.getText().toString();

        operation = operation.replace("x", "*");
        operation = operation.replace("%", "/100");

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String finalResult = "";

        try {
            Scriptable scriptable = rhino.initStandardObjects();
            finalResult = rhino.evaluateString(scriptable, operation, "javascript", 1, null).toString();
        } catch (Exception e) {
            finalResult = "Error";
        }

        checkNewOperation = true;
        outputText.setText(finalResult);
    }

    public void buttonDel (View vista) {
        operation = inputText.getText().toString();

        if (inputText.length()!= 0) {

            switch (inputText.getText().toString().charAt(inputText.length()-1)) {
                case '.':
                    checkDot = false;
                case '+':
                    checkSum = false;
                case '-':
                    checkRest = false;
                case 'x':
                    checkMult = false;
                case '/':
                    checkDiv = false;
                case '%':
                    checkPercent  = false;
            }
            inputText.setText(inputText.getText().toString().substring(0, inputText.length()-1));
        } else {
            inputText.setText("");
        }
    }

    public void buttonDot (View vista) {
        evalueSimbolsConstraints('.');
    }

}