package com.xyz.ilp.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xyz.ilp.bean.Branch;
import com.xyz.ilp.bean.Country;
import com.xyz.ilp.bean.Department;
import com.xyz.ilp.bean.Employee;
import com.xyz.ilp.bean.Location;
import com.xyz.ilp.service.EmployeeService;
import com.xyz.ilp.service.LoginService;
import com.xyz.ilp.service.PreFetchService;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService service = new LoginService();
	private EmployeeService empService = new EmployeeService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doLogin(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doLogin(request, response);
	}

	protected void doLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String action = request.getParameter("action");
		//System.out.println(action);
		
		
		
		if (action == "" || action == null) {
			response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
			//System.out.println("action null");
		} else if ("login".equals(action)) {
			//System.out.println("action not null - login");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			//System.out.println(username + password);
			if (username != null && password != null) {
				try {
					String pass = service.getPassword(username);
					//System.out.println("My Password:"+pass);
					
					
					// System.out.println(pass.equals(password));
					if (pass != null && pass.length()>0) {
						if (pass.equals(password) && password.length()>0) {

							Employee emp = service.fetchDetails(username);
							HttpSession session = request.getSession();
							session.setAttribute("empId", emp);
							//System.out.println(emp.getEmployeeRole()
									.getEmployeeRoleName());

							PreFetchService pfs = new PreFetchService();
							Employee employee = pfs.getEmployeeDetails(emp
									.getEmployeeId());
							String branchId = employee.getLocation()
									.getBranch().getBranchId();
							ArrayList<Employee> eList = pfs
									.fetchEmployeedetails(branchId);

							request.setAttribute("eList", eList);

							if ("Super Admin".equalsIgnoreCase(emp
									.getEmployeeRole().getEmployeeRoleName())) {

								RequestDispatcher rd = request
										.getRequestDispatcher("/jsp/dashboard/superAdminDashboard.jsp");
								rd.forward(request, response);

							} else if ("Admin".equalsIgnoreCase(emp
									.getEmployeeRole().getEmployeeRoleName())) {

								//System.out.println("Admin");
								RequestDispatcher rd = request
										.getRequestDispatcher("jsp/dashboard/adminDashboard.jsp");
								rd.forward(request, response);

							} else if ("Employee".equalsIgnoreCase(emp
									.getEmployeeRole().getEmployeeRoleName())) {

								RequestDispatcher rd = request
										.getRequestDispatcher("/jsp/dashboard/employeeDashboard.jsp");
								rd.forward(request, response);
							}

						}else{
							request.setAttribute("errmsg",
									"The username or password is incorrect");
							RequestDispatcher rd = request
									.getRequestDispatcher("/jsp/login.jsp");
							rd.forward(request, response);
						}
					} else {
						//System.out.println("Wrong Password");
						request.setAttribute("errmsg",
								"The username or password is incorrect");
						RequestDispatcher rd = request
								.getRequestDispatcher("/jsp/login.jsp");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			} else {
				request.setAttribute("errmsg",
						"Enter your credential.");
				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/login.jsp");
				rd.forward(request, response);
			}

		} else if ("fetchOfficeDetails".equals(action)) {

			//System.out.println("action not null - fetch");
			HttpSession session = request.getSession(false);
			String refer = request.getHeader("referer");
			if(refer ==null){
				response.sendRedirect(request.getContextPath() + "/jsp/opps.jsp");
			}
			
			
			if(session==null){
				response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
			}
			
			ArrayList<Country> c = empService.fetchCountry();
			ArrayList<Location> l = empService.fetchLocation();
			ArrayList<Branch> b = empService.fetBranch();
			ArrayList<Department> d = empService.fetchDepartment();
			if (c != null && l != null && b != null) {
				request.setAttribute("country", c);
				request.setAttribute("branch", b);
				request.setAttribute("location", l);
				request.setAttribute("department", d);

				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/employee/registration.jsp");
				rd.forward(request, response);
			} else {
				request.setAttribute("errmsg", "Some internal error occurred");
				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/employee/employeeError.jsp");
				rd.forward(request, response);

			}
		} else if ("registerEmployee".equals(action)) {
			
			//System.out.println("action not null - reg");
			String employeeId = request.getParameter("employee-id");
			String employeeName = request.getParameter("employee-name");
			String mobileNumber = request.getParameter("employee-mno");
			String extentionNumber = request.getParameter("employee-eno");
			String emailId = request.getParameter("employee-email");
			String cubicleNumber = request.getParameter("employee-cno");
			String country = request.getParameter("employee-country");
			String branch = request.getParameter("employee-branch");
			String locationId = request.getParameter("employee-location");
			String departmentId = request.getParameter("employee-department");

			Employee employee = new Employee(employeeId, employeeName,
					mobileNumber, extentionNumber, emailId, cubicleNumber,
					locationId, departmentId, country, branch);

			int result = empService.addEmployee(employee);
			int count = 0;
			if (result > 0) {
				try {
					count = service.createLogin(employeeId);
					if (count > 0) {
						request.setAttribute("succmsg",
								"You have been successfully registered");
						RequestDispatcher rd = request
								.getRequestDispatcher("/jsp/employee/registerSuccess.jsp");
						rd.forward(request, response);
					} else {
						request.setAttribute("errmsg",
								"You have been already registered");

						RequestDispatcher rd = request
								.getRequestDispatcher("/jsp/employee/registration.jsp");
						rd.forward(request, response);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			}
		} else if ("logout".equals(action)) {
			//System.out.println("action not null - log");
			//System.out.println("logout");
			HttpSession session = request.getSession(false);
			if(session==null){
				response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
			}
			String refer = request.getHeader("referer");
			if(refer ==null){
				response.sendRedirect(request.getContextPath() + "/jsp/opps.jsp");
			}
			if (session != null) {
				session.removeAttribute("empId");
				session.invalidate();
			}
			//System.out.println("logout");
			response.sendRedirect(request.getContextPath() + "/jsp/login.jsp");
		}else if("myDirection".equals(action)){
			Employee emp = (Employee) request.getSession().getAttribute("empId");
			PreFetchService pfs = new PreFetchService();
			Employee employee = pfs.getEmployeeDetails(emp
					.getEmployeeId());
			String branchId = employee.getLocation()
					.getBranch().getBranchId();
			ArrayList<Employee> eList = pfs
					.fetchEmployeedetails(branchId);

			request.setAttribute("eList", eList);

			if ("Super Admin".equalsIgnoreCase(emp
					.getEmployeeRole().getEmployeeRoleName())) {

				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/dashboard/superAdminDashboard.jsp");
				rd.forward(request, response);

			} else if ("Admin".equalsIgnoreCase(emp
					.getEmployeeRole().getEmployeeRoleName())) {

				//System.out.println("Admin");
				RequestDispatcher rd = request
						.getRequestDispatcher("jsp/dashboard/adminDashboard.jsp");
				rd.forward(request, response);

			} else if ("Employee".equalsIgnoreCase(emp
					.getEmployeeRole().getEmployeeRoleName())) {

				RequestDispatcher rd = request
						.getRequestDispatcher("/jsp/dashboard/employeeDashboard.jsp");
				rd.forward(request, response);
			}
			
			
			
		}
		else {
			//System.out.println("action not null - emperr");
			request.setAttribute("errmsg", "Some internal error occurred");
			RequestDispatcher rd = request
					.getRequestDispatcher("/jsp/employee/employeeError.jsp");
			rd.forward(request, response);
		}
	}

}