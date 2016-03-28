package com.example.admin.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.tester.R;

public class BroadcastTester extends Activity {

    private static final String DATAONE = "DataOne";
    protected String name_value = "未知";
    protected String age_value = "未知";
    protected String native_place_value = "未知";

    TextView name;
    TextView age;
    TextView nativePlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("BroadcastTester", "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast_tester);

        name = (TextView)findViewById(R.id.name);
        age = (TextView)findViewById(R.id.age);
        nativePlace = (TextView)findViewById(R.id.native_place);

        Button button = (Button)findViewById(R.id.fill_page);
        button.setOnClickListener(listener);
    }

    @Override
    public void onStart(){
        Log.e("BroadcastTester", "onStart");
        super.onStart();
        Toast.makeText(this, "start now", Toast.LENGTH_SHORT).show();
        MyReceiver receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DATAONE);
        registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onResume(){
        Log.e("BroadcastTester", "onResume");
        super.onResume();
        name.setText(name_value);
        age.setText(age_value);
        nativePlace.setText(native_place_value);
    }

    Button.OnClickListener listener = new Button.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(BroadcastTester.this, OrderActivity.class);
            startActivity(intent);
        }
    };

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent){
            Log.e("MyReceiver", "onReceive");
            Toast.makeText(BroadcastTester.this, "received data", Toast.LENGTH_SHORT).show();
            if(intent.getAction().equals(DATAONE)){
                name_value = intent.getStringExtra("name");
                age_value = intent.getStringExtra("age");
                native_place_value = intent.getStringExtra("native_place");
                onResume();
            }
        }
    }


}
