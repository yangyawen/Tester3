package com.example.admin.activities;

import com.example.admin.tester.R;
import com.example.admin.views.ChangeColorIconWithText;
import com.example.admin.views.TabFragment;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;

public class Home extends FragmentActivity implements OnClickListener, OnPageChangeListener {

    private ViewPager mViewPager;
    private List<Fragment> mFragmentContainer = new ArrayList<Fragment>();
    private String[] mPages = new String[]{ "Page One", "Page Two", "Page Three",	"Page Four" };

    private FragmentPagerAdapter mAdapter;
    private List<ChangeColorIconWithText> mViewContainer = new ArrayList<ChangeColorIconWithText>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //createAd();
        setContentView(R.layout.home_page);
        //setOverflowButtonAlways();
        //getActionBar().setDisplayShowHomeEnabled(false);

        initView();
        initDatas();
        mViewPager.setAdapter(mAdapter);
        initEvent();
    }

    private void initView(){
        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);

        ChangeColorIconWithText viewOne = (ChangeColorIconWithText) findViewById(R.id.id_indicator_one);
        mViewContainer.add(viewOne);
        ChangeColorIconWithText viewTwo = (ChangeColorIconWithText) findViewById(R.id.id_indicator_two);
        mViewContainer.add(viewTwo);
        ChangeColorIconWithText viewThree = (ChangeColorIconWithText) findViewById(R.id.id_indicator_three);
        mViewContainer.add(viewThree);
        ChangeColorIconWithText viewFour = (ChangeColorIconWithText) findViewById(R.id.id_indicator_four);
        mViewContainer.add(viewFour);

        viewOne.setOnClickListener(this);
        viewTwo.setOnClickListener(this);
        viewThree.setOnClickListener(this);
        viewFour.setOnClickListener(this);

        viewOne.setIconAlpha(1.0f);

    }

    /**
     * 初始化所有事件
     */
    private void initEvent() {
        mViewPager.setOnPageChangeListener(this);
    }

    private void initDatas() {
        for (String page : mPages)
        {
            TabFragment mFragment = new TabFragment();
            Bundle bundle = new Bundle();
            bundle.putString(TabFragment.TITLE, page);
            mFragment.setArguments(bundle);
            mFragmentContainer.add(mFragment);
        }

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()){

            @Override
            public int getCount(){
                return mFragmentContainer.size();
            }

            @Override
            public Fragment getItem(int position){
                return mFragmentContainer.get(position);
            }
        };
    }


    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

	/*
	private void setOverflowButtonAlways()
	{
		try
		{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKey = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");
			menuKey.setAccessible(true);
			menuKey.setBoolean(config, false);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}*/

    /**
     * 设置menu显示icon
     */
    /*
    @Override
    public boolean onMenuOpened(int featureId, Menu menu){

        if (featureId == Window.FEATURE_ACTION_BAR && menu != null){
            if (menu.getClass().getSimpleName().equals("MenuBuilder")){
                try{
                    Method m = menu.getClass().getDeclaredMethod(
                            "setOptionalIconsVisible", Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu, true);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return super.onMenuOpened(featureId, menu);
    }*/

    @Override
    public void onClick(View v){
        clickTab(v);
    }

    /**
     * 点击Tab按钮
     *
     * @param v
     */
    private void clickTab(View v){
        resetOtherTabs();

        switch (v.getId()){
            case R.id.id_indicator_one:
                mViewContainer.get(0).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.id_indicator_two:
                mViewContainer.get(1).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(1, false);
                break;
            case R.id.id_indicator_three:
                mViewContainer.get(2).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.id_indicator_four:
                mViewContainer.get(3).setIconAlpha(1.0f);
                mViewPager.setCurrentItem(3, false);
                break;
        }
    }

    /**
     * 重置其他的TabIndicator的颜色
     */
    private void resetOtherTabs(){
        for (int i = 0; i < mViewContainer.size(); i++){
            mViewContainer.get(i).setIconAlpha(0);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset,
                               int positionOffsetPixels){
        // Log.e("TAG", "position = " + position + " ,positionOffset =  "
        // + positionOffset);
        if (positionOffset > 0){
            ChangeColorIconWithText left = mViewContainer.get(position);
            ChangeColorIconWithText right = mViewContainer.get(position + 1);
            left.setIconAlpha(1 - positionOffset);
            right.setIconAlpha(positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPageScrollStateChanged(int state){
        // TODO Auto-generated method stub

    }

}
