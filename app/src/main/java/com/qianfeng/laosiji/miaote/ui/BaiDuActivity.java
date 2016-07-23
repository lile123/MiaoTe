package com.qianfeng.laosiji.miaote.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.map.TextureMapView;
import com.baidu.mapapi.model.LatLng;
import com.qianfeng.laosiji.miaote.R;
import com.qianfeng.laosiji.miaote.icallback.ICallBackLocation;
import com.qianfeng.laosiji.miaote.listener.MyLocationListener;

import java.util.ArrayList;
import java.util.List;

public class BaiDuActivity extends AppCompatActivity {
    TextureMapView mMapView = null;
    private BaiduMap mBaidumap;
    private LocationClient locationClient;

    /**
     * 提供一个方法获取locationClient对象
     *
     * @return
     */
    public LocationClient getLocationClient() {
        return locationClient;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_du);
        Intent intent = getIntent();
        float latitude = Float.parseFloat(intent.getStringExtra("latitude"));
        float longitude  = Float.parseFloat(intent.getStringExtra("longitude"));
        locationClient = new LocationClient(getApplicationContext());
        initLocation();//声明LocationClient类
        /**
         * 注册监听 并使用接口回调操作数据
         */
        locationClient.registerLocationListener(new MyLocationListener(new ICallBackLocation() {
            @Override
            public void callLocation(BDLocation bdLocation) {
                LatLng point = new LatLng(30.459964, 114.436169);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
                OverlayOptions overlayOptions = new MarkerOptions().position(point).icon(bitmapDescriptor);
                mBaidumap.addOverlay(overlayOptions);

                LatLng point2 = new LatLng(30.466345, 114.428677);
                BitmapDescriptor bitmapDescriptor2 = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
                OverlayOptions overlayOptions2 = new MarkerOptions().position(point2).icon(bitmapDescriptor2);
                mBaidumap.addOverlay(overlayOptions2);

                getDistance(point, point2);
                List<LatLng> points = new ArrayList<LatLng>();
                points.add(point);
                points.add(point2);

                OverlayOptions ooPolyline = new PolylineOptions().width(15).color(0xAAFF0000).points(points);
//添加到地图
                mBaidumap.addOverlay(ooPolyline);
            }

            private double  getDistance(LatLng point, LatLng point2) {
                double lat1 = (Math.PI/180)*point.latitude;
                double lat2 = (Math.PI/180)*point2.latitude;

                double lon1 = (Math.PI/180)*point.longitude;
                double lon2 = (Math.PI/180)*point2.longitude;


                //地球半径
                double R = 6371;

                //两点间距离 km，如果想要米的话，结果*1000就可以了
                double d =  Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(lon2-lon1))*R;

                return d*1000;
            }
        }));
        //启动定位
        locationClient.start();
        //获取地图控件引用
        mMapView = (TextureMapView) findViewById(R.id.bmapView);
        initMark();
    }




    /**
     * 定义中心点并加上标记
     */
    private void initMark() {
        mBaidumap = mMapView.getMap();
        LatLng cenpt = new LatLng(30.459825, 114.436102);
        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_openmap_focuse_mark);
        OverlayOptions overlayOptions = new MarkerOptions().position(cenpt).icon(bitmapDescriptor);
        mBaidumap.addOverlay(overlayOptions);
        //        设置地图的中心点
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(cenpt, 19);
        mBaidumap.setMapStatus(mapStatusUpdate);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy
        );//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        int span = 1000;
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤gps仿真结果，默认需要
        locationClient.setLocOption(option);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        locationClient.stop();
    }
}
