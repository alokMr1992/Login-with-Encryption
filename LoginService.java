package com.xyz.ilp.service;

import com.xyz.ilp.bean.Employee;
import com.xyz.ilp.dao.LoginDao;

import dummy.EncryptionDecryption;

public class LoginService {
	LoginDao dao = new LoginDao();

	public String getPassword(String username) throws Exception {
		// TODO Auto-generated method stub
		String pass = dao.getPassword(username);
		String decryptedPassword = EncryptionDecryption.decrypt(pass);
		return decryptedPassword;
	}

	public Employee fetchDetails(String username) {
		// TODO Auto-generated method stub
		return dao.fetchDetails(username);
	}

	public int createLogin(String employeeId) throws Exception {
		// TODO Auto-generated method stub
		String password = "ChangePwd@" + employeeId;
		String encryptedPassword = EncryptionDecryption.encrypt(password);
		return dao.createLogin(employeeId, encryptedPassword);
	}

}
