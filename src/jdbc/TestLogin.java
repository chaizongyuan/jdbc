package jdbc;


/**
 * DriverManager 驱动管理类 通过获取不同数据库的驱动类 实现不同的登陆方式
 * Connection 用于和数据库进行连接类
 * Statement  用于执行静态sql语句
 * ResultSet  游标对象 抓取行
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class TestLogin {
	/**
	 * 用于格式化转换日期
	 */
	public static void main(String[] args) {
		/**
		 * jdbc链接的数据服务器的ip 端口 数据库名
		 *mysql url是jdbc:mysal://ip地址:3306/数据库名
		 *oracle url是 jidc:oracle:thin:@ip地址:1521:sid(orcl)
		 */
		String url ="jdbc:mysql://localhost:3306/test";
		//告诉jdbc使用的是什么数据库 不同数据提供一些不同类型
		String driverClass="com.mysql.jdbc.Driver";
		String userName="root";
		String password="123456";
		//需要jvm加载该类
		try {
			Class.forName(driverClass);
			//登录成功
			Connection conn= DriverManager.getConnection(url,userName,password);
			System.out.println(conn);
			//Statement用于执行静态的sql语句
			Statement state=conn.createStatement();
			//ResultSet游标遍历数据行  next()
			ResultSet rs=state.executeQuery("select * from userinfo");
			while(rs.next()){
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				String age=rs.getString("age");
				String desc=rs.getString("desc");
				System.out.println(name+"--"+sex+"--"+age+"--"+desc);
			}
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
}
