package com.laurdawn.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Map;

public class HttpUtils {

    public static String urlJoinParam(String url, Map<String, String> paramMap) throws Exception{
        if(paramMap.isEmpty()){
            return url;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        for(Map.Entry<String, String> entry: paramMap.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            String tmpKv;
            tmpKv = String.format(Locale.ROOT, "%s=%s&", URLEncoder.encode(key, "UTF-8"),
                    URLEncoder.encode(value, "UTF-8"));
            sb.append(tmpKv);
        }
        url += sb;
        return url;
    }

    public static String doGet(String url) throws Exception{
        HttpGet httpGet = new HttpGet(url); // 通过httpget方式来实现我们的get请求
        String result;
        try(CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        }
        return result;
    }

    public static String doPost(String url, String param) throws Exception {
        String result = "";
        // 创建httpPost远程连接实例
        HttpPost httpPost = new HttpPost(url);
        // 配置请求参数实例
        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(35000)// 设置连接主机服务超时时间
                .setConnectionRequestTimeout(35000)// 设置连接请求超时时间
                .setSocketTimeout(60000)// 设置读取数据连接超时时间
                .build();
        // 为httpPost实例设置配置
        httpPost.setConfig(requestConfig);
        // 设置请求头
        httpPost.addHeader("Content-Type", "application/json");
        // 为httpPost设置封装好的请求参数
        httpPost.setEntity(new StringEntity(param, Charset.forName("UTF-8")));
        try(CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse httpResponse = httpClient.execute(httpPost)) {
            // 从响应对象中获取响应内容
            HttpEntity entity = httpResponse.getEntity();
            result = EntityUtils.toString(entity);
        } catch (Exception e){
            throw e;
        }
        return result;
    }
}
