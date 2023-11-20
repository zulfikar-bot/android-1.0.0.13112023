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

public class jawaban5 implements Parcelable {
    private String soal13, soal14, soal15, soal16, soal17, soal21;

    public jawaban5(String soal13, String soal14, String soal15, String soal16, String soal17, String soal21) {
        this.soal13 = soal13;
        this.soal14 = soal14;
        this.soal15 = soal15;
        this.soal16 = soal16;
        this.soal17 = soal17;
        this.soal21 = soal21;
    }

    protected jawaban5(Parcel in) {
        soal13 = in.readString();
        soal14 = in.readString();
        soal15 = in.readString();
        soal16 = in.readString();
        soal17 = in.readString();
        soal21 = in.readString();
    }

    public static final Creator<jawaban5> CREATOR = new Creator<jawaban5>() {
        @Override
        public jawaban5 createFromParcel(Parcel in) {
            return new jawaban5(in);
        }

        @Override
        public jawaban5[] newArray(int size) {
            return new jawaban5[size];
        }
    };

    public String getSoal13() {
        return soal13;
    }

    public void setSoal13(String soal13) {
        this.soal13 = soal13;
    }

    public String getSoal14() {
        return soal14;
    }

    public void setSoal14(String soal14) {
        this.soal14 = soal14;
    }

    public String getSoal15() {
        return soal15;
    }

    public void setSoal15(String soal15) {
        this.soal15 = soal15;
    }

    public String getSoal16() {
        return soal16;
    }

    public void setSoal16(String soal16) {
        this.soal16 = soal16;
    }

    public String getSoal17() {
        return soal17;
    }

    public void setSoal17(String soal17) {
        this.soal17 = soal17;
    }

    public String getSoal21() {
        return soal21;
    }

    public void setSoal21(String soal21) {
        this.soal21 = soal21;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(soal13);
        parcel.writeString(soal14);
        parcel.writeString(soal15);
        parcel.writeString(soal16);
        parcel.writeString(soal17);
        parcel.writeString(soal21);
    }
}
