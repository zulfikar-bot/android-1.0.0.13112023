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

public class jawaban implements Parcelable{

    private final String soal1, soal2, soal3, soal4, soal5, soal12;

    public jawaban(String soal1, String soal2, String soal3, String soal4, String soal5, String soal12) {
        this.soal1 = soal1;
        this.soal2 = soal2;
        this.soal3 = soal3;
        this.soal4 = soal4;
        this.soal5 = soal5;
        this.soal12 = soal12;
    }

    protected jawaban(Parcel in) {
        soal1 = in.readString();
        soal2 = in.readString();
        soal3 = in.readString();
        soal4 = in.readString();
        soal5 = in.readString();
        soal12 = in.readString();
    }

    public static final Creator<jawaban> CREATOR = new Creator<jawaban>() {
        @Override
        public jawaban createFromParcel(Parcel in) {
            return new jawaban(in);
        }

        @Override
        public jawaban[] newArray(int size) {
            return new jawaban[size];
        }
    };

    public String getSoal1() {
        return soal1;
    }

    public String getSoal2() {
        return soal2;
    }

    public String getSoal3() {
        return soal3;
    }

    public String getSoal4() {
        return soal4;
    }

    public String getSoal5() {
        return soal5;
    }

    public String getSoal12() {
        return soal12;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(soal1);
        parcel.writeString(soal2);
        parcel.writeString(soal3);
        parcel.writeString(soal4);
        parcel.writeString(soal5);
        parcel.writeString(soal12);
    }
}
