package sample;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class mysqlconnect {
    Connection conn=null;
    public static Connection ConnectDb(){
        try{
            //Connection conn =  DriverManager.getConnection("jdbc:sqlite:D:/UWU/2.2/RAD/Practical/HomeService/homeService.db");
            //Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/homeService_javaFX","root","");
            //JOptionPane.showMessageDialog(null,"Databased Connected ");
            return conn;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;

        }

    }
}
