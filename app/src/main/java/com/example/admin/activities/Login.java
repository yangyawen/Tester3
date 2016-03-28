package com.example.admin.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.tester.R;

/**
 * Created by admin on 2016/2/25.
 */
public class Login extends Activity implements View.OnClickListener{

    EditText userName;
    EditText password;
    Button submitBtn = null;

    String token = "";
    static final String user_name = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initUI();
    }

    @Override
    public void onStart() {
        super.onStart();

        token = getToken();

        if (token.equals("")) {
            return;
        } else {
            //tokenLogin(token);
            gotoHomePage();
        }
    }

    @Override
    public void onClick(View v) {
        String name = userName.getText().toString();
        String pss = password.getText().toString();

        if (name.equals("") || pss.equals("")) {
            Toast.makeText(this, "登录名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //passwordLogin(name, pss);
            gotoHomePage();
        }
    }


    public void initUI(){
        submitBtn = (Button)findViewById(R.id.login_submit);
        submitBtn.setOnClickListener(this);
        userName = (EditText) this.findViewById(R.id.user_name);
        password = (EditText) this.findViewById(R.id.password);
    }

    private String getToken(){
        SharedPreferences sharedPreferences = getSharedPreferences("token", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", "");
        return token;
    }

    private void gotoHomePage(){
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }

    private void tokenLogin(String token){
        return;
    }

    private void passwordLogin(String name, String pssword){
        return;
    }
}
