package com.qianfeng.laosiji.miaote.bean;

import java.util.List;

/**
 * Created by lenovo11 on 2016/7/17.
 */
public class TaskCenter {

    /**
     * result : 1
     * data : [{"id":"66","uid":"0","tid":"2","status":"0","receive":"0","step_name":"新手任务","step_desc":"发布1次报道喵街，让大家认识你一下！","action":"","reward":30,"award":"猫粮+30","condition":1,"task_name":"发布1次报道喵街","task_img":"","task_type":"2","progress_rate":0},{"id":"67","uid":"0","tid":"3","status":"0","receive":"0","step_name":"新手任务","step_desc":"评论1次喵街，关心下大家发布的内容","action":"","reward":15,"award":"猫粮+15","condition":1,"task_name":"评论1次喵街","task_img":"","task_type":"2","progress_rate":0},{"id":"68","uid":"0","tid":"4","status":"0","receive":"0","step_name":"新手任务","step_desc":"点赞1次喵街，支持下大家","action":"","reward":5,"award":"猫粮+5","condition":1,"task_name":"点赞1次喵街","task_img":"","task_type":"2","progress_rate":0},{"id":"69","uid":"0","tid":"5","status":"0","receive":"0","step_name":"新手任务","step_desc":"参加1个活动，不来一发面基嘛","action":"","reward":10,"award":"猫粮+10","condition":1,"task_name":"参加1个活动","task_img":"","task_type":"2","progress_rate":0},{"id":"65","uid":"0","tid":"1","status":"0","receive":"0","step_name":"新手任务","step_desc":"设置自定义头像，个性化你自己！","action":"","reward":30,"award":"猫粮+30","condition":1,"task_name":"设置自定义头像","task_img":"","task_type":"2","progress_rate":0},{"id":"64","uid":"0","tid":"6","status":"0","receive":"0","step_name":"新手任务","step_desc":"关注官方默认账号之外其他用户1人","action":"","reward":10,"award":"猫粮+10","condition":1,"task_name":"关注官方默认账号之外其他用户1人","task_img":"","task_type":"2","progress_rate":0},{"id":"63","uid":"0","tid":"11","status":"0","receive":"0","step_name":"活动任务","step_desc":"限时领取3元优惠券，使用有效期2016年8月25日(限量10000张)","action":"","reward":0,"award":"优惠券奖励","condition":1,"task_name":"暑假漫展大优惠","task_img":"","task_type":"2","progress_rate":0},{"id":"60","uid":"0","tid":"7","status":"0","receive":"0","step_name":"日常任务","step_desc":"每天评论1次喵街（奖励仅限一次哦）","action":"","reward":2,"award":"猫粮+2","condition":1,"task_name":"每天评论1次喵街","task_img":"","task_type":"1","progress_rate":0},{"id":"59","uid":"0","tid":"8","status":"0","receive":"0","step_name":"日常任务","step_desc":"每天点赞5次喵街（奖励仅限一次哦）","action":"","reward":5,"award":"猫粮+5","condition":5,"task_name":"每天点赞5次喵街","task_img":"","task_type":"1","progress_rate":0}]
     * info :
     * task_num : 2
     */

    private int result;
    private String info;
    private int task_num;
    /**
     * id : 66
     * uid : 0
     * tid : 2
     * status : 0
     * receive : 0
     * step_name : 新手任务
     * step_desc : 发布1次报道喵街，让大家认识你一下！
     * action :
     * reward : 30
     * award : 猫粮+30
     * condition : 1
     * task_name : 发布1次报道喵街
     * task_img :
     * task_type : 2
     * progress_rate : 0
     */

    private List<DataBean> data;

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

    public int getTask_num() {
        return task_num;
    }

    public void setTask_num(int task_num) {
        this.task_num = task_num;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        private String id;
        private String uid;
        private String tid;
        private String status;
        private String receive;
        private String step_name;
        private String step_desc;
        private String action;
        private int reward;
        private String award;
        private int condition;
        private String task_name;
        private String task_img;
        private String task_type;
        private int progress_rate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getReceive() {
            return receive;
        }

        public void setReceive(String receive) {
            this.receive = receive;
        }

        public String getStep_name() {
            return step_name;
        }

        public void setStep_name(String step_name) {
            this.step_name = step_name;
        }

        public String getStep_desc() {
            return step_desc;
        }

        public void setStep_desc(String step_desc) {
            this.step_desc = step_desc;
        }

        public String getAction() {
            return action;
        }

        public void setAction(String action) {
            this.action = action;
        }

        public int getReward() {
            return reward;
        }

        public void setReward(int reward) {
            this.reward = reward;
        }

        public String getAward() {
            return award;
        }

        public void setAward(String award) {
            this.award = award;
        }

        public int getCondition() {
            return condition;
        }

        public void setCondition(int condition) {
            this.condition = condition;
        }

        public String getTask_name() {
            return task_name;
        }

        public void setTask_name(String task_name) {
            this.task_name = task_name;
        }

        public String getTask_img() {
            return task_img;
        }

        public void setTask_img(String task_img) {
            this.task_img = task_img;
        }

        public String getTask_type() {
            return task_type;
        }

        public void setTask_type(String task_type) {
            this.task_type = task_type;
        }

        public int getProgress_rate() {
            return progress_rate;
        }

        public void setProgress_rate(int progress_rate) {
            this.progress_rate = progress_rate;
        }
    }
}
