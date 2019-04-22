package com.cpl.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TestPool {

    public static void main(String[] args) {
        MyDataSource dataSource = new MyDataSource();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = dataSource.getConnection();
            String sql = "insert into  account values(5,'lizhu','1000','12346','f')";
            ps = conn.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            dataSource.addBack(conn);
        }
    }
}



