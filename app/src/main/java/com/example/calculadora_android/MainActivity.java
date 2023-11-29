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
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonClearAll, buttonBracket, buttonPercent, buttonDiv, buttonMult, buttonRest, buttonSum, buttonResult, buttonDelete, buttonDot;
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

        initializeViews();

    }

    //Done
    private void initializeViews() {
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
        buttonClearAll = (Button) findViewById(R.id.clear_button);
        buttonBracket = (Button) findViewById(R.id.bracket_button);
        buttonPercent = (Button) findViewById(R.id.percent_button);
        buttonDiv = (Button) findViewById(R.id.div_button);
        buttonMult = (Button) findViewById(R.id.mult_button);
        buttonRest = (Button) findViewById(R.id.rest_button);
        buttonSum = (Button) findViewById(R.id.sum_button);
        buttonResult = (Button) findViewById(R.id.resul_button);
        buttonDelete = (Button) findViewById(R.id.del_button);
        buttonDot = (Button) findViewById(R.id.dot_button);
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
        if (checkIsNewOperation) {
            resetAllParams();
        }
    }

    //Done
    private void resetOperatorFlags() {
        checkSum = false;
        checkRest = false;
        checkMult = false;
        checkDiv = false;
        checkPercent  = false;
    }

    //Done
    private void resetAllParams() {
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

    //esto es para que no se pinten dos simbolos iguales seguidos
    //habria que mirar que no se puedan poner dos operadores diferentes seguidos
    //esto hay que mejorarlo, cambiar el switch por un map y una funcion para no repetir codigo o algo
    @SuppressLint("SetTextI18n")
    private void evalueSimbolsConstraints(char symbol) {
        switch (symbol){
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


    //BOTONES CONTROL
    //Done
    public void buttonClearAll(View vista) {
        resetAllParams();
    }

    //Revisar y mejorar esta logica
    public void buttonDelete(View vista) {
        if (inputText.length()!= 0) {
            String inputTextString = inputText.getText().toString();
            if (inputTextString.charAt(inputText.length() - 1) == ' ') {
                inputText.setText(inputTextString.substring(0, inputText.length() - 1));
                switch (inputTextString.charAt(inputText.length() - 1)) {
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
                }
                inputText.setText(inputTextString.substring(0, inputText.length() - 1));
                inputText.setText(inputTextString.substring(0, inputText.length() - 1));
            } else if (inputTextString.charAt(inputText.length() - 1) == '%') {
                checkPercent = false;
            } else {
                inputText.setText(inputTextString.substring(0, inputText.length()-1));
            }
            //Separa cada num del string y evalua si el ultimo tiene un punto y establece el checkdot en base a eso
            String[] prueba = inputText.getText().toString().split(" ");
            checkDot = prueba[prueba.length-1].contains(".") || prueba[prueba.length-1].contains("%");
        } else {
            inputText.setText("");
        }
    }

    //Done
    public void buttonResult(View vista) {
        operation = inputText.getText().toString();
        operation = operation.replace("x", "*");
        operation = operation.replace("%", "/100");

        outputText.setText(doOperationWithRhino(operation));
        checkIsNewOperation = true;
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
    //lógica muy basica para los parentesis
    @SuppressLint("SetTextI18n")
    public void buttonBracket(View vista) {
        inputText.setText(inputText.getText() + (checkBracket ? ")" : "("));
        checkBracket = !checkBracket;
    }

    //Done
    public void buttonPercent(View vista) {
        evalueSimbolsConstraints('%');
    }

    //Done
    public void buttonDot(View vista) {
        evalueSimbolsConstraints('.');
    }


    //BOTONES OPERADORES
    // Done
    public void buttonSum(View vista) {
        evalueSimbolsConstraints('+');
    }

    //Done
    public void buttonMult(View vista) {
        evalueSimbolsConstraints('x');
    }

    //Done
    public void buttonDiv(View vista) {
        evalueSimbolsConstraints('/');
    }

    //Done
    public void buttonRest(View vista) {
        evalueSimbolsConstraints('-');
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