package com.xyz.ilp.bean;

public class Login {
	private Employee employee= new Employee();
	private String password;
	private String timestamp;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public Login(String employeeId, String password, String timestamp) {
		this.employee.setEmployeeId(employeeId);
		this.password = password;
		this.timestamp = timestamp;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
