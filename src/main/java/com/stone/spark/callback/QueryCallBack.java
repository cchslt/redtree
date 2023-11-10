package com.stone.spark.callback;

import java.sql.ResultSet;

/**
 * @author chen
 * @create 2019-03-24 00:19
 *
 * 查询回调接口
 **/


public interface QueryCallBack {
    void process(ResultSet resultSet) throws Exception;
}
