package com.tang.iqiyi.scrapy;

public class SeriesScrapy extends AbstractScrapy {
    @Override
    protected String getUrlByPageNumPageSize(int pageNum, int pageSize) {
        String url = "http://search.video.iqiyi.com/o?pageNum=" + pageNum + "&" +
                "mode=11&" +
                "channel_id=2&" +
                "three_category=&" +
                "content_type=&" +
                "is_purchase=&" +
                "pageSize=" + pageSize + "&" +
                "pos=1&" +
                "type=list&" +
                "market_release_date_level=-&" +
                "access_play_control_platform=15&" +
                "site=iqiyi&" +
                "if=html5&" +
                "pu=&";
        return url;
    }
}
