package com.example.admin.views;


import com.example.admin.models.Memo;
import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by admin on 2016/3/22.
 */
public interface MemosView extends MvpView {
    void showMemos(List<Memo> memoList);
    void updateMemoList();
    void showLoading();
    void showError(Exception e);
}
