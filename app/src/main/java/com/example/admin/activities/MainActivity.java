package com.example.admin.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import com.example.admin.broadcast.BroadcastTester;
import com.example.admin.date_storage.StorageActivity;
import com.example.admin.tester.R;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mOptionsGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Main", "onStart");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOptionsGroup = (RadioGroup)findViewById(R.id.options_group);
    }

    @Override
    public void onStart(){
        super.onStart();
        PreferenceManager.setDefaultValues(this, R.xml.settings, false);
    }
    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
        String name = settings.getString("namePref", "");
        boolean isMoreEnabled = settings.getBoolean("morePref", false);
    }

    public void onPostClick(View v) {
        final int noteId = mOptionsGroup.getCheckedRadioButtonId();
        Intent intent;
        switch (noteId) {
            case R.id.id_notification:
                intent = new Intent(this, NotificationActivity.class);
                startActivity(intent);
                break;
            case R.id.id_broadcast:
                intent = new Intent(this, BroadcastTester.class);
                startActivity(intent);
                break;
            case R.id.id_preference:
                intent = new Intent(this, StorageActivity.class);
                startActivity(intent);
                break;
            case R.id.id_customize_layout:
                intent = new Intent(this, Home.class);
                startActivity(intent);
                break;
            case R.id.id_file:
                intent = new Intent(this, ShortInfoStorager.class);
                startActivity(intent);
                break;
            case R.id.id_contacts:
                intent = new Intent(this, FriendsManager.class);
                startActivity(intent);
                break;
            case R.id.id_note_listener:
                intent = new Intent(this, NoteListener.class);
                startActivity(intent);
                break;
            case R.id.id_memo:
                intent = new Intent(this, MemoActivity.class);
                startActivity(intent);
                break;
            default:
                throw new IllegalArgumentException("Unknown Type");
        }
    }
}
