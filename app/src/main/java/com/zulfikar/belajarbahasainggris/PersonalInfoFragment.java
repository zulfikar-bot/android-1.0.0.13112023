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

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Arrays;
import java.util.List;

public class PersonalInfoFragment extends Fragment {

    public PersonalInfoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_personal_info, container, false);

        // Ambil referensi ke setiap TextView dengan tautan
        TextView emailValue = rootView.findViewById(R.id.email_value);
        TextView teleponValue = rootView.findViewById(R.id.telepon_value);
        TextView websiteValue = rootView.findViewById(R.id.website_value);
        TextView website1Value = rootView.findViewById(R.id.website1_value);
        TextView socialValue = rootView.findViewById(R.id.social_value);
        TextView social1Value = rootView.findViewById(R.id.social1_value);
        TextView social2Value = rootView.findViewById(R.id.social2_value);
        TextView social3Value = rootView.findViewById(R.id.social3_value);
        TextView social4aValue = rootView.findViewById(R.id.social4a_value);
        TextView social5aValue = rootView.findViewById(R.id.social5a_value);

        // Atur teks tautan untuk setiap TextView
        setEmailLink(emailValue, getString(R.string.mzulganteng6_gmail_com));
        setPhoneLink(teleponValue, getString(R.string.telepon));
        setLinkText(websiteValue, getString(R.string.blogspot));
        setLinkText(website1Value, getString(R.string.wordpress));
        setLinkText(socialValue, getString(R.string.linkedin));
        setLinkText(social1Value, getString(R.string.youtube));
        setLinkText(social2Value, getString(R.string.facebook));
        setLinkText(social3Value, getString(R.string.twitter));
        setLinkText(social4aValue, getString(R.string.pinterest));
        setLinkText(social5aValue, getString(R.string.github2));

        return rootView;
    }

    private void setPhoneLink(TextView textView, String phoneNumbers) {
        SpannableString phoneNumber = new SpannableString(phoneNumbers);
        Linkify.addLinks(phoneNumber, Linkify.PHONE_NUMBERS);

        textView.setTextColor(getResources().getColor(R.color.colorAccent));

        // Membersihkan nomor telepon dari karakter non-angka
        String cleanedPhoneNumber = phoneNumbers.replaceAll("[^0-9]", "");

        // Atur teks tautan untuk nomor telepon
        textView.setText(phoneNumber);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Atur fungsi klik untuk membuka dialer dengan nomor telepon yang telah dibersihkan
        textView.setOnClickListener(view -> {
            Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + cleanedPhoneNumber));
            startActivity(dialIntent);
        });
    }

    private void setEmailLink(TextView textView, String email) {
        // Atur teks tautan untuk alamat email
        SpannableString emails = new SpannableString(email);
        Linkify.addLinks(emails, Linkify.EMAIL_ADDRESSES);
        textView.setTextColor(getResources().getColor(R.color.colorAccent));
        textView.setText(emails);
        textView.setMovementMethod(LinkMovementMethod.getInstance());

        // Atur fungsi klik untuk membuka aplikasi email dengan alamat email
        textView.setOnClickListener(view -> {
            // Membuat intent untuk mengirim email
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + email));

            // Memeriksa apakah ada aplikasi yang dapat menangani intent
            if (emailIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                startActivity(emailIntent);
            } else {
                // Jika tidak ada aplikasi yang dapat menangani intent, berikan informasi atau buka browser
                // Misalnya, membuka browser dan membuka Gmail di versi web
                Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com"));
                startActivity(webIntent);
            }
        });
    }

    private void setLinkText(TextView textView, String url) {
        // Daftar nama package aplikasi
        List<String> packageNames = Arrays.asList(
                "com.linkedin.android", // LinkedIn
                "com.google.android.youtube", // YouTube
                "com.facebook.katana", // Facebook
                "com.twitter.android", // Twitter
                "com.pinterest", // Pinterest
                "com.github.android", // Github
                "com.linkedin.android.lite",
                "com.facebook.lite",
                "com.twitter.android.lite",
                "com.pinterest.twa"
        );

        SpannableString link = new SpannableString(url);
        Linkify.addLinks(link, Linkify.WEB_URLS);

        textView.setText(link);

        if(!url.contains("facebook.com")) {
            // Atur fungsi klik untuk membuka tautan di aplikasi atau browser
            textView.setOnClickListener(view -> {
                Intent intent2 = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                PackageManager packageManager = requireActivity().getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(intent2, 0);

                boolean isAppAvailable = false;
                for (ResolveInfo info : activities) {
                    for (String packageName : packageNames) {
                        if (packageNames.contains(info.activityInfo.packageName) || isPackageInstalled(packageName, packageManager)) {
                            isAppAvailable = true;
                            break;
                        }
                    }
                }

                if (isAppAvailable) {
                    // Ada aplikasi yang dapat menangani tautan, buka dengan Intent.ACTION_VIEW
                    startActivity(intent2);
                } else {
                    // Tidak ada aplikasi yang dapat menangani tautan, gunakan WebView
                    WebViews.setUrls(url);

                    // Your second action
                    Intent intent = new Intent(requireActivity(), WebViews.class);
                    startActivity(intent);
                }
            });
        } else {
            textView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
        try {
            packageManager.getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}