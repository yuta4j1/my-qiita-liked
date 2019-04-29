package com.yuta4j1.myqiitaliked;

import com.yuta4j1.myqiitaliked.crawler.QiitaCrawler;
import com.yuta4j1.myqiitaliked.model.ArticleJson;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class QiitaCrawlerTest {

    private QiitaCrawler crawler;

    @Before
    public void setup() {
        crawler = new QiitaCrawler("https://qiita.com/ggggoooo/like");
    }

    @Test
    public void getData() {
        List<ArticleJson> articles = crawler.fetchArticleList();
        articles.forEach(article -> {
            assertNotNull(article.getUuid());
            assertNotNull(article.getTitle());
            assertNotNull(article.getUuid());
            assertNotNull(article.getUserImageUrl());
            assertNotNull(article.getUpdateDate());
        });
    }


}
