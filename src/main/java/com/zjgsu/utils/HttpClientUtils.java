package com.zjgsu.utils;


import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.SerializableEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;

/**
 * HttpClient工具类
 *
 * @author wmh
 */
@Component
public class HttpClientUtils {

    private static final String UTF8="utf-8";

    private static CloseableHttpClient httpClient;

    private static RequestConfig requestConfig;

    public static String sendHttp(HttpRequestMethodEnum requestMethod, String url, Map<String,Serializable> params, Map<String,String> header){

        HttpRequestBase request = requestMethod.createRequest(url);
        request.setConfig(requestConfig);

        CloseableHttpResponse httpResponse = null;
        String responseContent = null;

        //添加请求头
        if (header!=null){
            for (Map.Entry<String,String> entry:header.entrySet()){
                request.setHeader(entry.getKey(),entry.getValue());
            }
        }

        //添加请求参数
        try {
            if (params!=null){
                for (Map.Entry<String,Serializable> entry:params.entrySet()){
                    ((HttpEntityEnclosingRequest)request).setEntity(new SerializableEntity(entry.getValue(),true));
                }
            }
            //执行HTTP请求
            httpResponse=httpClient.execute(request);
            //获取响应对象实体
            HttpEntity entity = httpResponse.getEntity();
            //从响应对实体中获取响应的内容
            if (entity!=null){
                responseContent=EntityUtils.toString(entity,UTF8);
            }
        }catch (IOException e){
            e.printStackTrace();;
        }finally {
            try {
                //关闭响应对象;
                if (httpResponse != null) {
                    httpResponse.close();
                }
                //关闭HttpClient.
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return responseContent;
    }

    @Resource(name = "httpClient")
    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Resource(name = "requestConfig")
    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
