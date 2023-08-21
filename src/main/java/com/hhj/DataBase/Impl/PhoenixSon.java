package com.hhj.DataBase.Impl;

import com.hhj.DataBase.Father;

public class PhoenixSon extends Father {

    public PhoenixSon(String ip, String DbName) {
        super(ip,DbName);
        this.port = 2181;
        this.jdbcDriver +="phoenix:";
        this.DriverName = "org.apache.phoenix.jdbc.PhoenixDriver";
    }
}
