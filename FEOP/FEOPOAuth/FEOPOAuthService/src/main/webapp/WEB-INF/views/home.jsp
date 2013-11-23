<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css/default.css"/>" />
</head>
<body>
	<div id="container">
		<div id="header">
			<div id="header-left">
				<authz:authorize ifAllGranted="ROLE_USER">
					<a href="<c:url value="/home"/>">Open Auth Platform</a>
				</authz:authorize>
				<authz:authorize ifNotGranted="ROLE_USER">
					<a href="<c:url value="/"/>">Open Auth Platform</a>
				</authz:authorize>
			</div>
			<div id="header-right">
				<authz:authorize ifAllGranted="ROLE_USER">
					<div style="text-align: center">

						<a href="<c:url value="/oauth/logout.do"/>">Logout</a>
					</div>
				</authz:authorize>
				<authz:authorize ifNotGranted="ROLE_USER">
					<a href="<c:url value="/signup/"/>">Signup</a>
				</authz:authorize>
			</div>
		</div>

		<div id="content">
			<div id="content-header">
				<div id="content-header-left">
					<h2>Welcome to OAuth Platform</h2>
				</div>
				<div id="content-header-right">
					<div id="menu-bar">
						<ul id="main-menu">
							<li><a href="<c:url value="/home"/>">Home</a></li>
							<li><a href="<c:url value="/profile/"/>">Profile</a>
								<ul class="sub-menu">
									<li><a href="<c:url value="/profile/changepwd"/>">Change
											Password</a></li>
									<li><a href="<c:url value="/profile/"/>">Profile</a></li>
									<li><a href="<c:url value="/profile/update"/>">Update
											Profile</a></li>
								</ul></li>
							<li><a href="<c:url value="/client/list"/>">Applications</a>
								<ul class="sub-menu">
									<li><a href="<c:url value="/client/list"/>">My
											Application</a></li>
									<li><a href="<c:url value="/client/"/>">New
											Application</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
			<div id="content-body">
				<h1>You have been login!</h1>
				<hr>
				<h3>Welcome to Open Oauth Platform</h3>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy 2013 oauth.com. All rights
		reserved.</div>
</body>
</html>