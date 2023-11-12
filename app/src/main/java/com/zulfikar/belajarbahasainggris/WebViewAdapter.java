package com.zulfikar.belajarbahasainggris;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.SafeBrowsingResponse;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.RecyclerView;
import androidx.webkit.WebSettingsCompat;
import androidx.webkit.WebViewCompat;
import androidx.webkit.WebViewFeature;

import java.net.URI;
import java.net.URISyntaxException;

public class WebViewAdapter extends RecyclerView.Adapter<WebViewAdapter.WebViewHolder> {

    private final String[] urls;
    private final boolean isDarkMode;
    private final Activity activity;

    public WebViewAdapter(Activity activity, String[] urls, boolean isDarkMode) {
        this.activity = activity;
        this.urls = urls;
        this.isDarkMode = isDarkMode;
    }

    @NonNull
    @Override
    public WebViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_web_view, parent, false);
        return new WebViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WebViewHolder holder, int position) {
        holder.webView.getSettings().setJavaScriptEnabled(true);

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                try {
                    WebSettingsCompat.setAlgorithmicDarkeningAllowed(holder.webView.getSettings(), true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            activity.getWindow().getDecorView().setSystemUiVisibility(0);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            if (WebViewFeature.isFeatureSupported(WebViewFeature.ALGORITHMIC_DARKENING)) {
                try {
                    WebSettingsCompat.setAlgorithmicDarkeningAllowed(holder.webView.getSettings(), false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        WebSettings webSettings = holder.webView.getSettings();

        // Mengatur mode tampilan lebar
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);

        // Mengatur cache
        holder.webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setDomStorageEnabled(true);
        webSettings.setDatabaseEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(holder.webView, true);
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
        }

        // Mengatur mode render
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            holder.webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            holder.webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        holder.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);

                // Gunakan Safe Browsing API untuk memeriksa apakah URL aman
                if (WebViewFeature.isFeatureSupported(WebViewFeature.START_SAFE_BROWSING)) {
                    WebViewCompat.startSafeBrowsing(activity, success -> {
                        if (Boolean.TRUE.equals(success)) {
                            // Safe Browsing is initialized successfully, continue with the URL check
                            holder.webView.setWebViewClient(new WebViewClient() {
                                @Override
                                public void onSafeBrowsingHit(WebView view1, WebResourceRequest request, int threatType, SafeBrowsingResponse callback) {
                                    // The URL is not safe, show an error or disable JavaScript as a precaution
                                    holder.webView.getSettings().setJavaScriptEnabled(false);
                                }
                            });
                        } else {
                            // Safe Browsing could not be initialized, show an error or disable JavaScript as a precaution
                            holder.webView.getSettings().setJavaScriptEnabled(false);
                        }
                    });
                } else {
                    // Safe Browsing tidak tersedia atau getContext() adalah null, tampilkan kesalahan atau nonaktifkan JavaScript sebagai tindakan pencegahan
                    holder.webView.getSettings().setJavaScriptEnabled(false);
                }
            }
        });
        holder.webView.loadUrl(urls[position]);
        holder.webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // Set the height of WebView based on its content
                view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                int webViewHeight = view.getMeasuredHeight();
                ViewGroup.LayoutParams params = view.getLayoutParams();
                params.height = webViewHeight;
                view.setLayoutParams(params);
            }
        });
    }

    @Override
    public int getItemCount() {
        return urls.length;
    }

    static class WebViewHolder extends RecyclerView.ViewHolder {

        WebView webView;

        WebViewHolder(@NonNull View itemView) {
            super(itemView);
            webView = itemView.findViewById(R.id.WebView1);
        }
    }

    public String getDomainName(String url) {
        try {
            URI uri = new URI(url);
            String domain = uri.getHost();
            if (domain.startsWith("www.")) {
                domain = domain.substring(4);
            }
            // Hapus TLD
            domain = domain.replaceAll("(\\.[a-zA-Z]+)+$", "");
            // Ubah setiap awal kata menjadi huruf kapital
            String[] words = domain.split("\\.");
            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1)).append(" ");
            }
            return sb.toString().trim();
        } catch (URISyntaxException e) {
            // Handle error
            return url;
        }
    }
}