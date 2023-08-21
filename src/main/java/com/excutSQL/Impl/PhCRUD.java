package com.excutSQL.Impl;

import com.zhibei.Utils.CRUD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author hhj
 * @date 2023/7/17
 */
public class PhCRUD extends CRUD {

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
