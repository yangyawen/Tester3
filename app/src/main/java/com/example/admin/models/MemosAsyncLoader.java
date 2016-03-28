package com.example.admin.models;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

/**
 * Created by admin on 2016/3/22.
 */
public class MemosAsyncLoader extends AsyncTask<Void, Void, List<Memo>> {

    private static final String LOG_TAG = "MemosAsyncLoader";
    private Context context;

    public interface MemosLoaderListener {
        //public void onLoadingMemos();
        public void onSuccess(List<Memo> memos);
        public void onError(Exception e);
    }

    //private String baseText;
    private MemosLoaderListener listener;

    // 构造函数
    public MemosAsyncLoader(Context context, MemosLoaderListener listener) {
        this.context = context;
        this.listener = listener;
    }

    //public void onLoadingMemos(){

    //}

    //从数据库取出memos

    @Override
    protected List<Memo> doInBackground(Void... params) {
        Log.e(LOG_TAG, "doInBackground");

        MemoApi api = MemoApi.getInstance(context);
        List<Memo> memos = api.getMemos();
        return memos;
    }

    //返回
    @Override
    protected void onPostExecute(List<Memo> memos) {
        Log.e(LOG_TAG, "onPostExecute");
        if (isCancelled() || memos == null) {
            return;
        }
        listener.onSuccess(memos);
    }
}
