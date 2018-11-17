package com.sample.app.model;

public class Employee {
	
	private String empID;
	private String empName;
	private int    phoneNo;
	private String address;
	
	public Employee() {
		
	}
	
	public Employee(String empID, String empName, int phoneNo, String address) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.phoneNo = phoneNo;
		this.address = address;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public int getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Employee [empID=" + empID + ", empName=" + empName + ", phoneNo=" + phoneNo + ", address=" + address
				+ "]";
	}

}
