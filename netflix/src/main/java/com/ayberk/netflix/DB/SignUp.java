package com.ayberk.netflix.DB;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SignUp {
    public static DefaultTableModel dtm2;

    public static void kayitOl(String userMail, String userPassword, String userName, int bornYear) {

        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            String signUp = "INSERT INTO Kullanici(kullaniciAdi,kullaniciEmail,kullaniciSifre,dTarihi) values(?,?,?,?)";
            try {
                PreparedStatement pstmt = conn.prepareStatement(signUp);
                pstmt.setString(1, userName);
                pstmt.setString(2, userMail);
                pstmt.setString(3, userPassword);
                pstmt.setInt(4, bornYear);
                pstmt.executeUpdate();
                conn.close();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static int [] average(int tur1ID, int tur2ID, int tur3ID) {
        int x_1 = 0;
        int x_2 =0;
        int y_1 = 0;
        int y_2=0;
        int z_1 = 0;
        int z_2=0;
        int temp_;
        double temp;
        double x1 = 0.0;
        double x2 = 0.0;
        double y1 = 0.0;
        double y2 = 0.0;
        double z1 = 0.0;
        double z2 = 0.0;

        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            String averagex = "SELECT AVG(verilenPuan) FROM kullaniciProgram,Program,ProgramTur,Tur WHERE Tur.turID=ProgramTur.tur and Program.id=ProgramTur.programID and kullaniciProgram.pID=Program.id  and pID=(?) and Tur.turID=(?)";
            for (int i = 1; i < 76; i++) {
                PreparedStatement ps = conn.prepareStatement(averagex);
                ps.setInt(1, i);
                ps.setInt(2, tur1ID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getDouble(1));
                    if (rs.getDouble(1) > x2) {
                        x2 = rs.getDouble(1);
                        x_2 = i;
                    }
                    if (rs.getDouble(1) > x1) {
                        temp = x1;
                        temp_ = x_1;
                        x1 = x2;
                        x_1 = i;
                        x2 = temp;
                        x_2 = temp_;
                    }
                }

            }
            conn.close();


            conn = DriverManager.getConnection(dbPath);
            String averagey = "SELECT AVG(verilenPuan) FROM kullaniciProgram,Program,ProgramTur,Tur WHERE Tur.turID=ProgramTur.tur and Program.id=ProgramTur.programID and kullaniciProgram.pID=Program.id  and pID=(?) and Tur.turID=(?)";
            for (int i = 1; i < 76; i++) {
                System.out.println("a");
                PreparedStatement ps = conn.prepareStatement(averagey);
                ps.setInt(1, i);
                ps.setInt(2, tur2ID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getDouble(1));
                    if (rs.getDouble(1) > y2) {
                        y2 = rs.getDouble(1);
                        y_2 = i;
                    }
                    if (rs.getDouble(1) > y1) {
                        temp = y1;
                        temp_ = y_1;
                        y1 = y2;
                        y_1 = i;
                        y2 = temp;
                        y_2 = temp_;
                    }
                }

            }
            conn.close();
            conn = DriverManager.getConnection(dbPath);
            String averagez = "SELECT AVG(verilenPuan) FROM kullaniciProgram,Program,ProgramTur,Tur WHERE Tur.turID=ProgramTur.tur and Program.id=ProgramTur.programID and kullaniciProgram.pID=Program.id  and pID=(?) and Tur.turID=(?)";
            for (int i = 1; i < 76; i++) {
                PreparedStatement ps = conn.prepareStatement(averagez);
                ps.setInt(1, i);
                ps.setInt(2, tur3ID);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getDouble(1));
                    if (rs.getDouble(1) > z2) {
                        z2 = rs.getDouble(1);
                    }
                    if (rs.getDouble(1) > z1) {
                        temp = z1;
                        temp_ = z_1;
                        z1 = z2;
                        z_1 = i;
                        z2 = temp;
                        z_2 = temp_;
                    }
                }

            }
            conn.close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
        int [] bestPrograms={x_1,x_2,y_1,y_2,z_1,z_2};
        return  bestPrograms; }
}