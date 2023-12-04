package com.example.calculadora_android;

public class ButtonHandler {

    //BOTONES CONTROL
    public static void handlerButtonClearAll() {
        CalculatorLogic.resetAllParams();
    }

    public static void handlerButonDelete() {
        CalculatorLogic.evalueLastCharOnDelete();
    }

    public static void handlerButtonResult() {
        CalculatorLogic.sendOperationToRhno();
    }


    //BOTONES SIMBOLOS
    public static void handlerButtonBracket() {
        CalculatorLogic.setOperatorAndSymbols('(');
    }

    public static void handlerButtonPercent () {
        CalculatorLogic.setOperatorAndSymbols('%');
    }

    public static void handlerButtonDot() {
        CalculatorLogic.setOperatorAndSymbols('.');
    }


    //BOTONES OPERADORES
    public static void handlerButtonAddition() {
        CalculatorLogic.setOperatorAndSymbols('+');
    }

    public static void handlerButtonSubstraction() {
        CalculatorLogic.setOperatorAndSymbols('-');
    }

    public static void handlerButtonMultiplication() {
        CalculatorLogic.setOperatorAndSymbols('x');
    }

    public static void handlerButtonDivision() {
        CalculatorLogic.setOperatorAndSymbols('/');
    }


    //BOTONES NUMEROS
    public static void handlerButton0(){
        CalculatorLogic.refreshInputText("0");
    }

    public static void handlerButton1(){
        CalculatorLogic.refreshInputText("1");
    }

    public static void handlerButton2(){
        CalculatorLogic.refreshInputText("2");
    }

    public static void handlerButton3(){
        CalculatorLogic.refreshInputText("3");
    }

    public static void handlerButton4(){
        CalculatorLogic.refreshInputText("4");
    }

    public static void handlerButton5(){
        CalculatorLogic.refreshInputText("5");
    }

    public static void handlerButton6(){
        CalculatorLogic.refreshInputText("6");
    }

    public static void handlerButton7(){
        CalculatorLogic.refreshInputText("7");
    }

    public static void handlerButton8(){
        CalculatorLogic.refreshInputText("8");
    }

    public static void handlerButton9(){
        CalculatorLogic.refreshInputText("9");
    }


    //BOTONES FUNCIONES

    public  static void  handlerButtonSave () {

    }

    public  static void  handlerButtonHistorical () {

    }


}
