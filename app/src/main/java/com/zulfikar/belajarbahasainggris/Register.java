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
import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    private EditText PassInput;
    private CheckBox ShowPass;
    private EditText UsernameInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TextView login2 = findViewById(R.id.login2);
        login2.setLinkTextColor(Color.BLUE);
        login2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Login.class);
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
        TableRow submitTableRows = findViewById(R.id.submits);
        submitTableRows.setOnClickListener(view -> {
            String username = UsernameInput.getText().toString();
            String password = PassInput.getText().toString();

            // Memeriksa apakah input kosong
            if (username.isEmpty()) {
                Toast.makeText(Register.this, "Username tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.isEmpty()) {
                Toast.makeText(Register.this, "Password tidak boleh kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            // Memeriksa apakah username minimal 3 karakter
            if (username.length() < 3) {
                Toast.makeText(Register.this, "Username harus minimal 3 karakter", Toast.LENGTH_SHORT).show();
                return;
            }

            // Memeriksa apakah password minimal 8 karakter
            if (password.length() < 8) {
                Toast.makeText(Register.this, "Password harus minimal 8 karakter", Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.equals(username)) {
                Toast.makeText(Register.this, "Password dan username tidak boleh sama", Toast.LENGTH_SHORT).show();
                return;
            }

            // Mengenkripsi password
            String encryptedPassword = encryptPassword(password);

            // Mendapatkan SharedPreferences
            SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);

            // Mendapatkan data pengguna yang ada
            Gson gson = new Gson();
            String json = sharedPref.getString("userData", "");
            Type type = new TypeToken<Map<String, String>>(){}.getType();
            Map<String, String> userData = gson.fromJson(json, type);

            if (userData == null) {
                userData = new HashMap<>();
            }

            // Mengecek apakah username sudah ada
            if (userData.containsKey(username)) {
                // Username sudah ada
                Toast.makeText(this, "Username sudah ada", Toast.LENGTH_SHORT).show();
            } else {
                // Menambahkan data pengguna baru
                userData.put(username, encryptedPassword);

                // Menyimpan data pengguna
                SharedPreferences.Editor editor = sharedPref.edit();
                json = gson.toJson(userData);
                editor.putString("userData", json);
                editor.apply();

                // Menampilkan pesan bahwa data pengguna telah disimpan
                Toast.makeText(this, "Data pengguna telah disimpan", Toast.LENGTH_SHORT).show();
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
}