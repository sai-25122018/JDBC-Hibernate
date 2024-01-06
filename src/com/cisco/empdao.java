package com.cisco;

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
	public static void main(String[] args) {
		empdao ed = new empdao();
		ed.savedata(new employee(101, "Janaki", 987.90, "Java-Developer"));
	}
}
