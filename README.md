## Android Okhttp 如何选择模式如下：
```
// 初始化+ IPV6 优先模式（默认）
OKhttpRequest.getInstance().init(getContext());
// 只选择 IPV6 模式
//OKhttpRequest.getInstance().init(getContext(), DnsSelector.Mode.IPV6_ONLY);
```

## 如何测试当前网络支持IPV6
1. 利用联通卡/电信卡/移动卡，则可以分配到IPV6


## ToDoList
1. 是否支持图片访问 （Glide+Fresco+Picasso）
