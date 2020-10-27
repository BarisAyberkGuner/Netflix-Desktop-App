package com.ayberk.netflix.DB;

import java.sql.*;


public class WatchProgram {
    static int i = 0;


    public static void add(int progID, int rate, String watchDate, int currentWatchTime, String userID) {

        int episode = 1;
        int watchTime;
        String dbPath = ("jdbc:sqlite:DBFile\\NetflixDB.db");
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            String query2 = "INSERT INTO kullaniciProgram(izlemeTarihi,izlemeSuresi,kaldigiBolum,verilenPuan,kID,pID) values(?,?,?,?,?,?)";

            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setString(1, watchDate);
            ps2.setInt(2, currentWatchTime);
            ps2.setInt(3, episode);
            ps2.setInt(4, rate);
            ps2.setString(5, userID);
            ps2.setInt(6, progID);
            ps2.executeUpdate();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }


    }

    public static void watchProgram(int progID, int rate, String watchDate, int currentWatchTime, String userID) {
        String dbPath = ("jdbc:sqlite:DBFile\\NetflixDB.db");
        Connection conn = null;

        int watchTime;
        String d = "Dizi";

        int currentEpisode;

        try {
            conn = DriverManager.getConnection(dbPath);

            String query = "SELECT * FROM Program,KullaniciProgram WHERE Program.id=KullaniciProgram.pID and Program.id=(?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, progID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                currentEpisode = rs.getInt("kaldigiBolum");

                if (progID == rs.getInt("id") && userID.equals(rs.getString("kID")) && d.equals(rs.getString("programTipi"))) {
                    i = 2;

                    watchTime = rs.getInt("izlemeSuresi");
                    watchTime = watchTime + currentWatchTime;
                    if (watchTime > 59) {
                        watchTime = watchTime % 60;
                        currentEpisode++;
                        if (currentEpisode > 5) {
                            currentEpisode = currentEpisode % 5;
                        }
                    }
                    String query3 = "UPDATE KullaniciProgram SET izlemeSuresi=(?), kaldigiBolum=(?) WHERE kID=(?) AND pID=(?)";
                    PreparedStatement ps3 = conn.prepareStatement(query3);
                    ps3.setInt(1, watchTime);
                    ps3.setInt(2, currentEpisode);
                    ps3.setString(3, userID);
                    ps3.setInt(4, progID);
                    ps3.executeUpdate();

                }
                if (i == 0) {
                    if (progID == rs.getInt("id") && userID.equals(rs.getString("kID"))) {

                        i = 1;
                        watchTime = rs.getInt("izlemeSuresi");
                        watchTime = watchTime + currentWatchTime;
                        if (watchTime > 120) {
                            watchTime = watchTime % 120;
                        }

                        String query1 = "UPDATE KullaniciProgram SET izlemeSuresi=(?) WHERE kID=(?) AND pID=(?)";
                        PreparedStatement ps1 = conn.prepareStatement(query1);
                        ps1.setInt(1, watchTime);
                        ps1.setString(2, userID);
                        ps1.setInt(3, progID);
                        ps1.executeUpdate();

                    }
                }


            }
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (i == 0) {

            add(progID, rate, watchDate, currentWatchTime, userID);
        }
    }
}
