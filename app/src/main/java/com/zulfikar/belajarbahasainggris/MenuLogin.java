package com.zulfikar.belajarbahasainggris;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import android.content.Intent;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class MenuLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menulogin);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        Toolbar toolbar = findViewById(R.id.toolbar); // Anda perlu memiliki toolbar dengan id "toolbar" di layout Anda

        toolbar.setTitle("Dashboard");
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.toolbarTextColor, typedValue, true);
        int color = typedValue.data;
        toolbar.setTitleTextColor(color);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);
        switchMaterial.setTextColor(color);

        TextView textview = findViewById(R.id.logoutText);
        textview.setTextColor(color);

        TableRow menu1 = findViewById(R.id.menu1);
        menu1.setOnClickListener(v -> {
            Intent intent = new Intent(MenuLogin.this, Logout.class);
            startActivity(intent);
        });

        setSupportActionBar(toolbar);

        // Set up ViewPager with a PagerAdapter
        MyPagerAdapter pagerAdapter = new MyPagerAdapter(this);
        pagerAdapter.addFragment(new PersonalInfoFragment(), "Personal Info");
        pagerAdapter.addFragment(new EducationFragment(), "Education");
        pagerAdapter.addFragment(new ExperienceFragment(), "Experience");
        viewPager.setAdapter(pagerAdapter);
        // Connect TabLayout with ViewPager2
        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> tab.setText(pagerAdapter.getPageTitle(position).toString())
        ).attach();
    }
}