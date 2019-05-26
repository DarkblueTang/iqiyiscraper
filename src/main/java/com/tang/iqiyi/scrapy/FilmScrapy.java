package com.tang.iqiyi.scrapy;

public class FilmScrapy extends AbstractScrapy {
    @Override
    protected String getUrlByPageNumPageSize(int pageNum, int pageSize) {

        String url = "https://search.video.iqiyi.com/o?if=html5&" +
                "pageNum=" + pageNum + "&" +
                "pageSize=" + pageSize + "&" +
                "pu=&" +
                "video_allow_3rd=1&" +
                "intent_result_number=10&" +
                "intent_category_type=1&" +
                "mode=11&" +
                "type=list&" +
                "ctgname=电影&" +
                "graph_type=1_1_0_-1&" +
                "real_query=电影";
        return url;
    }
}
