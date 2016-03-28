package com.example.admin.activities;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.example.admin.database.MyDbHelper;
import com.example.admin.tester.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FriendsManager extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    EditText mText;
    Button mAdd;
    ListView mList;

    MyDbHelper myDbHelper;
    SQLiteDatabase mDb;
    Cursor mCursor;
    SimpleCursorAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_layout);

        mText = (EditText) findViewById(R.id.id_name);
        mAdd = (Button) findViewById(R.id.id_add_friend);
        mAdd.setOnClickListener(this);
        mList = (ListView) findViewById(R.id.id_friend_list);
        mList.setOnItemClickListener(this);

        myDbHelper = new MyDbHelper(this);
    }

    @Override
    public void onResume() {
        super.onResume();

        mDb = myDbHelper.getWritableDatabase();
        String[] columes = new String[] {"_id", MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
        mCursor = mDb.query(MyDbHelper.TABLE_NAME, columes, null, null, null, null, null);
        String[] headers = new String[] {MyDbHelper.COL_NAME, MyDbHelper.COL_DATE};
        mAdapter = new SimpleCursorAdapter(this, android.R.layout.two_line_list_item, mCursor, headers, new int[] {android.R.id.text1, android.R.id.text2});
        mList.setAdapter(mAdapter);

    }

    @Override
    public void onPause() {
        super.onPause();
        //关闭连接
        mDb.close();
        mCursor.close();
    }

    @Override
    public void  onClick(View v) {
        //向数据库添加新数据
        ContentValues cv = new ContentValues(2);
        cv.put(MyDbHelper.COL_NAME, mText.getText().toString());
        //格式化日期
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //插入当前时间
        cv.put(MyDbHelper.COL_DATE, dateFormat.format(new Date()));
        mDb.insert(MyDbHelper.TABLE_NAME, null, cv);
        //更新好友列表
        mCursor.requery();
        mAdapter.notifyDataSetChanged();
        //清空编辑字段
        mText.setText(null);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
        //删除好友（未填充代码）
    }
}


