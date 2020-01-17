package sample;

import javafx.scene.control.Button;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

class Calculator {
    static String expression = "";
    private boolean isUnderRadical = false;

    void calculate() throws ScriptException { // set on action =
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");
        Object result = engine.eval(expression);
        expression = result.toString();
    }

    void clearAll() { // set on press and hold ←
        expression = "";
    }

    void clearLastChar() { // set on action ←
        if (expression == null || expression.length() == 0) {
            return;
        }
        expression = expression.substring(0, expression.length()-1);
    }

    void sqrt() { // set on action √
        if (!isUnderRadical) expression += "Math.sqrt(";
        else expression += ")";
        isUnderRadical = !isUnderRadical; // по вызову √ дальнейшее выражение либо начинает записывать под радикал, либо выходит из под радикала
    }

    void multiplication() {
        expression += "*";
    }

    void division() {
        expression += "/";
    }

    void append(Button button) {
        String action = button.getText();
        expression += action;
    }
}
