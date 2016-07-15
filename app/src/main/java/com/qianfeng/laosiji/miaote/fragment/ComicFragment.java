package com.qianfeng.laosiji.miaote.fragment;


import android.os.Bundle;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import com.qianfeng.laosiji.miaote.ui.BaiDuActivity;

import com.qianfeng.laosiji.miaote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComicFragment extends Fragment {
    private List<Fragment> fragmentList;
    private MyAdapter adapter;
    private ViewPager mViewPager;
    private String[] titles = new String[]{"全部","参加","结束"};
    private TabLayout mTabLayout;

    public ComicFragment() {
        // Required empty public constructor
    }

    public static ComicFragment newInstance() {
        
        Bundle args = new Bundle();
        
        ComicFragment fragment = new ComicFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comic, container, false);
        mViewPager = (ViewPager) view.findViewById(R.id.comic_vp);
        mTabLayout = (TabLayout) view.findViewById(R.id.comic_tl);
        initFragment();
        initTabLayout();
        bindView();
        return view;
    }
    private void initTabLayout() {
        for(int i=0;i<titles.length;i++){
            mTabLayout.addTab(mTabLayout.newTab().setText(titles[i]));
        }
    }

    private void bindView() {
        adapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(AllFragment.newInstance());
        fragmentList.add(AttendFragment.newInstance());
        fragmentList.add(FinishFragment.newInstance());
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList == null ? 0 : fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

}}
