package com.zulfikar.belajarbahasainggris;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class Logout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        new Handler().postDelayed(() -> {
            SharedPreferences sharedPref = getSharedPreferences("UserData", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            // Arahkan pengguna ke menu sebelumnya
            Intent intent = new Intent(Logout.this, MainMenu.class);
            startActivity(intent);
            finish();
        }, 5000);  // Menunda selama 5 detik
    }
}