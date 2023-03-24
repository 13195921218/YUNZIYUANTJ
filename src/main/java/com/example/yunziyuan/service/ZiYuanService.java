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
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ZiYuanService {

    static {
        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                new javax.net.ssl.HostnameVerifier() {
                    public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
                        return hostname.equals("10.126.20.2");
                    }
                });
            }
    @Autowired
    ZiYuanDao ziyuanDao;
    public void httpRequestEcs() throws SQLException {
        Token token = new Token();
        ZiYuanDao c = new ZiYuanDao();  //连接数据库
        Connection con = c.getConn();
        c.getEcsCount1();
        c.getEcsCount2();
        //调用的api的接口地址
        for (int size = 1; size <= 3; size++) {
            String apiPath = "https://10.126.20.2/rest/tenant-resource/v1/instances/CLOUD_VM?pageNo=" +
                    size +
                    "&pageSize=1000";
            BufferedReader in = null;
            StringBuilder result = null;
            System.out.println("调用apiPath：" + apiPath);
            try {
                URL url = new URL(apiPath);
                //打开和url之间的连接
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                connection.setRequestProperty("X-Auth-Token", token.jsonObject1.get("accessSession").toString());
                connection.connect();
                result = new StringBuilder();
                //读取URL的响应
                in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
                String line;
                while ((line = in.readLine()) != null) {
                    result.append(line);
                }
                String result2 = result.toString(); //返回json字符串
                //获取数据
                JSONObject jsonObject = JSON.parseObject(result2);
                //JSONObject resultJsonObject = jsonObject.getJSONObject("??");
                //JSONObject bodyJsonObject = resultJsonObject.getJSONObject("??");
                JSONArray jsonArray = jsonObject.getJSONArray("objList");
                //System.out.println(jsonArray);
                try {
                    Statement sql;
                    ResultSet res;
                    int a;
                    sql = con.createStatement();
                    //从jsonBean中获取封装的数据插入数据库中，得道的每条的数据都插入
                    for (Object o : jsonArray) {
                        JSONObject jsonObject2 = (JSONObject) o;
                        if (jsonObject2.get("vdcName") != null) {
                            a = sql.executeUpdate("insert into HXRegionAB (vdcName,bizRegionNativeId,name,flavorRamSize,flavorVcpu,nativeId)"
                                    + "values('" + jsonObject2.get("vdcName").toString() + "','"
                                    + jsonObject2.get("bizRegionNativeId").toString() + "','"
                                    + jsonObject2.get("name").toString() + "','" +
                                    jsonObject2.get("flavorRamSize").toString() + "','" +
                                    jsonObject2.get("flavorVcpu").toString() + "','" +
                                    jsonObject2.get("nativeId").toString() + "')");
                        }
                    }
                    c.getEcsCount1();
                    System.out.println("当前时间：" + new Date() + "      第" + size +  "页数据插入成功！");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            catch (IOException e) {
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

    public void httpRequestDcs() throws SQLException {
        Token token = new Token();
        ZiYuanDao c = new ZiYuanDao();  //连接数据库
        Connection con = c.getConn();
        c.getDcsCount1();
        c.getDcsCount2();
        //调用的api的接口地址
        String apiPath = "https://10.126.20.2/rest/tenant-resource/v1/instances/CLOUD_DCS_INSTANCE?pageNo=1&pageSize=1000";
        BufferedReader in = null;
        StringBuilder result = null;
        System.out.println("调用apiPath：" + apiPath);
        try {
            URL url = new URL(apiPath);
            //打开和url之间的连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("X-Auth-Token", token.jsonObject1.get("accessSession").toString());
            connection.connect();
            result = new StringBuilder();
            //读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            String result2 = result.toString(); //返回json字符串
            //获取数据
            JSONObject jsonObject = JSON.parseObject(result2);
            JSONArray jsonArray = jsonObject.getJSONArray("objList");
            //System.out.println(jsonArray);
            try {
                Statement sql;
                ResultSet res;
                int a;
                sql = con.createStatement();
                //从jsonBean中获取封装的数据插入数据库中，得道的每条的数据都插入
                for (int j = 0; j < jsonArray.size(); j++) {
                    JSONObject jsonObject2 = (JSONObject) jsonArray.get(j);
                    if (jsonObject2.get("engineVersion").equals("5.0") ) {
                        a = sql.executeUpdate("insert into hxdcslist (vdcName,bizRegionName,name,engineVersion,usedMemory,cacheMode)"
                                + "values('" + jsonObject2.get("vdcName").toString() + "','"
                                + jsonObject2.get("bizRegionName").toString() + "','"
                                + jsonObject2.get("name").toString() + "','" +
                                jsonObject2.get("engineVersion").toString() + "','"+
                                jsonObject2.get("usedMemory").toString() +"','"+
                                jsonObject2.get("cacheMode").toString()+ "')");
                    }
                }
                c.getDcsCount1();
                System.out.println("当前时间：" + new Date()  +  "      数据插入成功！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        catch (IOException e) {
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


