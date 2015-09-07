package com.dashlane.forcesetupwizard;

import android.content.ComponentName;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //testSetupWizardHasRun();

        Intent intent = new Intent();
        intent.setAction("com.android.setupwizard.EDU_PRE_SETUP");
        intent.setFlags(0x4000000);
        intent.setComponent(new ComponentName("com.google.android.setupwizard",
                ".EduInitializeWrapper"));
        sendBroadcast(intent);
        Log.e("MainActivity", "Sent intent: " + intent.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void testSetupWizardHasRun() {
        for (int i = 0; i < 2; i++) {
            try {
                int getIntResult = Settings.System.getInt(getContentResolver(),
                        Settings.System.SETUP_WIZARD_HAS_RUN);
                Log.i("MainActivity", "SETUP_WIZARD_HAS_RUN: " + getIntResult);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            boolean putIntResult = Settings.System.putInt(getContentResolver(),
                    Settings.System.SETUP_WIZARD_HAS_RUN, 0);
            Log.i("MainActivity", "putInt: " + putIntResult);
        }
    }
}
