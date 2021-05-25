package com.yxf.spiderstock.dailylimit.spider;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author yangxiangfeng
 * @date 2021/5/25 5:33 下午
 */
public class StockSpider {

    public void test() {
        HttpClient httpClient = new DefaultHttpClient();

        // todo
        String url = "";
        // 抓取今日涨停股票
        HttpGet get = new HttpGet(url);



    }



}
