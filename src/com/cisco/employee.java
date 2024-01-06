package com.cisco;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class employee {
	@Id
	@GeneratedValue
	@Column(name = "eid")
	private int eid;
	@Column(name = "ename")
	private String ename;
	@Column(name = "esal")
	private double esal;
	@Column(name = "edesg")
	private String edesg;
	public employee() {
		
	}
	public employee(int eid, String ename, double esal, String edesg) {
		super();
		this.eid = eid;
		this.ename = ename;
		this.esal = esal;
		this.edesg = edesg;
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public double getEsal() {
		return esal;
	}
	public void setEsal(double esal) {
		this.esal = esal;
	}
	public String getEdesg() {
		return edesg;
	}
	public void setEdesg(String edesg) {
		this.edesg = edesg;
	}
	@Override
	public String toString() {
		return "eid=" + eid + ", ename=" + ename + ", esal=" + esal + ", edesg=" + edesg;
	}
	
	

}
