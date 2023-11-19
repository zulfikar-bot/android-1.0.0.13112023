package com.zulfikar.belajarbahasainggris.Tampilan;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;
import static com.zulfikar.belajarbahasainggris.Tampilan.Profil.NAME;
import static com.zulfikar.belajarbahasainggris.Tampilan.Profil.PROFIL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.zulfikar.belajarbahasainggris.ActivityLoading;
import com.zulfikar.belajarbahasainggris.DataLoadingUtility;
import com.zulfikar.belajarbahasainggris.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        ImageView profil = findViewById(R.id.profil);
        TextView name = findViewById(R.id.name);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String getname = extras.getString(NAME);
            String getprofil = extras.getString(PROFIL);
            name.setText(getname);
            if (getprofil != null) {
                profil.setImageURI(Uri.parse(getprofil));
            }
        }

        // Inside your activity's onCreate method
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                // Handle the back button event
                Intent intent = new Intent(Home.this, ActivityLoading.class);
                startActivity(intent);
                finish();
            }
        };
        getOnBackPressedDispatcher().addCallback(this, callback);
    }

    public void play(View view) {
        Intent intent = new Intent(this, Play.class);
        startActivity(intent);

    }
    public void handleprofil(View view) {
        Intent intent = new Intent(this, Profil.class);
        startActivity(intent);
    }

    public void handleabout(View view) {
        Intent intent = new Intent(this, ViewPager.class);
        startActivity(intent);
    }

    public void exit(View view) {
        Intent intent = new Intent(this, ActivityLoading.class);
        startActivity(intent);
        finish();
    }
}
