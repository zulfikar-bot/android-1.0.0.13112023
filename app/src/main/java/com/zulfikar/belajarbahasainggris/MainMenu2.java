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
import android.os.Bundle;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainMenu2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu2);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TextView atas = findViewById(R.id.atas);
        if (isDarkMode) {
            atas.setTextColor(getResources().getColor(R.color.colorAccent));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            atas.setTextColor(getResources().getColor(R.color.link));
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        TableRow menu4 = findViewById(R.id.menu4);
        menu4.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu2.this, QuizHome.class);
            startActivity(intent);
        });

        TableRow menu6 = findViewById(R.id.menu6);
        menu6.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu2.this, Tts.class);
            startActivity(intent);
        });

        TableRow menu7 = findViewById(R.id.menu7);
        menu7.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu2.this, Notepad.class);
            startActivity(intent);
        });
    }
}