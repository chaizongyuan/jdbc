package jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class Query {
	public static void main(String[] args)throws Exception{
		/**
		 * sql注入是利用字符串的漏洞 来进行 数据攻击
		 * 正常逻辑 job=' or 1=1 or 1=' 不存在数据 查询的集合null
		 * ''可以利用字符串'来结尾or 1=1这样的sql破解查询所有的数据
		 */
		//query("S",null,"1500");
		querySecure("MANAGER","S");
	}
	
	/**
	 * 
	 * @param ename  传入雇员名称
	 * @param job  传入职位
	 * @param sal  传入薪水
	 * @throws Exception
	 */
	public static void query(String ename,String job,String sal) throws Exception{
		String sql="select * from emp where 1=1";
		if(ename!=null && !"".equals(ename)){
			sql+=" and ename like '%"+ename+"%'";
		}
		if(job!=null && !"".equals(job)){
			sql+=" and job='"+job+"'";
		}
		if(sal!=null && !"".equals(sal)){
			sql+=" and sal>'"+sal+"'";
		}
		System.out.println(sql);
		Connection conn=DbUtils.getConnection();
		Statement sta=conn.createStatement();
		ResultSet rs=sta.executeQuery(sql);
		while(rs.next()){
			String enameVar=rs.getString("ename");
			String salVar=rs.getString("sal");
			String comm=rs.getString("comm");
			System.out.println("雇员:"+enameVar+"--奖金:"+comm+"--薪水:"+salVar);
		}
		rs.close();
		sta.close();
		conn.close();
	}
	/**
	 * 使用Statement的子类PrepareStatement来替代  可以使用  ? 替换原始 '' 防止sql注入
	 * @param ename
	 * @param job
	 * @param sal
	 * @throws Exception
	 */
	public static void querySecure(String job,String ename) throws Exception{
		String sql="select * from emp where job=? and ename like ?";
		Connection conn=DbUtils.getConnection();
		//预先编译sql语句
		PreparedStatement sta=conn.prepareStatement(sql);
		sta.setString(1, job);
		sta.setString(2, "%"+ename+"%");
		//没执行一次都要编译sql
		ResultSet rs=sta.executeQuery();
		while(rs.next()){
			String enameVar=rs.getString("ename");
			String salVar=rs.getString("sal");
			String comm=rs.getString("comm");
			System.out.println("雇员:"+enameVar+"--奖金:"+comm+"--薪水:"+salVar);
		}
		rs.close();
		sta.close();
		conn.close();
	}
}
