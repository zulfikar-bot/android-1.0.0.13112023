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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    RealmResults<Note> notesList;

    public interface OnNoteClickListener {
        void onNoteClick(long noteId);
        void onLongNoteClick(long noteId);
    }

    // Di dalam MyAdapter
    private final OnNoteClickListener onNoteClickListener;

    public MyAdapter(Context context, RealmResults<Note> notesList, OnNoteClickListener onNoteClickListener) {
        this.context = context;
        this.notesList = notesList;
        this.onNoteClickListener = onNoteClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        Note note = notesList.get(position);
        if (note != null && note.getTitle() != null) {
            String title = note.getTitle();

            if (title.length() > 80) {
                title = title.substring(0, 77) + "...";
            }

            holder.titleOutput.setText(title);
        } else {
            holder.titleOutput.setText(context.getString(R.string.default_title)); // Or any default text you want to set
        }
        if (note != null && note.getDescription() != null) {
            String description = note.getDescription();

            if (description.length() > 100) {
                description = description.substring(0, 97) + "...";
            }

            holder.descriptionOutput.setText(description);
        } else {
            holder.descriptionOutput.setText(context.getString(R.string.default_description));
        }

        if (note != null && note.getCreatedTime() != 0 && note.getEditedTime() != 0 && note.getId() != 0) {
            String formattedID = Long.toString(note.getId());
            String formattedTime = DateFormat.getDateTimeInstance().format(note.getCreatedTime());
            String formattedTimes = DateFormat.getDateTimeInstance().format(note.getEditedTime());
            String output = context.getString(R.string.formatted_output, formattedID, formattedTime, formattedTimes);
            holder.timeOutput.setText(output);
        } else {
            holder.timeOutput.setText(context.getString(R.string.default_time));
        }

        holder.itemView.setOnLongClickListener(v -> {

            PopupMenu menu = new PopupMenu(context,v);
            menu.getMenu().add("DELETE");
            menu.getMenu().add("EDIT");
            menu.setOnMenuItemClickListener(item -> {
                CharSequence title = item.getTitle();
                if (title != null && "DELETE".equals(title.toString())) {
                    //delete the note if it's not null
                    if (note != null) {
                        Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        note.deleteFromRealm();
                        realm.commitTransaction();
                        Toast.makeText(context,"Note deleted",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the case where 'note' is null
                        Toast.makeText(context,"Unable to delete note",Toast.LENGTH_SHORT).show();
                    }
                } else if (title != null && "EDIT".equals(title.toString())) {
                    if (note != null) {
                        Note selectedNote = notesList.get(position);
                        if (selectedNote != null && onNoteClickListener != null) {
                            onNoteClickListener.onLongNoteClick(selectedNote.getId());
                        }
                        Toast.makeText(context,"Redirect to Edited Notepad Menu",Toast.LENGTH_SHORT).show();
                    } else {
                        // Handle the case where 'note' is null
                        Toast.makeText(context,"Unable to delete note",Toast.LENGTH_SHORT).show();
                    }
                }
                return true;
            });
            menu.show();

            return true;
        });

        holder.itemView.setOnClickListener(v -> {
            Note selectedNote = notesList.get(position);
            if (selectedNote != null && onNoteClickListener != null) {
                onNoteClickListener.onNoteClick(selectedNote.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView titleOutput;
        TextView descriptionOutput;
        TextView timeOutput;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleOutput = itemView.findViewById(R.id.titleoutput);
            descriptionOutput = itemView.findViewById(R.id.descriptionoutput);
            timeOutput = itemView.findViewById(R.id.timeoutput);
        }
    }
}