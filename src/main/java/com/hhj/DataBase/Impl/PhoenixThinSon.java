package com.hhj.DataBase.Impl;

import com.hhj.DataBase.Father;

public class PhoenixThinSon extends Father {

    public String urlEnd = ";serialization=PROTOBUF";
    public PhoenixThinSon(String ip, String DbName) {
        super(ip,DbName);
        this.port = 8765;
        this.jdbcDriver +="phoenix:thin:url=http://";
        this.DriverName = "org.apache.phoenix.queryserver.client.Driver";
    }
    public String getUrl() {
        return this.jdbcDriver+this.ip+ ":" + this.port+ "/" +this.DbName+urlEnd;
    }
}
