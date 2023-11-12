package com.zulfikar.belajarbahasainggris;

import static com.zulfikar.belajarbahasainggris.DataLoadingUtility.THEME_PREFERENCE;

import android.os.Bundle;
import android.util.TypedValue;

import android.content.Context;
import android.view.Gravity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WebViews extends AppCompatActivity {
    // Assuming alamat is a static variable
    private static String[] alamat = {
            "https://www.google.co.id/",
            "https://www.bing.com",
            "https://www.yahoo.com",
            "https://www.ask.com",
            "https://www.duckduckgo.com",
            "https://www.yandex.com/",
            "https://www.aol.com/"
    };

    public static void setUrls(String... urls) {
        alamat = urls;
    }

    // Overloaded setUrls method without parameters
    public static void setUrls() {
        // Set default URLs or perform any other logic
        alamat = getDefaultUrls();
    }

    // Method to get default URLs
    private static String[] getDefaultUrls() {
        return new String[]{
                "https://www.google.co.id/",
                "https://www.bing.com",
                "https://www.yahoo.com",
                "https://www.ask.com",
                "https://www.duckduckgo.com",
                "https://www.yandex.com/",
                "https://www.aol.com/"
        };
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        boolean isDarkMode = getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE).getBoolean(DataLoadingUtility.IS_DARK_MODE, false);
        DataLoadingUtility.loadData(getSharedPreferences(THEME_PREFERENCE, MODE_PRIVATE), this, isDarkMode);

        Toolbar toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Web View");
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.toolbarTextColor, typedValue, true);
        int color = typedValue.data;
        toolbar.setTitleTextColor(color);

        SwitchMaterial switchMaterial = findViewById(R.id.dayxdark);
        switchMaterial.setTextColor(color);

        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager2 viewPager = findViewById(R.id.viewPager);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewPager.requestDisallowInterceptTouchEvent(true);
            }
        });
        WebViewAdapter adapter = new WebViewAdapter(this, alamat, isDarkMode);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    Context context = this;
                    TextView tabTextView = new TextView(context);
                    tabTextView.setText(adapter.getDomainName(alamat[position]));
                    tabTextView.setLayoutParams(new ViewGroup.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    tabTextView.setGravity(Gravity.CENTER);
                    tabTextView.setMaxLines(1);
                    tabTextView.setEllipsize(TextUtils.TruncateAt.END);
                    tab.setCustomView(tabTextView);
                }
        ).attach();
    }
}