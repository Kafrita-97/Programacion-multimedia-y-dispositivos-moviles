package com.example.calculadora_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

//Dividir el codigo en varias clases, por ejemplo en utiles y manejobotones
public class MainActivity extends AppCompatActivity {

    TextView inputText, outputText;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonClearAll, buttonBracket, buttonPercent, buttonDivision, buttonMultiplication, buttonSubstraction, buttonAdition, buttonResult, buttonDelete, buttonDot;
    String operation;
    boolean bracketFlag = false;
    boolean dotFlag = false;
    boolean isNewOperationFlag = false;
    boolean aditionFlag = false;
    boolean substractionFlag = false;
    boolean multiplicationFlag = false;
    boolean divisionFlag = false;
    boolean percentFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();

    }

    //Done
    private void initializeViews() {
        inputText = findViewById(R.id.input_text);
        outputText = findViewById(R.id.output_text);
        button0 = findViewById(R.id.value0_button);
        button1 = findViewById(R.id.value1_button);
        button2 = findViewById(R.id.value2_button);
        button3 = findViewById(R.id.value3_button);
        button4 = findViewById(R.id.value4_button);
        button5 = findViewById(R.id.value5_button);
        button6 = findViewById(R.id.value6_button);
        button7 = findViewById(R.id.value7_button);
        button8 = findViewById(R.id.value8_button);
        button9 = findViewById(R.id.value9_button);
        buttonClearAll = findViewById(R.id.clear_button);
        buttonBracket = findViewById(R.id.bracket_button);
        buttonPercent = findViewById(R.id.percent_button);
        buttonDivision = findViewById(R.id.div_button);
        buttonMultiplication = findViewById(R.id.mult_button);
        buttonSubstraction = findViewById(R.id.rest_button);
        buttonAdition = findViewById(R.id.sum_button);
        buttonResult = findViewById(R.id.resul_button);
        buttonDelete = findViewById(R.id.del_button);
        buttonDot = findViewById(R.id.dot_button);
    }

    //FUNCIONES GENERALES
    //Done
    @SuppressLint("SetTextI18n")
    private void refreshInputText(String value) {
        resetParamsIfItsNewOperation();

        inputText.setText(inputText.getText() + value);
        resetOperatorFlags();
    }

    //Done
    private void resetParamsIfItsNewOperation() {
        if (isNewOperationFlag) {
            resetAllParams();
        }
    }

    //Done
    private void resetOperatorFlags() {
        aditionFlag = false;
        substractionFlag = false;
        multiplicationFlag = false;
        divisionFlag = false;
        percentFlag = false;
    }

    //Done
    private void resetAllParams() {
        operation = "";
        inputText.setText("");
        outputText.setText("");
        bracketFlag = false;
        dotFlag = false;
        isNewOperationFlag = false;
        aditionFlag = false;
        substractionFlag = false;
        multiplicationFlag = false;
        divisionFlag = false;
        percentFlag = false;
    }


    //Done
    private void setOperatorAndSymbols(char symbol) {
        setOperator(symbol);
        setSymbol(symbol);
    }

    //Done
    private void setOperator(char symbol) {
        if (!aditionFlag && !substractionFlag && !multiplicationFlag && !divisionFlag) {
            switch (symbol) {
                case '+':
                    operation = inputText.getText().toString();
                    inputText.setText(String.format("%s + ", inputText.getText()));
                    aditionFlag = true;
                    dotFlag = false;
                    break;

                case '-':
                    operation = inputText.getText().toString();
                    inputText.setText(String.format("%s - ", inputText.getText()));
                    substractionFlag = true;
                    dotFlag = false;
                    break;

                case 'x':
                    operation = inputText.getText().toString();
                    inputText.setText(String.format("%s x ", inputText.getText()));
                    multiplicationFlag = true;
                    dotFlag = false;
                    break;

                case '/':
                    operation = inputText.getText().toString();
                    inputText.setText(String.format("%s / ", inputText.getText()));
                    divisionFlag = true;
                    dotFlag = false;
                    break;
            }
        }
    }

    //Done
    private void setSymbol(char symbol) {
        switch (symbol) {
            case '.':
                if (!dotFlag) {
                    operation = inputText.getText().toString();
                    inputText.setText(String.format("%s.", inputText.getText()));
                    dotFlag = true;
                }
                break;

            case '%':
                if (!percentFlag) {
                    operation = inputText.getText().toString();
                    inputText.setText(String.format("%s%%", inputText.getText()));
                    percentFlag = true;
                    dotFlag = true;
                }
                break;
        }
    }


    //BOTONES CONTROL
    //Done
    public void buttonClearAll(View vista) {
        resetAllParams();
    }

    //Revisar y mejorar esta logica menudo puto caos mimare
    public void buttonDelete(View vista) {
        if (inputText.length() != 0) {

            String inputTextString = inputText.getText().toString();
            char inputLastChar = inputTextString.charAt(inputText.length() - 1);

            if (inputLastChar == ' ') {
                resetCheckOperatorOnDelete(inputTextString);
            } else if (inputLastChar == '%') {
                resetCheckPercentOnDelete(inputTextString);
            } else {
                inputText.setText(inputTextString.substring(0, inputText.length() - 1));
            }
            //Separa cada num del string y evalua si el ultimo tiene un punto o un % y establece el checkdot en base a eso
            evalueDotOnDelete();
        } else {
            inputText.setText("");
        }
    }

    //Done
    private void resetCheckOperatorOnDelete(String inputTextString) {
        char lastChar = inputTextString.charAt(inputText.length() - 2);

        switch (lastChar) {
            case '+':
                aditionFlag = false;
                break;
            case '-':
                substractionFlag = false;
                break;
            case 'x':
                multiplicationFlag = false;
                break;
            case '/':
                divisionFlag = false;
                break;
        }
        inputText.setText(inputTextString.substring(0, inputText.length() - 3));
    }

    //Done
    private void resetCheckPercentOnDelete(String inputTextString) {
        percentFlag = false;
        inputText.setText(inputTextString.substring(0, inputText.length() - 1));
    }

    //Done
    private void evalueDotOnDelete() {
        String[] prueba = inputText.getText().toString().split(" ");
        dotFlag = prueba[prueba.length - 1].contains(".") || prueba[prueba.length - 1].contains("%");
    }

    //Done
    public void buttonResult(View vista) {
        operation = inputText.getText().toString();
        operation = operation.replace("x", "*").replace("%", "/100");

        outputText.setText(doOperationWithRhino(operation));
        isNewOperationFlag = true;
    }

    //Done
    private String doOperationWithRhino(String operation) {
        Context rhino = Context.enter();
        rhino.setOptimizationLevel(-1);
        String finalResult;

        try {
            Scriptable scriptable = rhino.initStandardObjects();
            finalResult = rhino.evaluateString(scriptable, operation, "javascript", 1, null).toString();
        } catch (Exception e) {
            finalResult = "Error";
        }
        return finalResult;
    }


    //BOTONES SÍMBOLOS
    //Done
    @SuppressLint("SetTextI18n")
    public void buttonBracket(View vista) {
        inputText.setText(inputText.getText() + (bracketFlag ? ")" : "("));
        bracketFlag = !bracketFlag;
    }

    //Done
    public void buttonPercent(View vista) {
        setOperatorAndSymbols('%');
    }

    //Done
    public void buttonDot(View vista) {
        setOperatorAndSymbols('.');
    }


    //BOTONES OPERADORES
    // Done
    public void buttonSum(View vista) {
        setOperatorAndSymbols('+');
    }

    //Done
    public void buttonMult(View vista) {
        setOperatorAndSymbols('x');
    }

    //Done
    public void buttonDiv(View vista) {
        setOperatorAndSymbols('/');
    }

    //Done
    public void buttonRest(View vista) {
        setOperatorAndSymbols('-');
    }


    //BOTONES NUMEROS
    //Done
    public void button0(View vista) {
        refreshInputText("0");
    }

    //Done
    public void button1(View vista) {
        refreshInputText("1");
    }

    //Done
    public void button2(View vista) {
        refreshInputText("2");
    }

    //Done
    public void button3(View vista) {
        refreshInputText("3");
    }

    //Done
    public void button4(View vista) {
        refreshInputText("4");
    }

    //Done
    public void button5(View vista) {
        refreshInputText("5");
    }

    //Done
    public void button6(View vista) {
        refreshInputText("6");
    }

    //Done
    public void button7(View vista) {
        refreshInputText("7");
    }

    //Done
    public void button8(View vista) {
        refreshInputText("8");
    }

    //Done
    public void button9(View vista) {
        refreshInputText("9");
    }

}