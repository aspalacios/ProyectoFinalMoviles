package com.momotombodevs.pgalante.bmi_calculator;

import com.tumblr.remember.Remember;

import io.realm.Realm;

public class Application  extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        Remember.init(getApplicationContext(), "com.momotombodevs.pgalante.bmi_calculator");
    }
}
