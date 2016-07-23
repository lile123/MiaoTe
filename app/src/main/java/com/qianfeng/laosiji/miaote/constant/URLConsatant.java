package com.qianfeng.laosiji.miaote.constant;

/**
 * Created by lenovo11 on 2016/7/14.
 */
public class URLConsatant {
    /**
     * 基础连接
     */
    public static final String URL_BASE = "http://api.nyato.com";

    /**
     * 获取热门城市列表
     */
    public static final String URL_HOT_CITY = "http://api.nyato.com/index.php?app=android&mod=Expo&act=getHotCitys&&token=2bc4c945edb32c64c7f4f9853eb8e463&app_version=3.4&tickets=1";

    /**
     * 漫展完成页面数据
     */
    public static final String URL_FINISH = "http://api.nyato.com/index.php?app=android&mod=Expo&act=expired_list&&token=36ac12bbc663a58524277e2093718bcd&app_version=3.4&tickets=1";


    /**
     * 详情页面
     */
    public static final String URL_Details = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_detail&&token=b9297bc832265b95d68f24c9b65430bc&app_version=3.4&tickets=1";

    /**
     * 漫展全部页面数据
     */
    public static final String URL_CITY_DATA = "http://api.nyato.com/index.php?app=android&mod=Expo&act=ex_list&&token=07a34d80b5457faf160e562266bc4770&app_version=3.4&tickets=1";

    /**
     * 广告栏
     */
    public static final String URL_BANNER = "http://api.nyato.com/index.php?app=android&mod=Index&act=getSlide&token=e3e50f189265d749cdce39e1b9491a63&app_version=3.4&tickets=1";
    /**
     * 喵街list
     */
    public static final String URL_STREET = "http://api.nyato.com/index.php?app=android&mod=Street&act=sencePhotoList&&token=3920b3cc52f620d74400e26e28db5065&app_version=3.4&tickets=1";
    /**
     * 喵街头文件
     */
    public static final String URL_STREET_HEAD = "http://api.nyato.com/index.php?app=android&mod=Street&act=getRecommend&&token=67446962f9f7e20d57768119ece1d267&app_version=3.4&tickets=1";
    /**
     * 发现
     */
    public static final String URL_FIND = "http://api.nyato.com/index.php?app=android&mod=Public&act=index&&token=8fd606a65db4f793ee92b2dd3a110d0e&app_version=3.4&tickets=1";
    /**
     * 发现商城(post请求) 参数p
     */
    public static final String URL_FIND_SHOP = "http://api.nyato.com/index.php?app=android&mod=Shop&act=giftData&&token=b26e671c2e5acb47a8ca3bb276fc1273&app_version=3.4&tickets=1";
    /**
     * 发现任务（post请求）
     */
    public static final String URL_FIND_TASK= "http://api.nyato.com/index.php?app=android&mod=Task&act=index&&token=8fd606a65db4f793ee92b2dd3a110d0e&app_version=3.4&tickets=1";
    /**
     * Report详情(post请求) 参数id
     */
    public static final String URL_REPORT_INFO = "http://api.nyato.com/index.php?app=android&mod=Report&act=report_info&&token=b86f22f2b9cb4ba9e5b14a56245d6f6a&app_version=3.4&tickets=1";
    /**
     * StreetInfo(post)
     */
    public static final String URL_STREET_INFO = "http://api.nyato.com/index.php?app=android&mod=Street&act=senceDetail&&token=887007a8f50dc432a27f82e3a402ebe0&app_version=3.4&tickets=1";
    /**
     *推荐喵街
     */
    public static final String URL_STREET_RECOMMEND ="http://api.nyato.com/index.php?app=android&mod=Street&act=sencePhotoList&&token=3920b3cc52f620d74400e26e28db5065&app_version=3.4&tickets=1";


}
