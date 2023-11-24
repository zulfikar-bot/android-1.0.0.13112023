/*
 * *
 *  * Copyright (c) 2023 - Muhammad Zulfikar Sachori Putra
 *  * Nama Aplikasi : Belajar Bahasa Inggris
 *  * Nama Package : com.zulfikar.belajarbahasainggris
 *  * Versi Aplikasi : 1.0.1.20112023
 *
 *
 */

package com.zulfikar.belajarbahasainggris;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DecimalFormat;

public class Kalkulator extends AppCompatActivity {
    Button b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bdot, bpi, bequal, bplus, bmin, bmul, bdiv, binv, bsqrt, bsquare, bfact, bln, blog, btan, bcos, bsin, bb1, bb2, bc, bac;
    private static final int MAX_INPUT_LENGTH = 44; // Change this to your desired maximum length
    TextView tvmain, tvsec;

    private void appendToMainTextView(String value) {
        String currentText = tvmain.getText().toString();
        if (currentText.length() < MAX_INPUT_LENGTH) {
            String text = currentText + value;
            tvmain.setText(text);
        } else {
            // Display an error message, for example:
            String text = "Error: Max input length reached";
            tvsec.setText(text);
            tvmain.setText("");
        }
    }

    private void appendToMainTextView2(String value) {
        String currentText = tvmain.getText().toString();
        if (currentText.length() < MAX_INPUT_LENGTH) {
            tvmain.setText(value);
        } else {
            // Display an error message, for example:
            String text = "Error: Max input length reached";
            tvsec.setText(text);
            tvmain.setText("");
        }
    }

    private void appendToMainTextView4(String value) {
        String currentText = tvmain.getText().toString();
        if (currentText.length() < MAX_INPUT_LENGTH) {
            tvsec.setText(value);
        } else {
            // Display an error message, for example:
            String text = "Error: Max input length reached";
            tvsec.setText(text);
            tvmain.setText("");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        Toolbar toolbar = findViewById(R.id.toolbar); // Anda perlu memiliki toolbar dengan id "toolbar" di layout Anda

        toolbar.setTitle("Kalkulator");
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.toolbarTextColor, typedValue, true);
        int color = typedValue.data;
        toolbar.setTitleTextColor(color);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);
        switchMaterial.setTextColor(color);

        setSupportActionBar(toolbar);

        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);
        b8 = findViewById(R.id.b8);
        b9 = findViewById(R.id.b9);
        b0 = findViewById(R.id.b0);
        bpi = findViewById(R.id.bpi);
        bdot = findViewById(R.id.bdot);
        bequal = findViewById(R.id.bequal);
        bplus = findViewById(R.id.bplus);
        bmin = findViewById(R.id.bmin);
        bmul = findViewById(R.id.bmul);
        bdiv = findViewById(R.id.bdiv);
        binv = findViewById(R.id.binv);
        bsqrt = findViewById(R.id.bsqrt);
        bsquare = findViewById(R.id.bsquare);
        bfact = findViewById(R.id.bfact);
        bln = findViewById(R.id.bln);
        blog = findViewById(R.id.blog);
        btan = findViewById(R.id.btan);
        bsin = findViewById(R.id.bsin);
        bcos = findViewById(R.id.bcos);
        bb1 = findViewById(R.id.bb1);
        bb2 = findViewById(R.id.bb2);
        bc = findViewById(R.id.bc);
        bac = findViewById(R.id.bac);

        tvmain = findViewById(R.id.tvmain);
        tvsec = findViewById(R.id.tvsec);

        if (isDarkMode) {
            warna(R.color.black);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            warna(R.color.white);
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        //onclick listeners
        b1.setOnClickListener(v -> appendToMainTextView("1"));

        // Add similar listeners for other numeric buttons
        // onclick listeners
        b2.setOnClickListener(v -> appendToMainTextView("2"));

        b3.setOnClickListener(v -> appendToMainTextView("3"));

        // Add similar listeners for other numeric buttons
        b4.setOnClickListener(v -> appendToMainTextView("4"));

// Add similar listeners for other operator buttons
        bmul.setOnClickListener(v -> appendToMainTextView("×"));

        // Add similar listeners for other numeric buttons

        bplus.setOnClickListener(v -> appendToMainTextView("+"));

        bmin.setOnClickListener(v -> appendToMainTextView("-"));

// Add similar listeners for other operator buttons

        bsquare.setOnClickListener(v -> appendToMainTextView("²"));

        bfact.setOnClickListener(v -> appendToMainTextView("!"));


        b5.setOnClickListener(v -> appendToMainTextView("5"));

        b6.setOnClickListener(v -> appendToMainTextView("6"));

        b7.setOnClickListener(v -> appendToMainTextView("7"));

        b8.setOnClickListener(v -> appendToMainTextView("8"));

        b9.setOnClickListener(v -> appendToMainTextView("9"));

        b0.setOnClickListener(v -> appendToMainTextView("0"));

        bdot.setOnClickListener(v -> appendToMainTextView("."));

        bpi.setOnClickListener(v -> {
            String pi = (String) bpi.getText();
            appendToMainTextView4(pi);
            appendToMainTextView("3.14159265");
        });

// Add similar listeners for other operator buttons

        bdiv.setOnClickListener(v -> appendToMainTextView("÷"));

        binv.setOnClickListener(v -> appendToMainTextView("^(-1)"));

// Add similar listeners for other operator buttons

        bsqrt.setOnClickListener(v -> {
            String currentText = tvmain.getText().toString();

            // Check if tvmain is not empty and contains a valid numeric expression
            if (!currentText.isEmpty() && isValidInteger(currentText)) {
                appendToMainTextView2("sqrt(" + currentText + ")");
            } else if (!currentText.isEmpty()) {
                appendToMainTextView2("sqrt(" + currentText + ")");
            } else {
                // Display an error message in tvsec
                tvsec.setText("Error: Invalid input for square root");
                clearMainTextView();
            }
        });

        bsin.setOnClickListener(v -> {
            String currentText = tvmain.getText().toString();

            // Check if tvmain is not empty and contains a valid numeric expression
            if (!currentText.isEmpty() && isValidInteger(currentText)) {
                appendToMainTextView2("sin(" + currentText + ")");
            } else if (!currentText.isEmpty()) {
                appendToMainTextView2("sin(" + currentText + ")");
            } else {
                // Display an error message in tvsec
                tvsec.setText("Error: Invalid input for square root");
                clearMainTextView();
            }
        });

        bcos.setOnClickListener(v -> {
            String currentText = tvmain.getText().toString();

            // Check if tvmain is not empty and contains a valid numeric expression
            if (!currentText.isEmpty() && isValidInteger(currentText)) {
                appendToMainTextView2("cos(" + currentText + ")");
            } else if (!currentText.isEmpty()) {
                appendToMainTextView2("cos(" + currentText + ")");
            } else {
                // Display an error message in tvsec
                tvsec.setText("Error: Invalid input for square root");
                clearMainTextView();
            }
        });

        btan.setOnClickListener(v -> {
            String currentText = tvmain.getText().toString();

            // Check if tvmain is not empty and contains a valid numeric expression
            if (!currentText.isEmpty() && isValidInteger(currentText)) {
                appendToMainTextView2("tan(" + currentText + ")");
            } else if (!currentText.isEmpty()) {
                appendToMainTextView2("tan(" + currentText + ")");
            } else {
                // Display an error message in tvsec
                tvsec.setText("Error: Invalid input for square root");
                clearMainTextView();
            }
        });

        blog.setOnClickListener(v -> {
            String currentText = tvmain.getText().toString();

            // Check if tvmain is not empty and contains a valid numeric expression
            if (!currentText.isEmpty() && isValidInteger(currentText)) {
                appendToMainTextView2("log(" + currentText + ")");
            } else if (!currentText.isEmpty()) {
                appendToMainTextView2("log(" + currentText + ")");
            } else {
                // Display an error message in tvsec
                tvsec.setText("Error: Invalid input for square root");
                clearMainTextView();
            }
        });

        bln.setOnClickListener(v -> {
            String currentText = tvmain.getText().toString();

            // Check if tvmain is not empty and contains a valid numeric expression
            if (!currentText.isEmpty() && isValidInteger2(currentText)) {
                appendToMainTextView2("ln(" + currentText + ")");
            } else if (!currentText.isEmpty()) {
                appendToMainTextView2("ln(" + currentText + ")");
            } else {
                // Display an error message in tvsec
                tvsec.setText("Error: Invalid input for square root");
                clearMainTextView();
            }
        });


        // Add similar listeners for other special function buttons
        bb1.setOnClickListener(v -> appendToMainTextView("("));

        bb2.setOnClickListener(v -> appendToMainTextView(")"));

        bac.setOnClickListener(v -> {
            clearMainTextView();
            tvsec.setText("");
        });

        bc.setOnClickListener(v -> removeLastChar());

        bequal.setOnClickListener(v -> evaluateExpression());
    }

    private void clearMainTextView() {
        tvmain.setText("");
    }

    private void removeLastChar() {
        String val = tvmain.getText().toString();
        if (!val.isEmpty()) {
            val = val.substring(0, val.length() - 1);
            tvmain.setText(val);
        }
    }

    private void evaluateExpression() {
        String val = tvmain.getText().toString().trim();

        if (val.isEmpty()) {
            // Display an error message for empty input
            String text ="Error: Input is empty";
            tvsec.setText(text);
            clearMainTextView();
            return;
        }

        if (val.length() > MAX_INPUT_LENGTH) {
            // Display an error message for exceeding max length
            String text = "Error: Max input length reached";
            tvsec.setText(text);
            clearMainTextView();
            return;
        }

        val = processSquareOperation(val);

        // Check if the expression contains a factorial symbol
        if (val.contains("!")) {
            // Extract the expression before the factorial symbol
            String expressionBeforeFactorial = val.substring(0, val.indexOf("!"));

            // Check if the expression before factorial contains only digits
            if (isValidInteger(expressionBeforeFactorial)) {
                // Factorial calculation logic
                int numberToFactorial = Integer.parseInt(expressionBeforeFactorial);
                int fact = factorial(numberToFactorial);

                // Update tvmain and tvsec with the factorial result
                tvmain.setText(String.valueOf(fact));
                tvsec.setText(val);
            } else {
                // Display an error message if the expression before factorial is not a valid integer
                String text = "Error: Invalid input for factorial";
                tvsec.setText(text);
                clearMainTextView();
            }
        } else {
            // If no factorial symbol, proceed with regular expression evaluation
            String replacedStr = val.replace('÷', '/').replace('×', '*');

            try {
                double result = eval(replacedStr);

                // Menggunakan DecimalFormat untuk menghilangkan desimal tidak signifikan
                DecimalFormat decimalFormat = new DecimalFormat("#.##########");
                String formattedResult = decimalFormat.format(result);

                if (formattedResult.equals("0")) {
                    clearMainTextView();
                    tvsec.setText(val);
                } else {
                    tvmain.setText(formattedResult);
                    tvsec.setText(val);
                }
            } catch (Exception e) {
                // Handle the exception
                String text = "Error: " + e.getMessage();
                tvsec.setText(text);
                clearMainTextView();
            }
        }
    }

    // Add a method to validate if the expression is a valid integer
    private boolean isValidInteger(String expression) {
        try {
            Integer.parseInt(expression);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidInteger2(String expression) {
        return expression.matches("[\\d.()+\\-*/^]+|sin|cos|tan|log|ln");
    }

    private String processSquareOperation(String expression) {
        // Temukan dan gantilah semua simbol kuadrat (²) dengan bentuk yang dapat dihitung
        expression = expression.replaceAll("²", "^2");
        return expression;
    }

    //factorial function
    int factorial(int n)
    {
        return (n==1 || n==0) ? 1 : n*factorial(n-1);
    }

    //eval function
    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        case "log":
                            x = Math.log10(x);
                            break;
                        case "ln":
                            x = Math.log(x);
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    throw new RuntimeException("Unexpected: " + (char) ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
    
    private void warna(int warna){
        bpi.setTextColor(getResources().getColor(warna)); // Atur warna untuk tombol phi
        bdot.setTextColor(getResources().getColor(warna)); // Atur warna untuk tombol tanda titik
        b1.setTextColor(getResources().getColor(warna)); // Atur warna untuk tombol 1 (dan sebagainya)
        b2.setTextColor(getResources().getColor(warna));
        b3.setTextColor(getResources().getColor(warna));
        b4.setTextColor(getResources().getColor(warna));
        b5.setTextColor(getResources().getColor(warna));
        b6.setTextColor(getResources().getColor(warna));
        b7.setTextColor(getResources().getColor(warna));
        b8.setTextColor(getResources().getColor(warna));
        b9.setTextColor(getResources().getColor(warna));
        b0.setTextColor(getResources().getColor(warna));
    }
}