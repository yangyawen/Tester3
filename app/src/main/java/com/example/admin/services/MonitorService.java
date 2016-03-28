package com.example.admin.services;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;

import com.example.admin.broadcast.BroadcastTester;
import com.example.admin.database.MyDbHelper;
import com.example.admin.tester.R;

public class MonitorService extends NotificationListenerService {

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {

        //确认通知是否来自此应用程序
        if(!TextUtils.equals(sbn.getPackageName(),getPackageName())){
            return;
        }
        Log.e("-------------", "Notification " + sbn.getId() + " Posted");
    }

    //当消息从消息栏删除时调用
    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){
        //确认通知是否来自此应用程序
        if(!TextUtils.equals(sbn.getPackageName(),getPackageName())){
            return;
        }
        //如果接收到基本类型的消息，删除所有消息
        if(R.id.id_basic_style != sbn.getId()){
            return;
        }

        /*
        for(StatusBarNotification note:getActiveNotifications()){
            if(TextUtils.equals(note.getPackageName(),getPackageName())){
                cancelNotification(note.getPackageName(), note.getTag(), note.getId());
            }else{
                cancelAllNotifications();
            }
        }*/
    }
}
