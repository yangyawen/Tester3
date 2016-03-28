package com.example.admin.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.admin.models.Memo;
import com.example.admin.presenters.MemosPresenter;
import com.example.admin.tester.R;
import com.example.admin.views.EditMemoFragment;
import com.example.admin.views.MemosView;
import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MemoActivity extends MvpActivity<MemosView, MemosPresenter> implements MemosView, AdapterView.OnItemClickListener {

    private final String LOG_TAG = getClass().getSimpleName();

    private int index;
    //Views相关对象
    @InjectView(R.id.memo_list) ListView memoListView;


    private ArrayList<HashMap<String, Object>> listItems = new ArrayList<HashMap<String, Object>>();
    private SimpleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(LOG_TAG, "onCreate");
        setContentView(R.layout.activity_memo);
        ButterKnife.inject(this);
        init();
    }

    public void onResume(){
        Log.e(LOG_TAG, "onResume");
        super.onResume();

        presenter.loadMemos();

    }


    protected void init(){
        Log.e(LOG_TAG, "init");
        //initiate views
        memoListView.setOnItemClickListener(this);

        //设置长按事件
        memoListView.setLongClickable(true);
        memoListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                index = (Integer) listItems.get(position).get("ID");
                return false;
            }
        });
        //初始化长按时出现的菜单
        memoListView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                //长按item弹出菜单
                /*长按item首先触发的是onItemLongClick函数，执行onItemLongClick操作
                *然后根据onItemLongClick的返回值判断是不是执行OnCreateContextMenuListener函数
                * 如果onItemLongClick返回false，则会继续执行OnCreateContextMenuListener函数
                * 否则返回true，忽略OnCreateContextMenuListener函数。
                */

                menu.add(0, 0, 0, "删除").setIcon(android.R.drawable.ic_menu_recent_history);
                menu.add(0, 1, 0, "备用的菜单项").setIcon(android.R.drawable.ic_menu_recent_history);
                menu.add(0, 2, 0, "备用的菜单项").setIcon(android.R.drawable.ic_menu_recent_history);
            }
        });

        adapter = new SimpleAdapter(this,
                listItems, R.layout.listview_item,
                new String[] {"TITLE", "TIME", "CONTENT" },
                new int[] {R.id.ItemTittle, R.id.ItemTime, R.id.ItemText});

    }

    @Override
    public MemosPresenter createPresenter(){
        return new MemosPresenter(this);
    }

    @Override
    public void showMemos(List<Memo> memoList){
        Log.e(LOG_TAG, "showMemos");
        // 刷新列表
        listItems.clear();
        for (Memo memo : memoList) {
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("ID", memo.getId());
            item.put("TITLE", memo.getTitle());
            item.put("CONTENT", memo.getContent());
            item.put("TIME", memo.getTime());
            listItems.add(item);
        }
        memoListView.setAdapter(adapter);
    }

    @Override
    public void showLoading(){
        Log.e(LOG_TAG, "showLoading");

    }

    @Override
    public void updateMemoList(){
        presenter.loadMemos();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
        Log.e(LOG_TAG, "onContextItemSelected");
        //点击长按备忘录出现的菜单调用该函数

        switch (item.getItemId()) {
            case 0:
                presenter.deleteMemoById(index);
                //onResume();
                break;
            case 1:
                Toast.makeText(this, "什么也没发生", Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(this, "什么也没发生", Toast.LENGTH_LONG).show();
                break;
        }
        return false;
    }

    public void addMemo(View v){
        Log.e(LOG_TAG, "Click to add a new Memo.");

        Intent intent = new Intent(MemoActivity.this, EditMemoActivity.class);
        intent.putExtra("ACTION_TYPE", "CREATE_MEMO");
        startActivity(intent);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.e(LOG_TAG, "onDestroy");
        //memoPresenter.close();
    }

    public void showError(Exception e){
        Log.e(LOG_TAG, "showError");
    }

    /**
     * AdapterView.OnItemClickListener接口onItemClick()
     * 点击某条备忘录时触发，将启动EditMemoActivity，进入该条备忘录的编辑界面
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.e(LOG_TAG, "onItemClick");
        //按下item后手指离开屏幕时调用 click up
        Intent intent = new Intent(MemoActivity.this, EditMemoActivity.class);

        intent.putExtra("ACTION_TYPE", "EDIT_MEMO");
        intent.putExtra("ID", (Integer) listItems.get(position).get("ID"));
        intent.putExtra("TITLE", listItems.get(position).get("TITLE").toString());
        intent.putExtra("CONTENT", listItems.get(position).get("CONTENT").toString());
        startActivityForResult(intent, 0);
    }
}