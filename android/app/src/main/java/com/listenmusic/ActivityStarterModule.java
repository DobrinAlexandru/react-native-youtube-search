package com.listenmusic;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

/**
 * Created by alexd on 09/08/2017.
 */

class ActivityStarterModule extends ReactContextBaseJavaModule {

    ActivityStarterModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "ActivityStarter";
    }

    @ReactMethod
    void navigateToExample(String value) {
        Log.v("bridge", value);
        ReactApplicationContext context = getReactApplicationContext();
//        Intent intent = new Intent(context, ExampleActivity.class);
//        context.startActivity(intent);
    }
}