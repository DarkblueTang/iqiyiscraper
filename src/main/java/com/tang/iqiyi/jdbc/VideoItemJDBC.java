package com.tang.iqiyi.jdbc;

import com.tang.iqiyi.entity.VideoItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class VideoItemJDBC {
    public static void saveAll(List<VideoItem> videoItems, int filmid, Connection connection) {
        for (int i = 0; i < videoItems.size(); i++) {
            VideoItem videoItem = videoItems.get(i);
            save(videoItem, filmid, connection);
        }
    }

    private static void save(VideoItem videoItem, int filmid, Connection connection) {
        String sql = "INSERT INTO tb_videoitem(filmId,serialNumber,url,title) VALUES(?,?,?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, filmid);
            preparedStatement.setInt(2, videoItem.getSerialNumber());
            preparedStatement.setString(3, videoItem.getUrl());
            preparedStatement.setString(4, videoItem.getTitle());
                preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByFilmID(int filmId, Connection connection) {
        String sql = "DELETE FROM tb_videoitem WHERE tb_videoitem.filmId=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, filmId);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteByFilmID(List<Integer> list, Connection connection) {
        for (int i = 0; i < list.size(); i++) {
            Integer filmId = list.get(i);
            deleteByFilmID(filmId, connection);
        }
    }
}
