package com.DataBase.Impl;

import com.DataBase.Father;

public class PGSon extends Father {

    public PGSon(String ip, String DbName) {
        super(ip,DbName);
        this.port = 5432;
        this.jdbcDriver +="postgresql://";
        this.DriverName = "org.postgresql.Driver";
    }
}
