package com.hhj.DataBase.Impl;

import com.hhj.DataBase.Father;

/**
 * @author hhj
 * @date 2023/7/24
 */
public class DMSon extends Father {
    public DMSon(String ip,String DbName) {
        super(ip,DbName);
        this.port = 5236;
        this.jdbcDriver +="dm://";
        this.DriverName = "dm.jdbc.driver.DmDriver";
    }

}
