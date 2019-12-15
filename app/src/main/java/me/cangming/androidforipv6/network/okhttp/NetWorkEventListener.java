package me.cangming.androidforipv6.network.okhttp;

import android.util.Log;


import androidx.annotation.Nullable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;

import me.cangming.androidforipv6.util.LogUtil;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @date 创建时间：2019-11-04
 * @auther cangming
 * @Description 网络库监听事件
 */
public class NetWorkEventListener extends EventListener {

    public static final String TAG = "NetWorkEventListener";
    private NetWorkRequestEvent netWorkRequestEvent;

    public static final Factory FACTORY = new Factory() {
        @Override
        public EventListener create(Call call) {
            return new NetWorkEventListener();
        }
    };


    private NetWorkEventListener() {
        super();
        netWorkRequestEvent = new NetWorkRequestEvent();
    }

    @Override
    public void callStart(Call call) {
        super.callStart(call);
    }

    @Override
    public void dnsStart(Call call, String domainName) {
        super.dnsStart(call, domainName);
        netWorkRequestEvent.dnsStartTime = System.nanoTime();
        LogUtil.i(TAG, ":  domainName: " + domainName);
    }

    @Override
    public void dnsEnd(Call call, String domainName, List<InetAddress> inetAddressList) {
        super.dnsEnd(call, domainName, inetAddressList);
        netWorkRequestEvent.dnsEndTime = System.nanoTime();
        LogUtil.i(TAG, call.request().url() + ":  dns Cost Time: " + (netWorkRequestEvent.dnsEndTime / 1000 - netWorkRequestEvent.dnsStartTime / 1000));
    }

    @Override
    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        super.connectStart(call, inetSocketAddress, proxy);
    }

    @Override
    public void secureConnectStart(Call call) {
        super.secureConnectStart(call);
    }

    @Override
    public void secureConnectEnd(Call call, @Nullable Handshake handshake) {
        super.secureConnectEnd(call, handshake);
    }

    @Override
    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol) {
        super.connectEnd(call, inetSocketAddress, proxy, protocol);
    }

    @Override
    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, @Nullable Protocol protocol, IOException ioe) {
        super.connectFailed(call, inetSocketAddress, proxy, protocol, ioe);
    }

    @Override
    public void connectionAcquired(Call call, Connection connection) {
        super.connectionAcquired(call, connection);
    }

    @Override
    public void connectionReleased(Call call, Connection connection) {
        super.connectionReleased(call, connection);
    }

    @Override
    public void requestHeadersStart(Call call) {
        super.requestHeadersStart(call);
    }

    @Override
    public void requestHeadersEnd(Call call, Request request) {
        super.requestHeadersEnd(call, request);
    }

    @Override
    public void requestBodyStart(Call call) {
        super.requestBodyStart(call);
    }

    @Override
    public void requestBodyEnd(Call call, long byteCount) {
        super.requestBodyEnd(call, byteCount);
    }

    @Override
    public void responseHeadersStart(Call call) {
        super.responseHeadersStart(call);
    }

    @Override
    public void responseHeadersEnd(Call call, Response response) {
        super.responseHeadersEnd(call, response);
    }

    @Override
    public void responseBodyStart(Call call) {
        super.responseBodyStart(call);
    }

    @Override
    public void responseBodyEnd(Call call, long byteCount) {
        super.responseBodyEnd(call, byteCount);
        netWorkRequestEvent.responseBodySize = byteCount;
    }

    @Override
    public void callEnd(Call call) {
        super.callEnd(call);
        netWorkRequestEvent.apiSuccess = true;
    }

    @Override
    public void callFailed(Call call, IOException ioe) {
        Log.i(TAG, "callFailed ");
        super.callFailed(call, ioe);
        netWorkRequestEvent.apiSuccess = false;
        netWorkRequestEvent.errorReason = Log.getStackTraceString(ioe);
        Log.i(TAG, "reason " + netWorkRequestEvent.errorReason);
    }

}
