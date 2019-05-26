package com.tang.iqiyi.entity;

import java.util.ArrayList;
import java.util.List;

public class FilmVideoEntity {
    private Film film;
    private List<VideoItem> videoItems = new ArrayList<>();

    public FilmVideoEntity() {
    }

    public FilmVideoEntity(Film film, List<VideoItem> videoItems) {
        this.film = film;
        this.videoItems = videoItems;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public List<VideoItem> getVideoItems() {
        return videoItems;
    }

    public void setVideoItems(List<VideoItem> videoItems) {
        this.videoItems = videoItems;
    }

    @Override
    public String toString() {
        return "FilmVideoEntity{" +
                "film=" + film +
                ", videoItems=" + videoItems +
                '}';
    }
}
