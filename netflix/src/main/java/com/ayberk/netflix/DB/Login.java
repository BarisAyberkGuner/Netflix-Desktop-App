package com.ayberk.netflix.DB;

import java.sql.*;

public class Login {
    static String logonId = "";

    public static boolean login(String mail, String password) {
        boolean result = false;
        Connection conn = null;
        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        try {
            conn = DriverManager.getConnection(dbPath);
            String users = "SELECT * FROM Kullanici";
            try {
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(users);

                while (rs.next()) {
                    String dbMail = rs.getString("kullaniciEmail");
                    String dbPass = rs.getString("kullaniciSifre");
                    if (mail.equals(dbMail) && password.equals(dbPass)) {
                        result = true;
                        break;
                    }

                }conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return result;
    }

    public static void setID(String s) {
        logonId = s;
    }

    public static String getID() {
        return logonId;
    }

}
