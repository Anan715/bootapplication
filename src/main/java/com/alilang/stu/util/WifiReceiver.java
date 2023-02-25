//package com.alilang.stu.util;
//
//
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.wifi.ScanResult;
//import android.net.wifi.WifiManager;
//import android.util.Log;
//
//import java.util.List;
//
//public class WifiReceiver extends BroadcastReceiver {
//    private WifiManager wifiManager;
//    private OnWifiScanResultListener onWifiScanResultListener;
//
//    public WifiReceiver(WifiManager wifiManager, OnWifiScanResultListener onWifiScanResultListener) {
//        this.wifiManager = wifiManager;
//        this.onWifiScanResultListener = onWifiScanResultListener;
//    }
//
//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (wifiManager.startScan()) {
//            List<ScanResult> scanResults = wifiManager.getScanResults();
//            onWifiScanResultListener.onWifiScanResult(scanResults);
//        } else {
//            Log.e("WifiScan", "Start scan failed");
//        }
//    }
//
//    public interface OnWifiScanResultListener {
//        void onWifiScanResult(List<ScanResult> scanResults);
//    }
//}