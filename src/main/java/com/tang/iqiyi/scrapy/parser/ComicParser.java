package com.tang.iqiyi.scrapy.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.iqiyi.entity.VideoItem;
import com.tang.iqiyi.tools.HTTPTools;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class ComicParser extends CommonParser {
    @Override
    protected List<VideoItem> parseVideoItems(JSONObject albumDocInfo) {

        LinkedList<VideoItem> videoItems = new LinkedList<>();
        String albumId = albumDocInfo.getString("albumId");

        getAndParseVideoItems(albumId, videoItems);
        if (videoItems.size() == 0) {
            String qipu_id = albumDocInfo.getString("qipu_id");
            getAndParseVideoItems(qipu_id, videoItems);
        }
        if (videoItems.size() == 0) {
            VideoItem videoItem = new VideoItem();
            videoItem.setTitle("1");
            videoItem.setUrl(albumDocInfo.getString("albumLink"));
            videoItems.add(videoItem);
        }
        return videoItems;
    }

    private void getAndParseVideoItems(String albumId, List<VideoItem> videoItemList) {
        String url = "http://pub.m.iqiyi.com/jp/h5/videoList?" +
                "albumId=" + albumId + "&" +
                "size=5000&" +
                "page=1&" +
                "needPrevue=true&" +
                "needVipPrevue=false";
        String s = HTTPTools.getString(url);
        JSONObject json = (JSONObject) JSONObject.parse(s.substring(s.indexOf("{")));
        parseVideoItems(videoItemList, json);
    }

    private void parseVideoItems(List<VideoItem> videoItemList, JSONObject json) {
        JSONArray videos = json.getJSONArray("videos");
        if (Objects.isNull(videos))
            return;

        for (int i = 0; i < videos.size(); i++) {
            JSONObject video = (JSONObject) (videos.get(i));
            VideoItem videoItem = new VideoItem();
            videoItem.setTitle(genTitle(video));
            videoItem.setSerialNumber(i);
            String url = video.getString("pageUrl").replace("//m", "http://www");
            videoItem.setUrl(url);
            videoItemList.add(videoItem);
        }
    }

    protected String genTitle(JSONObject video) {
        return video.getString("pd");
    }

}
