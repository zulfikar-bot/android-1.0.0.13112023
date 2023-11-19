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

public class QuizResult extends AppCompatActivity {

    private int nilai = 0;
    private int benar = 0;
    private int salah = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TextView tvNilai = findViewById(R.id.tvNilai);
        TextView tvMessage = findViewById(R.id.tvMessage);
        Button btnUlang = findViewById(R.id.btnUlang);
        TextView tvHasil = findViewById(R.id.tvhasil);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            this.nilai = extras.getInt("nilai", 0);
            this.benar = extras.getInt("benar", 0);
            this.salah = extras.getInt("salah", 0);
        }

        String test = "Jawaban Benar: " + benar +" , "+ "Jawaban Salah: " + salah;
        tvMessage.setText(test);
        tvNilai.setText(String.valueOf(nilai));

        String nilaitest = "Kamu Mendapatkan Nilai ";
        String lulus = " Kamu Berhasil!!!";
        String gagal = " Kamu Gagal!!!";
        String[] value = {
                "A+ ", "A ", "B+ ", "B ", "C+ ", "C ", "D ", "E "
        };
        if (nilai == 100){
            String s1 = nilaitest+value[0]+"("+nilai+"),"+lulus;
            tvHasil.setText(s1);
        }else if (nilai >= 93){
            String s2 = nilaitest+value[1]+"("+nilai+"),"+lulus;
            tvHasil.setText(s2);
        }else if (nilai >= 86){
            String s3 = nilaitest+value[2]+"("+nilai+"),"+lulus;
            tvHasil.setText(s3);
        }else if (nilai >= 80){
            String s4 = nilaitest+value[3]+"("+nilai+"),"+lulus;
            tvHasil.setText(s4);
        }else if (nilai >= 75){
            String s5 = nilaitest+value[4]+"("+nilai+"),"+lulus;
            tvHasil.setText(s5);
        }else if (nilai >= 70){
            String s6 = nilaitest+value[5]+"("+nilai+"),"+lulus;
            tvHasil.setText(s6);
        }else if (nilai >= 35){
            String s7 = nilaitest+value[6]+"("+nilai+"),"+gagal;
            tvHasil.setText(s7);
        }else if (nilai >= 0){
            String s8 = nilaitest+value[7]+"("+nilai+"),"+gagal;
            tvHasil.setText(s8);
        }

        btnUlang.setOnClickListener(v -> {
            Intent back = new Intent(getApplicationContext(), Quiz.class);
            startActivity(back);
            finish();
        });
    }
}