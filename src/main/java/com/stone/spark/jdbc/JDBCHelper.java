package com.stone.spark.jdbc;

import com.stone.spark.callback.QueryCallBack;
import com.stone.spark.conf.ConfigurationManager;
import com.stone.spark.constant.JDBCConstant;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author chen
 * @create 2019-03-23 23:05
 *
 * JDBC辅助类
 * 简单实现java数据库连接池
 **/

public class  JDBCHelper {

    //1、加载数据库驱动
    static {
        try {
            Class.forName(ConfigurationManager.getProperties(JDBCConstant.JDBC_DRIVER));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private LinkedList<Connection> datasource = new LinkedList<Connection>();

    //2、实现单例化
    private static JDBCHelper instance = null;
    public static JDBCHelper getInstance() {
        if (instance == null) {
            synchronized (JDBCHelper.class) {
                if (instance == null) {
                    instance = new JDBCHelper();
                }
            }
        }
        return instance;
    }

    //3、创建连接池
    private JDBCHelper() {
        int datasourceSize = ConfigurationManager.getInteger(JDBCConstant.JDBC_DATASOURCE_SIZE);
        String url = ConfigurationManager.getProperties(JDBCConstant.JDBC_URL);
        String userName = ConfigurationManager.getProperties(JDBCConstant.JDBC_USERNAME);
        String password = ConfigurationManager.getProperties(JDBCConstant.JDBC_PASSWORD);
        for (int i = 0; i < datasourceSize; i++) {
            Connection connection = null;
            try {
                connection = DriverManager.getConnection(url, userName, password);
                datasource.add(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    //4、获取连接池中的连接
    public synchronized Connection getConnection() {
        while (datasource.size() <=0) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return datasource.poll();
    }


    //5、执行CRUD的方法

    //5.1、增删改语句
    public int executeUpdate(String sql, Object[] params) {
        int rtn = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = createConn(conn, sql, params);

            rtn = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                datasource.push(conn);
            }
        }

        return rtn;
    }

    //5.2、查询方法
    public void executeQuery(String sql, Object[] params, QueryCallBack callBack) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = createConn(conn, sql, params);

            rs = pstmt.executeQuery();
            callBack.process(rs);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                datasource.push(conn);
            }
        }
    }

    //5.3、批量插入
    public int[] executeBatch(String sql, List<Object[]> paramsList ) {
        int[] rtn = null;
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false);
            pstmt = conn.prepareStatement(sql);

            for (Object[] params : paramsList) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                pstmt.addBatch();
            }

            rtn = pstmt.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                datasource.push(conn);
            }
        }

        return rtn;
    }


    private PreparedStatement createConn(Connection conn, String sql, Object[] params) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.length; i++) {
                pstmt.setObject(i + 1, params[i]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pstmt;
    }
}
