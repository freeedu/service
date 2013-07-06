<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="authz"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<c:url var="defaultCss" value="/resources/css/default.css" />

<title>Open Auth Platform - My Applications</title>
<link rel="stylesheet" href="${defaultCss }" />
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
					<h2>My Applications</h2>
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
				<c:forEach items="${applications}" var="app">
					<div style="margin: 5px;">
						<div class="widget">
							<div class="widget-header">
								<c:out value="${app.clientId }" />
							</div>
							<div class="widget-body">
								<div class="line">
									<label class="left">Application Id:</label><label class="right"><c:out
											value="${app.clientId }" /></label>
								</div>
								<div class="line">
									<label class="left">Client Secret:</label><label class="right"><c:out
											value="${app.clientSecret }" /></label>
								</div>
								<div class="line">
									<label class="left">Authorized Grant Types:</label><label
										class="right"><c:out
											value="${app.authorizedGrantTypes }" /></label>
								</div>
								<div class="line">
									<label class="left">Authorities:</label><label class="right"><c:out
											value="${app.authorities }" /></label>
								</div>
								<div class="line">
									<label class="left">Resource Ids:</label><label class="right"><c:out
											value="${app.resourceIds }" /></label>
								</div>
								<div class="line">
									<label class="left">Scope:</label><label class="right"><c:out
											value="${app.clientId }" /></label>
								</div>
								<div class="line">
									<label class="left">Redirect Uri:</label><label class="right"><c:out
											value="${app.scope }" /></label>
								</div>
								<div class="line">
									<label class="left">Additional Info:</label><label
										class="right"><c:out value="${app.additionalInfo }" /></label>
								</div>
								<div class="line">
									<label class="left"><a
										href='<c:url value="/client/delete/${app.clientId }"/>'>Delete</a></label>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>
	<div id="footer">Copyright &copy 2013 oauth.com. All rights
		reserved.</div>
</body>
</html>