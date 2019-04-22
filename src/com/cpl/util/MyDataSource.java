package com.cpl.util;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MyDataSource implements DataSource {
    List<Connection> list=new ArrayList<Connection>();
    public MyDataSource() {
        for (int i = 0; i < 10; i++) {
            Connection conn = JDBCUtil.getConn();
            list.add(conn);
        }
    }

    //连接池对外公布获取连接的地方
    @Override
    public Connection getConnection() throws SQLException {
        if(list.size()==0){
            for(int i=0;i<5;i++){
                Connection conn =JDBCUtil.getConn();
                list.add(conn);
            }
        }
        Connection conn=list.remove(0);
        return conn;
    }
    //---------------------------------------------------
    public void addBack(Connection conn){
        list.add(conn);
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }
}
