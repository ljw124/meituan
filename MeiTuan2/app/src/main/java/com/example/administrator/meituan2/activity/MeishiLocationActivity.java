package com.example.administrator.meituan2.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.example.administrator.meituan2.R;
import com.example.administrator.meituan2.location.MyOrientationListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/18.
 */

public class MeishiLocationActivity extends AppCompatActivity {

    //百度地图
    private MapView mMapView;
    private BaiduMap mBaiduMap;
    //百度地图定位
    private LocationClient mLocationClient;
    private LocationClientOption mLocationClientOption;
    private boolean isFirstIn = true;
    private double mLatitude; //用来记录最新的纬度
    private double mLongitude;
    //自定义定位图标
    private BitmapDescriptor mLocationIcon;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;
    //定位模式
    private MyLocationConfiguration.LocationMode mLocationMode;
    //导航
    private LatLng myLastLocation; //起点
    private LatLng myDestLocation; //终点
    private Button navigation;
    private String mSDCardPath = null;
    public static final String APP_FOLDER_NAME = "meituan";
    public static final String ROUTE_PLAN_NODE = "touteplan";
//    private BNRoutePlanNode.CoordinateType mCoordinateType = null;
    public static List<Activity> activityList = new LinkedList<Activity>();
    private Button xuniNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
        setContentView(R.layout.activity_meishi_location);
        initVeiw();
        initLocation();
    }

    /**
     * 地图
     */

    private void initVeiw() {
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.mapview);
        //获取百度地图
        mBaiduMap = mMapView.getMap();
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomTo(16);
        mBaiduMap.setMapStatus(mapStatusUpdate);
        //初始化按钮，并设置监听事件
//        xuniNavigation = (Button) findViewById(R.id.map_xuni_navigation);
//        navigation = (Button) findViewById(R.id.map_navigation);
//        xuniNavigation.setOnClickListener(this);
//        navigation.setOnClickListener(this);
        //给地图设置一个长按的监听事件，长按时添加标记终点的覆盖物
        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                //长按事件会返回一个经纬度，把此经纬度设置给终点覆盖物
                myDestLocation = latLng;
                addDestInfoOverlay(latLng);
            }
            //给地图添加表示终点的覆盖物
            private void addDestInfoOverlay(LatLng latLng) {
                mBaiduMap.clear();
                OverlayOptions options = new MarkerOptions().position(latLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_map_route_mark_end))
                        .zIndex(5);
                mBaiduMap.addOverlay(options);
            }
        });

    }

    /**
     * 定位
     */
    private void initLocation() {
        // 1，初始化LocationClient，此类是定位的核心
        mLocationClient = new LocationClient(this);
        // 2，给LocationClient注册监听，此步必不可少
        mLocationClient.registerLocationListener(new mDBLocationListener());
        // 3，给LocationClient设置参数
        mLocationClientOption = new LocationClientOption();
        mLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        mLocationClientOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
//        int span=5000;
//        mLocationClientOption.setScanSpan(span);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        mLocationClientOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        mLocationClientOption.setOpenGps(true);//可选，默认false,设置是否使用gps
        mLocationClientOption.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//        mLocationClientOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        mLocationClientOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        mLocationClientOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        mLocationClientOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
//        mLocationClientOption.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(mLocationClientOption);

        //--------------------------------------------------
        //初始化定位图标
        mLocationIcon = BitmapDescriptorFactory.fromResource(R.mipmap.ic_navi_map_gps_locked);
        //初始化方向传感器
        myOrientationListener = new MyOrientationListener(this);
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
        //初始化定位模式
        mLocationMode = MyLocationConfiguration.LocationMode.COMPASS;

//        if (initDirs()) {
//            initNavi();
//        }
    }


/**
     * 创建菜单项
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

/**
     * 在菜单中出来地图的转换
     * @param item
     * @return
     */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.map_common:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
            case R.id.map_site:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
                break;
            case R.id.map_tranfic:
                mBaiduMap.setTrafficEnabled(true);
                break;
            case R.id.map_location:
                LatLng latlng = new LatLng(mLatitude, mLongitude);
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latlng);
                mBaiduMap.setMapStatus(mapStatusUpdate);
                break;
            case R.id.map_mode_common:
                mLocationMode = MyLocationConfiguration.LocationMode.NORMAL;
                break;
            case R.id.map_mode_following:
                mLocationMode = MyLocationConfiguration.LocationMode.FOLLOWING;
                break;
            case R.id.map_mode_compass:
                mLocationMode = MyLocationConfiguration.LocationMode.COMPASS;
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private class mDBLocationListener implements BDLocationListener {
        //定位成功后调用此方法,参数bdLocation中包含了定位相关的所以信息
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            //重构定位信息,把定到的位设置给百度地图,用来显示当前位置
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getLatitude())
                    .longitude(bdLocation.getLongitude())
                    .direction(mCurrentX) //设置方向传感器
                    .build();
            mBaiduMap.setMyLocationData(data);

            //更新经纬度
            mLatitude = bdLocation.getLatitude();
            mLongitude = bdLocation.getLongitude();

            //配置定位图标,第一个参数是定位模式
            MyLocationConfiguration myLocationConfiguration = new MyLocationConfiguration(mLocationMode,true, mLocationIcon);
            mBaiduMap.setMyLocationConfigeration(myLocationConfiguration);

            //当用户第一次进入时，地图地位到当前位置
            if (isFirstIn){
                LatLng latlng = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latlng);
                mBaiduMap.setMapStatus(mapStatusUpdate);
                Toast.makeText(getApplicationContext(),bdLocation.getAddrStr(),Toast.LENGTH_LONG).show();
                isFirstIn = false;
            }
        }
    }

/**
     * 在onStart中开始地图定位
     */

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true);
        if (!mLocationClient.isStarted()){
            mLocationClient.start();
            //开启传感器
           // myOrientationListener.start();
        }
    }

/**
     * 在onStop中关闭地图定位
     */

    @Override
    protected void onStop() {
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);
        if (mLocationClient.isStarted()){
            mLocationClient.stop();
            //关闭传感器
            //myOrientationListener.stop();
        }

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
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }
}

/**
 * 按钮的监听事件
 * @param view
 */

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.map_location:
//                LatLng latlng = new LatLng(mLatitude, mLongitude);
//                MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLng(latlng);
//                mBaiduMap.setMapStatus(mapStatusUpdate);
//                break;
//            case R.id.map_xuni_navigation:
//                if (myDestLocation == null){
//                    Toast.makeText(MeishiLocationActivity.this,"长按地图设置终点",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                routeplanToNavi(false);
//                break;
//            case R.id.map_navigation:
//                if (myDestLocation == null){
//                    Toast.makeText(MeishiLocationActivity.this,"长按地图设置终点",Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                routeplanToNavi(true);
//                break;
//        }
//    }

//    private boolean initDirs() {
//        mSDCardPath = getSdcardDir();
//        if (mSDCardPath == null) {
//            return false;
//        }
//        File f = new File(mSDCardPath, APP_FOLDER_NAME);
//        if (!f.exists()) {
//            try {
//                f.mkdir();
//            } catch (Exception e) {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        return true;
//    }
//    private String getSdcardDir() {
//        if (Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)) {
//            return Environment.getExternalStorageDirectory().toString();
//        }
//        return null;
//    }
//
//    String authinfo = null;
//
//    private void initNavi() {
//        BaiduNaviManager.getInstance().init(this, mSDCardPath, APP_FOLDER_NAME,
//            new BaiduNaviManager.NaviInitListener() {
//                @Override
//                public void onAuthResult(int status, String msg) {
//                    if (0 == status) {
//                        authinfo = "key校验成功!";
//                    } else {
//                        authinfo = "key校验失败, " + msg;
//                    }
//                    MeishiLocationActivity.this.runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//                            Toast.makeText(MeishiLocationActivity.this, authinfo, Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//
//                public void initSuccess() {
//                    Toast.makeText(MeishiLocationActivity.this, "百度导航引擎初始化成功", Toast.LENGTH_SHORT).show();
//                }
//
//                public void initStart() {
//                    Toast.makeText(MeishiLocationActivity.this, "百度导航引擎初始化开始", Toast.LENGTH_SHORT).show();
//                }
//
//                public void initFailed() {
//                    Toast.makeText(MeishiLocationActivity.this, "百度导航引擎初始化失败", Toast.LENGTH_SHORT).show();
//                }
//            }, null, ttsHandler, ttsPlayStateListener);
//    }
//
//    private void routeplanToNavi(boolean b) {
//        mCoordinateType = BNRoutePlanNode.CoordinateType.GCJ02;
//        BNRoutePlanNode sNode = null;
//        BNRoutePlanNode eNode = null;
//
////        sNode = new BNRoutePlanNode(116.30142, 40.05087, "百度大厦", null, mCoordinateType);
////        eNode = new BNRoutePlanNode(116.39750, 39.90882, "北京天安门", null, mCoordinateType);
//        sNode = new BNRoutePlanNode(mLongitude,mLatitude,"我的位置", null,mCoordinateType);
//        eNode = new BNRoutePlanNode(myDestLocation.longitude,myDestLocation.latitude,"终点位置",
//                null,mCoordinateType);
//
//        if (sNode != null && eNode != null) {
//            List<BNRoutePlanNode> list = new ArrayList<>();
//            list.add(sNode);
//            list.add(eNode);
//            BaiduNaviManager.getInstance().launchNavigator(this, list, 1, true, new DemoRoutePlanListener(sNode));
//        }
//    }
//    public class DemoRoutePlanListener implements BaiduNaviManager.RoutePlanListener {
//
//        private BNRoutePlanNode mBNRoutePlanNode = null;
//
//        public DemoRoutePlanListener(BNRoutePlanNode node) {
//            mBNRoutePlanNode = node;
//        }
//
//        @Override
//        public void onJumpToNavigator() {
//             /*
//             * 设置途径点以及resetEndNode会回调该接口
//             */
//            for (Activity ac : activityList) {
//                if (ac.getClass().getName().endsWith("BNDemoGuideActivity")) {
//                    return;
//                }
//            }
//            Intent intent = new Intent(MeishiLocationActivity.this, BNDemoGuideActivity.class);
//            Bundle bundle = new Bundle();
//            bundle.putSerializable(ROUTE_PLAN_NODE, (BNRoutePlanNode) mBNRoutePlanNode);
//            intent.putExtras(bundle);
//            startActivity(intent);
//
//        }
//
//        @Override
//        public void onRoutePlanFailed() {
//            // TODO Auto-generated method stub
//            Toast.makeText(MeishiLocationActivity.this, "算路失败", Toast.LENGTH_SHORT).show();
//        }
//    }