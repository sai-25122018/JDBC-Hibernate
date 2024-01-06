package com.cisco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

public class empdao {
	public void savedata(employee emp) {
		Configuration Con = new AnnotationConfiguration().configure("hibernate.cfg.xml");
		SessionFactory sf =  Con.buildSessionFactory();
		Session s =  sf.openSession();
		Transaction T =  s.beginTransaction();
		s.save(emp);
		T.commit();
		s.close();
		
	}
	public void saveDataWithJDBC() {
        String url = "jdbc:mysql://localhost:3306/jdbchibernate";
        String username = "root";
        String password = "root";
        String sql = "INSERT INTO employee (eid, ename, esal, edesg) VALUES (2, 'Rabba Navaneeth', 879.90, 'Angular Developer')";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            int rowsInserted = statement.executeUpdate(sql);
            System.out.println("No of Rows Inserted in Database using JDBC ---> " + rowsInserted);
        } catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		empdao ed = new empdao();
		ed.saveDataWithJDBC();
	}
}
