package com.tang.iqiyi.scrapy.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tang.iqiyi.entity.Film;
import com.tang.iqiyi.entity.FilmVideoEntity;
import com.tang.iqiyi.entity.VideoItem;
import com.tang.iqiyi.jdbc.C3p0;
import com.tang.iqiyi.jdbc.FilmVideoEntityJDBC;
import com.tang.iqiyi.scrapy.IQIYIParser;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class CommonParser implements IQIYIParser {

    protected String videoType = null;

    @Override
    public List<FilmVideoEntity> parse(JSONObject json) {
        ArrayList<FilmVideoEntity> result = new ArrayList<FilmVideoEntity>();
        JSONArray docinfos = json.getJSONObject("data").getJSONArray("docinfos");
        Connection connection = C3p0.getConnection();

        docinfos.forEach(item -> {
            FilmVideoEntity entity = parseFilmOnce((JSONObject) item);
            if (entity != null){
//                result.add(entity);
                FilmVideoEntityJDBC.save(entity, connection);
            }
        });
        return result;
    }

    public FilmVideoEntity parseFilmOnce(JSONObject item) {
        try {
            if (checkIsParseAble(item)) {
                FilmVideoEntity entity = new FilmVideoEntity();
                Film film = new Film();
                List<VideoItem> videoItems = entity.getVideoItems();
                Double score = item.getDouble("score");
                JSONObject albumDocInfo = item.getJSONObject("albumDocInfo");
                film.setTitle(albumDocInfo.getString("albumTitle"));
                film.setType(videoType);
                film.setImg(albumDocInfo.getString("albumVImage"));
                film.setDirector(albumDocInfo.getString("director"));
                film.setRegion(albumDocInfo.getString("region"));
                film.setUrl(albumDocInfo.getString("albumLink"));
                film.setScore(score);
                String type1 = "";
                type1 += albumDocInfo.getString("threeCategory");

                JSONObject video_lib_meta = albumDocInfo.getJSONObject("video_lib_meta");
                JSONArray category = video_lib_meta.getJSONArray("category");
                if (!Objects.isNull(category))
                    for (int i = 0; i < category.size(); i++) {
                        type1 += category.get(i);
                    }
                film.setType1(type1);

                String language = "";
                JSONArray languageArray = video_lib_meta.getJSONArray("language");
                if (languageArray != null)
                    for (int i = 0; i < languageArray.size(); i++) {
                        language += languageArray.get(0);
                    }
                film.setLanguage(language);

                if (Objects.isNull(film.getDirector())) {
                    String director = "";
                    JSONArray directors = video_lib_meta.getJSONArray("director");
                    if (!Objects.isNull(directors))
                        for (int i = 0; i < directors.size(); i++) {
                            JSONObject d = (JSONObject) directors.get(i);
                            director += d.getString("name");
                        }
                    film.setDirector(director);
                }
                film.setSource("爱奇艺");

                String actors = "";
                JSONArray actor = video_lib_meta.getJSONArray("actor");
                if (!Objects.isNull(actor))
                    for (int i = 0; i < actor.size(); i++) {
                        JSONObject j = (JSONObject) actor.get(i);
                        actors += j.getString("name");
                    }
                film.setActors(actors);

                entity.setFilm(film);

                List<VideoItem> temp = parseVideoItems(albumDocInfo);

                videoItems.addAll(temp);
                return entity;
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract List<VideoItem> parseVideoItems(JSONObject albumDocInfo);

    private static boolean checkIsParseAble(JSONObject item) {
        JSONObject albumDocInfo = item.getJSONObject("albumDocInfo");
        if (Objects.isNull(albumDocInfo))
            return false;
        String albumLink = albumDocInfo.getString("albumLink");
        if (Objects.isNull(albumLink)) {
            return false;
        } else if (!albumLink.contains("www.iqiyi.com"))
            return false;
        return true;
    }

    @Override
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }
}
