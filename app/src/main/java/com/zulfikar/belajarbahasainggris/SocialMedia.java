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
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TableRow;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.List;

public class SocialMedia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialmedia);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TableRow githubRow = findViewById(R.id.menu1);
        githubRow.setOnClickListener(v -> openSocialMedia("https://github.com/zulfikar-bot/android-1.0.0.13112023/"));

        TableRow linkedinRow = findViewById(R.id.menu2);
        linkedinRow.setOnClickListener(v -> openSocialMedia("https://www.linkedin.com/in/muhammad-zulfikar-sachori-putra-701854154/"));

        TableRow youtubeRow = findViewById(R.id.menu3);
        youtubeRow.setOnClickListener(v -> openSocialMedia("https://www.youtube.com/@MuhammadZulfikarPutra"));

        TableRow twitterRow = findViewById(R.id.menu5);
        twitterRow.setOnClickListener(v -> openSocialMedia("https://twitter.com/mzulfikarsp/"));

        TableRow bloggerRow = findViewById(R.id.menu6);
        bloggerRow.setOnClickListener(v -> openSocialMedia("https://muhammadzulfikarputra.blogspot.com"));
        TableRow wordpressRow = findViewById(R.id.menu4);
        wordpressRow.setOnClickListener(v -> openSocialMedia("https://muhammadzulfikarputra.wordpress.com/"));

        TableRow pinterestRow = findViewById(R.id.menu7);
        pinterestRow.setOnClickListener(v -> openSocialMedia("https://id.pinterest.com/mzulfikarsp/"));

        TableRow facebookRow = findViewById(R.id.menu9);
        facebookRow.setOnClickListener(v -> openSocialMedia("https://www.facebook.com/profile.php/?id=100000658541302"));
    }

    private void openSocialMedia(String url) {
        if(!url.contains("facebook.com")) {
            // Daftar nama package aplikasi
            List<String> packageNames = Arrays.asList(
                    "com.linkedin.android", // LinkedIn
                    "com.google.android.youtube", // YouTube
                    "com.facebook.katana", // Facebook
                    "com.twitter.android", // Twitter
                    "com.pinterest", // Pinterest
                    "com.github.android", // Github
                    "com.linkedin.android.lite",
                    "com.facebook.lite",
                    "com.twitter.android.lite",
                    "com.pinterest.twa"
            );

            // Atur fungsi klik untuk membuka tautan di aplikasi atau browser
            Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            PackageManager packageManager = getPackageManager();
            List<ResolveInfo> activities = packageManager.queryIntentActivities(intent2, 0);

            boolean isAppAvailable = false;
            for (ResolveInfo info : activities) {
                for (String packageName : packageNames) {
                    if (packageNames.contains(info.activityInfo.packageName) || isPackageInstalled(packageName, packageManager)) {
                        isAppAvailable = true;
                        break;
                    }
                }
            }

            if (isAppAvailable) {
                // Ada aplikasi yang dapat menangani tautan, buka dengan Intent.ACTION_VIEW
                startActivity(intent2);
            } else {
                // Tidak ada aplikasi yang dapat menangani tautan, gunakan WebView
                WebViews.setUrls(url);

                // Your second action
                Intent intent = new Intent(this, WebViews.class);
                startActivity(intent);
            }
        } else {
            Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent2);
        }
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}