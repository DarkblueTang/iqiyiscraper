package com.tang.iqiyi.scrapy;

public class ComicScrapy extends AbstractScrapy {
    @Override
    protected String getUrlByPageNumPageSize(int pageNum, int pageSize) {

        String url = "http://search.video.iqiyi.com/o?" +
                "pageNum=" + pageNum + "&" +
                "mode=11&" +
                "channel_id=4&" +
                "content_type=&" +
                "pageSize=" + pageSize + "&" +
                "pos=1&" +
                "type=list&" +
                "access_play_control_platform=15&" +
                "site=iqiyi&" +
                "if=html5";
        return url;
    }
}
