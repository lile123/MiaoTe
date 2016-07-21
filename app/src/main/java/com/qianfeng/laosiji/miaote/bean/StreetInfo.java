package com.qianfeng.laosiji.miaote.bean;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/20.
 */
public class StreetInfo {

    /**
     * is_digg : 0
     * digg_count : 11
     * comment_count : 3
     * result : 1
     * info :
     * data : [{"comment_id":"231955","uid":"454152","ctime":"1468996789","content":"不同价格的票给的游戏券不同","uname":"kuma桑乃终生伴侣","uavatar":"http://img.nyato.com/data/upload/avatar/13/07/a7/original_200_200.jpg?v1468967111","sex":"2"},{"comment_id":"231949","uid":"450974","ctime":"1468996325","content":"@大呗 :好像要扫描什么的","uname":"支持巴奈","uavatar":"http://img.nyato.com/data/upload/avatar/0c/bc/f4/original_200_200.jpg?v1468732999","sex":"1"},{"comment_id":"231939","uid":"454499","ctime":"1468995976","content":"这里渣渣弱弱问个问题，如果买了电子票那么怎么进场","uname":"大呗","uavatar":"http://img.nyato.com/data/upload/avatar/c7/75/ca/original_200_200.jpg?v1468994394","sex":"1"}]
     */

    private int is_digg;
    private String digg_count;
    private String comment_count;
    private int result;
    private String info;
    /**
     * comment_id : 231955
     * uid : 454152
     * ctime : 1468996789
     * content : 不同价格的票给的游戏券不同
     * uname : kuma桑乃终生伴侣
     * uavatar : http://img.nyato.com/data/upload/avatar/13/07/a7/original_200_200.jpg?v1468967111
     * sex : 2
     */

    private List<DataBean> data;

    public int getIs_digg() {
        return is_digg;
    }

    public void setIs_digg(int is_digg) {
        this.is_digg = is_digg;
    }

    public String getDigg_count() {
        return digg_count;
    }

    public void setDigg_count(String digg_count) {
        this.digg_count = digg_count;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String comment_id;
        private String uid;
        private String ctime;
        private String content;
        private String uname;
        private String uavatar;
        private String sex;

        public String getComment_id() {
            return comment_id;
        }

        public void setComment_id(String comment_id) {
            this.comment_id = comment_id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getCtime() {
            return ctime;
        }

        public void setCtime(String ctime) {
            this.ctime = ctime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getUavatar() {
            return uavatar;
        }

        public void setUavatar(String uavatar) {
            this.uavatar = uavatar;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }
    }
}
