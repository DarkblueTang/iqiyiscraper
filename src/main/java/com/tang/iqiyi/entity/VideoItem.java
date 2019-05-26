package com.tang.iqiyi.entity;

public class VideoItem {
    private int id;
    private String title;
    private String url;
    private int filmId;
    private String playbackLine;
    private int serialNumber;

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public String getPlaybackLine() {
        return playbackLine;
    }

    public void setPlaybackLine(String playbackLine) {
        this.playbackLine = playbackLine;
    }

    @Override
    public String toString() {
        return "VideoItem{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", filmId=" + filmId +
                ", playbackLine='" + playbackLine + '\'' +
                ", serialNumber=" + serialNumber +
                '}';
    }
}
