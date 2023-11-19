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
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class Quiz extends AppCompatActivity {
    private TextView tvSoal, tvTime, waktu;
    private SeekBar seekbar;
    private RadioGroup rgPilihan;
    private RadioButton rbA, rbB, rbC, rbD;
    private ImageView ivImage, play_pause, stop ;
    private MediaPlayer mediaPlayer;
    private CountDownTimer countDownTimer;
    int nomor = 0;
    int score;
    int benar  = 0, salah = 0;

    int[] gambar = new int[]{
            0,
            R.drawable.image2,
            0,
            R.drawable.picture1,
            0
    };

    int[] audio = new int[]{
            0,
            0,
            R.raw.audio1,
            R.raw.audio2,
            0
    };

    static String[] Soal = new String[]{
            "1. How many players_______in a football team?",
            "2. Don’t stand __________ the television. I can’t see!",
            "3. Where is the woman from?",
            "4. What are they looking at?",
            "5. I’ve got all the data. Now I just need to __ the answer."
    };

    static String[] Option = new String[]{
            "there are", "are there", "is there", "there is",
            "in front of","on","above","under",
            "Germany", "Australia", "Croatia", "Russia",
            "a picture or a photo", "a story in a book", "an email", "a newspaper",
            "make out", "count out", "think out", "work out"
    };

    static String[] Jawaban = new String[]{
            "there are",
            "in front of",
            "Croatia",
            "a picture or a photo",
            "work out"
    };

    static int soal = Soal.length, detik = Soal.length*60;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Quiz");
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.toolbarTextColor, typedValue, true);
        int color = typedValue.data;
        toolbar.setTitleTextColor(color);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);
        switchMaterial.setTextColor(color);

        setSupportActionBar(toolbar);

        tvSoal = findViewById(R.id.tvSoal);
        tvTime = findViewById(R.id.tvTime);
        Button btnSelanjutnya = findViewById(R.id.btnSelanjutnya);
        rgPilihan = findViewById(R.id.rgPilihan);
        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);

        ivImage = findViewById(R.id.ivImage);
        seekbar = findViewById(R.id.seekbar);
        play_pause = findViewById(R.id.play_pause);
        stop = findViewById(R.id.stop);
        waktu = findViewById(R.id.waktu);
        LinearLayout linearlayout = findViewById(R.id.linearlayout);

        rgPilihan.check(0);

        tvSoal.setText(Soal[nomor]);
        if(gambar[nomor] != 0) {
            ivImage.setImageResource(gambar[nomor]);
            ivImage.setVisibility(View.VISIBLE);
        } else {
            ivImage.setVisibility(View.GONE);
        }
        if(audio[nomor] != 0) {
            linearlayout.setVisibility(View.VISIBLE);

            // Inisialisasi MediaPlayer dan SeekBar
            mediaPlayer = MediaPlayer.create(this, audio[nomor]);

            play_pause.setOnClickListener(new View.OnClickListener() {
                boolean isPlaying = false;
                @Override
                public void onClick(View v) {
                    isPlaying = !isPlaying; // Ubah status pemutaran
                    if(isPlaying) {
                        mediaPlayer.start();
                        play_pause.setImageResource(R.drawable.pause);
                        stop.setVisibility(View.VISIBLE);
                    } else {
                        mediaPlayer.pause();
                        play_pause.setImageResource(R.drawable.play);
                    }
                }
            });

            stop.setOnClickListener(v1 -> {
                mediaPlayer.stop();
                mediaPlayer.prepareAsync();
                play_pause.setImageResource(R.drawable.play);
                stop.setVisibility(View.GONE);
            });

            mediaPlayer.setOnCompletionListener(mp -> {
                play_pause.setImageResource(R.drawable.play);
                stop.setVisibility(View.GONE);
            });

            int duration = mediaPlayer.getDuration();
            int secondsTotal = duration / 1000;
            int hours = secondsTotal / (60 * 60);
            int minutes = (secondsTotal % (60 * 60)) / 60;
            int seconds = secondsTotal % 60;
            String times = hours+"."+minutes+"."+seconds;
            waktu.setText(times);

            // Atur maksimum SeekBar ke durasi audio (dalam milidetik)
            seekbar.setMax(mediaPlayer.getDuration());

            // Atur OnSeekBarChangeListener untuk SeekBar
            seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    if(fromUser) {
                        // Jika pengguna menggeser SeekBar, atur posisi pemutaran audio
                        mediaPlayer.seekTo(progress);
                    }
                    // Tampilkan waktu audio saat ini (dalam detik)
                    int seconds = (progress / 1000) % 60;
                    int minutes = (progress / (1000 * 60)) % 60;
                    int hours = (progress / (1000 * 60 * 60)) % 24;
                    String time = hours+"."+minutes+"."+seconds;
                    waktu.setText(time);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {}

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {}
            });

            // Buat Runnable untuk memperbarui posisi SeekBar saat audio diputar
            Runnable updateSeekBar = new Runnable() {
                @Override
                public void run() {
                    // Perbarui posisi SeekBar ke posisi pemutaran audio saat ini
                    seekbar.setProgress(mediaPlayer.getCurrentPosition());

                    // Jalankan Runnable ini lagi setelah 1000 milidetik
                    seekbar.postDelayed(this, 1000);
                }
            };

            // Mulai Runnable
            seekbar.post(updateSeekBar);
        } else {
            linearlayout.setVisibility(View.GONE);
        }
        rbA.setText(Option[0]);
        rbB.setText(Option[1]);
        rbC.setText(Option[2]);
        rbD.setText(Option[3]);

        int waktu1 = detik*1000;
        String jam = " Detik";
        String time = "Waktu : ";
        countDownTimer = new CountDownTimer(waktu1,  1000){

            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) millisUntilFinished/1000;
                tvTime.setText(time);
                tvTime.append(seconds+jam);
            }

            @Override
            public void onFinish() {
                String habis = "Waktu Habis!!";
                tvTime.setText(habis);
                score = (int) Math.round(((double)benar / soal) * 100);
                Intent next = new Intent(getApplicationContext(), QuizResult.class);
                next.putExtra("nilai", score);
                next.putExtra("benar", benar);
                next.putExtra("salah", salah);
                startActivity(next);
            }
        }.start();

        btnSelanjutnya.setOnClickListener(v -> {
            if (rbA.isChecked() || rbB.isChecked() || rbC.isChecked() || rbD.isChecked()) {

                RadioButton Pilihan_User = findViewById(rgPilihan.getCheckedRadioButtonId());
                String Jawaban_User = Pilihan_User.getText().toString();
                rgPilihan.check(0);

                if (Jawaban_User.equalsIgnoreCase(Jawaban[nomor])) {
                    benar++;
                } else {
                    salah++;
                }
                nomor++;
                if (nomor < Soal.length) {
                    tvSoal.setText(Soal[nomor]);
                    if(gambar[nomor] != 0) {
                        ivImage.setImageResource(gambar[nomor]);
                        ivImage.setVisibility(View.VISIBLE);
                    } else {
                        ivImage.setVisibility(View.GONE);
                    }
                    if(audio[nomor] != 0) {
                        linearlayout.setVisibility(View.VISIBLE);

                        // Inisialisasi MediaPlayer dan SeekBar
                        mediaPlayer = MediaPlayer.create(this, audio[nomor]);

                        play_pause.setOnClickListener(new View.OnClickListener() {
                            boolean isPlaying = false;
                            @Override
                            public void onClick(View v) {
                                isPlaying = !isPlaying; // Ubah status pemutaran
                                if(isPlaying) {
                                    mediaPlayer.start();
                                    play_pause.setImageResource(R.drawable.pause);
                                    stop.setVisibility(View.VISIBLE);
                                } else {
                                    mediaPlayer.pause();
                                    play_pause.setImageResource(R.drawable.play);
                                }
                            }
                        });

                        stop.setOnClickListener(v1 -> {
                            mediaPlayer.stop();
                            mediaPlayer.prepareAsync();
                            play_pause.setImageResource(R.drawable.play);
                            stop.setVisibility(View.GONE);
                        });

                        mediaPlayer.setOnCompletionListener(mp -> {
                            play_pause.setImageResource(R.drawable.play);
                            stop.setVisibility(View.GONE);
                        });

                        int duration = mediaPlayer.getDuration();
                        int secondsTotal = duration / 1000;
                        int hours = secondsTotal / (60 * 60);
                        int minutes = (secondsTotal % (60 * 60)) / 60;
                        int seconds = secondsTotal % 60;
                        String times = hours+"."+minutes+"."+seconds;
                        waktu.setText(times);

                        // Atur maksimum SeekBar ke durasi audio (dalam milidetik)
                        seekbar.setMax(mediaPlayer.getDuration());

                        // Atur OnSeekBarChangeListener untuk SeekBar
                        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                            @Override
                            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                                if(fromUser) {
                                    // Jika pengguna menggeser SeekBar, atur posisi pemutaran audio
                                    mediaPlayer.seekTo(progress);
                                }
                                // Tampilkan waktu audio saat ini (dalam detik)
                                int seconds = (progress / 1000) % 60;
                                int minutes = (progress / (1000 * 60)) % 60;
                                int hours = (progress / (1000 * 60 * 60)) % 24;
                                String time = hours+"."+minutes+"."+seconds;
                                waktu.setText(time);
                            }

                            @Override
                            public void onStartTrackingTouch(SeekBar seekBar) {}

                            @Override
                            public void onStopTrackingTouch(SeekBar seekBar) {}
                        });

                        // Buat Runnable untuk memperbarui posisi SeekBar saat audio diputar
                        Runnable updateSeekBar = new Runnable() {
                            @Override
                            public void run() {
                                // Perbarui posisi SeekBar ke posisi pemutaran audio saat ini
                                seekbar.setProgress(mediaPlayer.getCurrentPosition());

                                // Jalankan Runnable ini lagi setelah 1000 milidetik
                                seekbar.postDelayed(this, 1000);
                            }
                        };

                        // Mulai Runnable
                        seekbar.post(updateSeekBar);
                    } else {
                        linearlayout.setVisibility(View.GONE);
                    }

                    rbA.setText(Option[(nomor * 4)]);
                    rbB.setText(Option[(nomor * 4) + 1]);
                    rbC.setText(Option[(nomor * 4) + 2]);
                    rbD.setText(Option[(nomor * 4) + 3]);

                } else {
                    score = (int) Math.round(((double) benar / soal) * 100);
                    Intent next = new Intent(getApplicationContext(), QuizResult.class);
                    next.putExtra("nilai", score);
                    next.putExtra("benar", benar);
                    next.putExtra("salah", salah);
                    startActivity(next);
                }
            } else {
                Toast.makeText(Quiz.this, "Silahkan Pilih Jawaban Terlebih Dahulu!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

        @Override
    protected void onDestroy() {
        super.onDestroy();

        // Hentikan MediaPlayer
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }

        // Hentikan CountDownTimer
        if(countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }
}