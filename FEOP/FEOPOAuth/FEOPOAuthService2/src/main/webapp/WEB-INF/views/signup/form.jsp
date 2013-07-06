<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<c:set var="pageTitle" value="Signup" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title><c:out value="${pageTitle }" /></title>
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
		</div>

		<div id="content">
			<div id="content-header">
				<h2>Welcome to Signup OAuth Platform</h2>
			</div>
			<div id="content-body">
				<div id="login-form">
					<div id="login-form-header">User Information</div>
					<div id="login-form-content">
						<c:url value="/signup/new" var="newAction" />
						<form action="${newAction }" method="post" id="loginForm"
							name="signupForm">
							<s:hasBindErrors htmlEscape="true" name="signupForm">
								<c:if test="${errors.errorCount gt 0}">
									<h4>Errors:</h4>
									<font color="red"> <c:forEach
											items="${errors.allErrors}" var="error">
											<s:message code="${error.code}"
												arguments="${error.arguments}"
												text="${error.defaultMessage}" />
											<br />
										</c:forEach>
									</font>
								</c:if>
							</s:hasBindErrors>
							<div class="line">
								<label for="firstName" class="left">First Name</label> <input
									name="firstName" id="firstName" class="right" />
							</div>
							<div class="line">
								<label for="lastName" class="left">Last Name</label> <input
									name="lastName" id="lastName" class="right" />
							</div>
							<div class="line">
								<label for="email" class="left">Email(un)</label> <input
									name="email" id="email" class="right" />
							</div>
							<div class="line">
								<label for="password" class="left">Password</label> <input
									type="password" name="password" id="password" class="right" />
							</div>
							<div class="line">
								<input class="left" type="submit" value="Create Account" />
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">Copyright &copy 2013 oauth.com. All rights
		reserved.</div>
</body>
</html>