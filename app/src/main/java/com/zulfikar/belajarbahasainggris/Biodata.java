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
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
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
        email.setTextColor(getResources().getColor(R.color.colorAccent));
        email.setOnClickListener(view -> {
            String emailAddress = email.getText().toString();
            // Atur teks tautan untuk alamat email
            SpannableString emails = new SpannableString(emailAddress);
            Linkify.addLinks(emails, Linkify.EMAIL_ADDRESSES);
            email.setText(emails);
            email.setMovementMethod(LinkMovementMethod.getInstance());

            // Membuat intent untuk mengirim email
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + emailAddress));

            // Memeriksa apakah ada aplikasi yang dapat menangani intent
            if (emailIntent.resolveActivity(getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                // Jika tidak ada aplikasi yang dapat menangani intent, berikan informasi atau buka browser
                // Misalnya, membuka browser dan membuka Gmail di versi web
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com"));
                startActivity(webIntent);
            }
        });
    }
}