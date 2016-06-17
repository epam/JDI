package com.epam.jdi.jditestandroidapp;

import android.app.Application;

import com.deploygate.sdk.DeployGate;

/**
 * Created by vitalii_sokolov on 15.06.16.
 */
public class JdiApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DeployGate.install(this);
    }
}
