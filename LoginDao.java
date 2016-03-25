package com.xyz.ilp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.xyz.ilp.bean.Employee;
import com.xyz.ilp.util.DatabaseUtil;

public class LoginDao {

	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public String getPassword(String username) {
		// TODO Auto-generated method stub
		String pass = null;
		con = DatabaseUtil.getConnection();
		if (con != null) {
			try {
				String sql = "select password from login_b where login_id=?";
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				if (rs != null) {
					if (rs.next()) {
						pass = rs.getString(1);
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			} finally {
				DatabaseUtil.closeResultset(rs);
				DatabaseUtil.closeStatement(ps);
				DatabaseUtil.closeConnection(con);
			}
		}

		return pass;
	}

	public Employee fetchDetails(String username) {
		// TODO Auto-generated method stub
		Employee emp = null;
		con = DatabaseUtil.getConnection();
		if (con != null) {
			try {
				String sql = "select EMPLOYEE_ID,EMPLOYEE_NAME,PREV_CREATE,PREV_READ,PREV_UPDATE,PREV_DELETE,EMPLOYEE_ROLE_Name from employee_b e inner join employee_role_b er on e.employee_role_id=er.employee_role_id  where employee_id=? and e.active=1";
				ps = con.prepareStatement(sql);
				ps.setString(1, username);
				rs = ps.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						emp = new Employee();
						emp.setEmployeeId(rs.getString(1));
						emp.setEmployeeName(rs.getString(2));
						emp.setCreate(rs.getInt(3));
						emp.setView(rs.getInt(4));
						emp.setUpdate(rs.getInt(5));
						emp.setDelete(rs.getInt(6));
						emp.getEmployeeRole().setEmployeeRoleName(
								rs.getString(7));
					}
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			} finally {
				DatabaseUtil.closeResultset(rs);
				DatabaseUtil.closeStatement(ps);
				DatabaseUtil.closeConnection(con);
			}
		}
		return emp;
	}

	public int createLogin(String employeeId, String encryptedPassword) {
		// TODO Auto-generated method stub
		int count = 0;
		con = DatabaseUtil.getConnection();
		if (con != null) {
			try {
				String sql = "insert into login_b(login_id,password) values(?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, employeeId);
				ps.setString(2, encryptedPassword);
				count = ps.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			} finally {
				DatabaseUtil.closeStatement(ps);
				DatabaseUtil.closeConnection(con);
			}
		}
		return count;
	}

}
