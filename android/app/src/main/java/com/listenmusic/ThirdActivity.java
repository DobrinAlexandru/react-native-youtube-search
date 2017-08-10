package com.listenmusic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


public class ThirdActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(android.os.Build.VERSION.SDK_INT < 21) {finish();} else {finishAndRemoveTask();}
    }

    public static void closeApp(Context context){
        Intent intent = new Intent(context, ThirdActivity.class);intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        context.startActivity(intent);
    }
}
