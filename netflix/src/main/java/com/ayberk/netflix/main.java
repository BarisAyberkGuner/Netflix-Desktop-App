package com.ayberk.netflix;
import static com.ayberk.netflix.DB.DatabaseConnect.sqlConnect;
import javax.swing.*;

import com.ayberk.netflix.UI.KullaniciGiris;

import static com.ayberk.netflix.DB.SignUp.average;
import static com.ayberk.netflix.DB.WatchProgram.*;
public class main {
    public static void main (String args[]){


        new KullaniciGiris();


    }

}
