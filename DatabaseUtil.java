package com.xyz.ilp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@intvmoradb02:1521:ORAJAVADB";
	private static final String USERNAME = "PJ02DEV_TJA89";
	private static final String PASSWORD = "xyztvm";

	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

		} catch (ClassNotFoundException e) {

			System.out.println(e);
		} catch (SQLException e) {

			System.out.println(e);
		}
		return con;

	}

	public static void closeStatement(PreparedStatement pst) {

		if (pst != null) {

			try {
				pst.close();
			} catch (SQLException e) {

				System.out.println(e);
			}
		}

	}

	public static void closeConnection(Connection con) {

		if (con != null) {

			try {
				con.close();
			} catch (SQLException e) {

				System.out.println(e);
			}
		}

	}

	public static void closeResultset(ResultSet rs) {

		if (rs != null) {

			try {
				rs.close();
			} catch (SQLException e) {

				System.out.println(e);
			}
		}

	}

}
