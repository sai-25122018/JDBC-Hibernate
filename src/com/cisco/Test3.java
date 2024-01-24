package com.cisco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Test3 {
	public static Connection getCon() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/apple8", "root", "root");
		return Con;
		
	}
	public static int insertPerson(Connection Con,String pname,int page,String pqual) throws ClassNotFoundException, SQLException {
		Connection C = Test3.getCon();
		String insertPersonSQL = "insert into apple8.Person (pname,page,pqual) values(?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertPersonSQL, PreparedStatement.RETURN_GENERATED_KEYS);
		ps.setString(1, pname);
		ps.setInt(2, page);
		ps.setString(3, pqual);
		int RowsAffected =  ps.executeUpdate();
		if (RowsAffected >= 0) {
			java.sql.ResultSet GK = ps.getGeneratedKeys();
			if(GK.next()) {
				return GK.getInt(1);
			}else {
				throw new SQLException("Unable to retrive the PersonID");
			}
		}else {
			throw new SQLException("Failed to Insert the Person Details");
		}
		
		
	}
	public static void insertPassport(Connection Con,String pnum,String pexpdate,int fkpid) throws SQLException, ClassNotFoundException {
		Connection C = Test3.getCon();
		String insertPassportSQL = "insert into apple8.Passport (pnum,pexpdate,fkpid) values(?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertPassportSQL);
		ps.setString(1, pnum);
		ps.setString(2, pexpdate);
		ps.setInt(3, fkpid);
		int h = ps.executeUpdate();
		if(h<= 0) {
			throw new SQLException("Failed to insert the Passport Details");
		}
	}
	public static void insertMobile(Connection Con,String mnum,String msim,String mloc,int Personid) throws ClassNotFoundException, SQLException {
		Connection C = Test3.getCon();
		String insertMobileSQL = "insert into apple8.Mobile (mnum,msim,mloc,Personid) values(?,?,?,?)";
		PreparedStatement ps =  C.prepareStatement(insertMobileSQL);
		ps.setString(1, mnum);
		ps.setString(2, msim);
		ps.setString(3, mloc);
		ps.setInt(4, Personid);
		int h = ps.executeUpdate();
		if(h<= 0) {
			throw new SQLException("Failed to insert the Mobile Details");
		}
	}
	

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection Con = Test3.getCon();
		int Personid = Test3.insertPerson(Con, "Ramana", 50, "CA");
		Test3.insertPassport(Con, "1234abcd", "2029", Personid);
		Test3.insertMobile(Con, "8143456828", "Jio", "Delhi", Personid);
		Test3.insertMobile(Con, "7989089904", "Airtel", "Ayodya", Personid);
		System.out.println("Data inserted Clerly");

	}

}

