package com.DataBase.Impl;

import com.DataBase.Father;

public class KB8Son extends Father {

    public KB8Son(String ip, String DbName) {
        super(ip,DbName);
        this.port = 54321;
        this.jdbcDriver +="kingbase8://";
        this.DriverName = "com.kingbase8.Driver";
    }
    public KB8Son(String ip, String DbName,String user,String pwd) {
        this(ip,DbName);
        this.user = user;
        this.pwd = pwd;
    }
}
