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
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class Scrollable extends AppCompatActivity {

    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollable);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getTitle());
        setSupportActionBar(toolbar);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);

        AppBarLayout appBarLayout = findViewById(R.id.app_bar);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }

                if (scrollRange + verticalOffset == 0) {
                    // AppBarLayout collapsed
                    switchMaterial.animate().alpha(1).setDuration(200).start();
                    switchMaterial.setVisibility(View.VISIBLE);
                    isShow = true;
                } else if (isShow) {
                    // AppBarLayout expanded
                    switchMaterial.animate().alpha(0).setDuration(200).start();
                    isShow = false;
                }
            }
        });

        // Dapatkan referensi ke objek CollapsingToolbarLayout jika Anda menggunakannya
        this.collapsingToolbarLayout = findViewById(R.id.collapsingToolbarLayout);

        // Ubah warna teks title
        if (collapsingToolbarLayout != null) {
            if (isDarkMode) {
                warna(R.color.white);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            } else {
                warna(R.color.black);
                getWindow().getDecorView().setSystemUiVisibility(0);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }
        } else {
            // Jika tidak menggunakan CollapsingToolbarLayout
            TextView titleTextView = toolbar.findViewById(R.id.title);
            if (titleTextView != null) {
                titleTextView.setTextColor(getResources().getColor(R.color.black));
            }
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Handler handler = new Handler();
            // Menampilkan Snackbar selama 5 detik
            Snackbar snackbar = Snackbar.make(view, "Mengalihkan... maupun Tap Pop Up ini.\nSilahkan tunggu hingga 5 detik.", Snackbar.LENGTH_LONG);

// Define a custom OnClickListener to perform multiple actions
            View.OnClickListener clickListener = v -> {
                // Your first action
                WebViews.setUrls("https://www.oxfordinternationalenglish.com/common-english-grammar-mistakes/");

                // Your second action
                Intent intent = new Intent(Scrollable.this, WebViews.class);
                startActivity(intent);

                // Dismiss the Snackbar
                snackbar.dismiss();
            };

// Set the custom OnClickListener to the Snackbar
            snackbar.setAction("Buka Web View", clickListener);

// Set another action if needed (optional)
            snackbar.setAction("Cancel", v -> {
                // Your action for "Cancel"
                handler.removeCallbacksAndMessages(null);
            });

            snackbar.show();

            // Menunda pembukaan web selama 5 detik
            handler.postDelayed(() -> {
                // Tindakan yang ingin dijalankan setelah penundaan
                // Ganti "YourWebURL" dengan URL web yang ingin ditampilkan
                WebViews.setUrls("https://www.oxfordinternationalenglish.com/common-english-grammar-mistakes/");

                // Start the WebViews activity
                Intent intent = new Intent(Scrollable.this, WebViews.class);
                startActivity(intent);
            }, 5000); // 5000 milidetik = 5 detik
        });
    }

    private void warna(int warna){
        collapsingToolbarLayout.setCollapsedTitleTextColor(getResources().getColor(warna));
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
        collapsingToolbarLayout.setExpandedTitleColor(getResources().getColor(R.color.black));
    }
}