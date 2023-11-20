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

public class jawaban2 implements Parcelable {

    private String soal18, soal19, soal20, soal22, soal28;

    public jawaban2(String soal18, String soal19, String soal20, String soal22, String soal28) {
        this.soal18 = soal18;
        this.soal19 = soal19;
        this.soal20 = soal20;
        this.soal22 = soal22;
        this.soal28 = soal28;
    }

    protected jawaban2(Parcel in) {
        soal18 = in.readString();
        soal19 = in.readString();
        soal20 = in.readString();
        soal22 = in.readString();
        soal28 = in.readString();
    }

    public static final Creator<jawaban2> CREATOR = new Creator<jawaban2>() {
        @Override
        public jawaban2 createFromParcel(Parcel in) {
            return new jawaban2(in);
        }

        @Override
        public jawaban2[] newArray(int size) {
            return new jawaban2[size];
        }
    };

    public String getSoal18() {
        return soal18;
    }

    public void setSoal18(String soal18) {
        this.soal18 = soal18;
    }

    public String getSoal19() {
        return soal19;
    }

    public void setSoal19(String soal19) {
        this.soal19 = soal19;
    }

    public String getSoal20() {
        return soal20;
    }

    public void setSoal20(String soal20) {
        this.soal20 = soal20;
    }

    public String getSoal22() {
        return soal22;
    }

    public void setSoal22(String soal22) {
        this.soal22 = soal22;
    }

    public String getSoal28() {
        return soal28;
    }

    public void setSoal28(String soal28) {
        this.soal28 = soal28;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(soal18);
        parcel.writeString(soal19);
        parcel.writeString(soal20);
        parcel.writeString(soal22);
        parcel.writeString(soal28);
    }
}
