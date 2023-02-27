package com.alilang.stu.transactionaldemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class SelfTransactionManager {

    @Resource
    private DataSource dataSource;

    // 获取连接
    public Connection getConnection(){
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            ConnUtil.setConn(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    // 开始事务
    public void begin(){
        try {
            ConnUtil.getConn().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 提交事务
    public void commit(){
        try {
            ConnUtil.getConn().commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 回滚事务
    public void rollBack(){
        try {
            ConnUtil.getConn().rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 关闭连接
    public void closeConnection(){
        Connection conn = ConnUtil.getConn();
        try {
            if (conn!=null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
