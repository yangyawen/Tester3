package com.example.admin.activities;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.admin.tester.R;

public class ShortInfoStorager extends Activity implements View.OnClickListener{

    EditText email, message;
    CheckBox age;
    Button submit;

    SharedPreferences formStore;
    boolean submitSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form);

        email = (EditText) findViewById(R.id.id_email);
        message = (EditText) findViewById(R.id.id_message);
        age = (CheckBox) findViewById(R.id.id_age);

        submit = (Button) findViewById(R.id.id_form_submit);
        submit.setOnClickListener(this);

        formStore = getPreferences(Activity.MODE_PRIVATE);
    }

    @Override
    public void onResume(){
        super.onResume();
        email.setText(formStore.getString("email", ""));
        message.setText(formStore.getString("message", ""));
        age.setChecked(formStore.getBoolean("age", false));
    }

    @Override
    public void onPause(){
        super.onPause();
        if(submitSuccess){
            SharedPreferences.Editor editor = formStore.edit();
            editor.putString("email", email.getText().toString());
            editor.putString("message", message.getText().toString());
            editor.putBoolean("age", age.isChecked());
            editor.commit();
            submitSuccess = false;
        }
    }

    @Override
    public void onClick(View v) {
        submitSuccess = true;
        finish();
    }
}
