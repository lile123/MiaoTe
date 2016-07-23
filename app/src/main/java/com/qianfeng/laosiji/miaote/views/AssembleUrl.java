package com.qianfeng.laosiji.miaote.views;

import android.os.Looper;

import com.qianfeng.laosiji.miaote.constant.URLConsatant;

/**
 * Created by Administrator on 2016/7/22.
 */
public class AssembleUrl {
    private static AssembleUrl mAssembleUrl;
    private String province;
    private String city;
    private String p;
    private String section_id;
    private String ticket;
    private String month;

    public static AssembleUrl newInstance(String province, String city, String p){
        if(null == mAssembleUrl){
            mAssembleUrl = new AssembleUrl(province, city, p);
        }
        return mAssembleUrl;
    }

    private  AssembleUrl(String province, String city, String p) {
        this.province = province;
        this.city = city;
        this.p = p;
    }


    public AssembleUrl setProvince(String province) {
        this.province = province;
        return this;
    }

    public AssembleUrl setCity(String city) {
        this.city = city;
        return this;
    }

    public AssembleUrl setP(String p) {
        this.p = p;
        return this;
    }

    public AssembleUrl setSection_id(String section_id) {
        this.section_id = section_id;
        ticket = null;
        month = null;
        return this;
    }

    public AssembleUrl setTicket(String ticket) {
        this.ticket = ticket;
        section_id = null;
        month = null;
        return this;
    }

    public AssembleUrl setMonth(String month) {
        this.month = month;
        section_id = null;
        ticket = null;
        return this;
    }

    public String getUrl(String baseUrl) {
        String url = baseUrl + "&province=" + province + "&city=" + city + "&p=" + p;
        if (null != section_id) {
            url += "&section_id=" + section_id;
            return url;
        }
        if (null != ticket) {
            url += "&ticket=" + ticket;
            return url;
        }
        if(null != month){
            url += "&month=" + month;
            return url;
        }
        return url;
    }
}
