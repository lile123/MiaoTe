package com.qianfeng.laosiji.miaote.ui;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.androidxx.yangjw.httplibrary.IOKCallBack;
import com.androidxx.yangjw.httplibrary.OkHttpTool;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedHashTreeMap;
import com.qianfeng.laosiji.miaote.BaseActivity;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.bean.HotCityBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SelectCityActivity extends BaseActivity {
    public static final String URL_HOT_CITY = "http://api.nyato.com/index.php?app=android&mod=Expo&act=getHotCitys&&token=2bc4c945edb32c64c7f4f9853eb8e463&app_version=3.4&tickets=1";
    private TextView mTextViewCurrent;
    private ListView mCustomListView;
    private List<HotCityBean.CitysBean> list = new ArrayList<>();
    private List<Map<String,String>> cityList = new ArrayList<>();
    private SimpleAdapter adapter;
    private TextView mTextViewSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        initView();
        initData();

    }

    private void initData() {
        OkHttpTool.newInstance().start(URL_HOT_CITY).callback(new IOKCallBack() {
            @Override
            public void success(String result) {
                if(null == result){
                    return;
                }
                Gson gson = new Gson();
                HotCityBean bean = gson.fromJson(result,HotCityBean.class);
                list.addAll(bean.getCitys());
                for(int i=0;i<list.size();i++){
                    Map<String,String> cityMap = new LinkedHashTreeMap<>();
                    cityMap.put("cityName",list.get(i).getName());
                    cityList.add(cityMap);
                }
                initAdapter();
                bindAdapter();
            }


        });
    }

    private void bindAdapter() {
        mCustomListView.setAdapter(adapter);
    }

    private void initAdapter() {
        adapter = new SimpleAdapter(SelectCityActivity.this,cityList,R.layout.city_name_item,new String[]{"cityName"},new int[]{R.id.tv_city_name});
    }

    private void initView() {
        mTextViewCurrent = (TextView) findViewById(R.id.tv_current_city);
        mCustomListView = (ListView)findViewById(R.id.clv_hot_city);
        mTextViewSearch = (TextView)findViewById(R.id.tv_search_city_name);
        Drawable[] searchDrawable =  mTextViewSearch.getCompoundDrawables();
        searchDrawable[0].setBounds(0,0,60,60);
        mTextViewSearch.setCompoundDrawables(searchDrawable[0],null,null,null);
    }

}
