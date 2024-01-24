package com.cisco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test2 {
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apple7", "root", "root");
		return Con;
	}
	public static int insertPerson(Connection Con, String pname,int page, String pqual) throws ClassNotFoundException, SQLException {
		Connection C = Test2.getCon();
		String insertPersonSQL = "Insert into apple7.Person(pname,page,pqual) values(?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertPersonSQL,PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, pname);
		ps.setInt(2, page);
		ps.setString(3, pqual);
		int RowsAffected = ps.executeUpdate();
		if(RowsAffected >= 0) {
			java.sql.ResultSet GK = ps.getGeneratedKeys();
			if(GK.next()) {
				return GK.getInt(1);
			}else {
				throw new SQLException("Failed to Retrive the Person Id");
			}
		}else {
			throw new SQLException("Failed to Insert the Person Details");
		}
		
	}
	
	public static void insertPassport(Connection Con,String pnum,String pexpdate,int fkpid) throws ClassNotFoundException, SQLException {
		Connection C = Test2.getCon();
		String insertPassportSQL = "Insert into apple7.Passport(pnum,pexpdate,fkpid) values(?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertPassportSQL);
		ps.setString(1, pnum);
		ps.setString(2, pexpdate);
		ps.setInt(3, fkpid);
		
		int RowsAffected = ps.executeUpdate();
		if(RowsAffected <= 0) {
			throw new SQLException("Failed To Insert the Passport Details");
		}
	}
	public static void insertMobile(Connection Con,String msim,String mloc,int Personid) throws ClassNotFoundException, SQLException {
		Connection C = Test2.getCon();
		String insertMobileSQL = "Insert into apple7.Mobile(msim,mloc,Personid) values(?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertMobileSQL);
		ps.setString(1, msim);
		ps.setString(2, mloc);
		ps.setInt(3, Personid);
		
		int RowsAffected = ps.executeUpdate();
		if(RowsAffected <= 0) {
			throw new SQLException("Failed To Insert the Mobile Details");
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection Con = Test2.getCon();
		int Personid = Test2.insertPerson(Con, "Raja", 30, "MBA");
		Test2.insertPassport(Con, "8765abcd", "4999", Personid);
		Test2.insertMobile(Con, "Jio", "Hyderabad", Personid);
		Test2.insertMobile(Con, "Airtel", "Mumbai", Personid);
		System.out.println("Data Inserted Successfully");
		

	}

}
