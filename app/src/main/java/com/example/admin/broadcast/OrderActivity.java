package com.example.admin.broadcast;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.tester.R;

public class OrderActivity extends AppCompatActivity {

    private static final String DATAONE = "DataOne";
    private EditText name;
    private EditText age;
    private EditText nativePlace;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Button submit = (Button)findViewById(R.id.submit);
        submit.setOnClickListener(listener);
    }

    Button.OnClickListener listener = new Button.OnClickListener(){
        public void onClick(View v){
            name = (EditText)findViewById(R.id.name);
            age = (EditText)findViewById(R.id.age);
            nativePlace = (EditText)findViewById(R.id.native_place);

            Intent intent = new Intent();
            intent.setAction(DATAONE);
            intent.putExtra("name", name.getText().toString());
            intent.putExtra("age", age.getText().toString());
            intent.putExtra("native_place", nativePlace.getText().toString());
            sendBroadcast(intent);
        }
    };
}
