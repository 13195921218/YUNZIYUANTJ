package com.example.yunziyuan.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.yunziyuan.dao.ZiYuanDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ZiYuanService {

    static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                        if (hostname.equals("10.126.20.2")) {
                            return true;
                        }
                        return false;
                    }
                });
            }
    @Autowired
    ZiYuanDao ziyuanDao;


    public void httpRequest() {
        //得到long类型当前时间
        long l = System.currentTimeMillis();
        //new日期对象
        Date date = new Date(l);
        //转换提日期输出格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String time = dateFormat.format(date);
        //获取token的api的接口地址
        HttpURLConnectionPOST httpURLConnectionPOST = new HttpURLConnectionPOST();
        String strJson = httpURLConnectionPOST.httpURLConnectionPUT("https://10.126.20.2/rest/plat/smapp/v1/oauth/token", "{\"grantType\": \"password\",\"userName\": \"test0001\",\"value\": \"Huawei@123gsyb\"}");
        JSONObject jsonObject1 = JSON.parseObject(strJson);
        System.out.println(jsonObject1.get("accessSession").toString());
        //jsonObject1.get("accessSession");
        //调用的api的接口地址
        String apiPath = "https://10.126.20.2/rest/tenant-resource/v1/instances/CLOUD_VM?pageNo=3&pageSize=1000";
        BufferedReader in = null;
        StringBuffer result = null;
        try {
            URL url = new URL(apiPath);
            //打开和url之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("X-Auth-Token", jsonObject1.get("accessSession").toString());
            connection.connect();
            result = new StringBuffer();
            //读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            String result2 = result.toString(); //返回json字符串
            //获取数据
            JSONObject jsonObject = JSON.parseObject(result2);
            //JSONObject resultJsonObject = jsonObject.getJSONObject("result");
            //JSONObject bodyJsonObject = resultJsonObject.getJSONObject("showapi_res_body");
            JSONArray jsonArray = jsonObject.getJSONArray("objList");
            //System.out.println(jsonArray);
            ZiYuanDao c = new ZiYuanDao();  //连接数据库
            Connection con = c.getConn();
            try {
                Statement sql;
                ResultSet res;
                int a;
                sql = con.createStatement();
                //从jsonBean中获取封装的数据插入数据库中，得道的每条的数据都插入
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                    if (jsonObject2.get("vdcName") != null) {
                        a = sql.executeUpdate("insert into RegonAB (vdcName,bizRegionNativeId,name,flavorRamSize,flavorVcpu,nativeId)"
                                + "values('" + jsonObject2.get("vdcName").toString() + "','"
                                + jsonObject2.get("bizRegionNativeId").toString() + "','"
                                + jsonObject2.get("name").toString() + "','" +
                                jsonObject2.get("flavorRamSize").toString() + "','" +
                                jsonObject2.get("flavorVcpu").toString() + "','" +
                                jsonObject2.get("nativeId").toString() + "')");
                    }
                }
                System.out.println("数据插入成功");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


