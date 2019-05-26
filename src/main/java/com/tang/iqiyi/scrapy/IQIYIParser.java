package com.tang.iqiyi.scrapy;

import com.alibaba.fastjson.JSONObject;
import com.tang.iqiyi.entity.FilmVideoEntity;

import java.util.List;

public interface IQIYIParser {
    public abstract List<FilmVideoEntity> parse(JSONObject json);
    public abstract void setVideoType(String videoType);
}
