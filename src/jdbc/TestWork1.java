package jdbc;

import java.util.Scanner;

public class TestWork1 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		login();
	}
	public static void login(){
		System.out.println("Microsoft Windows [版本 6.1.7601]");
		System.out.println("版权所有 (c) 2009 Microsoft Corporation。保留所有权利。");
		System.out.print("C:\\Users\\Administrator>");
		String str=sc.next();
		//MySql登录的命令 -u用户名 -p密码 -P端口  -hip地址  数据库名
		String user="";
		String password="";
		String port="";
		String ip="";
		String dateBase="";
		String[] arry=str.split(",");
		for(int i =0;i<arry.length;i++){
			if(arry[0].equals("mysql")){
				if(arry[i].startsWith("-u")){
					user=arry[i].substring(2);
				}
				if(arry[i].startsWith("-p")){
					password=arry[i].substring(2);
				}
				if(arry[i].startsWith("-P")){
					port=arry[i].substring(2);
				}
				if(arry[i].startsWith("-h")){
					ip=arry[i].substring(2);
				}
				if(arry[i].startsWith("-p")){
					password=arry[i].substring(2);
				}
			}
		}
	}
}
