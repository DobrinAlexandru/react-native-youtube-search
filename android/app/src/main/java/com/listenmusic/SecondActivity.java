package com.listenmusic;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

/**
 * Created by tata on 7/19/2017.
 */

public class SecondActivity extends Activity{
    private InterstitialAd ad;
    private Activity activity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        AdRequest request = new AdRequest.Builder().build();
        ad = new InterstitialAd(this);
        MobileAds.initialize(this, "ca-app-pub-2640666806546520~4285377495");
        ad.setAdUnitId(getInter());
        ad.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                exitApp();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                showAdd();
            }
        });
        ad.loadAd(request);
    }

    private void exitApp(){
        ThirdActivity.closeApp(this);
    }
    private String getInter(){
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String inter = prefs.getString("inter", "ca-app-pub-2640666806546520/7238843892");//"No name defined" is the default value.
        return inter;
    }

    private void showAdd(){
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ad.isLoaded()) {ad.show();
                } else {exitApp();}
            }
        },  3000);
    }
}
