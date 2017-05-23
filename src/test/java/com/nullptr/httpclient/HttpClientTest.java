package com.nullptr.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

/**
 * Created by Nullptr on 2017/5/18.
 */
public class HttpClientTest {

    @Test
    public void testHttpClient() throws IOException {
        //创建一个httpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建一个HttpGet对象，需要指定一个请求的URL
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        //执行请求
        CloseableHttpResponse execute = httpClient.execute(httpGet);
        //接收返回结果
        HttpEntity httpEntity = execute.getEntity();
        //取出响应消息
        String html = EntityUtils.toString(httpEntity);

        System.out.println(html);

        execute.close();
        httpClient.close();
    }

    @Test
    public void testHttpPost() throws IOException {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost httpPost = new HttpPost("http://localhost:8082/posttest.html");

        List<NameValuePair> formList = new ArrayList<>();

        formList.add(new BasicNameValuePair("name", "张三"));
        formList.add(new BasicNameValuePair("pass", "1234"));

        StringEntity entity = new UrlEncodedFormEntity(formList, "utf-8");
        httpPost.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(httpPost);

        HttpEntity httpEntity = response.getEntity();
        String result = EntityUtils.toString(httpEntity);
        System.out.println(result);

        response.close();
        httpClient.close();


    }

    @Test
    public void test() {
        Date date = new Date();


        String dateString = new SimpleDateFormat("yyyy-MM-dd").format(date);
        System.out.println(dateString);



    }
}
