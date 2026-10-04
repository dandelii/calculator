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
    static JButton clearAll = new JButton("CA");
    static JButton openParen = new JButton("(");
    static JButton closeParen = new JButton(")");

    static JButton sin = new JButton("sin");
    static JButton cos = new JButton("cos");
    static JButton tan = new JButton("tan");
    static JButton exponent = new JButton("^");
    static JButton square = new JButton("^2");
    static JButton divide = new JButton("÷");
    static JButton log = new JButton("log");
    static JButton multiply = new JButton("*");
    static JButton ln = new JButton("ln");
    static JButton minus = new JButton("-");
    static JButton decimal = new JButton(".");
    static JButton negative = new JButton("(-1)");
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
    static ArrayList<String> operations = new ArrayList<String>();
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
        updateDisplay("");

        display.setLayout(new BoxLayout(display, BoxLayout.Y_AXIS));

        buttons.setMaximumSize(new Dimension(400, 500));
        textDisplay.setMaximumSize(new Dimension(400, 100));
        display.add(textDisplay);
        display.add(buttons);
        frame.add(display);

        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOperation();
                updateDisplay("");
            }
        });
        clearAll.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearOperation();
                operations.clear();
                updateDisplay("");
            }
        });
        del.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!operation.equals("")) {
                    if (operation.endsWith("sin") || operation.endsWith("cos") || operation.endsWith("tan")) {
                        operation = operation.substring(0, operation.length() - 3);
                    } else if (operation.endsWith("log")) {
                        operation = operation.substring(0, operation.length() - 3);
                    } else {
                        operation = operation.substring(0, operation.length() - 1);
                    }
                    updateDisplay(operation);
                }
            }
        });
        enter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!operation.equals("")) {
                    operations.add(operation);
                    parseOperation(operation);
                    updateDisplay(operation);
                }
            }
        });
        openParen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOperation("(");
                updateDisplay(operation);
            }
        });
        closeParen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOperation(")");
                updateDisplay(operation);
            }
        });
        sin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOperation("sin");
                updateDisplay(operation);
            }
        });
        cos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOperation("cos");
                updateDisplay(operation);
            }
        });
        tan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addToOperation("tan");
                updateDisplay(operation);
            }
        });

        ActionListener generalButtonHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();
                if (!cmd.equals("")) { 
                    addToOperation(cmd);
                    updateDisplay(operation);
                }
            }
        };
        JButton[] generalKeys = {one, two, three, four, five, six, seven, eight, nine, zero, plus, minus, multiply, divide, exponent, square, log, ln, decimal, negative};
        for (JButton key : generalKeys) {
            key.addActionListener(generalButtonHandler);
        }

        frame.setSize(400, 600);
        frame.setVisible(true);

    }

    static void closeProgram() {
        frame.dispose();
    }

    static void updateDisplay(String text) {
        textDisplay.setText(text);
    }

    static void addToOperation(String text) {
        operation += (text);
    }

    static void clearOperation() {
        operation = "";
    }

    static void parseOperation(String op) {
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
                if (openIndex > 0 && Character.isDigit(beforeParen.charAt(beforeParen.length() - 1))) {
                    op = beforeParen + "*" + result + afterParen;
                } else {
                    op = beforeParen + result + afterParen;
                }
            }

        } while (hasParentheses);

        operation = Double.toString(parseParentheses(op));
    }

    static double parseParentheses(String op) {
        ArrayList<String> localTokens = new ArrayList<String>();
        String currentNum = "";

        for (int i = 0; i < op.length(); i++) {
            char c = op.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                currentNum += c;
            } else if (Character.isLetter(c)) {
                if (!currentNum.equals("")) {
                    localTokens.add(currentNum);
                    localTokens.add("*");
                    currentNum = "";
                } else if (!localTokens.isEmpty() && localTokens.get(localTokens.size() - 1).equals(")")) {
                    localTokens.add("*");
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
                if (c == '(' && !localTokens.isEmpty()) {
                    String lastToken = localTokens.get(localTokens.size() - 1);
                    if (Character.isDigit(lastToken.charAt(lastToken.length() - 1)) || lastToken.equals(")")) {
                        localTokens.add("*");
                    }
                }
                localTokens.add(String.valueOf(c));
            }
        }

        if (!currentNum.equals("")) {
            localTokens.add(currentNum);
        }
        return evaluateTokens(localTokens);
    }

    static double evaluateTokens(ArrayList<String> tokens) {
        int i;
        for (i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("-")) {
                if (i == 0 || tokens.get(i - 1).equals("*") || tokens.get(i - 1).equals("÷") || tokens.get(i - 1).equals("+") || tokens.get(i - 1).equals("-")) {
                    tokens.set(i, "-" + tokens.get(i + 1));
                    tokens.remove(i + 1);
                }
            }
        }
        for (i = 0; i < tokens.size(); i++) {
            if (tokens.get(i).equals("sin") || tokens.get(i).equals("cos") || tokens.get(i).equals("tan")) {
                switch (tokens.get(i)) {
                    case "sin":
                        tokens.set(i, String.valueOf(Functions.sine(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "cos":
                        tokens.set(i, String.valueOf(Functions.cosine(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "tan":
                        tokens.set(i, String.valueOf(Functions.tangent(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    default: break;
                }
                continue;
            }
            if (tokens.get(i).equals("csc") || tokens.get(i).equals("sec") || tokens.get(i).equals("cot")) {
                switch (tokens.get(i)) {
                    case "csc":
                        tokens.set(i, String.valueOf(Functions.cosecant(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "sec":
                        tokens.set(i, String.valueOf(Functions.secant(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "cot":
                        tokens.set(i, String.valueOf(Functions.cotangent(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    default: break;
                }
                continue;
            }
            if (tokens.get(i).equals("arcsin") || tokens.get(i).equals("arccos") || tokens.get(i).equals("arctan")) {
                switch (tokens.get(i)) {
                    case "arcsin":
                        tokens.set(i, String.valueOf(Functions.arcsine(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "arccos":
                        tokens.set(i, String.valueOf(Functions.arccos(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "arctan":
                        tokens.set(i, String.valueOf(Functions.arctangent(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    default: break;
                }
                continue;
            }
            if (tokens.get(i).equals("log") || tokens.get(i).equals("ln") || tokens.get(i).equals("√")) {
                switch (tokens.get(i)) {
                    case "log":
                        if (i + 2 < tokens.size() && Character.isDigit(tokens.get(i + 2).charAt(0))) {
                            tokens.set(i, String.valueOf(Functions.log(Double.parseDouble(tokens.get(i + 1)), Double.parseDouble(tokens.get(i + 2)))));
                            tokens.remove(i + 1);
                            tokens.remove(i + 1);
                        } else {
                            tokens.set(i, String.valueOf(Functions.log(Double.parseDouble(tokens.get(i + 1)), 10.0)));
                            tokens.remove(i + 1);
                        }
                        i--;
                        break;
                    case "ln":
                        tokens.set(i, String.valueOf(Functions.ln(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    case "√":
                        tokens.set(i, String.valueOf(Functions.squareRoot(Double.parseDouble(tokens.get(i + 1)))));
                        tokens.remove(i + 1);
                        i--;
                        break;
                    default: break;
                }
            }
        }

        for (i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("^") || token.equals("nRoot") || token.equals("x^2")) {
                double left = Double.parseDouble(tokens.get(i - 1));
                double result = 0;
                if (token.equals("^")) {
                    double right = Double.parseDouble(tokens.get(i + 1));
                    result = Functions.nPower(left, right);
                    tokens.set(i - 1, String.valueOf(result));
                    tokens.remove(i); 
                    tokens.remove(i); 
                } else if (token.equals("nRoot")) {
                    double right = Double.parseDouble(tokens.get(i + 1));
                    result = Functions.nRoot(left, right);
                    tokens.set(i - 1, String.valueOf(result));
                    tokens.remove(i);
                    tokens.remove(i);
                } else if (token.equals("x^2")) {
                    result = Functions.square(left);
                    tokens.set(i - 1, String.valueOf(result));
                    tokens.remove(i);
                }
                i--;
            }
        }

        for (i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("*") || token.equals("÷")) {
                double left = Double.parseDouble(tokens.get(i - 1));
                double right = Double.parseDouble(tokens.get(i + 1));
                double result = token.equals("*") ? Functions.multiply(left, right) : Functions.divide(left, right);
                tokens.set(i - 1, String.valueOf(result));
                tokens.remove(i);
                tokens.remove(i);
                i--;
            }
        }

        for (i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);
            if (token.equals("+") || token.equals("-")) {
                if (i == 0) {
                    if (token.equals("-")) {
                        double right = Double.parseDouble(tokens.get(i + 1));
                        tokens.set(i, String.valueOf(-right));
                        tokens.remove(i + 1);
                    } else {
                        tokens.remove(i);
                    }
                    i--;
                } else {
                    double left = Double.parseDouble(tokens.get(i - 1));
                    double right = Double.parseDouble(tokens.get(i + 1));
                    double result = token.equals("+") ? Functions.add(left, right) : Functions.subtract(left, right);

                    tokens.set(i - 1, String.valueOf(result));
                    tokens.remove(i);
                    tokens.remove(i);
                    i--;
                }
            }
        }

        return Double.parseDouble(tokens.get(0));
    }

}