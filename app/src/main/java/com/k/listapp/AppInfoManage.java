package com.k.listapp;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import com.jaredrummler.android.processes.AndroidProcesses;
import com.jaredrummler.android.processes.models.AndroidAppProcess;
import com.jaredrummler.android.processes.models.AndroidProcess;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kenvi on 17-11-7.
 */

public class AppInfoManage {
    private Context context;

    public AppInfoManage(Context context){}

    //获取所有安装的应用
    public List<AppInfo> getAppInfos(Context context){
        List<AppInfo> listApp = new ArrayList<>();
        List<ApplicationInfo> applicationInfos;
        PackageManager pm = context.getPackageManager();

        applicationInfos = pm.getInstalledApplications(0);

        for (ApplicationInfo info: applicationInfos){
            AppInfo appInfo = new AppInfo();

            Drawable icon = info.loadIcon(pm);
            appInfo.setApp_icon(icon);

            String app_name = info.loadLabel(pm).toString();
            appInfo.setApp_name(app_name);

            String packname = info.packageName;
            appInfo.setPackname(packname);
            try {
                PackageInfo packageInfo = pm.getPackageInfo(packname,0);
            } catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }

            //显示用户数据
            /*
            boolean isUserApp = filterApp(info);
            appInfo.setUserApp(isUserApp);
            */

            listApp.add(appInfo);
        }

        return  listApp;
    }

    //判断是否为系统应用
    public boolean filterApp(ApplicationInfo applicationInfo){
        if ((applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0){
            return true;
        } else if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0){
            return true;
        }

        return false;
    }

    //获取正在运行的应用
    public List<AppInfo> getRunningApp(Context context){
        List<AppInfo> listApp = new ArrayList<>();
        List<RunningAppProcessInfo> runningTaskInfos = new ArrayList<>();

        PackageManager pm = context.getPackageManager();
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        if (am != null) {
            runningTaskInfos = am.getRunningAppProcesses(); //获取运行进程
        }

        for (RunningAppProcessInfo info : runningTaskInfos){
            AppInfo runningApp = new AppInfo();

            String app_packname = info.processName;
            runningApp.setPackname(app_packname);
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo(app_packname, 0);

                Drawable app_icon = applicationInfo.loadIcon(pm);
                runningApp.setApp_icon(app_icon);

                String app_name = applicationInfo.loadLabel(pm).toString();
                runningApp.setApp_name(app_name);

            }catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
                runningApp.setPackname(app_packname);
                Drawable app_icon = context.getResources().getDrawable(R.drawable.ic_launcher_background);
                runningApp.setApp_icon(app_icon);
            }

            listApp.add(runningApp);
        }

        return listApp;

    }

    public List<AppInfo> getRunningTask(Context context){
        List<AppInfo> listApp = new ArrayList<>();
        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();

        PackageManager pm = context.getPackageManager();

        for (AndroidProcess process : processes){
            AppInfo runningApp = new AppInfo();

            String packname = process.name;
            runningApp.setPackname(packname);
            try {
                ApplicationInfo applicationInfo = pm.getApplicationInfo(packname, 0);

                Drawable icon = applicationInfo.loadIcon(pm);
                runningApp.setApp_icon(icon);

                String name = applicationInfo.loadLabel(pm).toString();
                runningApp.setApp_name(name);
            } catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }

            listApp.add(runningApp);
        }

        return listApp;
    }


    public ApplicationInfo getAppInfo(String name, List<ApplicationInfo> appList){
        PackageManager pm = context.getPackageManager();
        appList = pm.getInstalledApplications(0);

        if (name == null){
            return null;
        } else {
            for (ApplicationInfo info : appList){
                if (name.equals(info.processName)){
                    return info;
                }
            }
        }
        return null;
    }

}
