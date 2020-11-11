package com.yxf.spiderstock.limitupanalysis.service;


import com.yxf.spiderstock.limitupanalysis.entity.LimitUpAnalysisEntity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 同花顺涨停复盘 每日爬取
 *
 * @author yxf
 */
@Service
public class LimitUpAnalysisSpider {

    private static final String BASE_URL = "http://yuanchuang.10jqka.com.cn/thshx_list/index_{0}.shtml";

    private static final String TITLE_KEY = "涨停复盘：";

    public static void main(String[] args) throws IOException {
        LimitUpAnalysisSpider spider = new LimitUpAnalysisSpider();
        spider.spider();
    }

    public void spider() {

        List<LimitUpAnalysisEntity> entityList = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            String url = MessageFormat.format(BASE_URL, i);
            entityList.addAll(crawlTitleAndContentUrl(url));

            for (LimitUpAnalysisEntity entity : entityList) {
                String content = crawlContent(entity.getUrl());
                if (StringUtils.hasText(content)) {
                    entity.setContent(content);
                    entity.setSpiderDate(new Date());
                }
            }
            if (entityList.size() > 30) {
                for (LimitUpAnalysisEntity entity : entityList) {
                    System.out.println(entity.getUrl());
                }
                break;
            }
        }
    }

    private List<LimitUpAnalysisEntity> crawlTitleAndContentUrl(String url) {
        List<LimitUpAnalysisEntity> list = new ArrayList<>();

        try {
            Document document = Jsoup.connect(url).get();
            Elements elementsByClass = document.getElementsByClass("list-con");
            if (elementsByClass == null || elementsByClass.size() != 1) {
                throw new RuntimeException("搜索页列表解析不正确。");
            }

            Element element = elementsByClass.get(0);
            Elements tagAs = element.getElementsByTag("a");
            for (int i = 0; i < tagAs.size(); i+=2) {
                Element tagA = tagAs.get(i);
                String title = tagA.attr("title");
                if (title.startsWith(TITLE_KEY)) {
                    LimitUpAnalysisEntity entity = new LimitUpAnalysisEntity();
                    entity.setTitle(title);
                    entity.setUrl(tagA.attr("href"));
                    entity.setDate(tagA.nextElementSibling().text());
                    list.add(entity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private String crawlContent(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements elementsByClass = document.getElementsByClass("main-text");
            if (elementsByClass == null || elementsByClass.size() != 1) {
                throw new RuntimeException("文章详情解析不正确。");
            }
            Element element = elementsByClass.get(0);
            return element.text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
