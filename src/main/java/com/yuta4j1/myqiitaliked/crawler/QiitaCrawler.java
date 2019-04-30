package com.yuta4j1.myqiitaliked.crawler;

import com.yuta4j1.myqiitaliked.model.ArticleJson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
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
                    .setUpdateDate(extractDateString(elm.select(".ItemLink__info").html())).build();
        }).collect(Collectors.toList());
    }

    private String extractDateString(final String src) {
        String markerStr = "posted on";
        int i = src.indexOf(markerStr);
        int startIdx = i + markerStr.length() + 1;
        String extracted = src.substring(startIdx);
        return convertLocaleDateString(extracted);
    }

    private String convertLocaleDateString(final String src) {
        String[] splitted = src.split("(\\s)|(,\\s)");
        Map<String, String> monthMap = Arrays.asList("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec").stream().collect(Collectors.toMap(s -> s, s -> {
            switch (s) {
                case "Jan":
                    return "01";
                case "Feb":
                    return "02";
                case "Mar":
                    return "03";
                case "Apr":
                    return "04";
                case "May":
                    return "05";
                case "Jun":
                    return "06";
                case "Jul":
                    return "07";
                case "Aug":
                    return "08";
                case "Sep":
                    return "09";
                case "Oct":
                    return "10";
                case "Nov":
                    return "11";
                case "Dec":
                    return "12";
                default:
                    return "none";
            }
        }));
        return String.join("/", splitted[2], monthMap.get(splitted[0]), splitted[1]);

    }

}
