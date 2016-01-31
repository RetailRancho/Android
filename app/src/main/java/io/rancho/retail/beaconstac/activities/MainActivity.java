package io.rancho.retail.beaconstac.activities;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import com.mobstac.beaconstac.core.Beaconstac;
import com.mobstac.beaconstac.core.BeaconstacReceiver;
import com.mobstac.beaconstac.core.MSConstants;
import com.mobstac.beaconstac.core.MSPlace;
import com.mobstac.beaconstac.models.MSAction;
import com.mobstac.beaconstac.models.MSBeacon;
import com.mobstac.beaconstac.utils.MSException;

import java.util.ArrayList;

import io.rancho.retail.beaconstac.fragments.Home;
import io.rancho.retail.beaconstac.fragments.Messages;
import io.rancho.retail.beaconstac.fragments.Settings;
import io.rancho.retail.beaconstac.R;
import io.rancho.retail.beaconstac.fragments.ToDoList;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    RadioButton button1,button2,button3,button4;
    FragmentManager fragmentManager;
    ActionBar actionBar;
    Beaconstac bstacInstance;
   public static int found = 0;

    private boolean registered = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bstacInstance = Beaconstac.getInstance(this);
        bstacInstance.setRegionParams("F94DBB23-2266-7822-3782-57BEAC0952AC",
                "com.mobstac.beaconstacexample");
        bstacInstance.syncRules();


        try {
            bstacInstance.startRangingBeacons();
        } catch (MSException e) {
            e.printStackTrace();
        }

        button1 = (RadioButton)findViewById(R.id.button_home);
        button1.setOnCheckedChangeListener(this);
        button2 = (RadioButton)findViewById(R.id.button_messages);
        button2.setOnCheckedChangeListener(this);
        button3 = (RadioButton)findViewById(R.id.button_settings);
        button3.setOnCheckedChangeListener(this);
        button4 = (RadioButton)findViewById(R.id.button_todolist);
        button4.setOnCheckedChangeListener(this);

        if (savedInstanceState == null) {
            registerBroadcast();
        }

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#03A9F4")));

        fragmentManager = getSupportFragmentManager();
        button1.setChecked(true);

    }
    private void registerBroadcast() {
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
    private void unregisterBroadcast() {
        if (registered) {
            // unregister beaconstac receiver
            unregisterReceiver(beaconstacReceiver);
            // unregister place sync receiver
            registered = false;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();

        // stop scanning when the app closes
        if (bstacInstance != null) {
            try {
                bstacInstance.stopRangingBeacons();
            } catch (MSException e) {
                // handle for older devices
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked) {
            switch (buttonView.getId()) {
                case R.id.button_home:
                    actionBar.setTitle("Retail Rancho");
                    fragmentManager.beginTransaction().replace(R.id.main_layout, new Home()).commit();
                    break;
                case R.id.button_messages:
                    actionBar.setTitle("Messages");
                    fragmentManager.beginTransaction().replace(R.id.main_layout, new Messages()).commit();
                    break;
                case R.id.button_settings:
                    actionBar.setTitle("Settings");
                    fragmentManager.beginTransaction().replace(R.id.main_layout, new Settings()).commit();
                    break;
                case R.id.button_todolist:
                    actionBar.setTitle("To do list");
                    fragmentManager.beginTransaction().replace(R.id.main_layout, new ToDoList()).commit();
                    break;
            }
        }
    }
    BeaconstacReceiver beaconstacReceiver = new BeaconstacReceiver() {
        @Override
        public void rangedBeacons(Context context, ArrayList<MSBeacon> arrayList) {
            found = 1;
            Intent intent = new Intent(context, LayoutActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
            Log.d("ABC","Value of found is "+found);
            finish();

        }

        @Override
        public void campedOnBeacon(Context context, MSBeacon msBeacon) {

        }

        @Override
        public void exitedBeacon(Context context, MSBeacon msBeacon) {

        }

        @Override
        public void triggeredRule(Context context, String s, ArrayList<MSAction> arrayList) {
            Log.d("ABC","Rule Triggered");
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
}
