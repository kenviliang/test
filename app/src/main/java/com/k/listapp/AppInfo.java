package com.k.listapp;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by kenvi on 17-11-7.
 */

public class AppInfo {
    private Drawable app_icon;
    private String app_name;
    private String packname;
    private boolean isUserApp;

    public AppInfo(){
        super();
    }

    public AppInfo(Drawable app_icon, String app_name, String packname, boolean isUserApp){
        this.app_icon = app_icon;
        this.app_name = app_name;
        this.packname = packname;
        this.isUserApp = isUserApp;
    }

    public Drawable getApp_icon(){
        return app_icon;
    }

    public void setApp_icon(Drawable app_icon){
        this.app_icon = app_icon;
    }


    public String getApp_name() {
        return app_name;
    }

    public void setApp_name(String app_name) {
        this.app_name = app_name;
    }

    public String getPackname() {
        return packname;
    }

    public void setPackname(String packname){
        this.packname = packname;
    }

    public boolean isUserApp(){
        return isUserApp;
    }

    public void setUserApp(boolean isUserApp){
        this.isUserApp = isUserApp;
    }

    @Override
    public String toString() {
        return "AppInfo: [ app_icon=" + app_icon +
                ", app_name=" + app_name +
                ", packname=" +packname +
                ", isUserApp=" + isUserApp +
                "]";
    }
}
