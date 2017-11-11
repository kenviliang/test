package com.k.listapp;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<AppInfo> appInfos = new ArrayList<>();
    AppInfoManage appInfoManage = new AppInfoManage(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.list_app);

        appInfos = appInfoManage.getAppInfos(getApplicationContext()); //get all applications
        //appInfos = appInfoManage.getRunningApp(getApplicationContext());  //get task
        //appInfos = appInfoManage.getRunningTask(getApplicationContext());



        /*
        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.GET_TASKS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.REAL_GET_TASKS}, 1);
        } else {
            //appInfos = appInfoManage.getRunningApp(getApplicationContext());
            //appInfos = appInfoManage.getRunningTask(getApplicationContext());
        }
        */





        AppAdapter mAdapter = new AppAdapter(getApplicationContext(), appInfos);
        mAdapter.notifyDataSetChanged();

        listView.setAdapter(mAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), appInfos.get(position).getApp_name(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
