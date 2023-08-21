package com.hhj.DataBase.Utils;


import com.hhj.DataBase.Father;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnUtils {

    public static Connection getConnection(String url){

        String  driverName = "com.zhibei.engine.spy.ZDriver";
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static Connection getConnection(String url, Father father){
        String driverName = father.getDriverName();
        if(url.contains("zhibei")){
            driverName = "com.zhibei.engine.spy.ZDriver";
        }
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static Connection getConnection(String url,String user,String pwd,Father father){
        String driverName = father.getDriverName();
        if(url.contains("zhibei")){
            driverName = "com.zhibei.engine.spy.ZDriver";
        }
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
        Connection con = null;
        try {
            con = DriverManager.getConnection(url,user,pwd);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
