package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class TestOracleLogin {
	/**
	 * ���ڸ�ʽ��ת������
	 */
	static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MMM-DD hh:mm:ss");
	public static void main(String[] args) {
		/**
		 * jdbc���ӵ����ݷ�������ip �˿� ���ݿ���
		 *mysql url��jdbc:mysal://ip��ַ:3306/���ݿ���
		 *oracle url�� jidc:oracle:thin:@ip��ַ:1521:sid(orcl)
		 */
		String url ="jdbc:oracle:thin://localhost:1521:orcl";
		//����jdbcʹ�õ���ʲô���ݿ� ��ͬ�����ṩһЩ��ͬ����
		String driverClass="oracle.jdbc.OracleDriver";
		String userName="scott";
		String password="tiger";
		//��Ҫjvm���ظ���
		try {
			Class.forName(driverClass);
			//��¼�ɹ�
			Connection conn= DriverManager.getConnection(url,userName,password);
			System.out.println(conn);
			//Statement����ִ�о�̬��sql���
			Statement state = conn.createStatement();
			//ResultSet �α���������� next();
			ResultSet rs = state.executeQuery("select * from emp");
			while(rs.next()){
				String ename=rs.getString("ename");//ͨ��������ȡ����
				double sal = rs.getDouble("sal");
				//mysql datetime��Ӧjava.sql.Timestamp
				Timestamp borndate = rs.getTimestamp("hiredate");//��ȡ��������ȡ����
				System.out.println(ename+"--"+sal+"--"+sdf.format(borndate));
			}
			
		} catch (Exception e) {	
			e.printStackTrace();
		}
		
	}
}