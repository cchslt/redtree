package com.stone.spark;


import com.stone.spark.callback.QueryCallBack;
import com.stone.spark.conf.ConfigurationManager;
import com.stone.spark.constant.JDBCConstant;
import com.stone.spark.dto.User;
import com.stone.spark.jdbc.JDBCHelper;
import com.stone.spark.utils.CopyUtils;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chen
 * @create 2019-03-23 23:34
 **/

@RunWith(SpringJUnit4ClassRunner.class)
public class Test {


    @org.junit.Test
  public void tt() {
      System.out.println("hello");
      System.out.println(ConfigurationManager.getProperties(JDBCConstant.JDBC_DATASOURCE_SIZE));
      System.out.println(ConfigurationManager.getInteger(JDBCConstant.JDBC_DATASOURCE_SIZE));
  }


  @org.junit.Test
  public void test() {
      JDBCHelper jdbcHelper = JDBCHelper.getInstance();
//      String sql = "insert into t_user(username, age) values(?, ?)";
//      Object[] params = new Object[]{"赵六", 22};
//      System.out.println(jdbcHelper.executeUpdate(sql, params));

      jdbcHelper.executeQuery("select * from t_user ",
              new Object[]{}, new QueryCallBack() {
                  @Override
                  public void process(ResultSet resultSet) throws Exception {
                        while (resultSet.next()) {
                            System.out.println(resultSet.getInt(1));
                            System.out.println(resultSet.getString(2));
                            System.out.println(resultSet.getInt(3));
                        }
                  }
              });


      //测试批量插入
//      String sql = "insert into t_user(username, age) values(?, ?)";
//      List<Object[]> paramsList = new ArrayList<Object[]>();
//      paramsList.add(new Object[]{"王五", 30});
//      paramsList.add(new Object[]{"麻子", 90});
//
//      jdbcHelper.executeBatch(sql, paramsList);

  }


  @org.junit.Test
  public void test1() {
      User user = new User();
//      user.setAge(111);

      String[] fields = CopyUtils.nullPropertyNames(user);
      for (String field : fields) {
          System.out.println(field);
      }

  }

}
