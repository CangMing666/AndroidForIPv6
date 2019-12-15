package me.cangming.androidforipv6.network.okhttp;

import android.content.Context;

import java.io.IOException;

import me.cangming.androidforipv6.network.NetCallBack;
import me.cangming.androidforipv6.network.NetRequest;
import me.cangming.androidforipv6.util.LogUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @date 创建时间：2019-12-15
 * @auther cangming
 * @Description Okhttp 网络请求类
 */
public class OKhttpRequest implements NetRequest {

    private static final String TAG = OKhttpRequest.class.getSimpleName();

    private OkHttpClient okHttpClient = null;

    private Context context;

    private DnsSelector.Mode IP_SELECTOR_MODE;

    private OKhttpRequest() {
    }

    public static OKhttpRequest getInstance() {
        return Holder.INSTANCE;
    }


    public void init(Context context) {
        init(context, DnsSelector.Mode.SYSTEM);
    }

    private void init(Context context, DnsSelector.Mode mode) {
        this.context = context;
        this.IP_SELECTOR_MODE = mode;
    }

    private static class Holder {
        static final OKhttpRequest INSTANCE = new OKhttpRequest();
    }

    @Override
    public void request(String requestUrl, NetCallBack callBack) {
        checkNet();

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                callBack.onFailed(e);
                LogUtil.e(TAG, "net reqeust error info: " + e.toString());
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String result = response.body().string();
                callBack.onSuccess(result);
            }
        });
    }


    private void checkNet() {
        if (okHttpClient == null) {
            okHttpClient = new OkHttpClient.Builder()
                    .eventListenerFactory(NetWorkEventListener.FACTORY)
                    .dns(new DnsSelector(IP_SELECTOR_MODE))
                    .build();
        }
    }


    public String getIpModeDetail() {
        if (IP_SELECTOR_MODE == DnsSelector.Mode.SYSTEM) {
            return "SYSTEM";
        } else if (IP_SELECTOR_MODE == DnsSelector.Mode.IPV4_FIRST) {
            return "IPV4_FIRST";
        } else if (IP_SELECTOR_MODE == DnsSelector.Mode.IPV6_FIRST) {
            return "IPV6_FIRST";
        } else if (IP_SELECTOR_MODE == DnsSelector.Mode.IPV4_ONLY) {
            return "IPV4_ONLY";
        } else {
            return "IPV6_ONLY";
        }
    }
}
