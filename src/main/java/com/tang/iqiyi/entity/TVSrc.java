package com.tang.iqiyi.entity;

public class TVSrc {
    private String name;
    private String type;
    private String link;

    public TVSrc() {
    }

    public TVSrc(String name, String type, String link) {
        this.name = name;
        this.type = type;
        this.link = link;
    }

    @Override
    public String toString() {
        return "TVSrc{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
