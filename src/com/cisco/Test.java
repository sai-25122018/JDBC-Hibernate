package com.cisco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test {
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apple7", "root", "root");
		return Con;
	}
	public static int insertPerson(Connection Con,String pname,int page,String pqual) throws ClassNotFoundException, SQLException {
		Connection C = Test.getCon();
		String insertPersonSQL = "Insert into apple7.Person (pname,page,pqual) values(?,?,?)";
		PreparedStatement ps = C.prepareStatement(insertPersonSQL,PreparedStatement.RETURN_GENERATED_KEYS);

		ps.setString(1, pname);
		ps.setInt(2, page);
		ps.setString(3, pqual);
		int RowsAffected = ps.executeUpdate();
		if(RowsAffected >0) {
			java.sql.ResultSet GeneratedKeys = ps.getGeneratedKeys();
			if(GeneratedKeys.next()) {
				return GeneratedKeys.getInt(1);
			}else {
				throw new SQLException("Failed to Retrive the Person Id");
			}
				
			}else {
				throw new SQLException("Failed to Insert the Person Details");
		}
		
		
		
	}
	public static void inserPassport(Connection Con,String pnum,String pexpdate ,int fkpid) throws ClassNotFoundException, SQLException {
		Connection C = Test.getCon();
		String insertPassportSQL = "insert into apple7.Passport (pnum,pexpdate,fkpid) values (?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertPassportSQL);
		ps.setString(1, pnum);
		ps.setString(2, pexpdate);
		ps.setInt(3, fkpid);
		
		int h = ps.executeUpdate();
		
		if(h <= 0) {
			throw new SQLException("Failed to insert the Passport Details");
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		Connection Con = Test.getCon();
		int PersonId = insertPerson(Con, "Siva Rao", 31, "Mtech");
		inserPassport(Con, "1234abcd", "2090", PersonId);
		System.out.println("Data inserted Succesfully");
		
	}

}
