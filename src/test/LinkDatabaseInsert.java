package test;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.DbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author Jiangyf
 * @version 1.0.0
 * @ClassName LinkDatabaseInsert.java
 * @Description TODO
 * @createTime 2021年04月30日 14:08:00
 */
public class LinkDatabaseInsert {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//1.注册数据库的驱动
		Class.forName("com.mysql.cj.jdbc.Driver");
		//2.获取数据库连接（里面内容依次是："jdbc:mysql://主机名:端口号/数据库名","用户名","登录密码"）
		Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student", "root", "");
		//3.需要执行的sql语句（?是占位符，代表一个参数）
		String sql = "insert into student (stu_id,id_num,name) values(?,?,?)";
		//4.获取预处理对象，并依次给参数赋值
		PreparedStatement statement = connection.prepareCall(sql);
		statement.setString(1, RandomUtil.randomString(18)); //数据库字段类型是int，就是setInt；1代表第一个参数
		statement.setString(2, RandomUtil.randomString(18));    //数据库字段类型是String，就是setString；2代表第二个参数
		statement.setString(3, "张三"); //数据库字段类型是int，就是setInt；3代表第三个参数
		//5.执行sql语句（执行了几条记录，就返回几）
		int i = statement.executeUpdate();
		System.out.println(i);
		//6.关闭jdbc连接
		statement.close();
		connection.close();


	}

}
