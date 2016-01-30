package io.rancho.retail.beaconstac.Activities;

import android.content.Context;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.mobstac.beaconstac.core.Beaconstac;
import com.mobstac.beaconstac.core.BeaconstacReceiver;
import com.mobstac.beaconstac.core.MSConstants;
import com.mobstac.beaconstac.core.MSPlace;
import com.mobstac.beaconstac.models.MSAction;
import com.mobstac.beaconstac.models.MSBeacon;
import com.mobstac.beaconstac.utils.MSException;

import java.util.ArrayList;

import io.rancho.retail.beaconstac.R;
import io.rancho.retail.beaconstac.Receivers.MyReceiver;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MSBeacon> beacons = new ArrayList<MSBeacon>();

    private TextView bCount;
    Beaconstac bstacInstance; BeaconstacReceiver beaconstacReceiver;
    private boolean registered;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCount = (TextView)findViewById(R.id.textview);

         beaconstacReceiver = new BeaconstacReceiver() {
            @Override
            public void rangedBeacons(Context context, ArrayList<MSBeacon> arrayList) {
                Log.d("ABC","Size is "+arrayList.size());
            }

            @Override
            public void campedOnBeacon(Context context, MSBeacon msBeacon) {
                Log.d("ABC","camped on, latest rssi = "+msBeacon.getLatestRSSI());
            }

            @Override
            public void exitedBeacon(Context context, MSBeacon msBeacon) {

            }

            @Override
            public void triggeredRule(Context context, String s, ArrayList<MSAction> arrayList) {

            }

            @Override
            public void enteredRegion(Context context, String s) {

            }

            @Override
            public void exitedRegion(Context context, String s) {

            }

            @Override
            public void enteredGeofence(Context context, ArrayList<MSPlace> arrayList) {

            }

            @Override
            public void exitedGeofence(Context context, ArrayList<MSPlace> arrayList) {

            }
        };

        bstacInstance = Beaconstac.getInstance(this);
        bstacInstance.setRegionParams("F94DBB23-2266-7822-3782-57BEAC0952AC",
                "com.mobstac.beaconstacexample");
        bstacInstance.syncRules();
        try {
            bstacInstance.startRangingBeacons();
        } catch (MSException e) {
            e.printStackTrace();

        }
        registerBroadcasts();
    }
    public void registerBroadcasts()
    {
        if (!registered) {
            // register beaconstac receiver
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_RANGED_BEACON);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_CAMPED_BEACON);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_EXITED_BEACON);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_RULE_TRIGGERED);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_ENTERED_REGION);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_EXITED_REGION);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_ENTERED_GEOFENCE);
            intentFilter.addAction(MSConstants.BEACONSTAC_INTENT_EXITED_GEOFENCE);
            registerReceiver(beaconstacReceiver, intentFilter);
            registered = true;
        }
    }
    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(beaconstacReceiver);
    }
}
