package jdbc;

import java.util.Scanner;

public class TestWork1 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		login();
	}
	public static void login(){
		System.out.println("Microsoft Windows [�汾 6.1.7601]");
		System.out.println("��Ȩ���� (c) 2009 Microsoft Corporation����������Ȩ����");
		System.out.print("C:\\Users\\Administrator>");
		String str=sc.next();
		//MySql��¼������ -u�û��� -p���� -P�˿�  -hip��ַ  ���ݿ���
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
