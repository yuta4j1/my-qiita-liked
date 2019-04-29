package com.yuta4j1.myqiitaliked.crawler;

import com.yuta4j1.myqiitaliked.model.ArticleJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class QiitaCrawler {

    private Document doc;

    public QiitaCrawler(String url) {
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ArticleJson> fetchArticleList() {

        Elements elms = doc.select("article.itemLink");
        if (elms.size() == 0) return null;
        return elms.stream().map(elm -> {
            return new ArticleJson.Builder().setUuid(elm.attr("data-uuid")).setUrl(elm.attr("data-item-url"))
                    .setUserImageUrl(elm.select("img").attr("src"))
                    .setTitle(elm.select(".media__body .ItemLink__title .u-link-no-underline").html())
                    .setTagList(elm.select(".TagList .TagList__item .TagList__label").stream().map(e -> e.html()).collect(Collectors.toList()))
                    .setUpdateDate(elm.select(".ItemLink__info").html()).build();
        }).collect(Collectors.toList());
    }

}
