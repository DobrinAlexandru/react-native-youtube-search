package com.listenmusic;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;


public class SecondActivity extends Activity {
    private InterstitialAd ad;
    public Activity activity = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        AdRequest request = new AdRequest.Builder().build();
        ad = new InterstitialAd(activity);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
       exitApp();
    }

    private void exitApp(){
        ThirdActivity.closeApp(activity);
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