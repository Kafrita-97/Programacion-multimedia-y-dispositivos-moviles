package com.example.calculadora_android;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class CalculatorLogic {

    @SuppressLint("StaticFieldLeak")
    private static TextView inputText = MainActivity.inputText;
    @SuppressLint("StaticFieldLeak")
    private static TextView outputText = MainActivity.outputText;
    @SuppressLint("StaticFieldLeak")
    private static CardView inOutCardView = MainActivity.inOutCardView;
    @SuppressLint("StaticFieldLeak")
    private static CardView historicalCardView = MainActivity.historicalCardView;
    private static String operation;
    private static boolean bracketFlag = false;
    private static boolean dotFlag = false;
    private static boolean isNewOperationFlag = false;
    private static boolean additionFlag = false;
    private static boolean substractionFlag = false;
    private static boolean multiplicationFlag = false;
    private static boolean divisionFlag = false;
    private static boolean percentFlag = false;

    //LOGICA CONTROL
    public static void resetAllParams() {
        operation = "";
        inputText.setText("");
        outputText.setText("");
        bracketFlag = false;
        dotFlag = false;
        isNewOperationFlag = false;
        additionFlag = false;
        substractionFlag = false;
        multiplicationFlag = false;
        divisionFlag = false;
        percentFlag = false;
    }

    public static void evalueLastCharOnDelete() {
        if (inputText.length() != 0) {

            char inputLastChar = inputText.getText().toString().charAt(inputText.length() - 1);

            if (inputLastChar == ' ') {
                CalculatorLogic.evaluateOperatorFlagOnDelete();
            } else if (inputLastChar == '%') {
                CalculatorLogic.evaluatePercentFlagOnDelete();
            } else if (inputLastChar == '(' || inputLastChar == ')') {
                CalculatorLogic.evaluateBracketOnDelete();
            } else {
                CalculatorLogic.deleteLastNChar(1);
            }

            CalculatorLogic.evalueDotOnDelete();

        } else {
            inputText.setText("");
        }
    }

    private static void evaluateOperatorFlagOnDelete() {
        char penultimateChar = inputText.getText().toString().charAt(inputText.length() - 2);

        switch (penultimateChar) {
            case '+':
                additionFlag = false;
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
        deleteLastNChar(3);
    }

    private static void evaluatePercentFlagOnDelete() {
        percentFlag = false;
        deleteLastNChar(1);
    }

    private static void evaluateBracketOnDelete() {
        bracketFlag = !bracketFlag;
        deleteLastNChar(1);
    }

    private static void deleteLastNChar(int numbersOfCharToDelete) {
        inputText.setText(inputText.getText().toString().substring(0, inputText.length() - numbersOfCharToDelete));
    }

    private static void evalueDotOnDelete() {
        //Separa cada num del string y evalua si el ultimo tiene un punto o un % y establece el checkdot en base a eso
        String[] numbersArray = inputText.getText().toString().split(" ");
        dotFlag = numbersArray[numbersArray.length - 1].contains(".") || numbersArray[numbersArray.length - 1].contains("%");
    }

    public static void sendOperationToRhno() {
        operation = inputText.getText().toString().replace("x", "*").replace("%", "/100");
        outputText.setText(CalculatorLogic.doOperationWithRhino(operation));

        isNewOperationFlag = true;
    }

    private static String doOperationWithRhino(String operation) {
        try {
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
        } finally {
            Context.exit();
        }


    }


    //LOGICA SIMBOLOS Y OPERADORES
    public static void setOperatorAndSymbols(char symbol) {
        setOperator(symbol);
        setSymbol(symbol);
    }

    private static void setOperator(char symbol) {
        if (!additionFlag && !substractionFlag && !multiplicationFlag && !divisionFlag) {
            switch (symbol) {
                case '+':
                    setOperatorSymbol('+');
                    additionFlag = true;
                    break;

                case '-':
                    setOperatorSymbol('-');
                    substractionFlag = true;
                    break;

                case 'x':
                    setOperatorSymbol('x');
                    multiplicationFlag = true;
                    break;

                case '/':
                    setOperatorSymbol('/');
                    divisionFlag = true;
                    break;
            }
        }
    }

    private static void setOperatorSymbol (char symbol) {
        inputText.setText(String.format("%s %s ", inputText.getText(), symbol));
        dotFlag = false;
    }

    private static void setSymbol(char symbol) {
        switch (symbol) {
            case '(':
                setBracketSymbol();
                break;

            case '.':
                setDotSymbol();
                break;

            case '%':
                setPercentSymbol();
                break;
        }
    }

    private static void setBracketSymbol () {
        inputText.setText(String.format("%s%s", inputText.getText(), bracketFlag ? ")" : "("));
        bracketFlag = !bracketFlag;
    }

    private static void setDotSymbol () {
        operation = inputText.getText().toString();
        inputText.setText(String.format("%s%s", inputText.getText(), dotFlag ? "" : "."));
        dotFlag = true;
    }

    private  static void  setPercentSymbol () {
        inputText.setText(String.format("%s%s", inputText.getText(), percentFlag ? "" : "%"));
        percentFlag = true;
        dotFlag = true;
    }

    //LOGICA NUMEROS
    @SuppressLint("SetTextI18n")
    public static void refreshInputText(String value) {
        resetParamsIfItsNewOperation();

        inputText.setText(inputText.getText() + value);
        resetOperatorFlags();
    }

    private static void resetParamsIfItsNewOperation() {
        if (isNewOperationFlag) {
            resetAllParams();
        }
    }

    private static void resetOperatorFlags() {
        additionFlag = false;
        substractionFlag = false;
        multiplicationFlag = false;
        divisionFlag = false;
        percentFlag = false;
    }

    //LOGICA FUNCIONES
    public static void changeCardview () {
        if (inOutCardView.getVisibility() == View.VISIBLE){
            inOutCardView.setVisibility(View.GONE);
            historicalCardView.setVisibility(View.VISIBLE);
        } else {
            inOutCardView.setVisibility(View.VISIBLE);
            historicalCardView.setVisibility(View.GONE);
        }
    }

}
