package com.qianfeng.laosiji.miaote.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.ui.BaiDuActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StreetFragment extends Fragment {


    public StreetFragment() {
        // Required empty public constructor
    }

    public static StreetFragment newInstance() {
        
        Bundle args = new Bundle();
        
        StreetFragment fragment = new StreetFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Intent intent = new Intent(getActivity(), BaiDuActivity.class);
        startActivity(intent);
        return inflater.inflate(R.layout.fragment_street, container, false);
    }

}
