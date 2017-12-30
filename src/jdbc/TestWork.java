package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class TestWork {
	/**
	 * 1.使用游标输出 scott中所有的雇员名称，部门名称，年薪
	 * @throws Exception
	 */
	@Test
	public void query() throws Exception{
		String sql =  "select e.ename,d.dname,(e.sal)*12 from emp e inner join dept d on e.deptno=d.deptno";
		Connection conn = DbUtils.getConnection();
		//Statement用于执行静态的sql语句
		Statement sta = conn.createStatement();
		//ResultSet 游标遍历数据行 next()
		ResultSet rs = sta.executeQuery(sql);
		while (rs.next()) {
			String ename = rs.getString("ename");
			String dname = rs.getString("dname");
			String sal = rs.getString("(e.sal)*12");
			System.out.println("雇员名称:"+ename+"----部门名称"+dname+"----年薪"+sal);
		}
		rs.close();
		sta.close();
		conn.close();
	}
	/**
	 * 2.写出一个分页的存储过程
     *   定义如下
     *       tablePager(tableName,curPage,pageSize)
     *   调用 
     *       tablePager('Emp',2,10) 
     *   查询emp表中 第二页的数据（每页显示10条 
	 * @throws Exception 
	 */
	@Test
	public void main(){
		try {
			tablePager("emp",1,5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void tablePager(String tableName,int curPage,int pageSize) throws Exception{
		
		String sql = "select * from "+tableName+" limit "+((curPage*pageSize)-pageSize)+","+pageSize;
		Connection conn = DbUtils.getConnection();
		//Statement用于执行静态的sql语句
		Statement sta = conn.createStatement();
		//ResultSet 游标遍历数据行 next()
		ResultSet rs = sta.executeQuery(sql);
		while(rs.next()){
			//获取列名总行数
			int count=rs.getMetaData().getColumnCount();
			for(int i=1;i<count;i++){
				String str=rs.getString(i);
				System.out.print(str+" ");
			}
			System.out.println();
		}
		rs.close();
		sta.close();
		conn.close();
	}
	/**
	 * 	4 定义一个存储过程 传入表名
     *   删除该表中的重复记录
     *   比如 deleteMul(tableName)
     *   调用 deleteMul('emp');
     *   必须删除表emp的重复数据  （execute immediate    using ）
	 * @throws Exception 
	 */
	@Test
	public void main1(){
		try {
			deleteMul("student");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void deleteMul(String tableName) throws Exception{
		String sql = "DELETE FROM "+ tableName +" WHERE `s#` NOT IN(SELECT m FROM (SELECT (MIN(`s#`)) m FROM "+ tableName +" GROUP BY sname,sage,ssex) b)";
		//连接到数据库
		Connection conn = DbUtils.getConnection();
		//提前预编译
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		//执行
		prepareStatement.execute();
		prepareStatement.close();
		conn.close();
	}
}
