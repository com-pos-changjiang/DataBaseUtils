package com.hhj.DataBase.Impl;

import com.hhj.DataBase.Father;

/**
 * @author hhj
 * @date 2023/7/22
 */
public class OracleSon extends Father {
    public String urlEnd = "orcl";
    public OracleSon(String ip,String DbName) {
        super(ip,DbName);
        this.port = 1521;
        this.jdbcDriver +="oracle:thin:@";
        this.DriverName = "oracle.jdbc.driver.OracleDriver";
    }

    public void setUrlEnd(String urlEnd) {
        this.urlEnd = urlEnd;
    }
    public String getUrl() {
        return this.jdbcDriver+this.ip+ ":" + this.port+ ":"+urlEnd;
    }

}
