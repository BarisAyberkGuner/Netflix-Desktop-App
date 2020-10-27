package com.ayberk.netflix.DB;

import com.ayberk.netflix.Entity.ListProgram;
import com.ayberk.netflix.Entity.ListProgramType;
import com.ayberk.netflix.Entity.Program;
import com.ayberk.netflix.UI.KullaniciPanel;

import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;

public class Search {
    public static DefaultTableModel dtm;


    public static ArrayList<ListProgram> listProgramName(String name) {
        if (name == null) {
            name = " ";
        }

        name = name.toLowerCase();
        ArrayList<ListProgram> listProgramName = new ArrayList();

        Connection conn;
        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        try {
            conn = DriverManager.getConnection(dbPath);
            String search = "SELECT * FROM Program WHERE LOWER(programAdi) LIKE(?) ";
            PreparedStatement ps = conn.prepareStatement(search);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            ListProgram pr = null;
            while (rs.next()) {
                pr = new ListProgram(rs.getInt("id"), rs.getString("programAdi"), rs.getString("programTipi"), rs.getString("programTuru"));
                listProgramName.add(pr);
                //System.out.println(rs.getInt("id")+" "+rs.getString("programAdi")+" "+rs.getString("programTipi")+" "+rs.getString("programTuru"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProgramName;
    }

    public static void showProgramforName(String name) {

        ArrayList<ListProgram> list = listProgramName(name);

        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getType();
            row[3] = list.get(i).getCategory();
            dtm.addRow(row);

        }
    }

    public static ArrayList<ListProgramType> listProgramType(int type) {


        ArrayList<ListProgramType> listProgramType = new ArrayList();
        Connection conn = null;

        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        try {
            conn = DriverManager.getConnection(dbPath);
            String search = "SELECT * FROM Program,ProgramTur,Tur WHERE Tur.turID=Programtur.tur and Program.id=ProgramTur.programID and Tur.turID=(?) ";
            PreparedStatement ps = conn.prepareStatement(search);
            ps.setInt(1, (type + 1));
            ResultSet rs = ps.executeQuery();
            ListProgramType pr;
            while (rs.next()) {
                pr = new ListProgramType(rs.getInt("id"), rs.getString("programAdi"), rs.getString("programTipi"), rs.getString("programTuru"));
                listProgramType.add(pr);
                //System.out.println(rs.getInt("id")+" "+rs.getString("programAdi")+" "+rs.getString("programTipi")+" "+rs.getString("programTuru"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listProgramType;
    }

    public static void showProgramforType(int type) {

        ArrayList<ListProgramType> list = listProgramType(type);

        Object[] row = new Object[4];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getName();
            row[2] = list.get(i).getType();
            row[3] = list.get(i).getCategory();
            dtm.addRow(row);

        }
    }

   /* public static void searchForName(String name) {
        name=name.toLowerCase();
        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            String search ="SELECT * FROM Program WHERE LOWER(programAdi) LIKE(?)";
            PreparedStatement ps = conn.prepareStatement(search);
            ps.setString(1,"%"+name+"%");
            ResultSet rs= ps.executeQuery();

            while(rs.next()){
                System.out.println(rs.getString("programAdi"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/

    /*public static void searchForType(int categoryID) {
String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbPath);
            String searchTypeQuery= "SELECT* FROM Program,Tur,ProgramTur WHERE Tur.turID=Programtur.tur and Program.id=ProgramTur.programID and Tur.turID=(?)";
            PreparedStatement ps = conn.prepareStatement(searchTypeQuery);
            ps.setInt(1,categoryID);
            ResultSet rs =ps.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString("programAdi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }*/


}
