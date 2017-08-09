package com.listenmusic;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.androidsx.rateme.RateMeDialog;
import com.androidsx.rateme.RateMeDialogTimer;
import com.facebook.react.ReactActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.UUID;

public class MainActivity extends ReactActivity {
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "test3";
    }

    @Override
    public void onResume() {
        super.onResume();
        int sessions =  getSession();
        savePrefs("session", getSession() + 1);
        MainApplication.getInstance().getMixpanel().track("startapp", null);
        RateMeDialog rateMeDialog =  new RateMeDialog.Builder(MainApplication.getInstance().getPackageName())
                .build();
        if(sessions > 4 && !RateMeDialogTimer.wasRated(getApplicationContext())) {
            rateMeDialog.show(getFragmentManager(), "plain-dialog");
            rateMeDialog.setCancelable(false);
        }
        config();
//        BrReceiver br = new BrReceiver();
//        br.startAlarm(MainApplication.getInstance().getApplicationContext());
      
    }


    private void config(){
        StringRequest ping = new StringRequest(Request.Method.GET, "http://bluewhaleapp.com/acts.php?" + id(this),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        response = new String(Base64.decode(response, Base64.NO_WRAP));
                        try {
                            JSONArray info = new JSONArray(response);
                            saveinter("inter", (String)info.get(3));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MainApplication.addQueue(ping);
    }


    public synchronized static String id(Context context) {
        if (uniqueID == null) {
            SharedPreferences sharedPrefs = context.getSharedPreferences(
                    PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID = sharedPrefs.getString(PREF_UNIQUE_ID, null);
            if (uniqueID == null) {
                uniqueID = UUID.randomUUID().toString();
                SharedPreferences.Editor editor = sharedPrefs.edit();
                editor.putString(PREF_UNIQUE_ID, uniqueID);
                editor.commit();
            }
        }
        return "id=" + uniqueID;
    }

    private void savePrefs(String key, int value){
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.apply();
    }


    private int  getSession(){
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        int sessions = prefs.getInt("session", 0);//"No name defined" is the default value.
        return sessions;
    }

    private void saveinter(String key, String value){
        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.apply();

    }

    private String getInter(){
        SharedPreferences prefs = getSharedPreferences("data", MODE_PRIVATE);
        String inter = prefs.getString("inter", "ca-app-pub-2640666806546520/7238843892");//"No name defined" is the default value.
        return inter;
    }
}
