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

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Map;

public class Login extends AppCompatActivity {
    private EditText PassInput;
    private CheckBox ShowPass;
    private EditText UsernameInput;
    private TextView AttemptsTextView;
    private int failedAttempts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TextView register2 = findViewById(R.id.register2);
        register2.setLinkTextColor(Color.BLUE);
        register2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Register.class);
            startActivity(intent);
            finish();
        });

        PassInput = findViewById(R.id.password);
        ShowPass = findViewById(R.id.showPass);

        ShowPass.setOnClickListener(view -> {
            if (ShowPass.isChecked()) {
                PassInput.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                PassInput.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });

        UsernameInput = findViewById(R.id.username);
        TableRow submitTableRow = findViewById(R.id.submit);
        AttemptsTextView = findViewById(R.id.attempts);

        // Mendapatkan SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
        failedAttempts = sharedPref.getInt("failedAttempts", 0);

        String attemptsLefts;
        long lockoutStartTime = sharedPref.getLong("lockoutStartTime", 0);
        long elapsedTime = System.currentTimeMillis() - lockoutStartTime;
        if (elapsedTime < 60000 || failedAttempts >= 3) {
            submitTableRow.setEnabled(false);
            new CountDownTimer(60000 - elapsedTime, 1000) {
                public void onTick(long millisUntilFinished) {
                    long secondsUntilFinished = millisUntilFinished / 1000;
                    String message = getResources().getString(R.string.login_attempts_message, secondsUntilFinished);
                    AttemptsTextView.setText(message);
                }

                public void onFinish() {
                    submitTableRow.setEnabled(true);
                    failedAttempts = 0;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("failedAttempts", failedAttempts);
                    editor.apply();
                    String attemptsLeft = getResources().getString(R.string.attempts_left, 3);
                    AttemptsTextView.setText(attemptsLeft);
                }
            }.start();
        } else if (failedAttempts == 0) {
            attemptsLefts = getResources().getString(R.string.attempts_left, 3);
            AttemptsTextView.setText(attemptsLefts);
        } else {
            attemptsLefts = getResources().getString(R.string.attempts_left, 3 - failedAttempts);
            AttemptsTextView.setText(attemptsLefts);
        }

        submitTableRow.setOnClickListener(view -> {
            String username = UsernameInput.getText().toString();
            String password = PassInput.getText().toString();

            // Memeriksa apakah input kosong
            if (username.isEmpty()) {
                Toast.makeText(Login.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(Login.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            // Memeriksa apakah username minimal 3 karakter
            if (username.length() < 3) {
                Toast.makeText(Login.this, "Username harus minimal 3 karakter", Toast.LENGTH_SHORT).show();
                return;
            }

            // Memeriksa apakah password minimal 8 karakter
            if (password.length() < 8) {
                Toast.makeText(Login.this, "Password harus minimal 8 karakter", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mengenkripsi password
            String encryptedPassword = encryptPassword(password);

            // Mendapatkan data pengguna yang ada
            Gson gson = new Gson();
            String json = sharedPref.getString("userData", "");
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            // Melakukan deserialisasi JSON menjadi objek HashMap
            Map<String, String> userData;
            userData = gson.fromJson(json, type);

            // Pengecekan apakah userData kosong atau tidak ada
            if (userData == null || userData.isEmpty()) {
                // Jika userData kosong, tampilkan Toast dan hentikan proses login
                Toast.makeText(Login.this, "Login gagal, Database Kosong!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (containsSpace(username) || containsSpace(password)) {
                // Menampilkan Toast jika input mengandung spasi
                Toast.makeText(this, "Username dan password tidak boleh mengandung spasi", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mengecek apakah data pengguna ada di set
            if (userData.containsKey(username)) {
                if (encryptedPassword.equals(userData.get(username))) {
                    // Login berhasil
                    Toast.makeText(Login.this, "Login berhasil", Toast.LENGTH_SHORT).show();
                    failedAttempts = 0;
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("failedAttempts", failedAttempts);
                    editor.putBoolean("isLoggedIn", true);
                    editor.apply();
                    Intent intent = new Intent(this, MenuLogin.class);
                    startActivity(intent);
                    finish();
                } else {
                    // Login gagal
                    failedAttempts++;

                    // Menyimpan jumlah percobaan login yang gagal
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("failedAttempts", failedAttempts);
                    editor.apply();

                    if (failedAttempts >= 3) {
                        // Jika pengguna telah mencoba login lebih dari 3 kali, nonaktifkan TableRow selama 1 menit
                        editor.putLong("lockoutStartTime", System.currentTimeMillis());
                        editor.apply();
                        submitTableRow.setEnabled(false);
                        new CountDownTimer(60000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                long secondsUntilFinished = millisUntilFinished / 1000;
                                String message = getResources().getString(R.string.login_attempts_message, secondsUntilFinished);
                                AttemptsTextView.setText(message);
                            }

                            public void onFinish() {
                                submitTableRow.setEnabled(true);
                                failedAttempts = 0;
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("failedAttempts", failedAttempts);
                                editor.apply();
                                String attemptsLeft = getResources().getString(R.string.attempts_left, 3);
                                AttemptsTextView.setText(attemptsLeft);
                            }
                        }.start();
                    } else {
                        String attemptsLeft = getResources().getString(R.string.attempts_left, 3 - failedAttempts);
                        AttemptsTextView.setText(attemptsLeft);
                    }
                    Toast.makeText(Login.this, "Login gagal, Password Salah!", Toast.LENGTH_SHORT).show();
                }
            } else {
                // Login gagal
                failedAttempts++;

                // Menyimpan jumlah percobaan login yang gagal
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putInt("failedAttempts", failedAttempts);
                editor.apply();

                if (failedAttempts >= 3) {
                    // Jika pengguna telah mencoba login lebih dari 3 kali, nonaktifkan TableRow selama 1 menit
                    editor.putLong("lockoutStartTime", System.currentTimeMillis());
                    editor.apply();
                    submitTableRow.setEnabled(false);
                    new CountDownTimer(60000, 1000) {
                        public void onTick(long millisUntilFinished) {
                            long secondsUntilFinished = millisUntilFinished / 1000;
                            String message = getResources().getString(R.string.login_attempts_message, secondsUntilFinished);
                            AttemptsTextView.setText(message);
                        }

                        public void onFinish() {
                            submitTableRow.setEnabled(true);
                            failedAttempts = 0;
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putInt("failedAttempts", failedAttempts);
                            editor.apply();
                            String attemptsLeft = getResources().getString(R.string.attempts_left, 3);
                            AttemptsTextView.setText(attemptsLeft);
                        }
                    }.start();
                } else {
                    String attemptsLeft = getResources().getString(R.string.attempts_left, 3 - failedAttempts);
                    AttemptsTextView.setText(attemptsLeft);
                }
                Toast.makeText(Login.this, "Login gagal, Username tidak ditemukan!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String encryptPassword(String password) {
        try {
            // Membuat instance MessageDigest dengan algoritma SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Mengenkripsi password
            byte[] hash;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            } else {
                try {
                    hash = digest.digest(password.getBytes("UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException("UTF-8 not supported", e);
                }
            }

            // Mengubah array byte menjadi string heksadesimal
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            // Mengembalikan string heksadesimal
            return hexString.toString();
        } catch(Exception ex) {
            throw new RuntimeException(ex);
        }
    }
    private boolean containsSpace(String input) {
        return input.contains(" ");
    }
}