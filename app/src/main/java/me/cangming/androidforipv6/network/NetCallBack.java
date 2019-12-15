package me.cangming.androidforipv6.network;

/**
 * @date 创建时间：2019-12-15
 * @auther cangming
 * @Description 网络请求回调
 */
public interface NetCallBack {
    void onSuccess(String result);

    void onFailed(Exception e);
}
