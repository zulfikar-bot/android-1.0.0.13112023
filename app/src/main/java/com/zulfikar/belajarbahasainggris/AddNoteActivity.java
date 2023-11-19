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

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.switchmaterial.SwitchMaterial;

import io.realm.Realm;

public class AddNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Add Notepad");
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.toolbarTextColor, typedValue, true);
        int color = typedValue.data;
        toolbar.setTitleTextColor(color);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);
        switchMaterial.setTextColor(color);

        setSupportActionBar(toolbar);

        TextView titletextview = findViewById(R.id.titletextview);
        EditText titleInput = findViewById(R.id.titleinput);
        EditText descriptionInput = findViewById(R.id.descriptioninput);
        MaterialButton saveBtn = findViewById(R.id.savebtn);

        if (isDarkMode) {
            titletextview.setTextColor(getResources().getColor(R.color.black));
            titleInput.setTextColor(getResources().getColor(R.color.black));
            descriptionInput.setTextColor(getResources().getColor(R.color.black));
            titleInput.setHintTextColor(getResources().getColor(R.color.black));
            descriptionInput.setHintTextColor(getResources().getColor(R.color.black));
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            titletextview.setTextColor(getResources().getColor(R.color.white));
            titleInput.setTextColor(getResources().getColor(R.color.white));
            descriptionInput.setTextColor(getResources().getColor(R.color.white));
            titleInput.setHintTextColor(getResources().getColor(R.color.white));
            descriptionInput.setHintTextColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        saveBtn.setOnClickListener(v -> {
            String title = titleInput.getText().toString();
            String description = descriptionInput.getText().toString();
            long createdTime = System.currentTimeMillis();
            long id = System.currentTimeMillis();

            realm.beginTransaction();
            Note note = realm.createObject(Note.class);
            note.setId(id);
            note.setTitle(title);
            note.setDescription(description);
            note.setCreatedTime(createdTime);
            note.setEditedTime(createdTime);
            realm.commitTransaction();
            Toast.makeText(getApplicationContext(),"Note saved",Toast.LENGTH_SHORT).show();
            finish();


        });


    }
}
