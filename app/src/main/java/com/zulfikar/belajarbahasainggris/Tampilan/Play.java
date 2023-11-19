package com.zulfikar.belajarbahasainggris.Tampilan;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.zulfikar.belajarbahasainggris.DataLoadingUtility;
import com.zulfikar.belajarbahasainggris.Fragment.Soal1Fragment;
import com.zulfikar.belajarbahasainggris.Fragment.Soal2Fragment;
import com.zulfikar.belajarbahasainggris.Fragment.Soal3Fragment;
import com.zulfikar.belajarbahasainggris.Fragment.Soal4Fragment;
import com.zulfikar.belajarbahasainggris.Fragment.Soal5Fragment;
import com.zulfikar.belajarbahasainggris.R;
import com.zulfikar.belajarbahasainggris.model.jawaban;
import com.zulfikar.belajarbahasainggris.model.jawaban2;
import com.zulfikar.belajarbahasainggris.model.jawaban3;
import com.zulfikar.belajarbahasainggris.model.jawaban4;
import com.zulfikar.belajarbahasainggris.model.jawaban5;

public class Play extends AppCompatActivity {

    public static final String SOAL_1 = "soal1";
    public static final String SOAL_2 = "soal2";
    public static final String SOAL_3 = "soal3";
    public static final String SOAL_4 = "soal4";
    public static final String SOAL_5 = "soal5";
    jawaban j;
    jawaban2 j2;
    jawaban3 j3;
    jawaban4 j4;
    jawaban5 j5;

    EditText edit1, edit2, edit3, edit4, edit5, edit12;
    EditText edit6, edit7, edit8, edit9, edit10, edit11;
    EditText edit13, edit14, edit15, edit16, edit17, edit21;
    EditText edit18, edit19, edit20, edit22, edit28;
    EditText edit23, edit24, edit25, edit26, edit27;
    Button buttohasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

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

        buttohasil = findViewById(R.id.button_hasil);
        buttohasil.setOnClickListener(v -> {
            if (edit1.length()==0 || edit2.length()==0 || edit3.length()==0 || edit4.length()==0 || edit5.length()==0 || edit12.length()==0) {
                Toast.makeText(Play.this, "No. 1 belum terisi penuh", Toast.LENGTH_LONG).show();
            }else if(edit6.length()==0 || edit7.length()==0 || edit8.length()==0 || edit9.length()==0 || edit10.length()==0 || edit11.length()==0){
                Toast.makeText(Play.this, "No. 4 belum terisi penuh", Toast.LENGTH_LONG).show();
            }else if(edit13.length()==0 || edit14.length()==0 || edit15.length()==0 || edit16.length()==0 || edit17.length()==0 || edit21.length()==0){
                Toast.makeText(Play.this, "No. 5 belum terisi penuh", Toast.LENGTH_LONG).show();
            }else if(edit18.length()==0 || edit19.length()==0 || edit20.length()==0 || edit22.length()==0 || edit28.length()==0){
                Toast.makeText(Play.this, "No. 2 belum terisi penuh", Toast.LENGTH_LONG).show();
            } else if(edit23.length()==0 || edit24.length()==0 || edit25.length()==0 || edit26.length()==0 || edit27.length()==0){
                Toast.makeText(Play.this, "No. 3 belum terisi penuh", Toast.LENGTH_LONG).show();
            }
            else {
                handlehasil();
            }
        });
    }

    public void soal1(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Soal1Fragment soal1Fragment = (Soal1Fragment) getSupportFragmentManager().findFragmentByTag("SOAL1_FRAGMENT");
        if(soal1Fragment != null && soal1Fragment.isVisible()){
            fragmentTransaction.commit();
        }else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.soallayout, new Soal1Fragment(), "SOAL1_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void soal2(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Soal2Fragment soal2_fragment = (Soal2Fragment) getSupportFragmentManager().findFragmentByTag("SOAL2_FRAGMENT");
        if(soal2_fragment != null && soal2_fragment.isVisible()){
            fragmentTransaction.commit();
        }else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.soallayout, new Soal2Fragment(), "SOAL2_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void soal3(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Soal3Fragment soal3_fragment = (Soal3Fragment) getSupportFragmentManager().findFragmentByTag("SOAL3_FRAGMENT");
        if(soal3_fragment != null && soal3_fragment.isVisible()){
            fragmentTransaction.commit();
        }else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.soallayout, new Soal3Fragment(), "SOAL3_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void soal4(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Soal4Fragment soal4_fragment = (Soal4Fragment) getSupportFragmentManager().findFragmentByTag("SOAL4_FRAGMENT");
        if(soal4_fragment != null && soal4_fragment.isVisible()){
            fragmentTransaction.commit();
        }else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.soallayout, new Soal4Fragment(), "SOAL4_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void soal5(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        Soal5Fragment soal5_fragment = (Soal5Fragment) getSupportFragmentManager().findFragmentByTag("SOAL5_FRAGMENT");
        if(soal5_fragment != null && soal5_fragment.isVisible()){
            fragmentTransaction.commit();
        }else {
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            fragmentTransaction.replace(R.id.soallayout, new Soal5Fragment(), "SOAL5_FRAGMENT");
            fragmentTransaction.commit();
        }
    }

    public void handlehasil() {

        String edit_1 = edit1.getText().toString();
        String edit_2 = edit2.getText().toString();
        String edit_3 = edit3.getText().toString();
        String edit_4 = edit4.getText().toString();
        String edit_5 = edit5.getText().toString();
        String edit_12 = edit12.getText().toString();

        String edit_6 = edit6.getText().toString();
        String edit_7 = edit7.getText().toString();
        String edit_8 = edit8.getText().toString();
        String edit_9 = edit9.getText().toString();
        String edit_10 = edit10.getText().toString();
        String edit_11 = edit11.getText().toString();

        String edit_13 = edit13.getText().toString();
        String edit_14 = edit14.getText().toString();
        String edit_15 = edit15.getText().toString();
        String edit_16 = edit16.getText().toString();
        String edit_17 = edit17.getText().toString();
        String edit_21 = edit21.getText().toString();

        String edit_18 = edit18.getText().toString();
        String edit_19 = edit19.getText().toString();
        String edit_20 = edit20.getText().toString();
        String edit_22 = edit22.getText().toString();
        String edit_28 = edit28.getText().toString();

        String edit_23 = edit23.getText().toString();
        String edit_24 = edit24.getText().toString();
        String edit_25 = edit25.getText().toString();
        String edit_26 = edit26.getText().toString();
        String edit_27 = edit27.getText().toString();

        j = new jawaban(edit_1, edit_2, edit_3, edit_4, edit_5, edit_12);
        j2 = new jawaban2(edit_18, edit_19, edit_20, edit_22, edit_28);
        j3 = new jawaban3(edit_23, edit_24, edit_25, edit_26, edit_27);
        j4 = new jawaban4(edit_6, edit_7, edit_8, edit_9, edit_10, edit_11);
        j5 = new jawaban5(edit_13, edit_14, edit_15, edit_16, edit_17, edit_21);

        Intent intent = new Intent(this, Hasil.class);
        intent.putExtra(SOAL_1, j);
        intent.putExtra(SOAL_2, j2);
        intent.putExtra(SOAL_3, j3);
        intent.putExtra(SOAL_4, j4);
        intent.putExtra(SOAL_5, j5);
        startActivity(intent);

    }
}
