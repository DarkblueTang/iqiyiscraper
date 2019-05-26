package com.tang.iqiyi.entity;

import java.util.LinkedList;
import java.util.List;

public class Film {
    private int id;
    private String img;
    private String title;
    private String actors;
    private String type;
    private String type1;
    private String language;
    private int time;
    private String source;
    private String anotherName;
    private String region;
    private String briefIntroduction;
    private String url;
    private String director;
    private String videoItemUrl;
    private boolean parser;
    private double score;

    private List videoList = new LinkedList<List<VideoItem>>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAnotherName() {
        return anotherName;
    }

    public void setAnotherName(String anotherName) {
        this.anotherName = anotherName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getBriefIntroduction() {
        return briefIntroduction;
    }

    public void setBriefIntroduction(String briefIntroduction) {
        this.briefIntroduction = briefIntroduction;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public boolean isParser() {
        return parser;
    }

    public void setParser(boolean parser) {
        this.parser = parser;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", actors='" + actors + '\'' +
                ", type='" + type + '\'' +
                ", type1='" + type1 + '\'' +
                ", language='" + language + '\'' +
                ", time=" + time +
                ", source='" + source + '\'' +
                ", anotherName='" + anotherName + '\'' +
                ", region='" + region + '\'' +
                ", briefIntroduction='" + briefIntroduction + '\'' +
                ", url='" + url + '\'' +
                ", director='" + director + '\'' +
                ", videoItemUrl='" + videoItemUrl + '\'' +
                ", parser=" + parser +
                ", score=" + score +
                ", videoList=" + videoList +
                '}';
    }

    public List getVideoList() {
        return videoList;
    }

    public void setVideoList(List videoList) {
        this.videoList = videoList;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getVideoItemUrl() {
        return videoItemUrl;
    }

    public void setVideoItemUrl(String videoItemUrl) {
        this.videoItemUrl = videoItemUrl;
    }
}
