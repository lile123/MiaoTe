package com.qianfeng.laosiji.miaote.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/13.
 */
public class OtherLocationBean {

    private int result;
    private String error;
    private int city_count;

    private List<DataBean> data;
    private List<CityDataBean> city_data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getCity_count() {
        return city_count;
    }

    public void setCity_count(int city_count) {
        this.city_count = city_count;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<CityDataBean> getCity_data() {
        return city_data;
    }

    public void setCity_data(List<CityDataBean> city_data) {
        this.city_data = city_data;
    }

}
