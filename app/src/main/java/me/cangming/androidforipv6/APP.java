package me.cangming.androidforipv6;

import android.app.Application;
import android.content.Context;

import me.cangming.androidforipv6.network.okhttp.DnsSelector;
import me.cangming.androidforipv6.network.okhttp.OKhttpRequest;

/**
 * @date 创建时间：2019-12-15
 * @auther cangming
 * @Description 应用初始化类
 */
public class APP extends Application {

    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        // 初始化+ IPV6 优先模式（默认）
        OKhttpRequest.getInstance().init(getContext());
        // 只选择 IPV6 模式
//        OKhttpRequest.getInstance().init(getContext(), DnsSelector.Mode.IPV6_ONLY);
    }

    public Context getContext() {
        return context;
    }
}
