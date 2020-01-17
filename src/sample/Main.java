package sample;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import jfxtras.styles.jmetro8.JMetro;

import javax.script.ScriptException;

public class Main extends Application {
    private GridPane root;
    private Label process;
    private Calculator calculator;
    private boolean isEqualsPressed = false;

    @Override
    public void start(Stage primaryStage) {
        root = new GridPane();
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 300, 254));
        primaryStage.show();
        primaryStage.setResizable(false);

        root.setHgap(1);
        root.setVgap(1);
        new JMetro(JMetro.Style.DARK).applyTheme(root);
        root.setStyle("-fx-background-color: black");

        process = new Label("0");
        process.setStyle("-fx-text-fill: white; -fx-font-size: 36");
        root.add(process, 0, 0, 4, 1);

        calculator = new Calculator();
        initButtons();
        root.requestFocus();
    }

    private void refreshLabel() { // если Calculator.expression длинее, чем MAX_LABEL_LENGTH, имитируем "движение при вводе", выводя последние MAX_LABEL_LENGTH элементов
        if (!isEqualsPressed) { // если не было нажато равно, имитируем движение при вводе, выдаем последние символы строки
            final int MAX_LABEL_LENGTH = 15; // сколько символов влезает в лейбл process
            if (Calculator.expression.length() <= MAX_LABEL_LENGTH)
                process.setText(Calculator.expression);
            else process.setText(Calculator.expression.substring(Math.max(0,
                    Calculator.expression.length() - MAX_LABEL_LENGTH)));
        }
        else { // если было нажато равно, то нужен точный ответ, начинаем вывод с начала, как и положено
            process.setText(Calculator.expression);
            isEqualsPressed = false;
        }
    }

    private void initButtons() {

        /* first row */
        Button delete = new Button("←");
        delete.setOnAction(event -> {
            calculator.clearLastChar();
            refreshLabel();
            root.requestFocus();
        });
        addPressAndHoldHandler(delete, Duration.seconds(0.5), event -> {
            calculator.clearAll();
            refreshLabel();
            root.requestFocus();
        });
        Button dot = new Button(".");
        dot.setOnAction(event -> {
            calculator.append(dot);
            refreshLabel();
            root.requestFocus();
        });
        Button sqrt = new Button("√");
        sqrt.setOnAction(event -> {
            calculator.sqrt();
            refreshLabel();
            root.requestFocus();
        });
        Button division = new Button("÷");
        division.setOnAction(event -> {
            calculator.division();
            refreshLabel();
            root.requestFocus();
        });

        /* second row */
        Button seven = new Button("7");
        seven.setOnAction(event -> {
            calculator.append(seven);
            refreshLabel();
            root.requestFocus();
        });
        Button eight = new Button("8");
        eight.setOnAction(event -> {
            calculator.append(eight);
            refreshLabel();
            root.requestFocus();
        });
        Button nine = new Button("9");
        nine.setOnAction(event -> {
            calculator.append(nine);
            refreshLabel();
            root.requestFocus();
        });
        Button multiplication = new Button("×");
        multiplication.setOnAction(event -> {
            calculator.multiplication();
            refreshLabel();
            root.requestFocus();
        });

        /* third row */
        Button four = new Button("4");
        four.setOnAction(event -> {
            calculator.append(four);
            refreshLabel();
            root.requestFocus();
        });
        Button five = new Button("5");
        five.setOnAction(event -> {
            calculator.append(five);
            refreshLabel();
            root.requestFocus();
        });
        Button six = new Button("6");
        six.setOnAction(event -> {
            calculator.append(six);
            refreshLabel();
            root.requestFocus();
        });
        Button minus = new Button("-");
        minus.setOnAction(event -> {
            calculator.append(minus);
            refreshLabel();
            root.requestFocus();
        });

        /* fourth row */
        Button one = new Button("1");
        one.setOnAction(event -> {
            calculator.append(one);
            refreshLabel();
            root.requestFocus();
        });
        Button two = new Button("2");
        two.setOnAction(event -> {
            calculator.append(two);
            refreshLabel();
            root.requestFocus();
        });
        Button three = new Button("3");
        three.setOnAction(event -> {
            calculator.append(three);
            refreshLabel();
            root.requestFocus();
        });
        Button plus = new Button("+");
        plus.setOnAction(event -> {
            calculator.append(plus);
            refreshLabel();
            root.requestFocus();
        });

        /* fifth row */
        Button leftBracket = new Button("(");
        leftBracket.setOnAction(event -> {
            calculator.append(leftBracket);
            refreshLabel();
            root.requestFocus();
        });
        Button zero = new Button("0");
        zero.setOnAction(event -> {
            calculator.append(zero);
            refreshLabel();
            root.requestFocus();
        });
        Button rightBracket = new Button(")");
        rightBracket.setOnAction(event -> {
            calculator.append(rightBracket);
            refreshLabel();
            root.requestFocus();
        });
        Button equals = new Button("=");
        equals.setOnAction(event -> {
            try {
                calculator.calculate();
                refreshLabel();
                isEqualsPressed = true;
            } catch (ScriptException e) {
                process.setText("Error");
                calculator.clearAll();
            }
            root.requestFocus();
        });

        Button[] buttons = new Button[]{
                delete, dot, sqrt, division,
                seven, eight, nine, multiplication,
                four, five, six, minus,
                one, two, three, plus,
                leftBracket, zero, rightBracket, equals
        };

        root.add(delete, 0, 1);
        root.add(dot, 1, 1);
        root.add(sqrt, 2, 1);
        root.add(division, 3, 1);

        root.add(seven, 0, 2);
        root.add(eight, 1, 2);
        root.add(nine, 2, 2);
        root.add(multiplication, 3, 2);

        root.add(four, 0, 3);
        root.add(five, 1, 3);
        root.add(six, 2, 3);
        root.add(minus, 3, 3);

        root.add(one, 0, 4);
        root.add(two, 1, 4);
        root.add(three, 2, 4);
        root.add(plus, 3, 4);

        root.add(leftBracket, 0, 5);
        root.add(zero, 1, 5);
        root.add(rightBracket, 2, 5);
        root.add(equals, 3, 5);

        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
            buttons[i].setWrapText(true);
            buttons[i].setStyle("-fx-font-size: 18");
        }
    }

    private void addPressAndHoldHandler(Node node, Duration holdTime, EventHandler<MouseEvent> handler) {
        class Wrapper<T> {
            private T content;
        }
        Wrapper<MouseEvent> eventWrapper = new Wrapper<>();
        PauseTransition holdTimer = new PauseTransition(holdTime);
        holdTimer.setOnFinished(event -> handler.handle(eventWrapper.content));
        node.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            eventWrapper.content = event;
            holdTimer.playFromStart();
        });
        node.addEventHandler(MouseEvent.MOUSE_RELEASED, event -> holdTimer.stop());
        node.addEventHandler(MouseEvent.DRAG_DETECTED, event -> holdTimer.stop());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
