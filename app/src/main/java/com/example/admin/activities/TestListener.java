package com.example.admin.activities;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.admin.tester.R;

public class TestListener extends Activity implements View.OnClickListener{

    private Button start;
    private Button stop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_listen_layout);
        start = (Button)findViewById(R.id.id_start_service);
        stop = (Button)findViewById(R.id.id_stop_service);

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

    }
}
