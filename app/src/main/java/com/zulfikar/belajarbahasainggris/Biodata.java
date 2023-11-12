package com.zulfikar.belajarbahasainggris;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Biodata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TextView email = findViewById(R.id.email_value);
        email.setLinkTextColor(Color.BLUE);
        email.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:mzulganteng6@gmail.com"));
            startActivity(intent);
        });
    }
}