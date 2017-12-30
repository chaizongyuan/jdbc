package jdbc;

import java.sql.CallableStatement;
import java.sql.Types;

import org.junit.Test;

import java.sql.Connection;

public class CallProcedure {
	/**
	 * 调用存储过程
	 * @throws Exception
	 */
	@Test
	public void callProdecre() throws Exception{
		String sql="{call prg_add(?,?,?)}";
		Connection conn=DbUtils.getConnection();
		CallableStatement sta=conn.prepareCall(sql);
		sta.setInt(1, 455715);
		sta.setInt(2, 684321546);
		sta.registerOutParameter(3, Types.INTEGER);
		sta.execute();
		int result=sta.getInt(3);
		System.out.println(result);
	}
	
	/**
	 * 调用函数
	 */
	@Test
	public void callFunction() throws Exception{
		String sql="{call prg_add(?,?)}";
		Connection conn=DbUtils.getConnection();
		CallableStatement sta=conn.prepareCall(sql);
		sta.setInt(2, 455715);
		sta.setInt(3, 684321546);
		sta.registerOutParameter(1, Types.INTEGER);
		sta.execute();
		int result=sta.getInt(1);
		System.out.println(result);
	}
}
