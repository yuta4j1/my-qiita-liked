package com.yuta4j1.myqiitaliked.controller;

import com.yuta4j1.myqiitaliked.crawler.QiitaCrawler;
import com.yuta4j1.myqiitaliked.model.ArticleJson;
import com.yuta4j1.myqiitaliked.model.PageParameter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mql")
public class MyLikedArticleController {

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<ArticleJson> fetchLikedArticleInfo() {
        int pageNum = 12;
        String baseUrl = "https://qiita.com/ggggoooo/like";
        List<ArticleJson> resultList = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {
            if (i == 1) {
                resultList.addAll(new QiitaCrawler(baseUrl).fetchArticleList());
            } else {
                resultList.addAll(new QiitaCrawler(baseUrl + "?page=" + String.valueOf(i)).fetchArticleList());
            }
        }
        return resultList;

    }

}
