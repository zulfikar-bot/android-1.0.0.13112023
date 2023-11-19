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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.text.DateFormat;

import io.realm.Realm;

public class DetailNoteActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> editActivityResultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_note);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Notepad");
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.toolbarTextColor, typedValue, true);
        int color = typedValue.data;
        toolbar.setTitleTextColor(color);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);
        switchMaterial.setTextColor(color);

        // Mengambil ID catatan yang dipilih dari intent
        long noteId = getIntent().getLongExtra("note_id", -1);

        // Mencari catatan berdasarkan ID di Realm
        Realm.init(getApplicationContext());
        Realm realm = Realm.getDefaultInstance();

        TextView titleOutput = findViewById(R.id.titleoutput);
        TextView descriptionOutput = findViewById(R.id.descriptionoutput);
        TextView timeOutput = findViewById(R.id.timeoutput);
        RelativeLayout relativeLayout = findViewById(R.id.relativelayout);
        Note note = realm.where(Note.class).equalTo("id", noteId).findFirst();
        if (note != null) {
            String formattedID = note.getId() != 0 ? Long.toString(note.getId()) : null;
            String formattedTime = note.getCreatedTime() != 0 ? DateFormat.getDateTimeInstance().format(note.getCreatedTime()) : null;
            String formattedTimes = note.getEditedTime() != 0 ? DateFormat.getDateTimeInstance().format(note.getEditedTime()) : null;
            String output = formattedID != null && formattedTime != null && formattedTimes != null ? getString(R.string.formatted_output, formattedID, formattedTime, formattedTimes) : getString(R.string.default_time);
            String title = note.getTitle() != null ? note.getTitle() : getString(R.string.default_title);
            String description = note.getDescription() != null ? note.getDescription() : getString(R.string.default_description);

            titleOutput.setText(title);
            descriptionOutput.setText(description);
            timeOutput.setText(output);
        }
        editActivityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Note note2 = realm.where(Note.class).equalTo("id", noteId).findFirst();
                        if (note2 != null) {
                            // Segarkan data
                            String formattedID2 = note2.getId() != 0 ? Long.toString(note2.getId()) : null;
                            String formattedTime2 = note2.getCreatedTime() != 0 ? DateFormat.getDateTimeInstance().format(note2.getCreatedTime()) : null;
                            String formattedTimes2 = note2.getEditedTime() != 0 ? DateFormat.getDateTimeInstance().format(note2.getEditedTime()) : null;
                            String output2 = formattedID2 != null && formattedTime2 != null && formattedTimes2 != null ? getString(R.string.formatted_output, formattedID2, formattedTime2, formattedTimes2) : getString(R.string.default_time);
                            String title2 = note2.getTitle() != null ? note2.getTitle() : getString(R.string.default_title);
                            String description2 = note2.getDescription() != null ? note2.getDescription() : getString(R.string.default_description);

                            titleOutput.setText(title2);
                            descriptionOutput.setText(description2);
                            timeOutput.setText(output2);
                        }
                    }
                }
        );
        relativeLayout.setOnLongClickListener(v -> {
            PopupMenu menu = new PopupMenu(this,v);
            menu.getMenu().add("EDIT");
            menu.getMenu().add("DELETE");
            menu.setOnMenuItemClickListener(item -> {
                CharSequence title = item.getTitle();
                if (title != null && "DELETE".equals(title.toString())) {
                    //delete the note if it's not null
                    if (note != null) {
                        realm.beginTransaction();
                        note.deleteFromRealm();
                        realm.commitTransaction();
                        Toast.makeText(this,"Note deleted",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the case where 'note' is null
                        Toast.makeText(this,"Unable to delete note",Toast.LENGTH_SHORT).show();
                    }
                } else if (title != null && "EDIT".equals(title.toString())) {
                    if (note != null) {
                        Intent intent = new Intent(this, EditNoteActivity.class);
                        intent.putExtra("note_id", noteId); // Kirim ID catatan yang dipilih
                        editActivityResultLauncher.launch(intent);
                        Toast.makeText(this,"Redirect to Edited Notepad Menu",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the case where 'note' is null
                        Toast.makeText(this,"Unable to delete note",Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            });
            menu.show();

            return true;
        });
    }
}
