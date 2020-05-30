package com.czy.test;

import java.sql.*;

public class TestSql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        /* 加载数据库驱动 */
        Class.forName("com.mysql.jdbc.Driver");

        /* 获取数据库连接 */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "123456");

        /* 获取执行sql的处理器 */
        PreparedStatement preparedStatement = connection.prepareStatement("select * from account;");

        /* 执行查询sql得到返回结果 */
        ResultSet resultSet = preparedStatement.executeQuery();

        /* 遍历得到的结果集 */
        while (resultSet.next()) {
            Integer id = resultSet.getInt("id");
            String name = resultSet.getString("username");
            double balance = resultSet.getDouble("balance");
            System.out.println(id + name + balance);
        }
    }
}
