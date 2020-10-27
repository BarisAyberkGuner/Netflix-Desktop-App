package com.ayberk.netflix.UI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;

import static com.ayberk.netflix.DB.Login.*;

public class KullaniciGiris {


    public KullaniciGiris() {
        final JFrame f = new JFrame("Netflix");
        JButton giris = new JButton("Giriş");
        JLabel a = new JLabel("Kullanici Adi");
        JLabel b = new JLabel("Sifre");
        final JTextArea mail = new JTextArea();
        final JTextArea sifre = new JTextArea();
        giris.setBounds(25, 125, 100, 40);
        mail.setBounds(25, 50, 150, 20);
        sifre.setBounds(25, 100, 150, 20);
        a.setBounds(25, 25, 150, 20);
        b.setBounds(25, 75, 150, 20);
        f.add(a);
        f.add(b);
        f.add(mail);
        f.add(sifre);
        f.add(giris);
        f.setSize(225, 300);//400 width and 500 height
        f.setLayout(null);//using no layout managers
        f.setVisible(true);//making the frame visible
        giris.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (login(mail.getText(), sifre.getText())) {
                    setID(mail.getText());
                    new KullaniciPanel();
                    f.setVisible(false);
                    //System.out.println(getID());
                } else {
                    new KullaniciKayit();
                    f.setVisible(false);
                }
            }
        });
    }

}
