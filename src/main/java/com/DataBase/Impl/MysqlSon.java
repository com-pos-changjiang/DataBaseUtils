package com.DataBase.Impl;


import com.DataBase.Father;

public class MysqlSon extends Father {

    public String urlEnd = "?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=UTC";
    public MysqlSon(String ip,String DbName) {
        super(ip,DbName);
        this.port = 3306;
        this.jdbcDriver +="mysql://";
        this.DriverName = "com.mysql.jdbc.Driver";
    }
    public String getUrl() {
        return this.jdbcDriver+this.ip+ ":" + this.port+ "/" +this.DbName+urlEnd;
    }
}
