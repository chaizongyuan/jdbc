package jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;


public class DbUtils {
	static Properties p=new Properties();
	static{
		InputStream is=DbUtils.class.getResourceAsStream("/jdbc.properties");
		try{
			p.load(is);
		}catch(IOException  e){
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws Exception{
		String url=p.getProperty("url");
		String driverClass=p.getProperty("driverClass");
		String username=p.getProperty("username");
		String password=p.getProperty("password");
		Class.forName(driverClass);
		//µÇÂ¼³É¹¦
		Connection conn= DriverManager.getConnection(url,username,password);
		return conn;
	}
}
