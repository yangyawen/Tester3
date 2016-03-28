package com.example.admin.models;

import android.content.Context;
import android.util.Log;

import com.example.admin.database.DatabaseHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import java.util.List;

/**
 * Created by admin on 2016/3/22.
 */
public class MemoApi {

    private static final String LOG_TAG = "MemoApi";

    private static MemoApi instance = null;

    private DatabaseHelper databaseHelper = null;
    //private RuntimeExceptionDao<Memo, Integer> memoDao;
    private RuntimeExceptionDao<Memo, Integer> dao;

    //private Memo memo = null;

    /* 所有备忘录的唯一集合 */
    //private List<Memo> MemoList = null;

    private MemoApi(Context context){
        Log.e(LOG_TAG,"MemoApi");
        if(databaseHelper == null){
            databaseHelper = DatabaseHelper.getHelper(context);
        }
        dao = databaseHelper.getRuntimeDao(Memo.class);
        if(dao == null){
            Log.e("-------------","dao is null");
        }

    }
    public synchronized static MemoApi getInstance(Context context){
        if (instance == null) {
            instance = new MemoApi(context.getApplicationContext());
        }
        return instance;
    }

    public List<Memo> getMemos() {
        Log.e(LOG_TAG,"getMemos");

        List<Memo> memos = dao.queryForAll();
        return memos;
    }

    //创建一条数据
    public int createMemo(String title, String content){
        Log.e(LOG_TAG,"createMemo");
        Memo memo = new Memo(title, content);
        dao.create(memo);
        Log.e(LOG_TAG, "Create a new Memo, id:" + memo.getId());
        return memo.getId();
    }

    public void updateMemo(int memoId, String title, String content) {
        Log.e(LOG_TAG,"updateMemo");
        Memo memo = dao.queryForId(memoId);
        memo.setTitle(title);
        memo.setContent(content);
        dao.update(memo);
    }


    //获取一条数据
    public Memo getMemoById(int id){
        if(id > 0){
            return  dao.queryForId(id);
        }else {
            Log.e(LOG_TAG, "Fail to get Memo for invalid id: " + id);
            return null;
        }

    }

    //删除一条数据
    public void deleteMemoById(int id){
        dao.deleteById(id);
    }

    public void close(){
        if(databaseHelper != null){
            //OpenHelperManager.releaseHelper();
            databaseHelper.close();
            databaseHelper = null;
        }
        instance = null;
    }
}
