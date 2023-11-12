package com.zulfikar.belajarbahasainggris;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private boolean isAppClosing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);
    }

    @Override
    public void onBackPressed() {
        if (!isAppClosing) {
            // Show a confirmation dialog when attempting to close the app
            new AlertDialog.Builder(this)
                    .setTitle("Apakah Anda ingin menutup aplikasi ini?")
                    .setPositiveButton("Ya", (dialog, which) -> {
                        isAppClosing = true;
                        finish();
                    })
                    .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss())
                    .show();
        } else {
            super.onBackPressed();
        }
    }

    public void openMainMenu(View view) {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}