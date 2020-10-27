package com.ayberk.netflix.UI;

import com.ayberk.netflix.Entity.Program;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

import static com.ayberk.netflix.DB.SignUp.kayitOl;
import static com.ayberk.netflix.DB.SignUp.*;

public class KullaniciKayit {

    JTable tablo;
    DefaultTableModel dtm;
    String[] tur = {"Aksiyon", "Belgesel", "Bilim Kurgu ve Fantastik Yapımlar", "Bilim ve Doğa", "Çocuk ve Aile", "Dramalar", "Gerilim", "Komedi", "Korku", "Romantik"};

    public ArrayList<Program> programList(int x, int y, int z, int q, int w, int r) {
        ArrayList<Program> programList = new ArrayList();
        Connection conn = null;
        String dbPath = "jdbc:sqlite:DBFile\\NetflixDB.db";
        int i = 0;

        try {
            conn = DriverManager.getConnection(dbPath);
            String query = "SELECT * FROM Program,ProgramTur,Tur WHERE  ProgramTur.programID=Program.id and ProgramTur.tur=Tur.turID and Program.id=(?) ";//Ayar yapılacak
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, x);
            ResultSet rs1 = ps.executeQuery();
            Program program;
            while (rs1.next()) {
                if (i == 2)
                    break;
                program = new Program(rs1.getString("programAdi"), rs1.getString("turAdi"), rs1.getString("programTipi"));
                programList.add(program);
                i++;
            }

            String query2 = "SELECT * FROM Program,ProgramTur,Tur WHERE ProgramTur.programID=Program.id and ProgramTur.tur=Tur.turID and Program.id=(?) ";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setInt(1, y);
            ResultSet rs2 = ps2.executeQuery();
            i = 0;
            while (rs2.next()) {
                if (i == 2)
                    break;
                program = new Program(rs2.getString("programAdi"), rs2.getString("turAdi"), rs2.getString("programTipi"));
                programList.add(program);
                i++;
            }

            String query3 = "SELECT * FROM Program,ProgramTur,Tur WHERE  ProgramTur.programID=Program.id and ProgramTur.tur=Tur.turID and Program.id=(?) ";
            PreparedStatement ps3 = conn.prepareStatement(query3);
            ps3.setInt(1, z);
            ResultSet rs3 = ps3.executeQuery();
            i = 0;
            while (rs3.next()) {
                if (i == 2)
                    break;
                program = new Program(rs3.getString("programAdi"), rs3.getString("turAdi"), rs3.getString("programTipi"));
                programList.add(program);
                i++;
            }
            String query4 = "SELECT * FROM Program,ProgramTur,Tur WHERE ProgramTur.programID=Program.id and ProgramTur.tur=Tur.turID and Program.id=(?) ";
            PreparedStatement ps4 = conn.prepareStatement(query4);
            ps4.setInt(1, q);
            ResultSet rs4 = ps4.executeQuery();
            i = 0;
            while (rs4.next()) {
                if (i == 2)
                    break;
                program = new Program(rs4.getString("programAdi"), rs4.getString("turAdi"), rs4.getString("programTipi"));
                programList.add(program);
                i++;
            }
            String query5 = "SELECT * FROM Program,ProgramTur,Tur WHERE ProgramTur.programID=Program.id and ProgramTur.tur=Tur.turID and  Program.id=(?) ";
            PreparedStatement ps5 = conn.prepareStatement(query5);
            ps5.setInt(1, w);
            ResultSet rs5 = ps5.executeQuery();

            while (rs5.next()) {

                program = new Program(rs5.getString("programAdi"), rs5.getString("turAdi"), rs5.getString("programTipi"));
                programList.add(program);

            }
            String query6 = "SELECT * FROM Program,ProgramTur,Tur WHERE  ProgramTur.programID=Program.id and ProgramTur.tur=Tur.turID and Program.id=(?) ";
            PreparedStatement ps6 = conn.prepareStatement(query6);
            ps6.setInt(1, r);
            ResultSet rs6 = ps6.executeQuery();
            while (rs6.next()) {
                program = new Program(rs6.getString("programAdi"), rs6.getString("turAdi"), rs6.getString("programTipi"));
                programList.add(program);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programList;
    }

    public void showProgram(int x, int y, int z, int q, int w, int r) {
        ArrayList<Program> list = programList(x, y, z, q, w, r);

        Object[] row = new Object[3];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getProgramName();
            row[1] = list.get(i).getProgramCategory();
            row[2] = list.get(i).getProgramType();
            dtm.addRow(row);
        }

    }

    KullaniciKayit() {
        final JFrame f = new JFrame("Netflix Kayıt");
        final JFrame oneri = new JFrame("Sizin İçin Önerdiklerimiz");
        f.setSize(300, 500);
        oneri.setSize(600, 600);


        final String[] columns = {"Program Adı", "Program Kategorisi", "Program Tipi"};


        JLabel a = new JLabel("E-mail");
        final JTextArea mail = new JTextArea();
        JLabel b = new JLabel("Şifre");
        final JTextArea sifre = new JTextArea();
        JLabel g = new JLabel("Adı");
        final JTextArea adi = new JTextArea();
        JLabel h = new JLabel("Doğum Tarihi");
        final JTextArea dogumTarihi = new JTextArea("YYYY");
        JTextField da = new JTextField();

        JLabel c = new JLabel("Tür-1");
        final JComboBox tur1 = new JComboBox(tur);
        JLabel d = new JLabel("Tür-2");
        final JComboBox tur2 = new JComboBox(tur);
        JLabel e = new JLabel("Tür-3");
        final JComboBox tur3 = new JComboBox(tur);
        final JButton kayit = new JButton("Kayıt Ol");
        final JButton backHomePage = new JButton("Ana Sayfaya Dönmek İçin Tıklayın");
        backHomePage.setBounds(135, 330, 250, 30);

        a.setBounds(10, 10, 75, 20);
        mail.setBounds(10, 35, 185, 20);
        b.setBounds(10, 60, 75, 20);
        sifre.setBounds(10, 85, 185, 20);
        g.setBounds(10, 110, 75, 20);
        adi.setBounds(10, 135, 185, 20);
        h.setBounds(10, 160, 75, 20);
        dogumTarihi.setBounds(10, 185, 185, 20);
        c.setBounds(10, 210, 75, 20);
        tur1.setBounds(10, 235, 185, 20);
        d.setBounds(10, 260, 75, 20);
        tur2.setBounds(10, 285, 185, 20);
        e.setBounds(10, 310, 75, 20);
        tur3.setBounds(10, 335, 185, 20);
        kayit.setBounds(55, 365, 95, 30);

        f.add(a);
        f.add(b);
        f.add(c);
        f.add(d);
        f.add(e);
        f.add(g);
        f.add(h);
        f.add(mail);
        f.add(sifre);
        f.add(adi);
        f.add(dogumTarihi);
        f.add(tur1);
        f.add(tur2);
        f.add(tur3);
        f.add(kayit);

        kayit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                kayitOl(mail.getText(), sifre.getText(), adi.getText(), Integer.parseInt(dogumTarihi.getText()));

                f.setVisible(false);

                int tur_int1 = tur1.getSelectedIndex();
                int tur_int2 = tur2.getSelectedIndex();
                int tur_int3 = tur3.getSelectedIndex();

                tablo = new JTable(new String[1][1], columns);
                tablo.setBounds(20, 20, 500, 300);
                dtm = new DefaultTableModel(0, 0);
                dtm.setColumnIdentifiers(columns);
                tablo.setModel(dtm);
                JScrollPane jScrollPane = new JScrollPane(tablo);
                jScrollPane.setBounds(20, 20, 500, 300);
                int[] besties = average((tur_int1 + 1), (tur_int2 + 1), (tur_int3 + 1));
                showProgram(besties[0], besties[1], besties[2], besties[3], besties[4], besties[5]);
                oneri.add(jScrollPane);
                oneri.add(backHomePage);
                backHomePage.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        new KullaniciGiris();
                        oneri.dispose();
                    }
                });
                oneri.setLayout(null);
                oneri.setVisible(true);


            }
        });
        f.setLayout(null);
        f.setVisible(true);
    }

}
