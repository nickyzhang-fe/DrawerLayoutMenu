package com.example.cral_gates.fragment;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.cral_gates.base.BaseActivity;
import com.example.cral_gates.base.BaseFragment;
import com.example.cral_gates.widget.IntegrateFolderTitleStrip;
import com.example.cral_gates.xmenu2.R;


/**
 * Created by Administrator on 2015/7/31.
 */
public class ContentFragment extends BaseFragment{
    private List<View> mViewList = new ArrayList<View>();
    private String[] title;
    //={"title1","title2","title3","title4","title5","title6","test7"};

    private MyPagerAdapter adapter;
    private static ViewPager mViewPager;
    private IntegrateFolderTitleStrip tabStrip;
    private Toolbar mToolbar;

    public ContentFragment(){
        super();
    }

    /*
     * #0001 澶栭儴浼犲叆title view
     * leobert
     * 2015-8-28 15:03:47
     * */
    public ContentFragment(String[] mTitle, List<View> mViewList2) {
        // TODO Auto-generated constructor stub
        this.title = mTitle;
        addContentFragment(mViewList2);
    }

    private void addContentFragment(List<View> mViewList2) {
        // TODO Auto-generated method stub
        for(int i=0;i<mViewList2.size();i++) {
            mViewList.add(mViewList2.get(i));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        BaseActivity activity = (BaseActivity)getActivity();

        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("XMenu");
        activity.setSupportActionBar(mToolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });

        mViewPager=(ViewPager)view.findViewById(R.id.viewpager1);
        tabStrip=(IntegrateFolderTitleStrip)view.findViewById(R.id.folder_pager_strip);

//        //TODO get layout!
//        for(int i=0;i<title.length;i++){
//            mViewList.add(LayoutInflater.from(getActivity()).inflate(R.layout.page_item, null));
//        }
        adapter=new MyPagerAdapter();
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                tabStrip.onPageSelected(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabStrip.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                tabStrip.onPageScrollStateChanged(state);
            }
        });
        tabStrip.setViewPager(mViewPager);
    }
    public class MyPagerAdapter extends PagerAdapter {


        public MyPagerAdapter() {

        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView(mViewList.get(arg1));
        }

        @Override
        public void finishUpdate(View arg0) {
        }

        @Override
        public int getCount() {
            return mViewList.size();
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(mViewList.get(arg1), 0);
            return mViewList.get(arg1);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == (arg1);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {
        }

        @Override
        public Parcelable saveState() {
            return null;
        }
    }



    public static void setCurrentContentView(int i) {
        // TODO Auto-generated method stub
        mViewPager.setCurrentItem(i, true);
    }
}
