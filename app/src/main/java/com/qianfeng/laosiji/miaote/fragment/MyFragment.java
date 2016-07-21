package com.qianfeng.laosiji.miaote.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.ui.MyApplication;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {
    MyApplication myApplication;

    public MyFragment() {
        // Required empty public constructor
    }

    public static MyFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        return view;
    }

}
