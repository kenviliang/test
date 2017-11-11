package com.k.listapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by kenvi on 17-11-7.
 */

public class AppAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<AppInfo> appInfos;
    private boolean isChecked;
    private Context context;

    public AppAdapter(Context context, List<AppInfo> appInfos){
        this.appInfos = appInfos;
        this.context = context;

        mInflater = (LayoutInflater) LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return appInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return appInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.app_item, null);
            viewHolder.app_icon = (ImageView) convertView.findViewById(R.id.app_icon);
            viewHolder.app_name = (TextView) convertView.findViewById(R.id.app_name);
            viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final AppInfo appInfo = appInfos.get(position);

        Drawable icon = appInfo.getApp_icon();
        String app_name = appInfo.getApp_name();


        //解决数据重复问题
        if (position % 2 == 0){
            viewHolder.app_icon.setImageDrawable(icon);
            viewHolder.app_name.setText(app_name);
            viewHolder.checkBox.setChecked(appInfo.isSelected());

            viewHolder.app_icon.setVisibility(View.VISIBLE);
            viewHolder.app_name.setVisibility(View.VISIBLE);
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        } else {
            viewHolder.app_icon.setVisibility(View.GONE);
            viewHolder.app_name.setVisibility(View.GONE);
            viewHolder.checkBox.setVisibility(View.GONE);
        }

        if (appInfos == null){
            appInfos.add(appInfo);
        }

        //控制checkbox选择
        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context, appInfo.getApp_name(), Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }

    public class ViewHolder {
        ImageView app_icon;
        TextView app_name;
        CheckBox checkBox;
    }
}
