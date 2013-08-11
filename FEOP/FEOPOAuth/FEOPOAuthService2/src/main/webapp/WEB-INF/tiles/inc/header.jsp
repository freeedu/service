<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<header>
	<div id="header-left">
		<sec:authorize ifAllGranted="ROLE_USER">
			<c:url value="/home" var="targetUrl"/>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<c:url value="/index" var="targetUrl"/>
		</sec:authorize>
		<div id="logo-icon">
			<a href="${targetUrl }">
			<img src="<c:url value="/resources/images/logo.png"/>" width="224" height="98" />
			</a>
		</div>
		<div id="logo-desc">
			<span>Free Online Education Platform</span>
		</div>
	</div>

	<div id="header-right">
		<sec:authorize ifAllGranted="ROLE_USER">
			<a class="darkgray" href="<c:url value="/oauth/logout.do"/>">Logout</a>
		</sec:authorize>
		<sec:authorize ifNotGranted="ROLE_USER">
			<a class="gray" href="<c:url value="/signup/"/>">Signup</a>
		</sec:authorize>
	</div>
</header>