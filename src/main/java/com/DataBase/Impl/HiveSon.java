package com.DataBase.Impl;


import com.DataBase.Father;

public class HiveSon extends Father {

    public String urlEnd = "?characterEncoding=UTF-8&isColumnEncrypt=true";
    public HiveSon(String ip, String DbName) {
        super(ip,DbName);
        this.port = 10000;
        this.jdbcDriver +="hive2://";
        this.DriverName = "org.apache.hive.jdbc.HiveDriver";
    }
    public String getUrl() {
        return this.jdbcDriver+this.ip+ ":" + this.port+ "/" +this.DbName+urlEnd;
    }
}
