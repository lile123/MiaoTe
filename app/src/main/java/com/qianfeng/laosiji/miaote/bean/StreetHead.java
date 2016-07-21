package com.qianfeng.laosiji.miaote.bean;

/**
 * Created by lenovo11 on 2016/7/14.
 */
public class StreetHead {

    /**
     * title : 喵特一夏
     * intro :  想畅玩漫展但却苦苦止步于门票的你不用再纠结啦！ 没错！ 喵特就是这么壕无人性！谁让大家这么支持我们呢 ⁄(⁄ ⁄•⁄ω⁄•⁄ ⁄)⁄

     活动须知：

     ① 暑假漫展期间参与漫展在成功进入会场后发布展会现场/集邮等漫展相关照片，发布喵街并加标签#喵特一夏# 即可参与活动。

     ② 通过喵街勾搭到小伙伴一起逛漫展并在喵特APP购票的参与者获得奖励的机会更大哦！

     ③奖励只限活动开始到截止日期期间漫展！获得最多赞+评论数的前100名用户，可以获得200猫粮。
     特别奖：全年门票为城市全年漫展单日票最高票价为50元！

     ④获得特别奖的小伙伴只需提供所在城市（例如：深圳）并在该漫展开展前一周私信展务娘领票哦！领取喵特全年门票后所成功进场的漫展都需拍照现场图片即发布微博或喵街 加话题#喵特我在现场#


     活动奖励：
     城市全年漫展单日票一份
     200猫粮 X 100份

     活动时间：
     7月1日—8月31日
     * logo : /data/upload/2016/0615/16/57611428adc8f.jpg
     * exhibition_id : 0
     * to_id : 0
     * data : {"eid":"0"}
     */

    private String title;
    private String intro;
    private String logo;
    private String exhibition_id;
    private String to_id;
    /**
     * eid : 0
     */

    private DataBean data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getExhibition_id() {
        return exhibition_id;
    }

    public void setExhibition_id(String exhibition_id) {
        this.exhibition_id = exhibition_id;
    }

    public String getTo_id() {
        return to_id;
    }

    public void setTo_id(String to_id) {
        this.to_id = to_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private String eid;

        public String getEid() {
            return eid;
        }

        public void setEid(String eid) {
            this.eid = eid;
        }
    }
}
