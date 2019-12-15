package me.cangming.androidforipv6.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import me.cangming.androidforipv6.R;
import me.cangming.androidforipv6.global.Constants;
import me.cangming.androidforipv6.network.NetCallBack;
import me.cangming.androidforipv6.network.okhttp.OKhttpRequest;
import me.cangming.androidforipv6.util.LogUtil;

/**
 * @date 创建时间：2019-12-14
 * @auther cangming
 * @Description IPV4+IPV6 测试页面
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Button mBtnIpv4Request;
    private Button mBtnIpv6Request;
    private Button mBtnIpv4AndIpv6Request;

    private TextView mTxtRequestResult;

    public static final int IPV4_REQUEST_MODE = 0;
    public static final int IPV6_REQUEST_MODE = 1;
    public static final int IPV4_AND_IPV6_REQUEST_MODE = 2;

    private int CURRENT_MODE_REQUEST = IPV4_AND_IPV6_REQUEST_MODE;
    private TextView mTxtRequestIpMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnIpv4Request = (Button) findViewById(R.id.btn_ipv4_request);
        mBtnIpv4Request.setOnClickListener(this);
        mBtnIpv6Request = (Button) findViewById(R.id.btn_ipv6_request);
        mBtnIpv6Request.setOnClickListener(this);
        mBtnIpv4AndIpv6Request = (Button) findViewById(R.id.btn_ipv4_and_ipv6_request);
        mBtnIpv4AndIpv6Request.setOnClickListener(this);
        mTxtRequestResult = (TextView) findViewById(R.id.txt_request_result);
        mTxtRequestIpMode = (TextView) findViewById(R.id.txt_request_ip_mode);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_ipv4_request:
                CURRENT_MODE_REQUEST = IPV4_REQUEST_MODE;
                ipv4Test();
                break;
            case R.id.btn_ipv6_request:
                CURRENT_MODE_REQUEST = IPV6_REQUEST_MODE;
                ipv6Test();
                break;
            case R.id.btn_ipv4_and_ipv6_request:
                CURRENT_MODE_REQUEST = IPV4_AND_IPV6_REQUEST_MODE;
                ipv4AndIpv6Test();
                break;
        }
    }

    private void ipv4Test() {
        realRequest(Constants.IPV4_TEST_URL);
    }

    private void ipv6Test() {
        realRequest(Constants.IPV6_TEST_URL);
    }

    private void ipv4AndIpv6Test() {
        realRequest(Constants.IPV4_AND_IPV6_TEST_URL);
    }

    private void realRequest(String requestUrl) {
        OKhttpRequest.getInstance().request(requestUrl, new NetCallBack() {
            @Override
            public void onSuccess(String result) {
                LogUtil.d(TAG, "net reqeust success info: " + result);
                showToast(result);
            }

            @Override
            public void onFailed(Exception e) {
                showToast("网络请求错误    " + e.toString());
            }
        });
    }


    private void showToast(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mTxtRequestIpMode.setText(getCurrentMode());
                mTxtRequestResult.setText("运营商分配到的IP地址为：\n\n " + message);
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }

        });
    }


    private String getCurrentMode() {
        if (CURRENT_MODE_REQUEST == IPV4_REQUEST_MODE) {
            return "当前请求模式： IPV4 接口  \n\n Okhttp 请求方式为：" + OKhttpRequest.getInstance().getIpModeDetail();
        } else if (CURRENT_MODE_REQUEST == IPV6_REQUEST_MODE) {
            return "当前请求模式： IPV6 接口  \n\n Okhttp 请求方式为：" + OKhttpRequest.getInstance().getIpModeDetail();
        } else {
            return "当前请求模式： IPV4+IPV6 接口 \n\n Okhttp 请求方式为：" + OKhttpRequest.getInstance().getIpModeDetail();
        }
    }
}
