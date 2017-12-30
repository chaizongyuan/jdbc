package jdbc;


/**
 * DriverManager ���������� ͨ����ȡ��ͬ���ݿ�������� ʵ�ֲ�ͬ�ĵ�½��ʽ
 * Connection ���ں����ݿ����������
 * Statement  ����ִ�о�̬sql���
 * ResultSet  �α���� ץȡ��
 * @author Administrator
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class TestLogin {
	/**
	 * ���ڸ�ʽ��ת������
	 */
	public static void main(String[] args) {
		/**
		 * jdbc���ӵ����ݷ�������ip �˿� ���ݿ���
		 *mysql url��jdbc:mysal://ip��ַ:3306/���ݿ���
		 *oracle url�� jidc:oracle:thin:@ip��ַ:1521:sid(orcl)
		 */
		String url ="jdbc:mysql://localhost:3306/test";
		//����jdbcʹ�õ���ʲô���ݿ� ��ͬ�����ṩһЩ��ͬ����
		String driverClass="com.mysql.jdbc.Driver";
		String userName="root";
		String password="123456";
		//��Ҫjvm���ظ���
		try {
			Class.forName(driverClass);
			//��¼�ɹ�
			Connection conn= DriverManager.getConnection(url,userName,password);
			System.out.println(conn);
			//Statement����ִ�о�̬��sql���
			Statement state=conn.createStatement();
			//ResultSet�α����������  next()
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