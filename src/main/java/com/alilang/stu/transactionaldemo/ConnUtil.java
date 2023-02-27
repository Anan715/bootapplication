package com.alilang.stu.transactionaldemo;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.sql.Connection;

@Component
public class ConnUtil {

    private static ThreadLocal<Connection> threadLocalConn  = new ThreadLocal<>();
    @Resource
    private SelfTransactionManager tm;

    private static SelfTransactionManager manager;

    public static void clear() {
        threadLocalConn.remove();
    }

    @PostConstruct
    public void setTransactionManager(){
        manager = tm;
    }

    public static Connection getConn() {
        Connection connection = threadLocalConn.get();
        if (connection!=null){
            return  connection;
        }else {
            return manager.getConnection();
        }
    }
    public static void setConn(Connection conn) {
        threadLocalConn.set(conn);
    }
}
