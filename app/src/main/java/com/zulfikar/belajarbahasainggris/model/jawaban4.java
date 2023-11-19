package com.zulfikar.belajarbahasainggris.model;

import android.os.Parcel;
import android.os.Parcelable;

public class jawaban4 implements Parcelable {

    private String soal6, soal7, soal8, soal9, soal10, soal11;

    public jawaban4(String soal6, String soal7, String soal8, String soal9, String soal10, String soal11) {
        this.soal6 = soal6;
        this.soal7 = soal7;
        this.soal8 = soal8;
        this.soal9 = soal9;
        this.soal10 = soal10;
        this.soal11 = soal11;
    }

    protected jawaban4(Parcel in) {
        soal6 = in.readString();
        soal7 = in.readString();
        soal8 = in.readString();
        soal9 = in.readString();
        soal10 = in.readString();
        soal11 = in.readString();
    }

    public static final Creator<jawaban4> CREATOR = new Creator<jawaban4>() {
        @Override
        public jawaban4 createFromParcel(Parcel in) {
            return new jawaban4(in);
        }

        @Override
        public jawaban4[] newArray(int size) {
            return new jawaban4[size];
        }
    };

    public String getSoal6() {
        return soal6;
    }

    public void setSoal6(String soal6) {
        this.soal6 = soal6;
    }

    public String getSoal7() {
        return soal7;
    }

    public void setSoal7(String soal7) {
        this.soal7 = soal7;
    }

    public String getSoal8() {
        return soal8;
    }

    public void setSoal8(String soal8) {
        this.soal8 = soal8;
    }

    public String getSoal9() {
        return soal9;
    }

    public void setSoal9(String soal9) {
        this.soal9 = soal9;
    }

    public String getSoal10() {
        return soal10;
    }

    public void setSoal10(String soal10) {
        this.soal10 = soal10;
    }

    public String getSoal11() {
        return soal11;
    }

    public void setSoal11(String soal11) {
        this.soal11 = soal11;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(soal6);
        parcel.writeString(soal7);
        parcel.writeString(soal8);
        parcel.writeString(soal9);
        parcel.writeString(soal10);
        parcel.writeString(soal11);
    }
}
