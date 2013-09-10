<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<c:url value="/"/>"><img src="<c:url value="/resources/images/logo.png"/>" height="20"></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="<c:url value="/blog/list"/>"><i class="glyphicon glyphicon-home"></i> Home</a></li>
				<c:if test="${not empty authentication }">
					<c:url value="/my/blog/list" var="myblogs" />
					<c:url value="/my/blog/create" var="createblog" />

					<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href='${myblogs }'>My Blog<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="${myblogs }">My Blogs</a></li>
							<li><a href="${createblog }">Write a Blog</a></li>
						</ul></li>
				</c:if>
				<li><a href="<c:url value="/contactus"/>">Contact</a></li>
				<li><a href="<c:url value="/aboutme"/>">About Me</a></li>
			</ul>

			<c:url value="/blog/search" var="search" />
			<form class="navbar-form navbar-left" action="${search }" method="get">
				<div class="form-group">
					<input type="text" class="form-control input-sm" name="q" placeholder="Search Site..." required>
				</div>
				<div class="form-group">
					<input type="submit" value="Go!" class="btn btn-default  btn-sm">
				</div>
			</form>
			<%-- <form action="${search }" method="get" class="navbar-form navbar-left" role="search">
				<div class="input-group">
					<input type="text" class="form-control" name="q" placeholder="Search Site..." required> <span class="input-group-btn">
						<button class="btn btn-default" type="submit">Go!</button>
					</span>
				</div>
			</form> --%>

			<div class="navbar-form navbar-right">
				<c:choose>
					<c:when test="${empty authentication }">
						<div class="btn-group">
							<a class="btn btn-default btn-sm" href='<c:url value="/user/login"/>'><i class="glyphicon glyphicon-log-in"></i> Login</a> <a
								type="button" class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/signup"><i class="glyphicon glyphicon-registration-mark"></i> Regist</a></li>
							</ul>
						</div>

					</c:when>
					<c:otherwise>
						<div class="btn-group">
							<a class="btn btn-default btn-sm"><c:out value="${authentication.userInfo.screenName }" /> </a> <a type="button"
								class="btn btn-default btn-sm dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span>
							</a>
							<ul class="dropdown-menu">
								<li><a href="/user/logout">Logout <i class="glyphicon glyphicon-log-out"></i></a></li>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</div>
</div>