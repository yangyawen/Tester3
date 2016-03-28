package com.example.admin.database.presenters;

import android.content.Context;
import android.util.Log;

import com.example.admin.database.DatabaseHelper;
import com.example.admin.database.tables.Memo;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by admin on 2016/3/11.
 */
public class MemoPresenter {

    private final String LOG_TAG = "MemoPresenter";

    private DatabaseHelper databaseHelper = null;
    private RuntimeExceptionDao<Memo, Integer> memoDao;

    private Memo memo = null;

    /* 所有备忘录的唯一集合 */
    private List<Memo> MemoList = null;

    public MemoPresenter(Context context){
        if(databaseHelper == null){
            databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }

        //memoDao = databaseHelper.getRuntimeMemoDao();
        getAll();
    }

    //从数据库中取出表中的所有数据
    public void getAll(){
        MemoList = memoDao.queryForAll();
    }

    //表中数据与view的接口，将表中数据填入list
    public ArrayList<HashMap<String , Object>> inflate(ArrayList<HashMap<String, Object>> list){
        for (Memo memo : MemoList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("ID", memo.getId());
            item.put("TITLE", memo.getTitle());
            item.put("CONTENT", memo.getContent());
            item.put("TIME", memo.getDate());
            list.add(item);
        }
        return list;
    }

    //创建一条数据
    public int createMemo(String title, String text){

        Memo memo = new Memo(title, text);
        memoDao.create(memo);
        Log.i(LOG_TAG, "Create a new Memo, id:" + memo.getId());
        return memo.getId();
    }

    //获取一条数据
    public void getMemoById(int id){
        if(id > 0){
            this.memo = memoDao.queryForId(id);
        }else {
            Log.e(LOG_TAG, "Fail to get Memo for invalid id: " + id);
        }

    }

    //删除一条数据
    public void deleteMemoById(int id){
        memoDao.deleteById(id);
    }

    public String getTitle(){
        if(memo != null) {
            return memo.getTitle();
        }
        return null;
    }

    public void setTitle(String title) {
        if (memo != null) {
            memo.setTitle(title);
        }else{
            Log.e(LOG_TAG, "Fail to set Title for null memo.");
        }

    }

    public String getContent(){
        if (memo != null) {
            return memo.getContent();
        }
        return null;
    }

    public void setContent(String content){
        if (memo != null) {
            memo.setContent(content);
        }else {
            Log.e(LOG_TAG, "Fail to set Content for null memo.");
        }
    }

    public String getDate(){
        if (memo != null) {
            return memo.getDate();
        }
        return null;
    }

    public void setDate(String date){
        if (memo != null) {
            memo.setDate(date);
        }else{
            Log.e(LOG_TAG, "Fail to set Date for null memo.");
        }

    }

    public void update(){
        memoDao.update(memo);
    }

    public void close(){
        if(databaseHelper != null){
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
        memo = null;
        MemoList = null;
        memoDao = null;
    }

}