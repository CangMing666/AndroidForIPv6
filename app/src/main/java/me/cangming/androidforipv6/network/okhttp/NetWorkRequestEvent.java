package me.cangming.androidforipv6.network.okhttp;

public class NetWorkRequestEvent {
    public long dnsStartTime;
    public long dnsEndTime;
    public long responseBodySize;
    public boolean apiSuccess;
    public String errorReason;
}