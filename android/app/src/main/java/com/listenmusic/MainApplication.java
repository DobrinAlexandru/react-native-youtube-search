package com.listenmusic;

import android.app.Application;

import com.RNFetchBlob.RNFetchBlobPackage;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.react.ReactApplication;
import com.learnium.RNDeviceInfo.RNDeviceInfo;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.soloader.SoLoader;
import com.mixpanel.android.mpmetrics.MixpanelAPI;
import com.oblador.vectoricons.VectorIconsPackage;
import com.sbugert.rnadmob.RNAdMobPackage;

import java.util.Arrays;
import java.util.List;

public class MainApplication extends Application implements ReactApplication {
  private static MainApplication Instance;
  private static RequestQueue queue;
  public static final String mt = "192e31193c665f7df5c3c6186e7e63be";
  private MixpanelAPI mp;
  public MixpanelAPI getMixpanel() {
    return mp;
  }
  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override
    public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override
    protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(
          new MainReactPackage(),
            new RNDeviceInfo(),
            new VectorIconsPackage(),
            new RNFetchBlobPackage(),
              new RNAdMobPackage()
      );
    }
  };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    Instance = this;
    queue = Volley.newRequestQueue(this);
    mp = MixpanelAPI.getInstance(this, mt);
    SoLoader.init(this, /* native exopackage */ false);
  }

  public MainApplication() {
    Instance = this;
  }


  public static MainApplication getInstance() {
    return Instance;
  }
  public static void addQueue(StringRequest request){
    queue.add(request);
  }
}
