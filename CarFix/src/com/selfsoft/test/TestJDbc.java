package com.selfsoft.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDbc {

	public static void main(String[] args) {
		try {
			Connection con;
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://localhost:1433;database=carFix;";
			con=DriverManager.getConnection(url,"sa","sa");
			Statement st=con.createStatement();
			String query="select * from TM_USER";
			st.execute(query);
			ResultSet rs=st.getResultSet();
			while(rs.next())
			{
				String i=rs.getString(2);
				System.out.println(i);
				
			}
			rs.close();
			st.close();
			con.close();
		} catch (Exception e) {
			 System.out.println(e.getMessage());
		}
	}
}
