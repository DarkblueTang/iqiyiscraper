package com.tang.iqiyi.scrapy.parser;

import com.alibaba.fastjson.JSONObject;

import java.util.Objects;

public class ArtParser extends ComicParser {
    protected String genTitle(JSONObject video) {
        String shortTitle = video.getString("shortTitle");
        if (Objects.isNull(shortTitle)) {
            return super.genTitle(video);
        }
        return shortTitle;
    }

}
