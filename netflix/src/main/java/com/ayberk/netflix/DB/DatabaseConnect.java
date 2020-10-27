package com.ayberk.netflix.DB;

import java.sql.*;

public class DatabaseConnect {

    public static void sqlConnect() {
        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("SQLite connected");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void sqlClose(Connection yourConnection){
        try {
            if(yourConnection.isClosed()){

                    yourConnection.close();}
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
