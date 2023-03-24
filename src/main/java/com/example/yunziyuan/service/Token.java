package com.example.yunziyuan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Token {
    //获取token的api的接口地址
    HttpURLConnectionPOST httpURLConnectionPOST = new HttpURLConnectionPOST();
    String strJson = httpURLConnectionPOST.httpURLConnectionPUT("https://10.126.20.2/rest/plat/smapp/v1/oauth/token", "{\"grantType\": \"password\",\"userName\": \"test0001\",\"value\": \"Huawei@123gsyb\"}");
    JSONObject jsonObject1 = JSON.parseObject(strJson);
}
