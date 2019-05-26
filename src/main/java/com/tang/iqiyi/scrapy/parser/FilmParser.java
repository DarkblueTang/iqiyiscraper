package com.tang.iqiyi.scrapy.parser;

import com.alibaba.fastjson.JSONObject;
import com.tang.iqiyi.entity.VideoItem;

import java.util.ArrayList;
import java.util.List;

public class FilmParser extends CommonParser {
    @Override
    protected List<VideoItem> parseVideoItems(JSONObject albumDocInfo) {

        ArrayList<VideoItem> videoItems = new ArrayList<>(1);
        VideoItem videoItem = new VideoItem();
        String url = albumDocInfo.getString("albumLink");
        videoItem.setUrl(url);
        videoItem.setTitle("1");
        videoItem.setSerialNumber(0);
        videoItems.add(videoItem);

        return videoItems;
    }
}
