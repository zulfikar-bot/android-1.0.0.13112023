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
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class QuizHome extends AppCompatActivity {
    int soal = Quiz.soal, detik = Quiz.detik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_home);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TextView soal1 = findViewById(R.id.soal);
        TextView waktu1 = findViewById(R.id.waktu);
        String tampilan1 = soal+" Question";
        String tampilan2 = detik+" Seconds";
        soal1.setText(tampilan1);
        waktu1.setText(tampilan2);

        Button start = findViewById(R.id.start);
        start.setOnClickListener(v -> {
            Intent next = new Intent(QuizHome.this, Quiz.class);
            startActivity(next);
        });
    }
}