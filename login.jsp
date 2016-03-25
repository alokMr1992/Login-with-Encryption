<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%-- <%@include file="../jsp/header/employeeHeader.jsp"%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
if(session.getAttribute("empId")!=null){

	request.getRequestDispatcher("/LoginController?action=myDirection").forward(request, response);
	return;	
}
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Room and Extension Management System</title>


<!-- Bootstrap Core CSS -->
<link href="<%=request.getContextPath()%>/css/bootstrap.min.css"
	rel="stylesheet">

<!-- MetisMenu CSS -->
<link href="<%=request.getContextPath()%>/css/metisMenu.min.css"
	rel="stylesheet">

<!-- Timeline CSS -->
<link href="<%=request.getContextPath()%>/css/timeline.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link href="<%=request.getContextPath()%>/css/sb-admin-2.css"
	rel="stylesheet">

<!-- Morris Charts CSS -->
<link href="<%=request.getContextPath()%>/css/morris.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link href="<%=request.getContextPath()%>/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link href="<%=request.getContextPath()%>/css/login.css"
	rel="stylesheet">
</head>
<body>
	<div class="container" style="margin-top: 40px">
		<div class="row">
			<div class="col-sm-6 col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong> Sign in to continue</strong>
					</div>
					<div class="panel-body">
						<form role="form" name="loginForm"
							action="<%=request.getContextPath()%>/LoginController?action=login"
							method="POST" onsubmit="return validateLogin()">

							<fieldset>
								<div class="row">
									<div class="center-block">
										<img class="profile-img"
											src="<%=request.getContextPath()%>/images/tata_logo1.png?siz=120"	alt="">
									</div>
								</div>
								<div class="form-group has-error">
									<%
										String msg = (String) request.getAttribute("errmsg");
										if (msg != null) {
									%>
									<div class="alert alert-danger alert-dismissable">
										<%=msg%>
									</div>
									<%
										}
									%>
								</div>
								<div class="row">
									<div class="col-sm-12 col-md-10  col-md-offset-1 ">
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-user"></i><label id="username-error"
													class="control-label" for="inputError"></label>
												</span> <input class="form-control" placeholder="Username"
													name="username" type="text" autofocus>
											</div>
										</div>
										<div class="form-group">
											<div class="input-group">
												<span class="input-group-addon"> <i
													class="fa fa-lock"></i><label id="password-error"
													class="control-label" for="inputError"></label>
												</span> <input class="form-control" placeholder="Password"
													name="password" type="password" value="">
											</div>
										</div>
										<div class="form-group">
											<input type="submit" class="btn btn-lg btn-primary btn-block"
												value="Sign in">
										</div>
									</div>
								</div>
							</fieldset>
						</form>
					</div>
					<div class="panel-footer ">
						New User ? <a
							href="<%=request.getContextPath()%>/LoginController?action=fetchOfficeDetails"
							onClick=""> Sign Up Here </a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- jQuery -->
	<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>


	<!-- Custom Theme JavaScript -->
	<script src="<%=request.getContextPath()%>/js/sb-admin-2.js"></script>

	<!-- Metis Menu Plugin JavaScript -->
	<script src="<%=request.getContextPath()%>/js/metisMenu.min.js"></script>

	<!-- Login Validation JavaScript -->
	<script src="<%=request.getContextPath()%>/js/custom/login.js"></script>
</body>
</html>