package com.ayberk.netflix.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import static com.ayberk.netflix.DB.Timer.*;
import static com.ayberk.netflix.DB.Search.*;
import static com.ayberk.netflix.DB.WatchProgram.*;
import static com.ayberk.netflix.DB.Login.*;

public class KullaniciPanel {
    JTable table;
    JLabel c;

    Date simdikiZaman = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    void reset() {
        dtm.setRowCount(0);
    }

    public KullaniciPanel() {


        final JFrame f = new JFrame();
        String[] category = {"Aksiyon", "Belgesel", "Bilim Kurgu ve Fantastik Yapımlar", "Bilim ve Doğa", "Çocuk ve Aile", "Dramalar", "Gerilim", "Komedi", "Korku", "Romantik"};
        final String[] columns = {"ProgramID", "Program Adı", "Program Kategorisi", "Program Tipi"};

        f.setSize(350, 500);
        int x = 5;
        String[] puan = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9","10"};
        JLabel a = new JLabel("Film İsmine Göre Ara");
        JLabel b = new JLabel("Film Türüne Göre Ara");
        final JTextArea isim = new JTextArea();
        final JComboBox tur1 = new JComboBox(category);
        JButton isimAra = new JButton("İsime Göre Ara");
        JButton turAra = new JButton("Türe Göre Ara");
        JButton izle = new JButton("İzle");
        final JButton bitir = new JButton("Bitir");
        final JButton rate = new JButton("Puan Ver");
        final JComboBox puanlama = new JComboBox(puan);
        final JButton clear = new JButton("Aramayı Temizle");
        final JLabel d = new JLabel("İzlenecek Film ID:");
        final JTextArea watchinID = new JTextArea();
        c = new JLabel();

        a.setBounds(10, 10, 125, 20);
        b.setBounds(145, 10, 125, 20);
        isim.setBounds(10, 35, 125, 20);
        tur1.setBounds(145, 35, 125, 20);
        isimAra.setBounds(10, 60, 125, 20);
        turAra.setBounds(145, 60, 125, 20);
        izle.setBounds(10, 125, 75, 30);
        bitir.setBounds(90, 125, 75, 30);
        c.setBounds(80, 165, 100, 20);
        puanlama.setBounds(80, 190, 100, 30);
        rate.setBounds(80, 230, 100, 30);
        clear.setBounds(650, 350, 200, 30);
        d.setBounds(10, 90, 100, 30);
        watchinID.setBounds(120, 95, 40, 20);
        f.add(a);
        f.add(b);
        f.add(isim);
        f.add(tur1);
        f.add(isimAra);
        f.add(turAra);
        f.add(izle);
        f.add(d);
        f.add(watchinID);

        clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              /*  public static void deleteAllRows(){
                    dtm.getDataVector().removeAllElements();
                    dtm.fireTableDataChanged();

                }
                */
                reset();

                f.setVisible(true);
            }
        });

        isimAra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                table = new JTable(new String[1][1], columns);
                table.setBounds(400, 20, 700, 300);

                table.setFocusable(true);
                table.setRowSelectionAllowed(false);
                dtm = new DefaultTableModel(0, 0);
                dtm.setColumnIdentifiers(columns);
                table.setModel(dtm);

                JScrollPane jScrollPane = new JScrollPane(table);
                jScrollPane.setBounds(400, 20, 700, 300);
                f.setSize(1200, 500);
                showProgramforName(isim.getText());
                f.add(jScrollPane);
                f.add(clear);
                f.setVisible(true);

            }
        });

        turAra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                table = new JTable(new String[1][1], columns);
                table.setBounds(400, 20, 700, 300);
                table.setFocusable(false);
                table.setRowSelectionAllowed(true);
                dtm = new DefaultTableModel(0, 0);
                dtm.setColumnIdentifiers(columns);
                table.setModel(dtm);
                JScrollPane jScrollPane = new JScrollPane(table);
                jScrollPane.setBounds(400, 20, 700, 300);
                f.setSize(1200, 500);
                showProgramforType(tur1.getSelectedIndex());
                f.add(jScrollPane);
                f.add(clear);
                f.setVisible(true);


            }
        });

        izle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();

                f.add(bitir);
                f.setVisible(false);
                f.setVisible(true);
            }
        });

        bitir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stop();
                c.setText("İzlenilen süre " + getElapsedSeconds() + "sn");
                f.setVisible(false);
                f.add(puanlama);
                f.add(c);
                f.add(rate);
                rate.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                       // System.out.println(Integer.parseInt(watchinID.getText())+" "+(puanlama.getSelectedIndex()+1)+" "+df.format(simdikiZaman).toString()+" "+getElapsedSeconds()+" "+getID());
                        watchProgram(Integer.parseInt(watchinID.getText()),(puanlama.getSelectedIndex()),df.format(simdikiZaman).toString(),getElapsedSeconds(),getID());
                    }
                });
                f.setVisible(true);
            }
        });

        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible

    }

}
