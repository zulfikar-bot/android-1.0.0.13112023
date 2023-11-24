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

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.zulfikar.belajarbahasainggris.DataLoadingUtility;
import com.zulfikar.belajarbahasainggris.R;

import java.io.IOException;

public class Profil extends AppCompatActivity {
    public static final String NAME = "name";
    public static final String PROFIL = "profil";

    private static final String TAG = Profil.class.getCanonicalName();
    private EditText name;
    private Button buttonedit;
    private ImageView profil, fakeprofil;
    private ConstraintLayout layout;
    String img;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_profil);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        name = findViewById(R.id.name);
        fakeprofil = findViewById(R.id.fakeimg);
        profil = findViewById(R.id.img);
        profil.setImageResource(0);

        this.buttonedit = findViewById(R.id.buttonedit);
        this.layout = findViewById(R.id.layout);
        if (isDarkMode) {
            warna(R.color.bgprofil2, Color.BLACK);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            warna(R.color.bgprofil, Color.WHITE);
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        buttonedit.setOnClickListener(v -> {
            if(name.length() == 0){
                Toast.makeText(Profil.this, "Nama Belum Terisi", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(Profil.this, "Profil Berhasil Disimpan", Toast.LENGTH_LONG).show();
                EditProfile();
                finish();
            }
        });

        ActivityResultLauncher<Intent> mLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        return;
                    }
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data != null) {
                            try {
                                Uri imageUri = data.getData();
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                                profil.setImageBitmap(bitmap);
                                System.out.println(imageUri);
                                setImg(String.valueOf(imageUri));
                                fakeprofil.setImageResource(0);
                            } catch (IOException e) {
                                Toast.makeText(Profil.this, "Can't load image", Toast.LENGTH_SHORT).show();
                                if (e.getMessage() != null) {
                                    Log.e(TAG, e.getMessage());
                                } else {
                                    Log.e(TAG, "An error occurred, but the error message was null");
                                }
                            }
                        }
                    }
                }
        );

        profil.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            mLauncher.launch(intent);
        });
    }

    private void warna(int bg, int warna) {
        buttonedit.setTextColor(warna);
        Drawable background = ResourcesCompat.getDrawable(getResources(), bg, null);
        layout.setBackground(background);
    }

    public void EditProfile() {
        String name = this.name.getText().toString();

        Intent intent = new Intent(this, Home.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(NAME, name);
        intent.putExtra(PROFIL, getImg());
        startActivity(intent);
    }
}
