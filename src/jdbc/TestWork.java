package jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.Test;

public class TestWork {
	/**
	 * 1.ʹ���α���� scott�����еĹ�Ա���ƣ��������ƣ���н
	 * @throws Exception
	 */
	@Test
	public void query() throws Exception{
		String sql =  "select e.ename,d.dname,(e.sal)*12 from emp e inner join dept d on e.deptno=d.deptno";
		Connection conn = DbUtils.getConnection();
		//Statement����ִ�о�̬��sql���
		Statement sta = conn.createStatement();
		//ResultSet �α���������� next()
		ResultSet rs = sta.executeQuery(sql);
		while (rs.next()) {
			String ename = rs.getString("ename");
			String dname = rs.getString("dname");
			String sal = rs.getString("(e.sal)*12");
			System.out.println("��Ա����:"+ename+"----��������"+dname+"----��н"+sal);
		}
		rs.close();
		sta.close();
		conn.close();
	}
	/**
	 * 2.д��һ����ҳ�Ĵ洢����
     *   ��������
     *       tablePager(tableName,curPage,pageSize)
     *   ���� 
     *       tablePager('Emp',2,10) 
     *   ��ѯemp���� �ڶ�ҳ�����ݣ�ÿҳ��ʾ10�� 
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
		//Statement����ִ�о�̬��sql���
		Statement sta = conn.createStatement();
		//ResultSet �α���������� next()
		ResultSet rs = sta.executeQuery(sql);
		while(rs.next()){
			//��ȡ����������
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
	 * 	4 ����һ���洢���� �������
     *   ɾ���ñ��е��ظ���¼
     *   ���� deleteMul(tableName)
     *   ���� deleteMul('emp');
     *   ����ɾ����emp���ظ�����  ��execute immediate    using ��
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
		//���ӵ����ݿ�
		Connection conn = DbUtils.getConnection();
		//��ǰԤ����
		PreparedStatement prepareStatement = conn.prepareStatement(sql);
		//ִ��
		prepareStatement.execute();
		prepareStatement.close();
		conn.close();
	}
}
