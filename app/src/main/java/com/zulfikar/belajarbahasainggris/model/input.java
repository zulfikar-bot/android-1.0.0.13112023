/*
 * *
 *  * Copyright (c) 2023 - Muhammad Zulfikar Sachori Putra
 *  * Nama Aplikasi : Belajar Bahasa Inggris
 *  * Nama Package : com.zulfikar.belajarbahasainggris
 *  * Versi Aplikasi : 1.0.1.20112023
 *
 *
 */

package com.zulfikar.belajarbahasainggris.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class input implements Parcelable {
    private final List<String> jawaban1, jawaban2, jawaban3, jawaban4, jawaban5;

    private final List<String> input1, input2, input3, input4, input5;

    protected input(Parcel in) {
        jawaban1 = in.createStringArrayList();
        jawaban2 = in.createStringArrayList();
        jawaban3 = in.createStringArrayList();
        jawaban4 = in.createStringArrayList();
        jawaban5 = in.createStringArrayList();
        input1 = in.createStringArrayList();
        input2 = in.createStringArrayList();
        input3 = in.createStringArrayList();
        input4 = in.createStringArrayList();
        input5 = in.createStringArrayList();
    }

    public static final Creator<input> CREATOR = new Creator<input>() {
        @Override
        public input createFromParcel(Parcel in) {
            return new input(in);
        }

        @Override
        public input[] newArray(int size) {
            return new input[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringList(jawaban1);
        parcel.writeStringList(jawaban2);
        parcel.writeStringList(jawaban3);
        parcel.writeStringList(jawaban4);
        parcel.writeStringList(jawaban5);
        parcel.writeStringList(input1);
        parcel.writeStringList(input2);
        parcel.writeStringList(input3);
        parcel.writeStringList(input4);
        parcel.writeStringList(input5);
    }
}
