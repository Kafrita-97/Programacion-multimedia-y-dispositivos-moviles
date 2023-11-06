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
    boolean checkIsNewOperation = false;
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

    private void resetParams() {
        operation = "";
        inputText.setText("");
        outputText.setText("");
        checkBracket = false;
        checkDot = false;
        checkIsNewOperation = false;
        checkSum = false;
        checkRest = false;
        checkMult = false;
        checkDiv = false;
        checkPercent  = false;
    }

    @SuppressLint("SetTextI18n")
    private void refreshInOut(String value) {
        if (checkIsNewOperation) {
            resetParams();
        }
        operation = inputText.getText().toString();
        inputText.setText(inputText.getText() + value);
        checkSum = false;
        checkRest = false;
        checkMult = false;
        checkDiv = false;
        checkPercent  = false;
    }

    //esto es para que no se pinten dos simbolos iguales seguidos
    //habria que mirar que no se puedan poner dos operadores diferentes seguidos
    @SuppressLint("SetTextI18n")
    private void evalueSimbolsConstraints(char simbol) {
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
                    inputText.setText(inputText.getText() + " + ");
                    checkSum = true;
                    checkDot = false;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case '-':
                if (!checkRest) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + " - ");
                    checkRest = true;
                    checkDot = false;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case 'x':
                if (!checkMult) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + " x ");
                    checkMult = true;
                    checkDot = false;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
            case '/':
                if (!checkDiv) {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + " / ");
                    checkDiv = true;
                    checkDot = false;
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
                    checkDot = true;
                } else {
                    operation = inputText.getText().toString();
                    inputText.setText(inputText.getText() + "");
                }
                break;
        }
    }

    //habria que echarle un ojo porque no va fino
    @SuppressLint("SetTextI18n")
    public void buttonBracket(View vista) {
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

    public void buttonPercent(View vista) {
        evalueSimbolsConstraints('%');
        checkDot = true;
    }

    public void buttonDot(View vista) {
        evalueSimbolsConstraints('.');
    }

    public void buttonClear(View vista) {
        resetParams();
    }

    public void buttonDel(View vista) {
        if (inputText.length()!= 0) {
            if (inputText.getText().toString().charAt(inputText.length()-1) == ' '){
                inputText.setText(inputText.getText().toString().substring(0, inputText.length()-1));
                switch (inputText.getText().toString().charAt(inputText.length()-1)) {
                    case '.':
                        checkDot = false;
                        break;
                    case '+':
                        checkSum = false;
                        break;
                    case '-':
                        checkRest = false;
                        break;
                    case 'x':
                        checkMult = false;
                        break;
                    case '/':
                        checkDiv = false;
                        break;
                    case '%':
                        checkPercent  = false;
                        break;
                }
                inputText.setText(inputText.getText().toString().substring(0, inputText.length()-1));
                inputText.setText(inputText.getText().toString().substring(0, inputText.length()-1));
            } else {
                inputText.setText(inputText.getText().toString().substring(0, inputText.length()-1));
            }
            String[] prueba = inputText.getText().toString().split(" ");
            checkDot = prueba[prueba.length-1].contains(".");
        } else {
            inputText.setText("");
        }
    }

    public void buttonResult(View vista) {
        operation = inputText.getText().toString();

        operation = operation.replace("x", "*");
        operation = operation.replace("%", "/100");

        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String finalResult;

        try {
            Scriptable scriptable = rhino.initStandardObjects();
            finalResult = rhino.evaluateString(scriptable, operation, "javascript", 1, null).toString();
        } catch (Exception e) {
            finalResult = "Error";
        }

        checkIsNewOperation = true;
        outputText.setText(finalResult);
    }

    public void buttonDiv(View vista) {
        evalueSimbolsConstraints('/');
    }

    public void buttonMult(View vista) {
        evalueSimbolsConstraints('x');
    }

    public void buttonRest(View vista) {
        evalueSimbolsConstraints('-');
    }

    public void buttonSum(View vista) {
        evalueSimbolsConstraints('+');
    }

    public void button0(View vista) {
        refreshInOut("0");
    }

    public void button1(View vista) {
        refreshInOut("1");
    }

    public void button2(View vista) {
        refreshInOut("2");
    }

    public void button3(View vista) {
        refreshInOut("3");
    }

    public void button4(View vista) {
        refreshInOut("4");
    }

    public void button5(View vista) {
        refreshInOut("5");
    }

    public void button6(View vista) {
        refreshInOut("6");
    }

    public void button7(View vista) {
        refreshInOut("7");
    }

    public void button8(View vista) {
        refreshInOut("8");
    }

    public void button9(View vista) {
        refreshInOut("9");
    }

}