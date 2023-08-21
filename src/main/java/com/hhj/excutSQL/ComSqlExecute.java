package com.hhj.excutSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ComSqlExecute {
    public static ResultSet executeQuery(Connection conn, String sql){
        System.out.println(sql);
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            pstat = conn.prepareStatement(sql);
            rs = pstat.executeQuery();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static int getTableSize(Connection conn,String tableName){
        String sql = "select count(1) from "+tableName;
        System.out.println(sql);
        int num = -1;
        try {
            PreparedStatement pst =  conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            if(rs.next())
                num = rs.getInt(1);
            rs.close();
            pst.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }





}
