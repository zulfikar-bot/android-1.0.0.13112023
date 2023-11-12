package com.zulfikar.belajarbahasainggris;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataLoadingUtility {
    public static final String THEME_PREFERENCE = "theme_preference";
    public static final String IS_DARK_MODE = "is_dark_mode";

    public static void loadData(SharedPreferences sharedPreferences, Activity activity, boolean isDarkMode) {
        LoadDataTask loadDataTask = new LoadDataTask(sharedPreferences, activity, isDarkMode);
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(loadDataTask);
    }

    private static class LoadDataTask implements Runnable {
        private final Handler handler = new Handler(Looper.getMainLooper());
        private final SharedPreferences sharedPreferences;
        private final Activity activity;
        private boolean isDarkMode;

        LoadDataTask(SharedPreferences sharedPreferences, Activity activity, boolean isDarkMode) {
            this.sharedPreferences = sharedPreferences;
            this.activity = activity;
            this.isDarkMode = isDarkMode;
        }

        @Override
        public void run() {
            // Perform background operation
            isDarkMode = loadThemePreference(sharedPreferences);

            // Post result on the UI thread
            handler.post(() -> initializeUI(activity, isDarkMode));
        }
    }

    public static void initializeUI(Activity activity, boolean isDarkMode) {
        SwitchMaterial switchs = activity.findViewById(R.id.dayxdark);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        // Mengatur tema berdasarkan preferensi
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        switchs.setChecked(isDarkMode);

        switchs.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Mengubah tema saat switch berubah
            toggleTheme(activity, isChecked);
        });
    }

    public static void toggleTheme(Activity activity, boolean isDarkMode) {
        SharedPreferences sharedPreferences = activity.getSharedPreferences(THEME_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(IS_DARK_MODE, isDarkMode);
        editor.apply();

        activity.recreate(); // Restart activity untuk menerapkan tema yang berubah
    }

    private static boolean loadThemePreference(SharedPreferences sharedPreferences) {
        return sharedPreferences.getBoolean(IS_DARK_MODE, false);
    }
}

