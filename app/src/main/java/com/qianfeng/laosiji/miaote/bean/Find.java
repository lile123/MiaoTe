package com.qianfeng.laosiji.miaote.bean;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/15.
 */
public class Find {

    /**
     * result : 1
     * task_num : 2
     * data : {"report_title":"COS正片：尤菲米娅","report_logo":"/data/upload/2016/0713/17/57860ca99cbdb.jpg","to_id":"204","task_title":"内测群号","task_logo":"/data/upload/2016/0705/14/577b520e90a9c.jpg"}
     * gift_data : [{"id":"657","name":"正版大鱼海棠周边折扇","img":"/data/upload/2016/0711/15/57834d2eef915.png","start_time":"1468548000","end_time":"1473904800","price":"2450","mb_price":"35","intro":" 注意：礼品会在兑换后30天内发货，请耐心等待哦~ ","pay_type":"0","is_start":0,"start_days":0,"surplus_days":62},{"id":"635","name":"漫猫咪后院猫粮袋束口单肩、双肩包","img":"/data/upload/2016/0711/15/578344e2a59c1.png","start_time":"1468548000","end_time":"1473904800","price":"2730","mb_price":"39","intro":" 注意：礼品会在兑换后30天内发货，请耐心等待哦~ 品名：猫咪后院猫粮印象束口袋 品牌：漫踪 尺寸：束口袋主体尺寸（平铺） 材质：涤纶 使用方法：手提、单肩背、双肩背皆可 ","pay_type":"0","is_start":0,"start_days":0,"surplus_days":62},{"id":"633","name":"舰队collection舰娘bismarck人字拖 41-42码","img":"/data/upload/2016/0711/15/5783454c0598d.png","start_time":"1468548000","end_time":"1473904800","price":"3150","mb_price":"45","intro":" 注意：礼品会在兑换后30天内发货，请耐心等待哦~ 品名：舰娘Bismarck印象拖鞋 品牌：漫踪 适合人群：绅士 材质：人字部分PVC，鞋底EVA发泡底 码数：41-42 鞋底厚度：1.8cm ","pay_type":"0","is_start":0,"start_days":0,"surplus_days":62},{"id":"630","name":"fate stay night亚瑟王saber手机壳套 iphone6","img":"/data/upload/2016/0711/15/578345aeb4e18.png","start_time":"1468548000","end_time":"1473904800","price":"1330","mb_price":"19","intro":" 注意：礼品会在兑换后30天内发货，请耐心等待哦~ 品牌：漫踪 品名：saber手机壳 材质：硅胶 ","pay_type":"0","is_start":0,"start_days":0,"surplus_days":62},{"id":"629","name":"fate stay night亚瑟王saber手机壳套 iphone6s","img":"/data/upload/2016/0711/15/5783460e1e20a.png","start_time":"1468548000","end_time":"1473904800","price":"1330","mb_price":"19","intro":" 注意：礼品会在兑换后30天内发货，请耐心等待哦~ 品牌：漫踪 品名：saber手机壳 材质：硅胶 ","pay_type":"0","is_start":0,"start_days":0,"surplus_days":62}]
     */

    private int result;
    private int task_num;
    /**
     * report_title : COS正片：尤菲米娅
     * report_logo : /data/upload/2016/0713/17/57860ca99cbdb.jpg
     * to_id : 204
     * task_title : 内测群号
     * task_logo : /data/upload/2016/0705/14/577b520e90a9c.jpg
     */

    private DataBean data;
    /**
     * id : 657
     * name : 正版大鱼海棠周边折扇
     * img : /data/upload/2016/0711/15/57834d2eef915.png
     * start_time : 1468548000
     * end_time : 1473904800
     * price : 2450
     * mb_price : 35
     * intro :  注意：礼品会在兑换后30天内发货，请耐心等待哦~
     * pay_type : 0
     * is_start : 0
     * start_days : 0
     * surplus_days : 62
     */

    private List<GiftDataBean> gift_data;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getTask_num() {
        return task_num;
    }

    public void setTask_num(int task_num) {
        this.task_num = task_num;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<GiftDataBean> getGift_data() {
        return gift_data;
    }

    public void setGift_data(List<GiftDataBean> gift_data) {
        this.gift_data = gift_data;
    }

    public static class DataBean {
        private String report_title;
        private String report_logo;
        private String to_id;
        private String task_title;
        private String task_logo;

        public String getReport_title() {
            return report_title;
        }

        public void setReport_title(String report_title) {
            this.report_title = report_title;
        }

        public String getReport_logo() {
            return report_logo;
        }

        public void setReport_logo(String report_logo) {
            this.report_logo = report_logo;
        }

        public String getTo_id() {
            return to_id;
        }

        public void setTo_id(String to_id) {
            this.to_id = to_id;
        }

        public String getTask_title() {
            return task_title;
        }

        public void setTask_title(String task_title) {
            this.task_title = task_title;
        }

        public String getTask_logo() {
            return task_logo;
        }

        public void setTask_logo(String task_logo) {
            this.task_logo = task_logo;
        }
    }

    public static class GiftDataBean {
        private String id;
        private String name;
        private String img;
        private String start_time;
        private String end_time;
        private String price;
        private String mb_price;
        private String intro;
        private String pay_type;
        private int is_start;
        private int start_days;
        private int surplus_days;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getMb_price() {
            return mb_price;
        }

        public void setMb_price(String mb_price) {
            this.mb_price = mb_price;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getPay_type() {
            return pay_type;
        }

        public void setPay_type(String pay_type) {
            this.pay_type = pay_type;
        }

        public int getIs_start() {
            return is_start;
        }

        public void setIs_start(int is_start) {
            this.is_start = is_start;
        }

        public int getStart_days() {
            return start_days;
        }

        public void setStart_days(int start_days) {
            this.start_days = start_days;
        }

        public int getSurplus_days() {
            return surplus_days;
        }

        public void setSurplus_days(int surplus_days) {
            this.surplus_days = surplus_days;
        }
    }
}
