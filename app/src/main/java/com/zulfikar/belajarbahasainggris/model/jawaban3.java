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

public class jawaban3 implements Parcelable {

    private final String soal23, soal24, soal25, soal26, soal27;

    protected jawaban3(Parcel in) {
        soal23 = in.readString();
        soal24 = in.readString();
        soal25 = in.readString();
        soal26 = in.readString();
        soal27 = in.readString();
    }

    public static final Creator<jawaban3> CREATOR = new Creator<jawaban3>() {
        @Override
        public jawaban3 createFromParcel(Parcel in) {
            return new jawaban3(in);
        }

        @Override
        public jawaban3[] newArray(int size) {
            return new jawaban3[size];
        }
    };

    public String getSoal23() {
        return soal23;
    }

    public String getSoal24() {
        return soal24;
    }

    public String getSoal25() {
        return soal25;
    }

    public String getSoal26() {
        return soal26;
    }

    public String getSoal27() {
        return soal27;
    }

    public jawaban3(String soal23, String soal24, String soal25, String soal26, String soal27) {
        this.soal23 = soal23;
        this.soal24 = soal24;
        this.soal25 = soal25;
        this.soal26 = soal26;
        this.soal27 = soal27;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(soal23);
        parcel.writeString(soal24);
        parcel.writeString(soal25);
        parcel.writeString(soal26);
        parcel.writeString(soal27);
    }
}
