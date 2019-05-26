package com.tang.iqiyi.jdbc;

import com.tang.iqiyi.entity.FilmVideoEntity;

import java.sql.Connection;
import java.util.List;

public class FilmVideoEntityJDBC {

    public static void saveAll(List<FilmVideoEntity> list, Connection connection) {
        for (int i = 0; i < list.size(); i++) {
            FilmVideoEntity entity = list.get(i);
//            System.out.println(entity.getFilm().getTitle() + "--->" + entity.getVideoItems().size());
            save(entity, connection);
        }
    }

    public static void save(FilmVideoEntity entity, Connection connection) {
        List<Integer> list = FilmJDBC.getID(entity.getFilm(), connection);

        int filmid = FilmJDBC.DeleteAndSave(entity.getFilm(), connection);

        VideoItemJDBC.deleteByFilmID(list, connection);

        VideoItemJDBC.saveAll(entity.getVideoItems(), filmid, connection);

    }

}
