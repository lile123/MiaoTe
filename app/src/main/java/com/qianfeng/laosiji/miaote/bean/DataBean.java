package com.qianfeng.laosiji.miaote.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DataBean {
    private String eid;
    private String name;
    private String section_id;
    private String cover;
    private String new_cover;
    private String location;
    private String province;
    private String city;
    private String start_time;
    private String end_time;
    private String addr;
    private String love;
    private String description;
    private String presale_price;
    private String scene_price;
    private String coordinate;
    private String tags;
    private String is_hot;
    private String is_ticket;


    private NewsDataBean news_data;
    private String section_name;
    private int news_status;
    private int guest_status;


    private GusetDataBean guset_data;

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection_id() {
        return section_id;
    }

    public void setSection_id(String section_id) {
        this.section_id = section_id;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getNew_cover() {
        return new_cover;
    }

    public void setNew_cover(String new_cover) {
        this.new_cover = new_cover;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getLove() {
        return love;
    }

    public void setLove(String love) {
        this.love = love;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPresale_price() {
        return presale_price;
    }

    public void setPresale_price(String presale_price) {
        this.presale_price = presale_price;
    }

    public String getScene_price() {
        return scene_price;
    }

    public void setScene_price(String scene_price) {
        this.scene_price = scene_price;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIs_hot() {
        return is_hot;
    }

    public void setIs_hot(String is_hot) {
        this.is_hot = is_hot;
    }

    public String getIs_ticket() {
        return is_ticket;
    }

    public void setIs_ticket(String is_ticket) {
        this.is_ticket = is_ticket;
    }

    public NewsDataBean getNews_data() {
        return news_data;
    }

    public void setNews_data(NewsDataBean news_data) {
        this.news_data = news_data;
    }

    public String getSection_name() {
        return section_name;
    }

    public void setSection_name(String section_name) {
        this.section_name = section_name;
    }

    public int getNews_status() {
        return news_status;
    }

    public void setNews_status(int news_status) {
        this.news_status = news_status;
    }

    public int getGuest_status() {
        return guest_status;
    }

    public void setGuest_status(int guest_status) {
        this.guest_status = guest_status;
    }

    public GusetDataBean getGuset_data() {
        return guset_data;
    }

    public void setGuset_data(GusetDataBean guset_data) {
        this.guset_data = guset_data;
    }

    public static class NewsDataBean {
        private String title;
        private String section_name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }
    }

    public static class GusetDataBean {
        private String title;
        private String section_name;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSection_name() {
            return section_name;
        }

        public void setSection_name(String section_name) {
            this.section_name = section_name;
        }
    }
}

