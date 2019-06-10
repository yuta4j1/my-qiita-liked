package com.yuta4j1.myqiitaliked.controller;

import com.yuta4j1.myqiitaliked.crawler.QiitaCrawler;
import com.yuta4j1.myqiitaliked.model.ArticleJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Qiitaのいいね記事一覧情報エンドポイント
 */
@RestController
@RequestMapping("/mql")
public class MyLikedArticleController {

    /**
     * Qiitaのいいね一覧情報を取得する
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public List<ArticleJson> fetchLikedArticleInfo(@RequestParam("uid") String uid) {
        System.out.println("get uid: " + uid);
        String baseUrl = "https://qiita.com/" + uid + "/like";
        QiitaCrawler firstCrawler = new QiitaCrawler(baseUrl);
        int pageNum = firstCrawler.getAllPageNum();
        List<ArticleJson> resultList = new ArrayList<>();
        for (int i = 1; i <= pageNum; i++) {
            if (i == 1) {
                resultList.addAll(firstCrawler.fetchArticleList());
            } else {
                resultList.addAll(new QiitaCrawler(baseUrl + "?page=" + String.valueOf(i)).fetchArticleList());
            }
        }
        return resultList;

    }

}
