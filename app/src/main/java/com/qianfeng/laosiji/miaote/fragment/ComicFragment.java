package com.qianfeng.laosiji.miaote.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qianfeng.laosiji.miaote.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ComicFragment extends Fragment {


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_comic, container, false);
    }

}
