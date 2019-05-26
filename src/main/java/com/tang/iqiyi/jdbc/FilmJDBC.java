package com.tang.iqiyi.jdbc;

import com.tang.iqiyi.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FilmJDBC {
    public static int DeleteAndSave(Film film, Connection connection) {
        deleteByTitle(film.getTitle(), connection);
        return save(film, connection);
    }

    private static int save(Film film, Connection connection) {
        String sql = "INSERT INTO tb_film(" +
                "img,title,actors," +
                "type,language,time," +
                "source,anotherName,region," +
                "briefIntroduction,director,videoItemUrl," +
                "score,type1" +
                ")VALUES(" +
                "?, ?,?," +
                "?, ?, ?," +
                "?, ?,?," +
                "?, ?,?," +
                "?, ?" +
                ");";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, film.getImg());
            preparedStatement.setString(2, film.getTitle());
            preparedStatement.setString(3, film.getActors());

            preparedStatement.setString(4, film.getType());
            preparedStatement.setString(5, film.getLanguage());
            preparedStatement.setInt(6, film.getTime());

            preparedStatement.setString(7, film.getSource());
            preparedStatement.setString(8, film.getAnotherName());
            preparedStatement.setString(9, film.getRegion());

            preparedStatement.setString(10, film.getBriefIntroduction());
            preparedStatement.setString(11, film.getDirector());
            preparedStatement.setString(12, film.getVideoItemUrl());

            preparedStatement.setDouble(13, film.getScore());
            preparedStatement.setString(14, film.getType1());

            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                return id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private static int deleteByTitle(String title, Connection connection) {
        if (Objects.isNull(connection))
            return 0;
        String sql = "DELETE FROM tb_film WHERE tb_film.title = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, title);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static List<Integer> getID(Film film, Connection connection) {
        if (Objects.isNull(connection))
            return null;
        String sql = "SELECT tb_film.id " +
                "from tb_film " +
                "where tb_film.title=? AND " +
                "tb_film.img=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, film.getTitle());
            preparedStatement.setString(2, film.getImg());
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Integer> res = new ArrayList<>(resultSet.getFetchSize());
            while (resultSet.next()) {
                res.add(resultSet.getInt(1));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
