package com.qianfeng.laosiji.miaote.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/16.
 */
public class HotCityBean {

    /**
     * pid : 110000
     * cid : 110100
     * name : 北京
     */

    private List<CitysBean> citys;

    public List<CitysBean> getCitys() {
        return citys;
    }

    public void setCitys(List<CitysBean> citys) {
        this.citys = citys;
    }

    public static class CitysBean {
        private String pid;
        private String cid;
        private String name;

        public String getPid() {
            return pid;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
