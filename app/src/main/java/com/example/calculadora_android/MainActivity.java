package com.example.calculadora_android;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static TextView inputText;
    @SuppressLint("StaticFieldLeak")
    static TextView outputText;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9, buttonClearAll, buttonBracket, buttonPercent, buttonDivision, buttonMultiplication, buttonSubstraction, buttonAdition, buttonResult, buttonDelete, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
    }

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
        buttonClearAll = findViewById(R.id.clearAll_button);
        buttonBracket = findViewById(R.id.bracket_button);
        buttonPercent = findViewById(R.id.percent_button);
        buttonDivision = findViewById(R.id.division_button);
        buttonMultiplication = findViewById(R.id.multiplication_button);
        buttonSubstraction = findViewById(R.id.substraction_button);
        buttonAdition = findViewById(R.id.addition_button);
        buttonResult = findViewById(R.id.result_button);
        buttonDelete = findViewById(R.id.delete_button);
        buttonDot = findViewById(R.id.dot_button);
    }

    //BOTONES CONTROL
    public void buttonClearAll(View vista) {
        ButtonHandler.handlerButtonClearAll();
    }

    public void buttonDelete(View vista) {
        ButtonHandler.handlerButonDelete();
    }

    public void buttonResult(View vista) {
        ButtonHandler.handlerButtonResult();
    }

    //BOTONES SÍMBOLOS
    public void buttonBracket(View vista) {
        ButtonHandler.handlerButtonBracket();
    }

    public void buttonPercent(View vista) {
        ButtonHandler.handlerButtonPercent();
    }

    public void buttonDot(View vista) {
        ButtonHandler.handlerButtonDot();
    }

    //BOTONES OPERADORES
    public void buttonAddition(View vista) {
        ButtonHandler.handlerButtonAddition();
    }

    public void buttonSubstraction(View vista) {
        ButtonHandler.handlerButtonSubstraction();
    }

    public void buttonMultiplication(View vista) {
        ButtonHandler.handlerButtonMultiplication();

    }

    public void buttonDivision(View vista) {
        ButtonHandler.handlerButtonDivision();
    }

    //BOTONES NUMEROS
    public void button0(View vista) {
        ButtonHandler.handlerButton0();
    }

    public void button1(View vista) {
        ButtonHandler.handlerButton1();
    }

    public void button2(View vista) {
        ButtonHandler.handlerButton2();
    }

    public void button3(View vista) {
        ButtonHandler.handlerButton3();
    }

    public void button4(View vista) {
        ButtonHandler.handlerButton4();
    }

    public void button5(View vista) {
        ButtonHandler.handlerButton5();
    }

    public void button6(View vista) {
        ButtonHandler.handlerButton6();
    }

    public void button7(View vista) {
        ButtonHandler.handlerButton7();
    }

    public void button8(View vista) {
        ButtonHandler.handlerButton8();
    }

    public void button9(View vista) {
        ButtonHandler.handlerButton9();
    }

}