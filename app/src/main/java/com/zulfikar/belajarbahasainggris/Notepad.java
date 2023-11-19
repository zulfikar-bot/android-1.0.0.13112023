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
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import io.realm.Realm;
import io.realm.RealmResults;
import io.realm.Sort;

public class Notepad extends AppCompatActivity implements MyAdapter.OnNoteClickListener {
    private MyAdapter myAdapter;
    private Realm realm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
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

        setSupportActionBar(toolbar);

        Button addNoteBtn = findViewById(R.id.addnewnotebtn);
        addNoteBtn.setTextColor(color);
        addNoteBtn.setOnClickListener(v -> startActivity(new Intent(Notepad.this,AddNoteActivity.class)));

        Realm.init(getApplicationContext());
        realm = Realm.getDefaultInstance();

        RealmResults<Note> notesList = realm.where(Note.class).sort("createdTime", Sort.DESCENDING).findAll();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new MyAdapter(getApplicationContext(), notesList, this);
        recyclerView.setAdapter(myAdapter);

        notesList.addChangeListener((notes, changeSet) -> {
            if (changeSet != null) {
                // Determine changes and update the adapter accordingly
                int[] insertions = changeSet.getInsertions();
                int[] deletions = changeSet.getDeletions();
                int[] changes = changeSet.getChanges();

                // Handle insertions
                for (int index : insertions) {
                    myAdapter.notifyItemInserted(index);
                }

                // Handle deletions
                for (int index : deletions) {
                    myAdapter.notifyItemRemoved(index);
                }

                // Handle modifications
                for (int index : changes) {
                    myAdapter.notifyItemChanged(index);
                }
            } else {
                // Handle a case where changeSet is null (for example, when all data is deleted)
                myAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    public void onNoteClick(long noteId) {
        // Panggil aktivitas baru untuk menampilkan detail catatan
        Intent intent = new Intent(this, DetailNoteActivity.class);
        intent.putExtra("note_id", noteId); // Kirim ID catatan yang dipilih
        startActivity(intent);
    }

    @Override
    public void onLongNoteClick(long noteId) {
        // Panggil aktivitas baru untuk menampilkan detail catatan
        Intent intent = new Intent(this, EditNoteActivity.class);
        intent.putExtra("note_id", noteId); // Kirim ID catatan yang dipilih
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Pastikan untuk menutup instance realm setelah selesai digunakan
        if (realm != null) {
            realm.close();
        }
    }
}
