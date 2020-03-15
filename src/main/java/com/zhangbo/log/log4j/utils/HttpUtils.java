package com.zhangbo.log.log4j.utils;

//import com.zhangbo.log.log4j.test.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;

import javax.validation.constraints.NotNull;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author zhangbo
 * ${Date} ${TIme}
 */
public class HttpUtils {
//    public static   String doPost(String url, String map, String charset) {
//        org.apache.http.client.HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try {
//            httpClient = new SSLClient();
//            httpPost = new HttpPost(url);
//            //设置参数
//            httpPost.addHeader("Authorisation", "jRC6YLWuK9Wj2fzo2viLiTpZ0LjTrQsBYcgCzTTxcmCLhhRvGGxPfEjo2vQv");
//            httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
//            StringEntity stringEntity = new StringEntity(map);
//            stringEntity.setContentEncoding("UTF-8");
//            stringEntity.setContentType("application/x-www-form-urlencoded");
//
//            httpPost.setEntity(stringEntity);
//            HttpResponse response = httpClient.execute(httpPost);
//            if (response != null) {
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                    result = EntityUtils.toString(resEntity, charset);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

//    public static String doPost(@NotNull String path, Map<String,String> map, String charset) {
//        org.apache.http.client.HttpClient httpClient = null;
//        HttpPost httpPost = null;
//        String result = null;
//        try {
//            httpClient = new SSLClient();
//            httpPost = new HttpPost(path);
//            //设置参数
//            List<NameValuePair> list = new ArrayList<NameValuePair>();
//            Iterator iterator = map.entrySet().iterator();
//            while (iterator.hasNext()) {
//                Map.Entry<String, String> elem = (Map.Entry<String, String>) iterator.next();
//                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
//            }
//            if (list.size() > 0) {
//                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
//                entity.setContentType("application/json");
//                httpPost.setHeader("Authorisation", "jRC6YLWuK9Wj2fzo2viLiTpZ0LjTrQsBYcgCzTTxcmCLhhRvGGxPfEjo2vQv");
//                httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
//                httpPost.setEntity(entity);
//            }
//            HttpResponse response = httpClient.execute(httpPost);
//            if (response != null) {
//                HttpEntity resEntity = response.getEntity();
//                if (resEntity != null) {
//                    result = EntityUtils.toString(resEntity, charset);
//                }
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return result;
//    }

    public static String doPost(String path,String parmas){
        URL url = null ;
        StringBuilder sb = new StringBuilder();

        try {
            url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod(HttpMethod.POST.name());
            connection.setConnectTimeout(1000);
            connection.setReadTimeout(10000);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorisation","jRC6YLWuK9Wj2fzo2viLiTpZ0LjTrQsBYcgCzTTxcmCLhhRvGGxPfEjo2vQv");
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            String content = "refNo=" + URLEncoder.encode("jusda", "UTF-8");
            content +="&status="+URLEncoder.encode("OPEN", "UTF-8");
            content +="&timestamp="+URLEncoder.encode("2020-02-02 17:36:11");
            connection.connect();
            OutputStream os = connection.getOutputStream();
            os.write(content.getBytes());
            os.flush();
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK){
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line ;
                while ((line = br.readLine())!=null){
                    sb.append(line);
                }
                br.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return sb.toString();
    }
}
