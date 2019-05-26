package com.tang.iqiyi.jdbc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class C3p0 {
    private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("mysql");
    public static Connection getConnection() {
        try {
            return comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void init(){
//        comboPooledDataSource.close();
//        comboPooledDataSource = new ComboPooledDataSource("mysql");
    }
}
