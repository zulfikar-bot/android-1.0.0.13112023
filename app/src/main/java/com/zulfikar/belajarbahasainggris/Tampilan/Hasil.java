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

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_1;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_2;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_3;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_4;
import static com.zulfikar.belajarbahasainggris.Tampilan.Play.SOAL_5;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import com.zulfikar.belajarbahasainggris.DataLoadingUtility;
import com.zulfikar.belajarbahasainggris.R;
import com.zulfikar.belajarbahasainggris.model.jawaban;
import com.zulfikar.belajarbahasainggris.model.jawaban2;
import com.zulfikar.belajarbahasainggris.model.jawaban3;
import com.zulfikar.belajarbahasainggris.model.jawaban4;
import com.zulfikar.belajarbahasainggris.model.jawaban5;

public class Hasil extends AppCompatActivity {
    ConstraintLayout layout;
    TextView edit1,edit2,edit3,edit4,edit5,edit12;
    TextView edit6, edit7, edit8, edit9, edit10, edit11;
    TextView edit13, edit14, edit15, edit16, edit17, edit21;
    TextView edit18, edit19, edit20, edit22, edit28;
    TextView edit23, edit24, edit25, edit26, edit27;
    TextView edit12a, edit6a, edit21a, edit8a, edit28a;
    TextView ket, ket2, ket3, ket4, ket5, hasil;
    String benar = "*Jawaban Benar";
    String salah = "*Jawaban Salah";
    int score;
    int question = 5;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = (this.score) + score*(100/question);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts_hasil);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        ket = findViewById(R.id.ket);
        ket2 = findViewById(R.id.ket2);
        ket3 = findViewById(R.id.ket3);
        ket4 = findViewById(R.id.ket4);
        ket5 = findViewById(R.id.ket5);
        hasil = findViewById(R.id.score);

        edit12a = findViewById(R.id.edit12a);
        edit6a = findViewById(R.id.edit6a);
        edit21a = findViewById(R.id.edit21a);
        edit8a = findViewById(R.id.edit8a);
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

        layout = findViewById(R.id.layout);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            warna(R.drawable.bluebg);
        } else {
            getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            warna(R.drawable.ttsbg2);
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            // TODO: display value here
            jawaban j = extras.getParcelable(SOAL_1);
            jawaban2 j2 = extras.getParcelable(SOAL_2);
            jawaban3 j3 = extras.getParcelable(SOAL_3);
            jawaban4 j4 = extras.getParcelable(SOAL_4);
            jawaban5 j5 = extras.getParcelable(SOAL_5);

            assert j != null;
            assert j2 != null;
            assert j3 != null;
            assert j4 != null;
            assert j5 != null;

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

            checkAnswer(new String[]{soal1, soal2, soal3, soal4, soal5, soal12}, this.ket, new String[]{"C", "A", "N", "D", "L", "E"});
            checkAnswer(new String[]{soal18, soal19, soal20, soal21, soal22, soal28}, this.ket2, new String[]{"S", "P", "O", "N", "G", "E"});
            checkAnswer(new String[]{soal8, soal23, soal24, soal25, soal26, soal27, soal28}, this.ket3, new String[]{"O", "U", "T", "S", "I", "D", "E"});
            checkAnswer(new String[]{soal6, soal7, soal8, soal9, soal10, soal11, soal12}, this.ket4, new String[]{"P", "R", "O", "M", "I", "S", "E"});
            checkAnswer(new String[]{soal6, soal13, soal14, soal15, soal16, soal17, soal21}, this.ket5, new String[]{"P", "O", "P", "C", "O", "R", "N"});

            if (getScore() < 0){
                setScore(0);
                this.hasil.setText(String.valueOf(getScore()));
            }else {
                this.hasil.setText(String.valueOf(getScore()));
            }

            this.edit12a.setText(soal12);
            this.edit6a.setText(soal6);
            this.edit21a.setText(soal21);
            this.edit8a.setText(soal8);
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

    private void warna(int bg) {
        Drawable background = ResourcesCompat.getDrawable(getResources(), bg, null);
        layout.setBackground(background);
        edit1.setTextColor(Color.BLACK);
        edit2.setTextColor(Color.BLACK);
        edit3.setTextColor(Color.BLACK);
        edit4.setTextColor(Color.BLACK);
        edit5.setTextColor(Color.BLACK);
        edit12.setTextColor(Color.BLACK);
        edit6.setTextColor(Color.BLACK);
        edit7.setTextColor(Color.BLACK);
        edit8.setTextColor(Color.BLACK);
        edit9.setTextColor(Color.BLACK);
        edit10.setTextColor(Color.BLACK);
        edit11.setTextColor(Color.BLACK);
        edit13.setTextColor(Color.BLACK);
        edit14.setTextColor(Color.BLACK);
        edit15.setTextColor(Color.BLACK);
        edit16.setTextColor(Color.BLACK);
        edit17.setTextColor(Color.BLACK);
        edit21.setTextColor(Color.BLACK);
        edit18.setTextColor(Color.BLACK);
        edit19.setTextColor(Color.BLACK);
        edit20.setTextColor(Color.BLACK);
        edit22.setTextColor(Color.BLACK);
        edit28.setTextColor(Color.BLACK);
        edit23.setTextColor(Color.BLACK);
        edit24.setTextColor(Color.BLACK);
        edit25.setTextColor(Color.BLACK);
        edit26.setTextColor(Color.BLACK);
        edit27.setTextColor(Color.BLACK);
        edit12a.setTextColor(Color.BLACK);
        edit6a.setTextColor(Color.BLACK);
        edit21a.setTextColor(Color.BLACK);
        edit8a.setTextColor(Color.BLACK);
        edit28a.setTextColor(Color.BLACK);
    }

    private void checkAnswer(String[] answers, TextView textView, String[] correctAnswers) {
        boolean isCorrect = true;
        for (int i = 0; i < answers.length; i++) {
            if (!answers[i].equals(correctAnswers[i])) {
                isCorrect = false;
                break;
            }
        }

        if (isCorrect) {
            textView.setText(benar);
            Paint paint = textView.getPaint();
            paint.setShadowLayer(1, 3, 3, Color.BLACK);
            textView.setLayerType(View.LAYER_TYPE_SOFTWARE, paint);
            setScore(1);
        } else {
            textView.setText(salah);
            textView.setTextColor(Color.RED);
        }
    }

}
