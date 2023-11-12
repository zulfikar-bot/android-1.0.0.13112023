package com.zulfikar.belajarbahasainggris;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TableRow menu1 = findViewById(R.id.menu1);
        menu1.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Biodata.class);
            startActivity(intent);
        });

        TableRow menu2 = findViewById(R.id.menu2);
        menu2.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Scrollable.class);
            startActivity(intent);
        });

        TableRow menu3 = findViewById(R.id.menu3);
        menu3.setOnClickListener(v -> {
            WebViews.setUrls();
            Intent intent = new Intent(MainMenu.this, WebViews.class);
            startActivity(intent);
        });

        TableRow menu4 = findViewById(R.id.menu4);
        menu4.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, Kalkulator.class);
            startActivity(intent);
        });

        TableRow menu5 = findViewById(R.id.menu5);
        menu5.setOnClickListener(v -> {
            Intent intent = new Intent(MainMenu.this, SocialMedia.class);
            startActivity(intent);
        });

        TableRow menu6 = findViewById(R.id.menu6);
        menu6.setOnClickListener(v -> {
            SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
            boolean isLoggedIn = sharedPref.getBoolean("isLoggedIn", false);
            Intent intent;
            if (!isLoggedIn) {
                // Mengarahkan pengguna ke halaman login
                intent = new Intent(this, Login.class);
            } else {
                intent = new Intent(this, MenuLogin.class);
            }
            startActivity(intent);
        });
    }
}