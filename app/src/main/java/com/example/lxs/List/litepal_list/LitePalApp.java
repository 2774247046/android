package com.example.lxs.List.litepal_list;

import android.app.Application;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

public class LitePalApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LitePal.initialize(this);
    }
}
