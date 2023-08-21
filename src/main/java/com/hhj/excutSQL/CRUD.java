package com.hhj.excutSQL;

import java.sql.*;

/**
 *  数据库增删改查常用的方法
 */
public class CRUD {
    public static void select(Connection connection, String sql) throws SQLException {
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        try {
            System.out.println(sql);
            ps = connection.prepareStatement(sql);
            resultSet = ps.executeQuery();
            selectResut(resultSet);
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            ps.close();
        }
    }
    public static void selectResut(ResultSet resultSet) throws SQLException {
        ResultSetMetaData metaData = resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        int i;
        for(i = 1; i <= columnCount; ++i) {
            System.out.print(metaData.getColumnName(i) + "\t|");
        }
        System.out.println();
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        while(resultSet.next()) {
            for(i = 1; i <= columnCount; ++i) {
                System.out.print(resultSet.getString(i) + "\t|");
            }
            System.out.println();
        }
    }

    /**
     *  sql : insert delete updata
     * @param connection
     * @param sql
     * @throws SQLException
     */
    public static void excute(Connection connection,String sql) throws SQLException {
        System.out.println(sql);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int a = preparedStatement.executeUpdate();
        System.out.println(a);
        connection.commit();
        connection.close();
    }

    public  void BatchInsert(Connection connection) throws SQLException {

        String sql = "INSERT INTO public.test_z  VALUES(?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setObject(1,4);
        preparedStatement.setObject(2,"caoac");
        preparedStatement.addBatch();
        preparedStatement.executeBatch();
        connection.close();
        preparedStatement.close();
        System.out.println("插入成功");
    }

}
