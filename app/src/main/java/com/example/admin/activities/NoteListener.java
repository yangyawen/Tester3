package com.example.admin.activities;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.admin.tester.R;

import java.util.List;

public class NoteListener extends Activity {

    private TextView serviceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_listener);
        serviceState = (TextView) findViewById(R.id.id_service_state);

        if(isServicceRunning(this, "com.example.admin.services.MonitorService")){
            serviceState.setText("通知监听器正在监听您的通知...");
        }else{
            serviceState.setText("通知监听器未启动，请在设置-安全-通知读取权限中开通此应用程序的监听权限");
        }


    }

    public static boolean isServicceRunning(Context context, String name) {
        boolean isRunning = false;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = manager.getRunningServices(30);

        if(!(services.size() > 0)){
            return false;
        }

        for(int i=0; i<services.size(); i++){
            if(services.get(i).service.getClassName().equals(name) == true){
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }
}
