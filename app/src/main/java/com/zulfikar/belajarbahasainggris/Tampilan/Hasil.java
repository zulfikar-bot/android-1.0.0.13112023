/*
 * *
 *  * Copyright (c) 2023 - Muhammad Zulfikar Sachori Putra
 *  * Nama Aplikasi : Belajar Bahasa Inggris
 *  * Nama Package : com.zulfikar.belajarbahasainggris
 *  * Versi Aplikasi : 1.0.1.20112023
 *
 *
 */

package com.zulfikar.belajarbahasainggris.Tampilan;

import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_1;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_2;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_3;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_4;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_5;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.zulfikar.belajarbahasainggris.R;
import com.zulfikar.belajarbahasainggris.model.jawaban;
import com.zulfikar.belajarbahasainggris.model.jawaban2;
import com.zulfikar.belajarbahasainggris.model.jawaban3;
import com.zulfikar.belajarbahasainggris.model.jawaban4;
import com.zulfikar.belajarbahasainggris.model.jawaban5;

public class Hasil extends AppCompatActivity {
    TextView edit1,edit2,edit3,edit4,edit5,edit12;
    TextView edit6, edit7, edit8, edit9, edit10, edit11;
    TextView edit13, edit14, edit15, edit16, edit17, edit21;
    TextView edit18, edit19, edit20, edit22, edit28;
    TextView edit23, edit24, edit25, edit26, edit27;
    TextView edit12a, edit6a, edit21a, edit10a, edit28a;
    TextView ket, ket2, ket3, ket4, ket5, hasil;
    String benar = "*Jawaban Benar";
    String salah = "*Jawaban Salah";
    int score;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = (this.score) + score*25;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);

        ket = findViewById(R.id.ket);
        ket2 = findViewById(R.id.ket2);
        ket3 = findViewById(R.id.ket3);
        ket4 = findViewById(R.id.ket4);
        ket5 = findViewById(R.id.ket5);
        hasil = findViewById(R.id.score);

        edit12a = findViewById(R.id.edit12a);
        edit6a = findViewById(R.id.edit6a);
        edit21a = findViewById(R.id.edit21a);
        edit10a = findViewById(R.id.edit10a);
        edit28a = findViewById(R.id.edit28a);

        edit1 = findViewById(R.id.edit1);
        edit2 = findViewById(R.id.edit2);
        edit3 = findViewById(R.id.edit3);
        edit4 = findViewById(R.id.edit4);
        edit5 = findViewById(R.id.edit5);
        edit12 = findViewById(R.id.edit12);

        edit6 = findViewById(R.id.edit6);
        edit7 = findViewById(R.id.edit7);
        edit8 = findViewById(R.id.edit8);
        edit9 = findViewById(R.id.edit9);
        edit10 = findViewById(R.id.edit10);
        edit11 = findViewById(R.id.edit11);

        edit13 = findViewById(R.id.edit13);
        edit14 = findViewById(R.id.edit14);
        edit15 = findViewById(R.id.edit15);
        edit16 = findViewById(R.id.edit16);
        edit17 = findViewById(R.id.edit17);
        edit21 = findViewById(R.id.edit21);

        edit18 = findViewById(R.id.edit18);
        edit19 = findViewById(R.id.edit19);
        edit20 = findViewById(R.id.edit20);
        edit22 = findViewById(R.id.edit22);
        edit28 = findViewById(R.id.edit28);

        edit23 = findViewById(R.id.edit23);
        edit24 = findViewById(R.id.edit24);
        edit25 = findViewById(R.id.edit25);
        edit26 = findViewById(R.id.edit26);
        edit27 = findViewById(R.id.edit27);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            // TODO: display value here
            jawaban j = extras.getParcelable(SOAL_1);
            jawaban2 j2 = extras.getParcelable(SOAL_2);
            jawaban3 j3 = extras.getParcelable(SOAL_3);
            jawaban4 j4 = extras.getParcelable(SOAL_4);
            jawaban5 j5 = extras.getParcelable(SOAL_5);

            String soal1 = j.getSoal1().toUpperCase();
            String soal2 = j.getSoal2().toUpperCase();
            String soal3 = j.getSoal3().toUpperCase();
            String soal4 = j.getSoal4().toUpperCase();
            String soal5 = j.getSoal5().toUpperCase();
            String soal12 = j.getSoal12().toUpperCase();

            String soal18 = j2.getSoal18().toUpperCase();
            String soal19 = j2.getSoal19().toUpperCase();
            String soal20 = j2.getSoal20().toUpperCase();
            String soal22 = j2.getSoal22().toUpperCase();
            String soal28 = j2.getSoal28().toUpperCase();

            String soal23 = j3.getSoal23().toUpperCase();
            String soal24 = j3.getSoal24().toUpperCase();
            String soal25 = j3.getSoal25().toUpperCase();
            String soal26 = j3.getSoal26().toUpperCase();
            String soal27 = j3.getSoal27().toUpperCase();

            String soal6 = j4.getSoal6().toUpperCase();
            String soal7 = j4.getSoal7().toUpperCase();
            String soal8 = j4.getSoal8().toUpperCase();
            String soal9 = j4.getSoal9().toUpperCase();
            String soal10 = j4.getSoal10().toUpperCase();
            String soal11 = j4.getSoal11().toUpperCase();

            String soal13 = j5.getSoal13().toUpperCase();
            String soal14 = j5.getSoal14().toUpperCase();
            String soal15 = j5.getSoal15().toUpperCase();
            String soal16 = j5.getSoal16().toUpperCase();
            String soal17 = j5.getSoal17().toUpperCase();
            String soal21 = j5.getSoal21().toUpperCase();

            if (soal1.equals("C") && soal2.equals("A")&& soal3.equals("N")&& soal4.equals("D")&& soal5.equals("L")&& soal12.equals("E")){
                this.ket.setText(benar);
                setScore(1);
            }
            else{
                this.ket.setText(salah);
                this.ket.setTextColor(Color.RED);
            }
            if(soal18.equals("S") && soal19.equals("P") && soal20.equals("O") && soal21.equals("N") && soal22.equals("G") && soal28.equals("E")){
                this.ket2.setText(benar);
                setScore(1);
            }else {
                this.ket2.setText(salah);
                this.ket2.setTextColor(Color.RED);
            }
            if(soal10.equals("I") && soal23.equals("N")&&soal24.equals("S") && soal25.equals("P") && soal26.equals("I") && soal27.equals("R") && soal28.equals("E")){
                this.ket3.setText(benar);
                setScore(1);
            }else {
                this.ket3.setText(salah);
                this.ket3.setTextColor(Color.RED);
            }
            if(soal6.equals("P") && soal7.equals("R") && soal8.equals("O") && soal9.equals("M")&& soal10.equals("I") && soal11.equals("S")&& soal12.equals("E")){
                this.ket4.setText(benar);
                setScore(1);
            }else {
                this.ket4.setText(salah);
                this.ket4.setTextColor(Color.RED);
            }
            if(soal6.equals("P") && soal13.equals("O") && soal14.equals("P") && soal15.equals("C") && soal16.equals("O")&& soal17.equals("R")&& soal21.equals("N")){
                this.ket5.setText(benar);
                setScore(1);
            }else {
                this.ket5.setText(salah);
                this.ket5.setTextColor(Color.RED);
            }

            if (getScore() < 0){
                setScore(0);
                this.hasil.setText(String.valueOf(getScore()));
            }else {
                this.hasil.setText(String.valueOf(getScore()));
            }

            this.edit12a.setText(soal12);
            this.edit6a.setText(soal6);
            this.edit21a.setText(soal21);
            this.edit10a.setText(soal10);
            this.edit28a.setText(soal28);

            this.edit1.setText(soal1);
            this.edit2.setText(soal2);
            this.edit3.setText(soal3);
            this.edit4.setText(soal4);
            this.edit5.setText(soal5);
            this.edit12.setText(soal12);

            this.edit6.setText(soal6);
            this.edit7.setText(soal7);
            this.edit8.setText(soal8);
            this.edit9.setText(soal9);
            this.edit10.setText(soal10);
            this.edit11.setText(soal11);

            this.edit13.setText(soal13);
            this.edit14.setText(soal14);
            this.edit15.setText(soal15);
            this.edit16.setText(soal16);
            this.edit17.setText(soal17);
            this.edit21.setText(soal21);

            this.edit18.setText(soal18);
            this.edit19.setText(soal19);
            this.edit20.setText(soal20);
            this.edit22.setText(soal22);
            this.edit28.setText(soal28);

            this.edit23.setText(soal23);
            this.edit24.setText(soal24);
            this.edit25.setText(soal25);
            this.edit26.setText(soal26);
            this.edit27.setText(soal27);
        }

        Button handlehome = findViewById(R.id.button);
        handlehome.setOnClickListener(view -> {
            Intent intent = new Intent(this, Home.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }
}
