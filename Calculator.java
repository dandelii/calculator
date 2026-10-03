import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

public class Calculator {

    static JFrame frame = new JFrame();
    static JPanel display = new JPanel();
    static JLabel textDisplay = new JLabel();
    static JPanel buttons = new JPanel(new GridLayout(9, 5));
    static ArrayList<JButton> buttonList = new ArrayList<JButton>();

    static JButton up = new JButton("↑");
    static JButton left = new JButton("←");
    static JButton down = new JButton("↓");
    static JButton right = new JButton("→");

    static JButton enter = new JButton ("enter");
    static JButton second = new JButton("2nd");
    static JButton del = new JButton("del");
    static JButton clear = new JButton("CE");
    static JButton openParen = new JButton("(");
    static JButton closeParen = new JButton(")");

    static JButton sin = new JButton("sin");
    static JButton cos = new JButton("cos");
    static JButton tan = new JButton("tan");
    static JButton exponent = new JButton("^");
    static JButton square = new JButton("x^2");
    static JButton divide = new JButton("÷");
    static JButton log = new JButton("log");
    static JButton multiply = new JButton("x");
    static JButton ln = new JButton("ln");
    static JButton minus = new JButton("-");
    static JButton decimal = new JButton(".");
    static JButton negative = new JButton("(-)");
    static JButton plus = new JButton("+");

    static JButton one = new JButton("1");
    static JButton two = new JButton("2");
    static JButton three = new JButton("3");
    static JButton four = new JButton("4");
    static JButton five = new JButton("5");
    static JButton six = new JButton("6");
    static JButton seven = new JButton("7");
    static JButton eight = new JButton("8");
    static JButton nine = new JButton("9");
    static JButton zero = new JButton("0");

    static Color buttonColor = new Color(245, 245, 245);

    static String operation = "";
    static ArrayList<String> tokens = new ArrayList<String>();

    public static void main(String[] args) {
        int i = 0;

        // row 1
        buttonList.add(second);
        buttonList.add(del);
        buttonList.add(new JButton());
        buttonList.add(up);
        buttonList.add(new JButton());
        // row 2
        buttonList.add(new JButton());
        buttonList.add(new JButton());
        buttonList.add(left);
        buttonList.add(down);
        buttonList.add(right);
        // row 3
        buttonList.add(new JButton());
        buttonList.add(new JButton());
        buttonList.add(new JButton());
        buttonList.add(new JButton());
        buttonList.add(clear);
        // row 4
        buttonList.add(new JButton());
        buttonList.add(sin);
        buttonList.add(cos);
        buttonList.add(tan);
        buttonList.add(exponent);
        // row 5
        buttonList.add(square);
        buttonList.add(new JButton());
        buttonList.add(openParen);
        buttonList.add(closeParen);
        buttonList.add(divide);
        // row 6
        buttonList.add(log);
        buttonList.add(seven);
        buttonList.add(eight);
        buttonList.add(nine);
        buttonList.add(multiply);
        // row 7
        buttonList.add(ln);
        buttonList.add(four);
        buttonList.add(five);
        buttonList.add(six);
        buttonList.add(minus);
        // row 8
        buttonList.add(new JButton());
        buttonList.add(one);
        buttonList.add(two);
        buttonList.add(three);
        buttonList.add(plus);
        // row 9
        buttonList.add(new JButton());
        buttonList.add(zero);
        buttonList.add(decimal);
        buttonList.add(negative);
        buttonList.add(enter);

        for (i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setBackground(buttonColor);
            buttons.add(buttonList.get(i));
        }

        textDisplay.setSize(300, 100);
        updateDisplay("Hello!");

        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));

        buttons.setMaximumSize(new Dimension(400, 500));
        textDisplay.setMaximumSize(new Dimension(400, 100));
        display.add(textDisplay);
        display.add(buttons);
        frame.add(display);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateDisplay("");
            }
        });

        frame.setSize(400, 600);
        frame.setVisible(true);
    }

    public static void closeProgram() {
        frame.dispose();
    }

    public static void updateDisplay(String text) {
        textDisplay.setText(text);
    }

    public static void addToOperation(String text) {
        operation += (text);
    }

    public static void clearOperation() {
        operation = "";
    }

    public static void parseOperation(String op) {
        String currentOperation;
        int openIndex;
        int closeIndex;
        boolean hasParentheses = false;

        do {
            openIndex = -1;
            closeIndex = -1;
            currentOperation = "";
            hasParentheses = false;

            for(int i = 0; i < op.length(); i++) {
                if (op.charAt(i) == (')')) {
                    closeIndex = i;
                    hasParentheses = true;
                    for (int j = i - 1; j >= 0; j--) {
                        if (op.charAt(j) == ('(')) {
                            openIndex = j;
                            break;
                        }
                    }
                    currentOperation = op.substring(openIndex + 1, closeIndex);
                    break;
                }
            }

            if (!currentOperation.equals("")) {
                double result = parseParentheses(currentOperation);
                String beforeParen = op.substring(0, openIndex);
                String afterParen = op.substring(closeIndex + 1);
                op = beforeParen + result + afterParen;
            }

        } while (hasParentheses);
    }

    public static double parseParentheses(String op) {
        ArrayList<String> localTokens = new ArrayList<String>();
        String currentNum = "";

        for (int i = 0; i < op.length(); i++) {
            char c = op.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                currentNum += c;
            } else if (Character.isLetter(c)) {
                if (!currentNum.equals("")) {
                    localTokens.add(currentNum);
                    currentNum = "";
                }
                String currentWord = "";
                while (i < op.length() && Character.isLetter(op.charAt(i))) {
                    currentWord += op.charAt(i);
                    i++;
                }
                i--;
                localTokens.add(currentWord);
            } else {
                if (!currentNum.equals("")) {
                    localTokens.add(currentNum);
                    currentNum = "";
                }
                localTokens.add(String.valueOf(c));
            }
        }

        if (!currentNum.equals("")) {
            localTokens.add(currentNum);
        }

        // return evaluateTokens(localTokens);
        return 0.0;
    }

}