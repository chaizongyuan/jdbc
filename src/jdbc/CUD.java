package jdbc;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Test;

/**
 * 增删改演示
 * @author Administrator
 *
 */

public class CUD {
	
	@Test
	/**
	 * 增加
	 * @throws Exception
	 */
	public void insert() throws Exception{
		String sql="INSERT INTO userinfo VALUES(6,'赵六','男',18,'赵六')";
		Connection conn=DbUtils.getConnection();
		Statement sta=conn.createStatement();
		sta.execute(sql);
		sta.close();
		conn.close();
	}
	@Test
	/**
	 * 修改
	 * @throws Exception
	 */
	public void update() throws Exception{
		String sql="update userinfo set name ='李五' where id=6";
		Connection conn=DbUtils.getConnection();
		Statement sta=conn.createStatement();
		sta.execute(sql);
		sta.close();
		conn.close();
	}
	@Test
	
	/**
	 * 删除
	 * @throws Exception
	 */
	public void delete() throws Exception{
		String sql="delete from userinfo where id =6";
		Connection conn=DbUtils.getConnection();
		Statement sta=conn.createStatement();
		sta.execute(sql);
		sta.close();
		conn.close();
	}
}
