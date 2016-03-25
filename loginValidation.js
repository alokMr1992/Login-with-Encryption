function validateLogin() {
	var uname = validateUsername();
	var pass = validatePassword();
	var bool;

	if (uname && pass) {
		bool = true;
	} else {
		bool = false;
	}
	return bool;
}

function validateUsername() {
	var uname = document.forms["loginForm"]["username"].value.trim();
	var ename = document.getElementById("username-error");
	var enameClass = ename.parentNode.parentNode.className;
	var bool;

	if (uname == "" || uname == null) {
		ename.innerHTML = "* Enter username";
		ename.parentNode.parentNode.className = enameClass + " has-error";
		bool = false;
	} else {
		ename.innerHTML = "";
		ename.parentNode.parentNode.classList.remove("has-error");
		bool = true;
	}
	return bool;
}

function validatePassword() {
	var pass = document.forms["loginForm"]["password"].value.trim();
	var ename = document.getElementById("password-error");
	var enameClass = ename.parentNode.parentNode.className;
	var bool;
	if (pass == "" || pass == null) {
		ename.innerHTML = "* Enter password";
		ename.parentNode.parentNode.className = enameClass + " has-error";
		bool = false;
	} else{
		ename.innerHTML = "";
		ename.parentNode.parentNode.classList.remove("has-error");
		bool = true;
	}
	return bool;
}