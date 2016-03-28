package com.example.admin.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.database.presenters.MemoPresenter;
import com.example.admin.models.MemoApi;
import com.example.admin.presenters.MemoEditPresenter;
import com.example.admin.tester.R;
import com.example.admin.views.MemoEditView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class EditMemoActivity extends Activity {

    private final String LOG_TAG = getClass().getSimpleName();

    private static String EDITOR_STATE;//Flag
    private static final String ACTION_EDIT_MEMO = "EDIT_MEMO";
    private static final String ACTION_NEW_MEMO = "CREATE_MEMO";

    private MemoApi memoApi;
    private int memoId;

    @InjectView(R.id.memo_title) EditText title;
    @InjectView(R.id.memo_content) EditText content;
    @InjectView(R.id.update_memo_bt) TextView updateButton;
    @InjectView(R.id.create_memo_bt) TextView createButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e(LOG_TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memo_editor);

        ButterKnife.inject(this);

        Intent intent = getIntent();
        String actionType = intent.getStringExtra("ACTION_TYPE");

        if(actionType.equals(ACTION_EDIT_MEMO)) {
            EDITOR_STATE = "UPDATE";
            memoId = intent.getIntExtra("ID", 0);
            title.setText(intent.getStringExtra("TITLE"));
            content.setText(intent.getStringExtra("CONTENT"));
            createButton.setVisibility(View.GONE);

        } else if(actionType.equals(ACTION_NEW_MEMO)) {
            EDITOR_STATE = "CREATE";
            updateButton.setVisibility(View.GONE);
        }

        memoApi = MemoApi.getInstance(this);

    }

    @Override
    protected void onResume(){
        super.onResume();
    }


    public void onClick(View v){
        Log.e(LOG_TAG, "onClick:" + EDITOR_STATE);

        String title = this.title.getText().toString();
        String content = this.content.getText().toString();

        if(title == null) {
            Toast toast = Toast.makeText(this, "请输入标题", Toast.LENGTH_LONG);
            toast.show();
        }

        switch (v.getId()) {
            case R.id.create_memo_bt:
                memoId = memoApi.createMemo(title, content);
                updateButton.setVisibility(View.VISIBLE);
                createButton.setVisibility(View.GONE);
                break;
            case R.id.update_memo_bt:
                memoApi.updateMemo(memoId, title, content);
                break;
        }

        //判断是否成功写入DB???????????????
        Toast toast = Toast.makeText(this, "保存成功！", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        memoApi.close();
    }

}
