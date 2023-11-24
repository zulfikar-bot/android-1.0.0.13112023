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
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.zulfikar.belajarbahasainggris.Tampilan.ViewPager;

public class Tts extends AppCompatActivity {

    ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE)
                .getBoolean(DataLoadingUtility.IS_DARK_MODE, false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        this.layout = findViewById(R.id.layout);
        if (isDarkMode) {
            warna(R.drawable.bluebg);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            warna(R.drawable.ttsbg3);
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        new Handler().postDelayed(() -> {
            Intent i = new Intent(Tts.this, ViewPager.class);
            startActivity(i);
            finish();
        }, 1500);
    }

    private void warna(int bg) {
        Drawable background = ResourcesCompat.getDrawable(getResources(), bg, null);
        layout.setBackground(background);
    }
}
