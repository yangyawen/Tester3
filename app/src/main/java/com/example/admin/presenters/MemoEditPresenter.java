package com.example.admin.presenters;

import android.content.Context;
import android.util.Log;

import com.example.admin.models.Memo;
import com.example.admin.models.MemoApi;
import com.example.admin.views.MemoEditView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

/**
 * Created by admin on 2016/3/23.
 */
public class MemoEditPresenter extends MvpBasePresenter<MemoEditView> {
    private static final String LOG_TAG = "MemoEditPresenter";
    private Context context;

    public MemoEditPresenter(Context context){
        this.context = context;
    }

    public void createMemo(String title, String content) {
        Log.e(LOG_TAG, "createMemo");
        //MemoApi api = new MemoApi(context);
        //api.createMemo(title, content);

    }

    public void updateMemo(int memoId, String title, String content) {
        Log.e(LOG_TAG, "updateMemo");
        //MemoApi api = new MemoApi(context);
        //api.updateMemo(memoId, title, content);

    }
}
