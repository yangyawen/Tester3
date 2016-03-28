package com.example.admin.presenters;

import android.content.Context;
import android.util.Log;

import com.example.admin.models.Memo;
import com.example.admin.models.MemoApi;
import com.example.admin.models.MemosAsyncLoader;
import com.example.admin.views.MemosView;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.List;

/**
 * Created by admin on 2016/3/22.
 */
public class MemosPresenter extends MvpBasePresenter<MemosView> {

    private static final String LOG_TAG = "MemosPresenter";

    //Greeting Task is "business logic"
    private MemosAsyncLoader memosLoader;
    private Context context;
    private MemoApi memoApi;

    public MemosPresenter(Context context) {
        this.context = context;
        memoApi = MemoApi.getInstance(context);
    }

    private void cancelLoadingTaskIfRunning(){
        if(memosLoader != null){
            memosLoader.cancel(true);
        }
    }

    public void loadMemos(){
        Log.d(LOG_TAG, "loadMemos");

        getView().showLoading();

        if (memosLoader != null && !memosLoader.isCancelled()) {
            memosLoader.cancel(true);
        }

        memosLoader = new MemosAsyncLoader(context,
                new MemosAsyncLoader.MemosLoaderListener() {

                    @Override
                    public void onSuccess(List<Memo> memos) {
                        if (isViewAttached()) {
                            Log.e(LOG_TAG, "onSuccess");
                            getView().showMemos(memos);
                        }
                    }

                    @Override
                    public void onError(Exception e) {
                        if (isViewAttached()) {
                            Log.e(LOG_TAG, "onError");
                            getView().showError(e);
                        }
                    }
                });
        memosLoader.execute();
    }
    public void deleteMemoById(int id) {
        memoApi.deleteMemoById(id);
        getView().updateMemoList();
    }

    // Called when Activity gets destroyed, so cancel running background task

    public void detachView(boolean retainPresenterInstance){
        super.detachView(retainPresenterInstance);
        if(!retainPresenterInstance)
            cancelLoadingTaskIfRunning();
    }

}
