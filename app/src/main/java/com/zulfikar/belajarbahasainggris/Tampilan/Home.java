/*
 * *
 *  * Copyright (c) 2023 - Muhammad Zulfikar Sachori Putra
 *  * Nama Aplikasi : Belajar Bahasa Inggris
 *  * Nama Package : com.zulfikar.belajarbahasainggris
 *  * Versi Aplikasi : 1.0.1.20112023
 *
 *
 */

package com.zulfikar.belajarbahasainggris.Tampilan;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;
import static com.zulfikar.belajarbahasainggris.Tampilan.Profil.NAME;
import static com.zulfikar.belajarbahasainggris.Tampilan.Profil.PROFIL;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.zulfikar.belajarbahasainggris.ActivityLoading;
import com.zulfikar.belajarbahasainggris.DataLoadingUtility;
import com.zulfikar.belajarbahasainggris.R;

public class Home extends AppCompatActivity {
    ConstraintLayout layout;
    Button exit, play, about, profil2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_home);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        ImageView profil = findViewById(R.id.profil);
        TextView name = findViewById(R.id.name);
        this.layout = findViewById(R.id.layout);
        this.exit = findViewById(R.id.button_exit);
        this.play = findViewById(R.id.button_play);
        this.about = findViewById(R.id.button_about);
        this.profil2 = findViewById(R.id.btnprofil);
        profil2.setOnClickListener(v -> {
            Intent intent = new Intent(this, Profil.class);
            startActivity(intent);
        });
        exit.setOnClickListener(v -> {
            Intent intent = new Intent(this, ActivityLoading.class);
            startActivity(intent);
            finish();
        });
        play.setOnClickListener(v -> {
            Intent intent = new Intent(this, Play.class);
            startActivity(intent);
        });
        about.setOnClickListener(v -> {
            Intent intent = new Intent(this, ViewPager.class);
            startActivity(intent);
        });
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            warna(R.drawable.bluebg, Color.BLACK);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            warna(R.drawable.ttsbg2, Color.WHITE);
        }
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String getname = extras.getString(NAME);
            String getprofil = extras.getString(PROFIL);
            name.setText(getname);
            if (getprofil != null) {
                profil.setImageURI(Uri.parse(getprofil));
            }
        }

        // Inside your activity's onCreate method
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent intent = new Intent(Home.this, ActivityLoading.class);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    private void warna(int bg, int color) {
        Drawable background = ResourcesCompat.getDrawable(getResources(), bg, null);
        layout.setBackground(background);
        exit.setTextColor(color);
        play.setTextColor(color);
        about.setTextColor(color);
        profil2.setTextColor(color);
    }
}
