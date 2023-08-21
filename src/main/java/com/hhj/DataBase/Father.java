package com.hhj.DataBase;



import com.hhj.DataBase.Utils.ConnUtils;

import java.sql.Connection;
import java.sql.SQLException;

public class Father {
    public String jdbcDriver = "jdbc:";
    public String urlTarget = "zhibei";
    public String ip;
    public int port;
    public String DbName;
    public String user = "root";
    public String pwd = "Zbxxkj@2072";
    public String DriverName;
    public Connection conn;

    public String getDriverName() {
        return DriverName;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public Father(String ip, String dbName) {
        this.ip = ip;
        DbName = dbName;
    }

    public Connection getConnection(String url) throws SQLException {
        if(this.conn!=null){
            this.conn.close();
        }
        System.out.println("用户名:"+this.user+"  密码："+this.pwd+" 如需更改请再获取连接前set");
        this.conn = ConnUtils.getConnection(url,this.user,this.pwd,this);
        return this.conn;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public String getZbUrl() {
        String url = getUrl();
        int index = url.indexOf(":");
        url = url.substring(0,index+1)+this.urlTarget+url.substring(index);
        System.out.println("智贝链接 : "+url);
        return url;
    }
    public String getUrl() {
        String url =  this.jdbcDriver+this.ip+ ":" + this.port+ "/" +this.DbName;
        System.out.println("官方链接 : "+url);
        return url;
    }
}
